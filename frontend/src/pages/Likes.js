import React from 'react';
import './Likes.css';

const Likes = () => {
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
				    <select className="Likes-sort-select">
				      <option value="latest">최신순</option>
				      <option value="name">이름순</option>
				    </select>
				    <span className="Likes-arrow">▼</span>
				  </div>
				</div>
			
			    <div className="Likes-empty-card">
			      <p className="Likes-empty-main">찜한상품이 없습니다</p>
			      <p className="Likes-empty-sub">상품을 찜하기에 추가해보세요!</p>
			    </div>
			  </div>
			</main>
	    </div>
	  );
};

export default Likes;