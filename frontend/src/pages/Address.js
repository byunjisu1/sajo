import React from 'react';
import './Address.css';
import { useNavigate } from 'react-router-dom';

const Address = () => {
	const navigate = useNavigate();
	return (
		<div className="address-container">
			<header className="address-profile">
				<div className="address-profile-info">
					<span className="address-nickname">지수</span>
					<span className="address-user-name">변지수 님</span>
					<span className="address-chevron">〉</span>
				</div>
			</header>
			<div className="address-body">
				<aside className="address-sidebar">
					<div className="menu-group">
						<h3 className="menu-title">쇼핑</h3>
						<ul>
							<li onClick={() => navigate('/mypage')} className="active">주문 내역</li>
						</ul>
					</div>
					<div className="menu-group">
						<h3 className="menu-title">설정</h3>
						<ul>
							<li onClick={() => navigate(`/memberupdate`)}>회원 정보 수정</li>
							<li onClick={() => navigate(`/address`)}>배송지 관리</li>
						</ul>
					</div>
				</aside>
				<div className="address-update-container">
					<h2 className="page-title">주소 추가 및 변경</h2>

					<div className="address-item">
						<div className="address-info">
							<div className="address-header">
								<span className="address-name">우리집</span>
								<span className="badge">기본 배송지</span>
							</div>

							<div className="user-contact">
								<span className="user-name">배승빈</span>
								<span className="divider">/</span>
								<span className="phone">01047056832</span>
							</div>

							<p className="address-text">
								(08775) 서울 관악구 문성로 221-5<br />
								P1900000035232
							</p>
						</div>

						<div className="address-actions">
							<button className="btn-outline">삭제</button>
							<button className="btn-dark">수정</button>
						</div>
					</div>

					<hr className="section-divider" />

					<div className="add-address-wrapper">
						<button className="add-address-btn" onClick={() => alert('배송지 추가 클릭!')}>
							<span className="plus-icon">+</span> 배송지 추가
						</button>
					</div>
				</div>
			</div>
		</div>
	);
};

export default Address;