import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './Cart.css';

const Cart = () => {
	const navigate = useNavigate();
    const [showList, setShowList] = useState([]);
	const [checkedItems, setCheckedItems] = useState([]);
	const memberNo = sessionStorage.getItem("member_no");
	
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
	
	// 1. 체크된 상품들만 필터링
	const selectedProducts = showList.filter(item => checkedItems.includes(item.itemIdx));

	// 2. 상품 소계 계산 (체크된 상품들의 가격 합계)
	const subTotal = selectedProducts.reduce((sum, item) => sum + (item.itemPrice || 0), 0);

	// 3. 부수 비용 설정 (예시: 상품이 있을 때만 배송비 등이 붙도록 설정 가능)
	const localShipping = selectedProducts.length > 0 ? 6025 : 0;
	const internationalShipping = selectedProducts.length > 0 ? 6025 : 0;
	const tax = selectedProducts.length > 0 ? 6025 : 0;

	// 4. 최종 합계
	const totalAmount = subTotal + localShipping + internationalShipping + tax;
	
	//전체 삭제 기능
	const handleDeleteSelected = () => {
	    if (checkedItems.length === 0) {
	        alert("삭제할 상품을 선택해주세요.");
	        return;
	    }

	    if (window.confirm(`선택한 ${checkedItems.length}개의 상품을 장바구니에서 삭제하시겠습니까?`)) {
	        // 1. 서버에 삭제 요청 (백엔드 설계에 따라 다를 수 있음)
	        // 예: 반복문으로 보내거나, 배열 통째로 보내는 API가 있다면 그것을 사용
	        const deleteRequests = checkedItems.map(itemIdx => 
	            axios.delete(`/sajo/cart/${memberNo}/${itemIdx}`)
	        );

	        Promise.all(deleteRequests)
	            .then(() => {
	                alert("선택한 상품이 삭제되었습니다.");
	                
	                // 2. 화면(State)에서 삭제된 아이템 제외하고 업데이트
	                const updatedList = showList.filter(item => !checkedItems.includes(item.itemIdx));
	                setShowList(updatedList);
	                
	                // 3. 체크박스 상태 초기화
	                setCheckedItems([]);
	            })
	            .catch(err => {
	                console.error("삭제 중 오류 발생:", err);
	                alert("삭제에 실패했습니다. 다시 시도해주세요.");
	            });
	    }
	};
	//개별 삭제 기능
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
	//상품클릭시 상품상세페이지 이동
	const clickItem = (itemIdx)=> {
		navigate(`/itemDetail/${itemIdx}`);
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
                        <button className="cart-btn-go-shopping" onClick={() => navigate(`/`)}>
                            <span className="cart-btn-icon">🛍️</span>
                            상품 보러 가기
                        </button>
                    </div>
                </div>
            ) : (
                <div className="cart-main-container">
                    {/* 왼쪽 상품 리스트 */}
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
							{/* 선택 삭제 버튼 추가 */}
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

                    {/* 오른쪽 주문 개요 */}
					<div className="cart-right-section">
					    <div className="cart-order-summary-card">
					        <h3 className="cart-summary-title">주문 개요</h3>
					        <div className="cart-price-info-area">
					            <div className="cart-summary-row">
					                <span>상품 소계</span>
					                <span>₩{subTotal.toLocaleString()}</span>
					            </div>
					            <div className="cart-summary-row">
					                <span>현지 유통비</span>
					                <span>₩{localShipping.toLocaleString()}</span>
					            </div>
					            <div className="cart-summary-row">
					                <span>국제 배송비</span>
					                <span>₩{internationalShipping.toLocaleString()}</span>
					            </div>
					            <div className="cart-summary-row">
					                <span>통관・관세</span>
					                <span>₩{tax.toLocaleString()}</span>
					            </div>
					        </div>
					        <div className="cart-total-amount-row">
					            <span>전체 금액</span>
					            <span className="cart-total-price-value">₩{totalAmount.toLocaleString()}</span>
					        </div>
					    </div>
					    <button 
					        className="cart-btn-submit-pay" 
					        onClick={() => navigate(`/payment`, { state: { totalAmount, selectedProducts } })}
					        disabled={selectedProducts.length === 0} // 아무것도 선택 안 하면 버튼 비활성화
					    >
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