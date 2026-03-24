import React, { useState,useEffect } from 'react';
import './MemberUpdate.css';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const MemberUpdate = () => {
	const [ member, setMember ] = useState({})
	const [ headerProfile, setHeaderProfile ] = useState({});
	const [ memberProfile, setMemberProfile ] = useState({nickname:'',korName:'',engName:''});
	const memberNo = sessionStorage.getItem("member_no");
	const [ isSaving, setIsSaving ] = useState(false);
	
	const getHeaderProfile=(memberNo)=>{
		axios.get(`/sajo/member/${memberNo}`)	
		.then((res)=>{
			console.log(res.data);
			setHeaderProfile(res.data);
		})
		.catch((err)=>{
			console.error(err);
		})
	};
	
	const getMemberUpdateProfile=(memberNo)=>{
		axios.get(`/sajo/memberUpdate/${memberNo}`)
		.then((res)=>{
			console.log(res.data);
			setMemberProfile(res.data);
		})
		.catch((err)=>{
			console.error(err);
		})
	};
	
	const navigate = useNavigate();
	const handleChange = (e) => {
		const { name,value } = e.target;
		setMemberProfile({...memberProfile,[name]:value});
	};
	
	useEffect(()=>{getHeaderProfile(memberNo)},[]);
	useEffect(()=>{getMemberUpdateProfile(memberNo)},[]);
	
	const handleSubmit=(e)=>{
		e.preventDefault();
		if(isSaving) return;
		setIsSaving(true);
		axios.put(`/sajo/modify/${memberNo}`, memberProfile)
		.then(()=>{
			alert("정상적으로 수정되었습니다!");
			navigate(`/mypage`)
		})
		.catch((err)=>{
			console.error(err);
		})
		.finally(()=>{
			setIsSaving(false);
		})
	};

	return (
		<div className="member-update-container">
			<header className="member-update-profile">
				<div className="member-update-profile-info">
					<span className="member-update-nickname">{headerProfile.profileImg}</span>
					<span className="member-update-user-name">{headerProfile.nickname} 님</span>
					<span className="member-update-chevron">〉</span>
				</div>
			</header>
			<div className="member-update-body">
				<aside className="member-update-sidebar">
					<div className="menu-group">
						<h3 className="menu-title">쇼핑</h3>
						<ul>
							<li onClick={() => navigate('/myPage')}>주문 내역</li>
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
				<div className="main-content">
					<div className="profile-container">
						<h2 className="page-title">회원 정보 설정 및 변경</h2>

						<form className="profile-form" onSubmit={handleSubmit}> 
							<div className="form-group">
								<label>닉네임</label>
								<input className="nickname-input" type="text" name="nickname"value={memberProfile.nickname} onChange={handleChange} />
							</div>

							<div className="form-group">
								<div className="label-row">
									<label>이메일</label>
									<div className="link-group">
										<span onClick={() => navigate(`/`)}>회원탈퇴</span>
									</div>
								</div>
								<input className="email-input" type="email" value={memberProfile.email} readOnly={true} />
							</div>

							<div className="form-group">
								<label>한국어 성명</label>
								<input className="kor-input" type="text" name="nameKor" value= {memberProfile.nameKor} onChange={handleChange} />
							</div>

							<div className="form-group">
								<label>영문 성명</label>
								<input className="eng-input" type="text" name="nameEng" placeholder="영문 이름" value={memberProfile.nameEng} onChange={handleChange} />
							</div>

							<div className="form-group">
								<label>생년월일</label>
								<input className="birth-input" type="text" value={memberProfile.birth} readOnly={true} />
							</div>

							<div className="form-group">
								<label>전화번호</label>
								<input className="phone-input" type="text" value={`KR ⌵ +82${memberProfile.phone}`} readOnly={true} />
							</div>

							<div className="form-group">
								<label>프로필 사진</label>
								<div className="profile-upload-row">
									<div className="current-avatar">
										<div className="avatar-placeholder"></div>
										<button type="button" className="delete-btn">삭제</button>
									</div>
									<div className="upload-box">
										<div className="upload-icon">☁️</div>
										<p><span className="highlight">클릭하여 업로드</span> 또는 끌어다 놓기</p>
										<p className="sub-text">PNG, JPG 또는 GIF(최대 10MB)</p>
									</div>
								</div>
							</div>

							<button type="submit" className="save-btn">변경 내용 저장</button>
						</form>
					</div>
				</div>
			</div>
		</div>


	)
};


export default MemberUpdate;