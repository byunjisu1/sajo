import React, { useState } from 'react';
import './Payment.css';

const Payment = () => {
    // 주문 상품 목록 열림/닫힘 상태 (기본값: false)
    const [isProductListOpen, setIsProductListOpen] = useState(false);
    // 결제 수단 선택 상태
    const [paymentMethod, setPaymentMethod] = useState('card');

    return (
        <div className="payment-page">
            {/* 상단 공지 */}
            <div className="payment-top-banner">
                <span className="payment-banner-icon">🚚</span>
                <span className="payment-banner-text">업계 최저 배송비로 제공해드립니다!</span>
            </div>

            <div className="payment-main-container">
                {/* 왼쪽 영역: 정보 입력 */}
                <div className="payment-left-section">
                    
                    {/* 1. 주문 상품 목록 (아코디언) */}
                    <div className="payment-section-card">
                        <div 
                            className="payment-accordion-header" 
                            onClick={() => setIsProductListOpen(!isProductListOpen)}
                        >
                            <h3 className="payment-section-title">주문 상품 목록</h3>
                            <span className={`payment-arrow ${isProductListOpen ? 'open' : ''}`}>
                                {isProductListOpen ? '▲' : '▼'}
                            </span>
                        </div>
                        
                        {isProductListOpen && (
                            <div className="payment-order-items-list">
                                <div className="payment-product-item">
                                    <img src="https://assets.mercari-shops-static.com/-/large/plain/NujUqwcd2TWWgnbUaYttjM.webp@jpg" alt="상품" />
                                    <div className="payment-item-detail">
                                        <p>[중고] 피규어 ◎ 엔데버 “나의 히어로 아카데미아” SOFVIMATES</p>
                                        <p className="payment-item-sub">₩32,534 · 수량 1개</p>
                                    </div>
                                </div>
                                <div className="payment-product-item">
                                    <img src="https://assets.mercari-shops-static.com/-/large/plain/NujUqwcd2TWWgnbUaYttjM.webp@jpg" alt="상품" />
                                    <div className="payment-item-detail">
                                        <p>[중고] 피규어 올마이트 “나의 히어로 아카데미아” vol.5</p>
                                        <p className="payment-item-sub">₩33,491 · 수량 1개</p>
                                    </div>
                                </div>
                            </div>
                        )}
                    </div>

                    {/* 2. 배송지 */}
                    <div className="payment-section-card">
                        <h3 className="payment-section-title">배송지</h3>
                        <div className="payment-address-empty-box">
                            <p>등록된 배송지가 없습니다</p>
                            <button className="payment-btn-black">배송지 추가</button>
                        </div>
                    </div>

                    {/* 3. 결제 수단 선택 (image_2f3686 디자인 복구) */}
                    <div className="payment-section-card">
                        <h3 className="payment-section-title">결제 수단 선택</h3>
                        <p className="payment-method-desc">원하시는 결제 수단을 선택해 주세요.</p>
                        
                        <div className="payment-alert-box">
                            <p className="payment-alert-title">결제 안내</p>
                            <p>안전한 결제를 위해 고객님의 정보는 암호화되어 처리됩니다.</p>
                            <p className="payment-escrow-text">🔒 구매안전서비스(에스크로) 적용 중</p>
                        </div>

                        <div className="payment-method-options">
                            <div 
                                className={`payment-method-box ${paymentMethod === 'card' ? 'active' : ''}`}
                                onClick={() => setPaymentMethod('card')}
                            >
                                <span className="payment-method-icon">💳</span>
                                <span>카드결제</span>
                                {paymentMethod === 'card' && <div className="payment-select-dot"></div>}
                            </div>
                            <div 
                                className={`payment-method-box ${paymentMethod === 'phone' ? 'active' : ''}`}
                                onClick={() => setPaymentMethod('phone')}
                            >
                                <span className="payment-method-icon">📱</span>
                                <span>휴대폰결제</span>
                                {paymentMethod === 'phone' && <div className="payment-select-dot"></div>}
                            </div>
                        </div>
                    </div>
                </div>

                {/* 오른쪽 영역: Sticky 주문 개요 (쿠폰 제외) */}
                <div className="payment-right-section">
                    <div className="payment-sticky-card">
                        <h3 className="payment-summary-title">주문 개요</h3>
                        
                        <div className="payment-price-breakdown">
                            <div className="payment-price-row">
                                <span>상품 소계</span>
                                <span>₩66,025</span>
                            </div>
                            <div className="payment-price-row">
                                <span>현지 유통비</span>
                                <span>₩6,314</span>
                            </div>
                            <div className="payment-price-row">
                                <span>국제 배송비</span>
                                <span>₩11,482</span>
                            </div>
                            <div className="payment-price-row">
                                <span>통관 · 관세 </span>
                                <span>₩6,602</span>
                            </div>
                        </div>

                        <div className="payment-total-section">
                            <span>전체 금액</span>
                            <span className="payment-total-value">₩90,423</span>
                        </div>

                        <button className="payment-submit-button">
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