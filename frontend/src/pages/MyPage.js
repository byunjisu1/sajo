import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './MyPage.css';

const MyPage = () => {
	const navigate = useNavigate();
	const [hasOrder, setHasOrder] = useState(false);
	return (
		<div className="mypage-container">
			<header className="mypage-profile">
				<div className="mypage-profile-info">
					<span className="mypage-nickname">지수</span>
					<span className="mypage-user-name">변지수 님</span>
					<span className="mypage-chevron">〉</span>
				</div>
			</header>

			<div className="mypage-body">

				<aside className="mypage-sidebar">
					<div className="menu-group">
						<h3 className="menu-title">쇼핑</h3>
						<ul>
							<li onClick={() => navigate('/myPage')} className="active">주문 내역</li>
						</ul>
					</div>
					<div className="menu-group">
						<h3 className="menu-title">설정</h3>
						<ul>
							<li onClick={() => navigate(`/memberUpdate`)}>회원 정보 수정</li>
							<li onClick={() => navigate(`/address`)}>배송지 관리</li>
						</ul>
					</div>
				</aside>
					<main className="mypage-main">
						<div className="content-header">
							<h2>주문 내역</h2>
						</div>
						{hasOrder ? (
						<div className="order-item">
							<div className="order-info">
								<span className="order-date">2026.01.20</span>
								<span className="order-id">26012035904</span>
							</div>

							<div className="order-card">
								<p className="order-status">📦 배송완료</p>
								<div className="product-details">
									<p className="product-name">PRADA 보스턴백 모스 그린 카키</p>
									<p className="product-price">₩142,577 · 1개</p>
								</div>
								<button className="track-btn">배송조회</button>
							</div>
						</div>
					) : (
						<div className="empty-order-container">
							<div className="empty-box">
								<p className="empty-text">구매 내역이 없습니다.</p>
								<p className="suggest-text">사줘의 상품을 구매해보세요!</p>
							</div>
						</div>
					)
					}
					</main>
			</div>
			<button onClick={() => setHasOrder(!hasOrder)} style={{ marginTop: '20px' }}>
				화면 전환 테스트 (현재: {hasOrder ? '주문 있음' : '주문 없음'})
			</button>
		</div>
	);
}

export default MyPage;