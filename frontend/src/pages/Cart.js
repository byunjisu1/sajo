import React, { useState } from 'react';
import './Cart.css';

const Cart = () => {
    const [showList, setShowList] = useState(false);

    return (
        <div className="cart-page">
            {!showList ? (
                <div className="cart-empty-content">
                    <h2>카트에 담긴 상품이 없습니다.</h2>
                    <p>원하시는 상품을 찾아보세요.</p>
                    <div className="button-group">
                        <button className="btn-go-shopping" onClick={() => window.location.href = '/'}>
                            상품 보러 가기
                        </button>
                        <button className="btn-test-toggle" onClick={() => setShowList(true)}>
                            목록 보기(test)
                        </button>
                    </div>
                </div>
            ) : (
                <div className="cart-container">
                    {/* 왼쪽 상품 리스트 */}
                    <div className="cart-left-section">
                        <div className="cart-header">
                            <div className="custom-checkbox-wrapper">
                                <input type="checkbox" id="all-select" checked readOnly />
                                <label htmlFor="all-select">전체 선택 (2/2)</label>
                            </div>
                            <button className="btn-delete">삭제</button>
                        </div>

                        <div className="cart-item-list">
                            {/* 상품 1 */}
                            <div className="cart-item">
                                <input type="checkbox" checked readOnly className="item-check" />
                                <div className="item-thumb">
                                    <img src="https://assets.mercari-shops-static.com/-/large/plain/NujUqwcd2TWWgnbUaYttjM.webp@jpg" alt="상품" />
                                </div>
                                <div className="item-details">
                                    <p className="item-title">[중고] 피규어 ◎ 엔데버 "나의 히어로 아카데미아"</p>
                                    <p className="item-price">₩32,534</p>
                                </div>
                                <button className="btn-close">×</button>
                            </div>
                        </div>
						<div className="cart-item-list">
                            {/* 상품 1 */}
                            <div className="cart-item">
                                <input type="checkbox" checked readOnly className="item-check" />
                                <div className="item-thumb">
                                    <img src="https://assets.mercari-shops-static.com/-/large/plain/NujUqwcd2TWWgnbUaYttjM.webp@jpg" alt="상품" />
                                </div>
                                <div className="item-details">
                                    <p className="item-title">[중고] 피규어 ◎ 엔데버 "나의 히어로 아카데미아"</p>
                                    <p className="item-price">₩32,534</p>
                                </div>
                                <button className="btn-close">×</button>
                            </div>
                        </div>
                        <button className="btn-back-link" onClick={() => setShowList(false)}>뒤로가기</button>
                    </div>

					{/* 오른쪽 주문 개요 */}
					<div className="cart-right-section">
					    <div className="order-card">
					        <h3>주문 개요</h3>
					        <div className="price-info">
					            {/* 한 줄씩 독립적으로 구성 */}
					            <div className="row">
					                <span>상품 소계</span>
					                <span>₩66,025</span>
					            </div>
					            <div className="row">
					                <span>현지 유통비</span>
					                <span>₩6,025</span>
					            </div>
								<div className="row">
					                <span>국제 배송비</span>
					                <span>₩6,025</span>
					            </div>
								<div className="row">
								    <span>통관・관세 </span>
								    <span>₩6,025</span>	
								</div>
					        </div>
					        <div className="total-row">
					            <span>전체 금액</span>
					            <span className="total-price">₩78,941</span>
					        </div>
					    </div>
						<button className="btn-submit-pay">
						    <span className="btn-icon">💳</span>
						    <span className="btn-text">결제하기</span>
						</button>
					</div>
                </div>
            )}
        </div>
    );
};

export default Cart;