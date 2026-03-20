import React from 'react';
import { useNavigate } from 'react-router-dom';
import './Header.css';

const Header = () => {
	const navigate = useNavigate();
	
	return (
	    <header className="sazo-header">
	      <div className="sazo-header-top">
	        <a href="/" className="sazo-logo" aria-label="사줘 홈">
	          <img src={`${process.env.PUBLIC_URL}/logo-sajo.png`} className="sazo-logo-img" decoding="async"/>
	        </a>

	        <div className="sazo-search-wrap">
	          <div className="sazo-search-box">
	            <span className="sazo-search-prefix" aria-hidden="true">
	              <img src={`${process.env.PUBLIC_URL}/search-bar-icon.png`} className="sazo-search-prefix-img" decoding="async"/>
	            </span>
	            <input type="text" className="sazo-search" placeholder="키워드 또는 URL 입력" aria-label="검색어 입력"/>
	            <span className="sazo-search-caret-wrap" aria-hidden="true">
	              <svg className="sazo-search-caret-solid" viewBox="0 0 10 6" aria-hidden="true">
	                <path d="M0 0 L5 6 L10 0 Z" fill="currentColor" />
	              </svg>
	            </span>
	            <button type="button" className="sazo-search-btn" aria-label="검색">
	              <svg className="sazo-search-svg" viewBox="0 0 24 24" aria-hidden="true">
	                <circle cx="10.5" cy="10.5" r="6.25" />
	                <path d="M15.2 15.2L20 20" />
	              </svg>
	            </button>
	          </div>
	        </div>

	        <div className="sazo-icons">
	          <button type="button" className="sazo-icon-btn" aria-label="찜 목록" onClick={() => navigate('/likes')}>
	            <svg className="sazo-icon-svg" viewBox="0 0 24 24" aria-hidden="true">
	              <path d="M19 21l-7-4.6L5 21V5a2 2 0 0 1 2-2h10a2 2 0 0 1 2 2v16z"/>
	            </svg>
	          </button>
	          <button type="button" className="sazo-icon-btn" aria-label="장바구니" onClick={() => navigate('/cart')}>
	            <svg className="sazo-icon-svg" viewBox="0 0 24 24" aria-hidden="true">
	              <circle cx="9" cy="21" r="1"/>
	              <circle cx="20" cy="21" r="1"/>
	              <path d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6"/>
	            </svg>
	          </button>
	          <button type="button" className="sazo-icon-btn" aria-label="알림">
	            <svg className="sazo-icon-svg" viewBox="0 0 24 24" aria-hidden="true">
	              <path d="M18 8A6 6 0 0 0 6 8c0 7-3 7-3 7h18s-3 0-3-7"/>
	              <path d="M13.73 21a2 2 0 0 1-3.46 0"/>
	              <circle cx="12" cy="21.1" r="1.15" fill="currentColor" />
	            </svg>
	          </button>
	        </div>

	        <button type="button" className="sazo-login-btn" onClick={() => navigate('/login')}>로그인</button>
	      </div>

	      <div className="sazo-nav-shell">
	        <nav className="sazo-header-nav" aria-label="메인 메뉴">
	          <a href="/">홈</a>
	          <a href="/board">문의게시판</a>
	          <a href="/rakuten">라쿠텐</a>
	        </nav>
	      </div>
	    </header>
	  );
};

export default Header;