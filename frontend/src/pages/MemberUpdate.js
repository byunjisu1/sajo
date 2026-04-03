import React, { useState, useEffect, useRef, useContext } from 'react';
import { AuthContext } from '../App';
import './MemberUpdate.css';
import { useNavigate, useLocation } from 'react-router-dom';
import axios from 'axios';

const MemberUpdate = () => {
	const fileInputRef = useRef(null);
	const [profileImage, setProfileImage] = useState(null);
	const [previewImage, setPreviewImage] = useState(null);
	const [member, setMember] = useState({})
	const [headerProfile, setHeaderProfile] = useState({});
	const [memberProfile, setMemberProfile] = useState({
		nickname: '',
		nameKor: '',
		nameEng: '',
		birth: '',
		email: '',
		phone: '',
		profileImg: ''
	});
	const { memberNo } = useContext(AuthContext);
	const [isSaving, setIsSaving] = useState(false);
	const navigate = useNavigate();
	const profileImg = memberProfile.profileImg?.trim();
	const location = useLocation();

	const getHeaderProfile = (memberNo) => {
		axios.get(`/sajo/member/${memberNo}`)
			.then((res) => {
				console.log("겟헤더프로필에서 : ", res.data);
				setHeaderProfile(res.data);
			})
			.catch((err) => {
				console.error(err);
			})
	};

	const getMemberUpdateProfile = (memberNo) => {
		axios.get(`/sajo/memberUpdate/${memberNo}`)
			.then((res) => {
				console.log("멤버업데이트프로필 : ", res.data);
				setMemberProfile(res.data);
			})
			.catch((err) => {
				console.error(err);
			})
	};

	const handleFileUpload = (e) => {
		const selectedFile = e.target.files[0];
		if (selectedFile) {
			setProfileImage(selectedFile);
			console.log("보관함에 저장완료 : ", selectedFile.name);
			const reader = new FileReader(); // 업로드한 사진 미리보기 객체 (파일읽기도구)
			reader.onloadend = () => {
				setPreviewImage(reader.result);
			};
			reader.readAsDataURL(selectedFile);
		}
	};

	const handleChange = (e) => {
		const { name, value } = e.target;
		setMemberProfile({ ...memberProfile, [name]: value });
	};

	useEffect(() => {
		if (!memberNo) return;

		getHeaderProfile(memberNo)
		getMemberUpdateProfile(memberNo)
		if (location.state?.showAlert) {
			setTimeout(() => {
				alert("회원정보를 입력해주세요!");

			}, 300);
		}
	}, [memberNo, location.state]);

	const handleSubmit = (e) => {
		e.preventDefault();
		if (isSaving) return;
		setIsSaving(true);;
		const formData = new FormData();
		formData.append("birth", memberProfile.birth);
		formData.append("email", memberProfile.email);
		formData.append("nameEng", memberProfile.nameEng);
		formData.append("nameKor", memberProfile.nameKor);
		formData.append("nickname", memberProfile.nickname);
		formData.append("phone", memberProfile.phone);
		if (profileImage) {
			formData.append("uploadFile", profileImage);
		}

		axios.post(`/sajo/modify/${memberNo}`, formData, {
			headers: {
				'Content-Type': 'Multipart/form-data'
			}
		})
			.then(() => {
				alert("정상적으로 수정되었습니다!");
				navigate(`/mypage`)
			})
			.catch((err) => {
				console.error(err);
			})
			.finally(() => {
				setIsSaving(false);
			})
	};

	return (
		<div className="member-update-container">
			<header className="member-update-profile">
				<div className="member-update-profile-info">
					<span className="member-update-profile-image">
						{headerProfile.profileImg ? (<img
							src={headerProfile.profileImg.startsWith('http') ? headerProfile.profileImg : `http://13.209.208.223:9090/sajo/upload/${headerProfile.profileImg}`}
							style={{ width: "100%", height: "100%", objectFit: "cover", borderRadius: "8px" }}
						/>) : (null)

						}
					</span>
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
								<input className="nickname-input" type="text" name="nickname" value={memberProfile.nickname} onChange={handleChange} required />
							</div>

							<div className="form-group">
								<div className="label-row">
									<label>이메일</label>
								</div>
								<input className="email-input" type="email" name="email" value={memberProfile.email} onChange={handleChange} required />
							</div>

							<div className="form-group">
								<label>한국어 성명</label>
								<input className="kor-input" type="text" name="nameKor" value={memberProfile.nameKor} onChange={handleChange} required />
							</div>

							<div className="form-group">
								<label>영문 성명</label>
								<input className="eng-input" type="text" name="nameEng" placeholder="영문 이름" value={memberProfile.nameEng} onChange={handleChange} required />
							</div>

							<div className="form-group">
								<label>생년월일</label>
								<input className="birth-input" type="text" name="birth" placeholder="ex)19960226" value={memberProfile.birth} onChange={handleChange} required />
							</div>

							<div className="form-group">
								<label>전화번호</label>
								<input className="phone-input" type="text" name="phone" placeholder="ex)01012345678" value={memberProfile.phone} onChange={handleChange} required />
							</div>

							<div className="form-group">
								<label>프로필 사진</label>
								<div className="profile-upload-row">
									<div className="current-avatar">
										<div className="avatar-placeholder">
											{previewImage ? (<img src={previewImage} style={{ width: "100%", height: "100%", objectFit: "cover", borderRadius: "8px" }} />)
												: (profileImg && profileImg !== 'null') ?
													(<img
														src={profileImg.startsWith('http') ? profileImg : `http://13.209.208.223:9090/sajo/upload/${profileImg}`}
														style={{ width: "100%", height: "100%", objectFit: "cover", borderRadius: "8px" }}
														alt="프로필"
														// 혹시 서버에 사진 파일이 없을 때를 대비한 엑박 방지
														referrerpolicy="no-referrer" />) : (<div style={{ display: 'flex', alignItems: 'center', justifyContent: 'center', height: '100%' }}>
															사진없음
														</div>)}
										</div>
										<button type="button" className="delete-btn">삭제</button>
									</div>
									<div className="upload-box">
										<div className="upload-icon">☁️</div>
										<p onClick={() => fileInputRef.current.click()}><span className="highlight">클릭하여 업로드</span></p>
										<input type="file" ref={fileInputRef} onChange={handleFileUpload} style={{ display: "none" }} />
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