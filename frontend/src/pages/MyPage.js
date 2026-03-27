import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './MyPage.css';

const MyPage = () => {
	const navigate = useNavigate();
	const [orderList, setOrderList] = useState([])
	const [headerProfile, setHeaderProfile] = useState({});
	const memberNo = sessionStorage.getItem("member_no");

	const getHeaderProfile = (memberNo) => {
		axios.get(`/sajo/member/${memberNo}`)
			.then(res => {
				console.log(res.data);
				setHeaderProfile(res.data);
			})
			.catch(err => {
				console.error(err);
			});
	}
	const getOrders = () => {
		axios.get(`/sajo/getOrderList/${memberNo}`)
			.then((res) => {
				setOrderList(res.data);
				console.log(res.data);
			})
			.catch((err) => {
				console.error(err);
			});
	};

	useEffect(
		() => {
			getHeaderProfile(memberNo);
			getOrders(memberNo);
		}, []
	);

	return (
		<div className="mypage-container">
			<header className="mypage-profile">
				<div className="mypage-profile-info">
					<span className="mypage-profile-image">{headerProfile.profileImg}</span>
					<span className="mypage-user-name">{headerProfile.nickname} 님</span>
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
					{orderList.length === 0 ? (
						<div className="empty-order-container">
							<div className="empty-box">
								<p className="empty-text">구매 내역이 없습니다.</p>
								<p className="suggest-text">사줘의 상품을 구매해보세요!</p>
							</div>
						</div>
					) : (
							orderList.map((item) => (
								<div className="order-item" key={item.orderNo}>
									<div className="order-info">
										<span className="order-date">{item.orderDate.substring(0,11)}</span>
										<span className="order-id">{item.orderNo}</span>
									</div>

									<div className="order-card">
										<p className="order-status">{item.orderStatus === 'order_complete' ? '📦 배송완료' : '⏳ 결제대기'}📦</p>
										<div className="item-img-div">
											<img src={item.itemImg} className="item-img" />
										</div>
										<div className="product-details">
											<p className="product-name">{item.itemName}</p>
											<p className="product-price">₩{item.itemPrice} · {item.qty}개</p>
										</div>
									</div>
								</div>
							))
					)
					}
				</main>
			</div>
		</div>
	);
}

export default MyPage;