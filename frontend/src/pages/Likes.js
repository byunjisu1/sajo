import React, { useState } from 'react';
import './Likes.css';

const Likes = () => {
	const [ hasLikes, setHasLikes ] = useState(false);
	
	return (
		<div className="Likes-wrapper">
			<header className="Likes-profile-header">
			    <div className="Likes-profile-info">
			      <span className="Likes-nickname">지수</span>
			      <span className="Likes-user-name">변지수 님</span>
			      <span className="Likes-chevron">〉</span>
			    </div>
			</header>
			
			<main className="Likes-main-area">
			  <div className="Likes-main-container">
			    <header className="Likes-content-title-area">
			      <h1 className="Likes-content-title">찜하기</h1>
			    </header>
			
			    <div className="Likes-divider"></div>
			
				<div className="Likes-filter-bar">
				  <div className="Likes-select-container">
				  	<button onClick={() => setHasLikes(!hasLikes)}>찜 (현재: {hasLikes ? '찜 있음' : '찜 없음'})</button>
				    <select className="Likes-sort-select">
				      <option value="latest">최신순</option>
				      <option value="name">이름순</option>
				    </select>
				    <span className="Likes-arrow">▼</span>
				  </div>
				</div>
			
			    <div className={hasLikes ? "Likes-grid" : "Likes-list"}>
				{
					hasLikes ?
					(
						<div className="Likes-item-card">
					      <div className="Likes-item-image-wrapper">
					        <img src="https://shop.r10s.jp/book/cabinet/9001/6942630809001.jpg" alt="상품 이미지" className="Likes-item-image"/>
					      </div>
					      <div className="Likes-item-info">
					        <p className="Likes-item-title">1/7 “젠리스 존 제로” 키요스미가 마사요 호시미 버전을 교정합니다.(미리 도색된 완성된 피규어) 장난감</p>
					        <div className="Likes-item-price-row">
					          <span className="Likes-item-price">￦372,356</span>
					        </div>
					      </div>
					    </div>	
					) : (<>
						<p className="Likes-empty-main">찜한상품이 없습니다</p>
						<p className="Likes-empty-sub">상품을 찜하기에 추가해보세요!</p>
					</>)
				}
			    </div>
			  </div>
			</main>
	    </div>
	  );
};

export default Likes;