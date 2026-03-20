import React from 'react';
import './Footer.css';

const Footer = () => {
	return (
	    <footer className="sazo-footer">
	      <p>
	        사줘는 관세법 등 법령을 준수하며, 분할배송·가격/품명 허위신고 등 불법 요청에는
	        협조하지 않습니다.
	      </p>
	      <p>
	        보상은 운송사 약관/보험 범위(세관신고가액 또는 실제 결제금액 한도) 내 처리되며,
	        상품 정보는 판매처 기준입니다.
	      </p>
	      <p>의약품은 구매대행을 진행하지 않습니다.</p>

	      <p className="sazo-footer-copy">
	        © 2026 SAJO PRJ.
	      </p>

	      <div className="sazo-footer-bottom">
	        <span>SAJO 한국지사 - (주)SMJ</span>
	        <div className="sazo-policy-links">
	          <a href="#terms">이용약관</a>
	          <a href="#privacy">개인정보처리방침</a>
	        </div>
	      </div>
	    </footer>
	  );
};

export default Footer;