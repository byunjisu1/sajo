import React from 'react';
import './ItemDetail.css';

const ItemDetail = () => {
	return (
	    <main className="item-detail-page" aria-label="상품 상세">
	      <section className="item-detail-layout">
	        <div className="item-gallery">
	          <div className="item-main-image-wrap" aria-label="상품 이미지">
	            <div className="item-main-image">
					<img src="https://shop.r10s.jp/book/cabinet/9001/6942630809001.jpg" alt="피규어" />
	            </div>

	            <div className="item-image-nav">
	              <button type="button" className="item-image-arrow" aria-label="이전 이미지">
	                ‹
	              </button>
	              <button type="button" className="item-image-arrow" aria-label="다음 이미지">
	                ›
	              </button>
	            </div>
	          </div>

	          <button type="button" className="item-analyze-btn">
	            가품 분석하기
	          </button>
	        </div>

	        <div className="item-summary">
	          <h1 className="item-title">
	            1/7 “젠리스 존 제로” 키요스미가 마사요 호시미 버전을 교정합니다.(미리 도색된 완성된 피규어) 장난감
	          </h1>
			  <button type="button" className="item-likes-btn">
  	            <svg className="item-likes-svg" viewBox="0 0 24 24" aria-hidden="true">
  	              <path d="M19 21l-7-4.6L5 21V5a2 2 0 0 1 2-2h10a2 2 0 0 1 2 2v16z"/>
  	            </svg>
  	          </button>

	          <p className="item-subtitle">1/7 『ゼンレスゾーンゼロ』 星見雅 世を正す清澄 Ver. (塗装済み完成品フィギュア) 玩具</p>

	          <div className="item-price-row">
	            <span className="item-price">￦372,356</span>
	          </div>

			  <div className="item-section">
			    <h2 className="item-section-title">상품 상세 정보</h2>
			    <p className="item-desc">
				발매일:2027년 02월경<br/>
				판매원: APEX TOYS<br/>
				대응 기종 등: 장난감<br/>
				JAN:6942630809001
			    </p>
			  </div>
			  
	          <div className="item-section">
	            <h2 className="item-section-title">일반 일본상품</h2>
	            <p className="item-desc">개인별 보안 인증을 위해 배송정보를 확인합니다.</p>
	          </div>

	          <ul className="item-shipping-list">
	            <li>일본내 배송 및 현지 이동: 4 ~ 6일</li>
	            <li>국제배송 및 수령: 6 ~ 8일</li>
	          </ul>

	          <div className="item-qty-box" aria-label="수량 선택">
	            <button type="button" className="item-qty-btn" aria-label="수량 감소">
	              −
	            </button>
	            <span className="item-qty-value">1</span>
	            <button type="button" className="item-qty-btn" aria-label="수량 증가">
	              +
	            </button>
	          </div>

	          <div className="item-total-box">
	            <div className="item-total-row item-total-row--strong">
	              <span>총 상품 금액</span>
	              <strong>₩19,137</strong>
	            </div>
	            <div className="item-total-row">
	              <span>상품 가격</span>
	              <span>₩19,137</span>
	            </div>
	            <div className="item-total-row">
	              <span>현지 우편비</span>
	              <span>₩0</span>
	            </div>
	          </div>

	          <button type="button" className="item-cart-btn">
	            장바구니 담기
	          </button>
	          <button type="button" className="item-buy-btn">
	            바로 구매하기
	          </button>
	        </div>
	      </section>
	    </main>
	  );
};

export default ItemDetail;