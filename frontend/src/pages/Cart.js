import React, { useState } from 'react';
import './Cart.css';

const Cart = () => {
    const [showList, setShowList] = useState(false);

    return (
        <div className="cart-page">
            {!showList ? (
                <div className="cart-empty-content">
                    <h2 className="cart-empty-title">카트에 담긴 상품이 없습니다.</h2>
                    <p className="cart-empty-desc">원하시는 상품을 찾아보세요.</p>
                    <div className="cart-empty-button-group">
                        <button className="cart-btn-go-shopping" onClick={() => window.location.href = '/'}>
                            <span className="cart-btn-icon">🛍️</span>
                            상품 보러 가기
                        </button>
                        <button className="cart-btn-test-toggle" onClick={() => setShowList(true)}>
                            목록 보기(test)
                        </button>
                    </div>
                </div>
            ) : (
                <div className="cart-main-container">
                    {/* 왼쪽 상품 리스트 */}
                    <div className="cart-left-section">
                        <div className="cart-header-bar">
                            <div className="cart-custom-checkbox-wrapper">
                                <input type="checkbox" id="cart-all-select" checked readOnly />
                                <label htmlFor="cart-all-select">전체 선택 (2/2)</label>
                            </div>
                            <button className="cart-btn-delete-selected">삭제</button>
                        </div>

                        <div className="cart-item-list">
                            <div className="cart-product-item">
                                <input type="checkbox" checked readOnly className="cart-item-check" />
                                <div className="cart-item-thumb">
                                    <img src="https://assets.mercari-shops-static.com/-/large/plain/NujUqwcd2TWWgnbUaYttjM.webp@jpg" alt="상품" />
                                </div>
                                <div className="cart-item-details">
                                    <p className="cart-item-title">[중고] 피규어 ◎ 엔데버 "나의 히어로 아카데미아"</p>
                                    <p className="cart-item-price">₩32,534</p>
                                </div>
                                <button className="cart-btn-item-close">×</button>
                            </div>

                            <div className="cart-product-item">
                                <input type="checkbox" checked readOnly className="cart-item-check" />
                                <div className="cart-item-thumb">
                                    <img src="https://assets.mercari-shops-static.com/-/large/plain/NujUqwcd2TWWgnbUaYttjM.webp@jpg" alt="상품" />
                                </div>
                                <div className="cart-item-details">
                                    <p className="cart-item-title">[중고] 피규어 ◎ 엔데버 "나의 히어로 아카데미아"</p>
                                    <p className="cart-item-price">₩32,534</p>
                                </div>
                                <button className="cart-btn-item-close">×</button>
                            </div>
                        </div>
                        <button className="cart-btn-back-link" onClick={() => setShowList(false)}>뒤로가기</button>
                    </div>

                    {/* 오른쪽 주문 개요 */}
                    <div className="cart-right-section">
                        <div className="cart-order-summary-card">
                            <h3 className="cart-summary-title">주문 개요</h3>
                            <div className="cart-price-info-area">
                                <div className="cart-summary-row">
                                    <span>상품 소계</span>
                                    <span>₩66,025</span>
                                </div>
                                <div className="cart-summary-row">
                                    <span>현지 유통비</span>
                                    <span>₩6,025</span>
                                </div>
                                <div className="cart-summary-row">
                                    <span>국제 배송비</span>
                                    <span>₩6,025</span>
                                </div>
                                <div className="cart-summary-row">
                                    <span>통관・관세</span>
                                    <span>₩6,025</span>
                                </div>
                            </div>
                            <div className="cart-total-amount-row">
                                <span>전체 금액</span>
                                <span className="cart-total-price-value">₩78,941</span>
                            </div>
                        </div>
                        <button className="cart-btn-submit-pay" onClick={() => window.location.href = '/payment'}>
                            <span className="cart-btn-icon">💳</span>
                            <span className="cart-btn-text" >결제하기</span>
                        </button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default Cart;