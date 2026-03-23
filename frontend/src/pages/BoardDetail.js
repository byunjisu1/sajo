import React from 'react';
import { useNavigate } from 'react-router-dom';
import './BoardDetail.css';

const BoardDetail = () => {
	const navigate = useNavigate();
	
	return (
	    <section className="board-detail-page" aria-label="게시글 상세보기">
	      <header className="board-detail-header">
	        <span className="board-detail-badge">공지사항</span>
	        <h1 className="board-detail-title">[라쿠텐] 더 안전한 배송을 위한 안내</h1>
			<div className="board-detail-writer">SAJO 운영팀</div>
	        <div className="board-detail-date">2026.02.27</div>
	      </header>

	      <div className="board-detail-scroll">
		  	<p>
			안녕하세요, [사줘]입니다.<br/>
			따뜻한 봄바람이 불어오는 설레는 계절, 저희 [사줘]와 함께해 주셔서 감사합니다! 🌸 <br/>
			고객님께서 [사줘]에서 경험하신 기분 좋은 봄맞이 쇼핑 이야기를 들려주세요.<br/>
			이용하시는 커뮤니티나 개인 SNS에 자발적으로 솔직한 후기를 남겨주시면, [사줘]에서 사용 가능한 쿠폰을 선물로 드립니다.<br/>
			플랫폼이 다를 경우 중복 지급도 가능하니 이 기회를 놓치지 마세요!<br/>
			어쩌구<br/>
			저쩌구<br/>
			샬라샬라<br/>
			으아아아<br/>
			ㅇㅇㅇㅇ
			</p>
	      </div>
		  
		  <button className="board-detail-back" onClick={() => navigate('/board')}>목록으로 돌아가기</button>
	    </section>
	  );
};

export default BoardDetail;