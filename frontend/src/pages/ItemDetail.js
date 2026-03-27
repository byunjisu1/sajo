import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import './ItemDetail.css';

const ItemDetail = () => {
  const navigate = useNavigate();
  const { itemIdx } = useParams();
  const [item, setItem] = useState(null); 
  const memberNo = sessionStorage.getItem("member_no");

  const getList = async () => {
    try {
      // 주소창에서 성공했던 경로가 /itemDetail/1 이라면 아래대로,
      // 만약 /sajo/itemDetail/1 이라면 앞에 /sajo를 붙여주세요.
      const resp = await axios.get(`/sajo/itemDetail/${itemIdx}`);
      setItem(resp.data); 
    } catch (error) {
      console.error("데이터 로딩 실패:", error);
    }
  };
  
  const addCart = async () => {
      try {
          await axios.post(`/sajo/addCart/${memberNo}/${itemIdx}`);
          
          if (window.confirm("장바구니에 담겼습니다. 장바구니로 이동할까요?")) {
              navigate('/cart');
          }
      } catch (error) {
          console.error("장바구니 에러:", error);
          alert("장바구니 담기에 실패했습니다. 다시 시도해주세요.");
      }
  };
  
  //일단은 결제페이지로 이동(itemIdx 들고와야되긴함 (가격 이름 이미지))
  const movePayment = () => {
	navigate(`/payment`);
  };

  useEffect(() => {
    getList();
  }, [itemIdx]);

  // 이 부분 때문에 데이터가 오기 전에는 화면이 바뀌지 않은 것처럼 보일 수 있습니다.
  if (!item) {
    return <div className="item-detail-page">상품 정보를 불러오는 중입니다...</div>;
  }

  return (
    <main className="item-detail-page" aria-label="상품 상세">
      <section className="item-detail-layout">
        {/* 왼쪽: 이미지 갤러리 (itemImg 반영) */}
        <div className="item-gallery">
          <div className="item-main-image-wrap" aria-label="상품 이미지">
            <div className="item-main-image">
              <img src={item.itemImg} alt={item.itemName} />
            </div>

            <div className="item-image-nav">
              <button type="button" className="item-image-arrow" aria-label="이전 이미지">‹</button>
              <button type="button" className="item-image-arrow" aria-label="다음 이미지">›</button>
            </div>
          </div>

          <button type="button" className="item-analyze-btn">
            가품 분석하기
          </button>
        </div>

        {/* 오른쪽: 상품 정보 요약 (itemName, itemPrice, itemDetail 반영) */}
        <div className="item-summary">
          <button type="button" className="item-likes-btn">
            <svg className="item-likes-svg" viewBox="0 0 24 24" aria-hidden="true">
              <path d="M19 21l-7-4.6L5 21V5a2 2 0 0 1 2-2h10a2 2 0 0 1 2 2v16z"/>
            </svg>
          </button>

          <h1 className="item-title">{item.itemName}</h1>
          <p className="item-subtitle">상품 번호: #{item.itemIdx}</p>

          <div className="item-price-row">
            <span className="item-price">￦{item.itemPrice.toLocaleString()}</span>
          </div>

          <div className="item-section">
            <h2 className="item-section-title">상품 상세 정보</h2>
            {/* itemDetail 필드 출력 */}
            <p className="item-desc">{item.itemDetail}</p>
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
            <button type="button" className="item-qty-btn">−</button>
            <span className="item-qty-value">1</span>
            <button type="button" className="item-qty-btn">+</button>
          </div>

          <div className="item-total-box">
            <div className="item-total-row item-total-row--strong">
              <span>총 상품 금액</span>
              <strong>₩{item.itemPrice.toLocaleString()}</strong>
            </div>
            <div className="item-total-row">
              <span>상품 가격</span>
              <span>₩{item.itemPrice.toLocaleString()}</span>
            </div>
            <div className="item-total-row">
              <span>현지 우편비</span>
              <span>₩0</span>
            </div>
          </div>

          <button type="button" className="item-cart-btn" onClick={addCart}>
            장바구니 담기
          </button>
          <button type="button" className="item-buy-btn" onClick={movePayment}>
            바로 구매하기
          </button>
        </div>
      </section>
    </main>
  );
};

export default ItemDetail;