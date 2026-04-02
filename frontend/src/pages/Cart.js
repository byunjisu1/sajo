import React, { useState, useEffect, useContext } from 'react';
import { AuthContext } from '../App';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './Cart.css';

const Cart = () => {
	const navigate = useNavigate();
    const [showList, setShowList] = useState([]);
	const [checkedItems, setCheckedItems] = useState([]);
	const { memberNo } = useContext(AuthContext);
	
	//장바구니 내역 리스트 조회 기능
	const getShowList = () => {
		axios.get(`/sajo/cart/${memberNo}`)
		.then(res => {
			console.log(res.data);
			setShowList(res.data);
			const allIdx = res.data.map(item => item.itemIdx);
			setCheckedItems(allIdx);
		})
		.catch(err => {
			console.error(err);
		});
	};
	
	// 개별 체크박스 기능
	const handleSingleCheck = (id) => {
	    if (checkedItems.includes(id)) {
	        // 이미 체크되어 있다면 제거 (체크 해제)
	        setCheckedItems(checkedItems.filter(el => el !== id));
	    } else {
	        // 체크되어 있지 않다면 추가 (체크 선택)
	        setCheckedItems([...checkedItems, id]);
	    }
	};
	
	//전체 선택 체크박스 기능
	const handleAllCheck = (checked) => {
	    if (checked) {
	        // 전체 선택 시 모든 itemIdx를 넣음
	        const allIdx = showList.map(item => item.itemIdx);
	        setCheckedItems(allIdx);
	    } else {
	        // 전체 해제 시 빈 배열로 만듦
	        setCheckedItems([]);
	    }
	};
	
	// 체크된 상품들만 필터링
	const selectedProducts = showList.filter(item => checkedItems.includes(item.itemIdx));

	// 상품 소계
	const totalAmount = selectedProducts.reduce((sum, item) => sum + (item.itemPrice || 0), 0);
	
	//전체 삭제 기능
	const handleDeleteSelected = () => {
	    if (checkedItems.length === 0) {
	        alert("삭제할 상품을 선택해주세요.");
	        return;
	    }

	    if (window.confirm(`선택한 ${checkedItems.length}개의 상품을 장바구니에서 삭제하시겠습니까?`)) {
	        const deleteRequests = checkedItems.map(itemIdx => 
	            axios.delete(`/sajo/cart/${memberNo}/${itemIdx}`)
	        );

	        Promise.all(deleteRequests)
	            .then(() => {
	                alert("선택한 상품이 삭제되었습니다.");
	                
	                // 화면(State)에서 삭제된 아이템 제외하고 업데이트
	                const updatedList = showList.filter(item => !checkedItems.includes(item.itemIdx));
	                setShowList(updatedList);
	                
	                // 체크박스 상태 초기화
	                setCheckedItems([]);
	            })
	            .catch(err => {
	                console.error("삭제 중 오류 발생:", err);
	                alert("삭제에 실패했습니다. 다시 시도해주세요.");
	            });
	    }
	};
	
	// 개별 삭제 기능
	const handleDeleteSingle = (itemIdx) => {
	    if (window.confirm("이 상품을 삭제하시겠습니까?")) {
	        axios.delete(`/sajo/cart/${memberNo}/${itemIdx}`)
	            .then(() => {
	                setShowList(showList.filter(item => item.itemIdx !== itemIdx));
	                setCheckedItems(checkedItems.filter(id => id !== itemIdx));
	            })
	            .catch(err => console.error(err));
	    }
	};
	
	// 상품클릭시 상품상세페이지 이동
	const clickItem = (itemIdx)=> {
		navigate(`/itemDetail/${itemIdx}`);
	};
	
	const handlePayment = async() => {
	    if (selectedProducts.length === 0) {
	        alert("결제할 상품을 선택해주세요.");
	        return;
	    }
		
		try {
			alert("상품에 대한 예상 무게와 세율 계산 중 .. 기다려주세요.");
	        const response = await axios.post(`/sajo/item/customsInfoList`, selectedProducts);
	        const { estimatedAverageTaxRate, totalWeightGrams } = response.data;
	        navigate(`/payment`, { 
	            state: { 
	                totalAmount,          
	                selectedProducts,      
	                weight: totalWeightGrams,        
	                trrt: estimatedAverageTaxRate,
					type: "gpt"       
	            } 
	        });
	    } catch (err) {
	        console.error("통합 관세 산출 에러:", err);
	        alert("관세 정보 계산 중 오류가 발생했습니다.");
	    }
	};
		
	useEffect(() => {
		if (memberNo) {
	        getShowList();
	    } else {
	        navigate('/login');
	    }
		}, [memberNo]
	);
	
    return (
        <div className="cart-page">
            {!showList.length > 0 ? (
                <div className="cart-empty-content">
                    <h2 className="cart-empty-title">카트에 담긴 상품이 없습니다.</h2>
                    <p className="cart-empty-desc">원하시는 상품을 찾아보세요.</p>
                    <div className="cart-empty-button-group">
                        <button className="cart-btn-go-shopping" onClick={() => navigate(`/rakuten`)}>
                            <span className="cart-btn-icon">🛍️</span>
                            상품 보러 가기
                        </button>
                    </div>
                </div>
            ) : (
                <div className="cart-main-container">
                    <div className="cart-left-section">
                        <div className="cart-header-bar">
                            <div className="cart-custom-checkbox-wrapper">
							<input 
							    type="checkbox" 
							    id="cart-all-select" 
							    onChange={(e) => handleAllCheck(e.target.checked)}
							    checked={checkedItems.length === showList.length && showList.length > 0} 
							/>
							<label htmlFor="cart-all-select">
							    전체 선택 ({checkedItems.length}/{showList.length})
							</label>
                            </div>
						    <button 
						        className="cart-btn-delete-selected" 
						        onClick={handleDeleteSelected}
						        disabled={checkedItems.length === 0} // 선택된 게 없으면 비활성화
						    >삭제
							</button>
                        </div>
						{
							showList.map(({itemIdx,itemName, itemPrice, itemImg}) => (
		                        <div key={itemIdx} className="cart-item-list">
		                            <div className="cart-product-item">
										<input 
											type="checkbox" 
							                className="cart-item-check"
							                checked={checkedItems.includes(itemIdx)}
							                onChange={() => handleSingleCheck(itemIdx)}
									     />
		                                <div className="cart-item-thumb">
		                                    <img src={itemImg} alt="상품" />
		                                </div>
										<div className="cart-item-details" onClick={() => clickItem(itemIdx)} style={{ cursor: 'pointer' }}>
										    <p className="cart-item-title">{itemName}</p>
										    <p className="cart-item-price">₩{itemPrice?.toLocaleString()}</p>
										</div>
		                                <button 
											className="cart-btn-item-close" 
										    onClick={() => handleDeleteSingle(itemIdx)}
										>×
										</button>
		                            </div>
		                        </div>
							))
						}
                    </div>

					<div className="cart-right-section">
					    <div className="cart-order-summary-card">
					        <h3 className="cart-summary-title">주문 개요</h3>
					       
					        <div className="cart-total-amount-row">
					            <span>전체 금액</span>
					            <span className="cart-total-price-value">₩{totalAmount.toLocaleString()}</span>
					        </div>
					    </div>
					    <button className="cart-btn-submit-pay" onClick={handlePayment} disabled={selectedProducts.length === 0} >
					        <span className="cart-btn-icon">💳</span>
					        <span className="cart-btn-text">결제하기</span>
					    </button>
					</div>
                </div>
            )}
        </div>
    );
};

export default Cart;