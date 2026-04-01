import React, { useContext } from 'react';
import { AuthContext } from '../App';
import { Link, useNavigate } from 'react-router-dom';
import './Header.css';
import axios from 'axios';

const Header = () => {
	const navigate = useNavigate();
	const { isLogin, setIsLogin, setMemberNo } = useContext(AuthContext);
	
	const handleLogout = async () => {
	    try {
	        await axios.post('/sajo/logout');
	        setIsLogin(false);
			setMemberNo('');	        
			alert("로그아웃 되었습니다.");
	        navigate('/');
	    } catch (error) {
	        console.error("로그아웃 중 오류 발생:", error);
	        setIsLogin(false);
	        navigate('/');
	    }
	};
	
	const handleSearch = async() => {
		const searchInput = document.querySelector('.sajo-search');
	    const searchValue = searchInput ? searchInput.value : "";

	    if (!searchValue.trim()) {
	        alert("키워드 또는 URL을 입력해주세요.");
	        return;
	    }
		
		try {
	        const res = await axios.post('/sajo/item/url/search', { searchUrl: searchValue });
	        console.log("서버 응답 데이터:", res.data);
			const itemIdx = res.data;
	        navigate(`/itemDetail${itemIdx}`);
	    } catch (error) {
	        console.error("검색 전송 중 오류 발생:", error);
	        alert("서버와 통신 중 오류가 발생했습니다.");
	    }

	};
	
	return (
		<header className="sajo-header">
			<div className="sajo-header-top">
				<Link to="/" className="sajo-logo" aria-label="사줘 홈">
					<img src={`${process.env.PUBLIC_URL}/logo-sajo.png`} className="sajo-logo-img" decoding="async" />
				</Link>

				<div className="sajo-search-wrap">
					<div className="sajo-search-box">
						<span className="sajo-search-prefix" aria-hidden="true">
							<img src={`${process.env.PUBLIC_URL}/search-bar-icon.png`} className="sajo-search-prefix-img" decoding="async" />
						</span>
						<input type="text" className="sajo-search" placeholder="키워드 또는 URL 입력" aria-label="검색어 입력" />
						<span className="sajo-search-caret-wrap" aria-hidden="true">
							<svg className="sajo-search-caret-solid" viewBox="0 0 10 6" aria-hidden="true">
								<path d="M0 0 L5 6 L10 0 Z" fill="currentColor" />
							</svg>
						</span>
						<button type="button" className="sajo-search-btn" aria-label="검색" onClick={handleSearch}>
							<svg className="sajo-search-svg" viewBox="0 0 24 24" aria-hidden="true">
								<circle cx="10.5" cy="10.5" r="6.25" />
								<path d="M15.2 15.2L20 20" />
							</svg>
						</button>
					</div>
				</div>

				<div className="sajo-icons">
					<button type="button" className="sajo-icon-btn" aria-label="찜 목록" onClick={() => navigate('/likes')}>
						<svg className="sajo-icon-svg" viewBox="0 0 24 24" aria-hidden="true">
							<path d="M19 21l-7-4.6L5 21V5a2 2 0 0 1 2-2h10a2 2 0 0 1 2 2v16z" />
						</svg>
					</button>
					<button type="button" className="sajo-icon-btn" aria-label="장바구니" onClick={() => navigate('/cart')}>
						<svg className="sajo-icon-svg" viewBox="0 0 24 24" aria-hidden="true">
							<circle cx="9" cy="21" r="1" />
							<circle cx="20" cy="21" r="1" />
							<path d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6" />
						</svg>
					</button>
					<button type="button" className="sajo-icon-btn" aria-label="알림">
						<svg className="sajo-icon-svg" viewBox="0 0 24 24" aria-hidden="true">
							<path d="M18 8A6 6 0 0 0 6 8c0 7-3 7-3 7h18s-3 0-3-7" />
							<path d="M13.73 21a2 2 0 0 1-3.46 0" />
							<circle cx="12" cy="21.1" r="1.15" fill="currentColor" />
						</svg>
					</button>
				</div>
				{isLogin ?
					(<>
						<span onClick={() => navigate(`/myPage`)} className="sajo-header-mypage">마이페이지</span>
						<span className="sajo-header-mypage" onClick={handleLogout}>로그아웃</span>
					</>
					)
					: (<button type="button" className="sajo-login-btn" onClick={() => navigate('/login')}>로그인</button>)
				}
			</div>

			<div className="sajo-nav-shell">
				<nav className="sajo-header-nav" aria-label="메인 메뉴">
					<Link to="/">홈</Link>
					<Link to="/board">문의게시판</Link>
					<Link to="/rakuten">라쿠텐</Link>
				</nav>
			</div>
		</header>
	);
};

export default Header;