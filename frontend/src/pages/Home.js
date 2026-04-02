import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import Loading from '../components/Loading';
import './Home.css';

const Home = () => {
	const { searchValue } = useParams();
	const [loading, setLoading] = useState(false); // 로딩 상태
	const navigate = useNavigate();
	
	useEffect(() => {
		if(searchValue) {
			setLoading(true);
			axios.post('/sajo/item/url/search', { searchUrl: searchValue })
			.then((res) => {
				console.log("서버 응답 데이터:", res.data);
				setLoading(false);
				const itemIdx = res.data;
		        navigate(`/itemDetail/${itemIdx}`);
			})
			.catch(err => {
				console.error("검색 전송 중 오류 발생:", err);
		        alert("서버와 통신 중 오류가 발생했습니다.");
				setLoading(false);
			});
		} else {
			setLoading(false);
		}
	}, [searchValue]);
	
	if (loading) {
		return <Loading />;
  	}
	
	return (
	    <div className="sajo-page">
	      <main className="home-main" aria-label="홈 메인">
		  
	        <section className="home-banner-row" aria-label="메인 배너">
	          <div className="home-banner-shell">
	            <article className="home-banner-card" aria-label="개인통관 안내 배너">
	              <div className="home-banner-inner">
	                <div className="home-banner-text">
	                  <h2 className="home-banner-title">
	                    저희는 프로젝트 '사줘' 팀 입니다.
	                  </h2>
	                  <p className="home-banner-subtitle">
	                  	React와 Spring Boot를 이용해서 열심히 만든 프로젝트입니다.<br/>
						예쁘게 봐주세요! 😉
	                  </p>
	                </div>
	              </div>
	            </article>
	          </div>
	        </section>

	        <section className="home-intro" aria-label="소개 문구">
	          <div className="home-intro-line" aria-hidden="true" />
	          <h2 className="home-intro-title">일본 직구, 0원으로 시작하세요</h2>
	          <p className="home-intro-desc">
	            대행수수료 0원 + 첫 국제 배송비 0원 혜택으로 부담 없이 시작
	          </p>
	          <p className="home-intro-desc">추가 비용 걱정 없이 그대로 구매하세요.</p>
	        </section>
	      </main>
	    </div>
	  );
};

export default Home;