import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './Likes.css';

const Likes = () => {
	const navigate = useNavigate();
	const [ headerProfile, setHeaderProfile ] = useState({});
	const [ wishList, setWishList ] = useState([]);
	const [sortType, setSortType] = useState("latest");
	const memberNo = sessionStorage.getItem("member_no");
	
	const getHeaderProfile = () => {
		axios.get(`/sajo/member/${memberNo}`)
		.then(res => {
			console.log(res.data);
			setHeaderProfile(res.data);
		})
		.catch(err => {
			console.error(err);
		});
	};
	
	const getWishList = (sort) => {
		console.log("요청하는 정렬 방식:", sort);
		axios.get(`/sajo/likes/${memberNo}?sort=${sort}`)
		.then(res => {
			setWishList(res.data);
		})
		.catch(err => {
			console.error(err);
		});
	};
	
	const handleSortChange = (e) => {
		setSortType(e.target.value);
	};
	
	const handleWishDelete = (likeIdx) => {
		if(window.confirm("해당 상품의 찜을 삭제하시겠습니까?")) {
			axios.delete(`/sajo/likes/${likeIdx}`)
			.then(resp => {
				alert("삭제되었습니다.");
				getWishList(sortType);
			});
		}
	};
	
	useEffect(
		() => {
			if (memberNo) {
				getHeaderProfile();
				getWishList(sortType);
		    } else {
		        navigate('/login');
		    }
		}, [sortType]
	);
	
	return (
		<div className="Likes-wrapper">
			<header className="Likes-profile-header">
			    <div className="Likes-profile-info">
			      <span className="Likes-profileImg">{headerProfile.profileImg}</span>
			      <span className="Likes-nickname">{headerProfile.nickname} 님</span>
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
				    <select className="Likes-sort-select" onChange={handleSortChange} value={sortType}>
				      <option value="latest">최신순</option>
				      <option value="name">이름순</option>
				    </select>
				    <span className="Likes-arrow">▼</span>
				  </div>
				</div>
			
			    <div className={wishList.length > 0 ? "Likes-grid" : "Likes-list"}>
				{
					wishList.length > 0 ?
					(wishList.map(({likeIdx, itemImg, itemName, itemPrice}) => (
						<div key={likeIdx} className="Likes-item-card">
					      <div className="Likes-item-image-wrapper">
					        <img src={itemImg} alt="상품 이미지" className="Likes-item-image"/>
							<button className="Likes-wish-icon" onClick={() => handleWishDelete(likeIdx)}>
						  		<svg viewBox="0 0 24 24" height="18" width="18" xmlns="http://www.w3.org/2000/svg" fill="#f04461" stroke="#f04461" strokeWidth="1">
									<path d="m19 21-7-4-7 4V5a2 2 0 0 1 2-2h10a2 2 0 0 1 2 2v16z"></path>
								</svg>
						    </button>
					      </div>
					      <div className="Likes-item-info">
					        <p className="Likes-item-title">{itemName}</p>
					        <div className="Likes-item-price-row">
					          <span className="Likes-item-price">￦{itemPrice?.toLocaleString()}</span>
					        </div>
					      </div>
					    </div>	
					))) : (<>
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