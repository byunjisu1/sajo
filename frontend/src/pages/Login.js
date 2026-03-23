import React from 'react';
import { useNavigate } from 'react-router-dom';
import './Login.css';
const Login = ({ setIsLogin }) => {
	const navigate = useNavigate();
	const handleTestLogin = () => {
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
					<button className="login-btn kakao" onClick={() => navigate(`/kakao`)}>
						<span className="icon">💬</span> 카카오로 계속하기
					</button>

					<button className="login-btn google" onClick={() => navigate(`/google`)}>
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