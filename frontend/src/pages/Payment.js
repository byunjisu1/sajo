import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import axios from 'axios';
import './Payment.css';

const Payment = () => {
	const navigate = useNavigate();
	const location = useLocation();
	
	const memberNo = sessionStorage.getItem("member_no");
	
    // 주문 상품 목록 열림/닫힘 상태 (기본값: false)
    const [isProductListOpen, setIsProductListOpen] = useState(false);
	
	const { totalAmount=0, selectedProducts=[] } = location.state || {};
	console.log(location.state);
	const [addressList, setAddressList] = useState([]); // 배송지 목록
    const [selectedAddr, setSelectedAddr] = useState(null); // 선택된 배송지 ID
	
	const getAddressList = () => {
        axios.get(`/sajo/getAddressList/${memberNo}`)
        .then(res => {
			console.log(res.data);
            setAddressList(res.data);
            // 첫 번째 배송지가 있다면 기본 선택
            if (res.data.length > 0) {
                setSelectedAddr(res.data[0].addressIdx); 
            }
        })
        .catch(err => console.error("배송지 조회 에러:", err));
    };
		
	const btnPay = () => {
		const selectedAddressDetail = addressList.find(addr => addr.addressIdx === selectedAddr);
		    
	    // 만약 배송지가 선택되지 않았다면 차단 (방어 코드)
	    if (!selectedAddressDetail) {
	        alert("배송지를 선택해주세요.");
	        return;
	    }
		
		const IMP = window.IMP; 
		
		// 주문번호 생성 : 날짜+회원no+대표상품idx+주문상품건수 ex)20260330111
		const now = new Date();
	    const dateStr = now.toISOString().slice(0, 10).replace(/-/g, '');
		const timeStr = String(now.getHours()).padStart(2, '0') + String(now.getMinutes()).padStart(2, '0');
	    const firstItemIdx = selectedProducts[0].itemIdx;
	    const totalCount = selectedProducts.length;

        // 결제 요청
        IMP.request_pay(
            {
                pg: "kakaopay.TC0ONETIME",
                merchant_uid: `${dateStr}${timeStr}${memberNo}${firstItemIdx}${totalCount}`,
                name: selectedProducts.length > 1 ? (`${selectedProducts[0].itemName} 외 ${selectedProducts.length - 1}건`) : selectedProducts[0].itemName,
                amount: totalAmount,
                buyer_addr: `${selectedAddressDetail.address} ${selectedAddressDetail.detailAddress}`,
                buyer_postcode: selectedAddressDetail.postCode,
                custom_data: {"list":[{1:1, 2:2, 3:3}]}
            },
            function (rsp) {
                console.log(rsp);
                // 결제 성공 시 처리
                if (rsp.success) {
					const paymentData = {
				        memberNo: memberNo,
				        totalAmount: totalAmount,
				        merchantUid: rsp.merchant_uid,
				        impUid: rsp.imp_uid,
						items: selectedProducts.map(item => ({
				            itemIdx: item.itemIdx,
				            qty: 1,
				            orderPrice: item.itemPrice
				        }))
				    };
					
					axios.post(`/sajo/payment`, paymentData)
					.then(() => {
						alert("결제되었습니다.");
						navigate(`/myPage`);
					})
					.catch(err => {
			            console.error("DB 저장 에러:", err);
			            alert("결제는 성공했으나 정보 저장 중 오류가 발생했습니다.");
			        });
                } else {
                    alert(rsp.error_msg);
                }
            }
        );	
	};

	useEffect(() => {
		getAddressList();
	    // 컴포넌트 마운트 시 초기화
	    const IMP = window.IMP; 
	    if (IMP) {
	        IMP.init("imp17227166");
	    }
	}, []);
	
    return (
        <div className="payment-page">
            <div className="payment-top-banner">
                <span className="payment-banner-icon">🚚</span>
                <span className="payment-banner-text">업계 최저 배송비로 제공해드립니다!</span>
            </div>

            <div className="payment-main-container">
                <div className="payment-left-section">
                    
                    <div className="payment-section-card">
                        <div className="payment-accordion-header" onClick={() => setIsProductListOpen(!isProductListOpen)}>
                            <h3 className="payment-section-title">주문 상품 목록</h3>
                            <span className={`payment-arrow ${isProductListOpen ? 'open' : ''}`}>
                                {isProductListOpen ? '▲' : '▼'}
                            </span>
                        </div>
                        
                        {isProductListOpen && (
                            <div className="payment-order-items-list">
								{selectedProducts && selectedProducts.length > 0 ? (
									selectedProducts.map((item) => (
										<div key={item.itemIdx} className="payment-product-item">
		                                    <img src={item.itemImg} alt="{item.itemName}" />
		                                    <div className="payment-item-detail">
		                                        <p>{item.itemName}</p>
		                                        <p className="payment-item-sub">₩{item.itemPrice?.toLocaleString()} · 수량 1개</p>
		                                    </div>
		                                </div>
									))
								) : (
									<p style={{ padding: '15px' }}>주문 상품이 없습니다.</p>
								)}                                
                            </div>
                        )}
                    </div>

                    <div className="payment-section-card">
                        <h3 className="payment-section-title">배송지</h3>
						{!addressList || addressList.length === 0 ? (
							<div className="payment-address-empty-box">
	                            <p>등록된 배송지가 없습니다</p>
	                        </div>
						) : (
							<div className="payment-address-list-container">
	                            {addressList.map((addr) => (
	                                <label key={addr.addressIdx} className={`payment-address-item ${selectedAddr === addr.addressIdx ? 'selected' : ''}`} >
	                                    <div className="payment-addr-radio-wrapper">
	                                        <input type="radio" name="address" checked={selectedAddr === addr.addressIdx} onChange={() => setSelectedAddr(addr.addressIdx)}/>
	                                    </div>
	                                    <div className="payment-addr-info">
	                                        <p className="payment-addr-text">({addr.postCode}) {addr.address} {addr.detailAddress}</p>
	                                    </div>
	                                </label>
	                            ))}
	                        </div>
						)}
                    </div>

                    <div className="payment-section-card">
                        <h3 className="payment-section-title">결제 수단 선택</h3>
                        <p className="payment-method-desc">원하시는 결제 수단을 선택해 주세요.</p>
                        
                        <div className="payment-alert-box">
                            <p className="payment-alert-title">결제 안내</p>
                            <p>안전한 결제를 위해 고객님의 정보는 암호화되어 처리됩니다.</p>
                            <p className="payment-escrow-text">🔒 구매안전서비스(에스크로) 적용 중</p>
                        </div>

                        <div className="payment-method-options">
                            <div className="payment-method-box active">
                                <span className="payment-method-icon">💳</span>
                                <span>카카오페이결제</span>
                                <div className="payment-select-dot"></div>
                            </div>
                        </div>
                    </div>
                </div>

                <div className="payment-right-section">
                    <div className="payment-sticky-card">
                        <h3 className="payment-summary-title">주문 개요</h3>
                        
                        <div className="payment-price-breakdown">
                            <div className="payment-price-row">
                                <span>상품 소계</span>
                                <span>₩510,000</span>
                            </div>
                            <div className="payment-price-row">
                                <span>현지 유통비</span>
                                <span>₩6,025</span>
                            </div>
                            <div className="payment-price-row">
                                <span>국제 배송비</span>
                                <span>₩6,025</span>
                            </div>
                            <div className="payment-price-row">
                                <span>통관 · 관세 </span>
                                <span>₩6,025</span>
                            </div>
                        </div>

                        <div className="payment-total-section">
                            <span>전체 금액</span>
                            <span className="payment-total-value">₩{totalAmount?.toLocaleString()}원</span>
                        </div>

                        <button className="payment-submit-button" onClick={btnPay}>
                            <span className="payment-btn-icon">💳</span>
                            <span>결제하기</span>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Payment;