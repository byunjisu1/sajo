import React, { useEffect, useContext } from 'react';
import { AuthContext } from '../App';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './KakaoCallBack.css';

const KakaoCallBack = () => {
	const navigate = useNavigate();
	const { setIsLogin } = useContext(AuthContext);
	const { setMemberNo } = useContext(AuthContext);
	
	useEffect(() => {
		const code = new URL(window.location.href).searchParams.get("code");
		if (code) {
			console.log("인가코드 : ", code);
			axios.get(`/sajo/oauth/kakao/callback?code=${code}`)
				.then((res) => {
					console.log(res.data);
					const kakaoUserInfo = res.data.member;
					const id = res.data.id;
					const nickname = res.data.nickname;

					console.log("1단계 유저정보:", kakaoUserInfo);
					return axios.post('/sajo/api/member/kakao', { member: kakaoUserInfo, id: id, nickname: nickname });
				})
				.then((res) => {
					const memberNo = res.data;
					console.log("memberNo : ", memberNo);
					if (memberNo) {
						setMemberNo(memberNo);
						setIsLogin(true);
						alert("카카오 로그인 성공!");
						navigate("/memberUpdate",{state: { showAlert: true }});
					}
				})
				.catch((err) => {
					console.error(err);
					alert("로그인 처리중 오류가 발생했습니다.");
				});
		}

	}, [navigate])
	return (
		<div className="kakao-loading-container">
			<div className="kakao-loading-box">
				<div className="kakao-spinner"></div>
				<h2>카카오 로그인 처리 중... 🎣</h2>
				<p>잠시만 기다려주세요!</p>
			</div>
		</div>
	);
};

export default KakaoCallBack;