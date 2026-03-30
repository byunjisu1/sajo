import React, { useEffect } from 'react';


const KakaoCallBack = () => {
	useEffect(() => {
		const code = new URL(window.location.href).searchParams.get("code");
		if(code){
			console.log("인가코드 : ",code);
			alert("인가코드를 뽑아냈습니다!");	
		}
		
	},[])
	return (
		<div>
			<h2>카카오 로그인 처리 중... 🎣</h2>
			<p>잠시만 기다려주세요!</p>
		</div>
	);
};

export default KakaoCallBack;