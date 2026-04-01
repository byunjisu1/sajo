import React, { useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from '../App';
import axios from 'axios';
import './Login.css';
import { KAKAO_AUTH_URL } from '../components/KakaoConfig';
const Login = () => {
	const { setIsLogin, setMemberNo } = useContext(AuthContext);
	
	const handleKakaoLogin=()=>{
		window.location.href = KAKAO_AUTH_URL;	
	};
	
	const handleGoogleLogin = async () => {
	    const response = await axios.get("/sajo/google/url");
	    window.location.href = response.data; // 받은 주소로 이동
	};
	
	const navigate = useNavigate();
	const handleTestLogin = () => {
		setMemberNo('1');
		setIsLogin(true);
		alert("테스트 계정으로 로그인 되었습니다.");
		navigate("/");
	};
	return (
		<>
			<div className="login-wrapper">
				<nav className="login-container">
					<h2>로그인<br />또는 회원가입</h2>
					<p>로그인 방법을 선택하세요</p>
				</nav>
				<div className="btn-container">
					<button className="login-btn kakao" onClick={handleKakaoLogin}>
						<span className="icon">💬</span> 카카오로 계속하기
					</button>

					<button className="login-btn google" onClick={handleGoogleLogin}>
						<span className="icon">G</span> 구글로 계속하기
					</button>
					<button className="login-btn test" onClick={handleTestLogin}>
						<span className="icon">T</span> 테스트 로그인버튼
					</button>
				</div>
			</div>
		</>
	);
};

export default Login;