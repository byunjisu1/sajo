import React from 'react';
import './MemberUpdate.css';
import { useNavigate } from 'react-router-dom';

const MemberUpdate = () => {
	const navigate = useNavigate();
	const handleChange = () => {
		
	};

	return (
		<div className="member-update-container">
			<header className="member-update-profile">
				<div className="member-update-profile-info">
					<span className="member-update-nickname">지수</span>
					<span className="member-update-user-name">변지수 님</span>
					<span className="member-update-chevron">〉</span>
				</div>
			</header>
			<div className="member-update-body">
				<aside className="member-update-sidebar">
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
				<div class="main-content">
					<div class="profile-container">
						<h2 class="page-title">회원 정보 설정 및 변경</h2>

						<form class="profile-form">
							<div class="form-group">
								<label>닉네임</label>
								<input type="text" value="배승빈" onChange={handleChange}/>
							</div>

							<div class="form-group">
								<div class="label-row">
									<label>이메일</label>
									<div class="link-group">
										<a href="#">회원탈퇴</a>
										<a href="#">이메일 변경</a>
									</div>
								</div>
								<input type="email" value="qotmdqls12@naver.com" readonly class="readonly-input"/>
							</div>

							<div class="form-group">
								<label>한국어 성명</label>
								<input type="text" value="배승빈" onChange={handleChange}/>
							</div>

							<div class="form-group">
								<label>영문 성명</label>
								<input type="text" placeholder="영문 이름" onChange={handleChange}/>
							</div>

							<div class="form-group">
								<label>생년월일</label>
								<input type="text" value="1996-02-26" readonly class="readonly-input"/>
							</div>

							<div class="form-group">
								<label>전화번호</label>
								<input type="text" value="KR ⌵ +82 010-4705-6832" readonly class="readonly-input"/>
							</div>

							<div class="form-group">
								<label>프로필 사진</label>
								<div class="profile-upload-row">
									<div class="current-avatar">
										<div class="avatar-placeholder"></div>
										<button type="button" class="delete-btn">삭제</button>
									</div>
									<div class="upload-box">
										<div class="upload-icon">☁️</div>
										<p><span class="highlight">클릭하여 업로드</span> 또는 끌어다 놓기</p>
										<p class="sub-text">PNG, JPG 또는 GIF(최대 10MB)</p>
									</div>
								</div>
							</div>

							<button type="submit" class="save-btn">변경 내용 저장</button>
						</form>
					</div>
				</div>
			</div>
		</div>


	)
};


export default MemberUpdate;