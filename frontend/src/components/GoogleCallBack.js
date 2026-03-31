import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const GoogleCallback = ({ setIsLogin }) => {
    const navigate = useNavigate();

    useEffect(() => {
        // URL 주소창에서 구글이 보낸 'code' 파라미터 추출
        const params = new URLSearchParams(window.location.search);
        const code = params.get('code');

        if (code) {
            // 추출한 코드를 백엔드 컨트롤러로 전달
            axios.post('/sajo/api/v1/auth/google', { code: code })
                .then((response) => {
                    console.log("로그인 성공!", response.data);
                    
                    // 세션 스토리지 등에 필요한 정보 저장 (선택 사항)
                    sessionStorage.setItem("member_no", response.data.memberNo);
					// 프로필 이미지 저장
				    if (response.data.profileImg) {
				        sessionStorage.setItem("profile_img", response.data.profileImg);
				    }
				    // 이름 저장
				    if (response.data.nameKor) {
				        sessionStorage.setItem("user_name", response.data.nameKor);
				    }
                
                    // 로그인 상태 변경 및 메인 페이지로 이동
                    setIsLogin(true);
                    alert(`${response.data.nickname}님, 환영합니다!`);
                    navigate('/memberUpdate');
                })
                .catch((error) => {
                    console.error("로그인 처리 중 에러 발생:", error);
                    alert("로그인에 실패했습니다.");
                    navigate('/login');
                });
        } else {
            alert("인증 코드가 없습니다.");
            navigate('/login');
        }
    }, [navigate, setIsLogin]);

    return (
        <div className="callback-container">
            <p>로그인 중입니다. 잠시만 기다려주세요...</p>
        </div>
    );
};

export default GoogleCallback;