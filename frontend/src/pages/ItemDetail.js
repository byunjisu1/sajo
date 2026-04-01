import React, { useState, useEffect, useContext } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { AuthContext } from '../App';
import axios from 'axios';
import Loading from '../components/Loading';
import ItemAnalysisModal from '../components/ItemAnalysisModal';
import './ItemDetail.css';

const ItemDetail = () => {
  const navigate = useNavigate();
  const { itemIdx } = useParams();
  const [item, setItem] = useState(null); 
  const [ isModalOpen, setIsModalOpen ] = useState(false);
  const [ analysis, setAnalysis ] = useState(null);
  const [ isAnalyzing, setIsAnalyzing ] = useState(false);
  const { memberNo, isLogin } = useContext(AuthContext);

  const getList = async () => {
    try {
	  setItem(null);
      const resp = await axios.get(`/sajo/itemDetail/${itemIdx}`);
      setItem(resp.data); 
    } catch (error) {
      console.error("데이터 로딩 실패:", error);
    }
  };
  
  const addCart = async() => {
	if(isLogin) {
      try {
          await axios.post(`/sajo/addCart/${memberNo}/${itemIdx}`)
		  .then(res => {
			if(res.data) {
		          if (window.confirm("장바구니에 담겼습니다. 장바구니로 이동할까요?")) {
		              navigate('/cart');
		          }
			} else {
				alert("이미 장바구니에 담긴 상품입니다.");
			}
		  });
      } catch (error) {
          console.error("장바구니 에러:", error);
          alert("장바구니 담기에 실패했습니다. 다시 시도해주세요.");
      }
	} else {
		alert("로그인 먼저 해주세요.");
		navigate(`/login`);
	}
  };
  
  const addLikes = async() => {
	if(isLogin) {
		await axios.post(`/sajo/addLikes/${memberNo}/${itemIdx}`)
		.then(res => {
			if (res.data) { // res.data가 true일 때
				if (window.confirm("찜 성공! 찜 페이지로 이동할까요?")) {
	              navigate('/likes');
	            }
			} else {        // res.data가 false일 때
	          alert("이미 찜한 상품입니다.");
			}
		});
	} else {
		alert("로그인 먼저 해주세요.");
		navigate(`/login`);
	}
  };
  
  const handleAnalyze = async() => {
	if(isLogin) {
		setIsAnalyzing(true);
		try {
			const res = await axios.post(`/sajo/item/analyze`, { imageUrl: item.itemImg, description: item.itemDetail })
			console.log(res.data);
			setAnalysis(res.data);
			setIsModalOpen(true);
		} catch(err) {
			console.error("분석 에러 :", err);
			alert("AI 분석 서비스에 접속할 수 없습니다.");
			setIsAnalyzing(false);
		}
	} else {
		alert("로그인 후 이용해주세요.");
		navigate(`/login`);
	}
  };
  
  const movePayment = () => {
	if(isLogin) {
		const singleProduct = [{
			itemIdx: item.itemIdx,
			itemName: item.itemName,
			itemPrice: item.itemPrice,
			itemImg: item.itemImg,
			qty: 1
		}];
		
		navigate(`/payment`, { state: { totalAmount: item.itemPrice, selectedProducts: singleProduct } });
	} else {
		alert("로그인 후 이용해주세요.");
		navigate(`/login`);
	}
  };

  useEffect(() => {
    getList();
  }, [itemIdx]);

  if (!item) {
	return <Loading />;
  }

  return (
    <main className="item-detail-page" aria-label="상품 상세">
      <section className="item-detail-layout">
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

          <button 
		  	type="button" 
			className="item-analyze-btn" 
			onClick={handleAnalyze} 
			disabled={isAnalyzing}
			style={{ 
				backgroundColor: isAnalyzing ? 'gold' : '',
				color: isAnalyzing ? '#000' : '',
				border: isAnalyzing ? 'none' : ''
			}}
		   >
            {isAnalyzing ? "AI 분석 중" : "AI 가품 분석하기"}
          </button>
		  <ItemAnalysisModal isOpen={isModalOpen} onClose={() => {setIsModalOpen(false); setIsAnalyzing(false);}} data={analysis}/>
        </div>

        <div className="item-summary">
          <button type="button" className="item-likes-btn" onClick={addLikes}>
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