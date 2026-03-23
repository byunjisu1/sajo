import React from 'react';
import { useNavigate } from 'react-router-dom';
import './Board.css';

const Board = () => {
	const navigate = useNavigate();
	
	return (
	    <div className="board-page" aria-label="문의게시판 홈">
	      <div className="board-header">
	        <div className="board-title-row">
	          <h1 className="board-title">문의게시판</h1>
	          <div className="board-write-title" aria-hidden="true">
	            <span className="board-write-pill" onClick={() => navigate('/boardWrite')}>📝 문의하기</span>
	          </div>
	        </div>

	        <div className="board-table-head" aria-hidden="true">
	          <div className="board-col-title">제목</div>
	          <div className="board-col-author">작성자</div>
	          <div className="board-col-date">날짜</div>
	        </div>
	      </div>

			<div className="board-list" role="list" aria-label="게시글 목록">
			  {[
			    { title: "[라쿠텐] 더 안전한 배송을 위한 안내", author: "SAJO 운영팀", date: "2026-02-27" },
			    { title: "[안내] 라쿠텐 상품 구매 시 유의사항", author: "SAJO 운영팀", date: "2026-01-20" },
				{ title: "[안내] 문의 등록 시 유의사항", author: "SAJO 운영팀", date: "2026-01-19" },
			    { title: "이 상품 구매 가능한가요?", author: "지수", date: "2025-10-19" },
			    { title: "구매 취소하고 싶어요", author: "지수", date: "2025-08-03" },
			    { title: "가격이 안 떠요", author: "승빈", date: "2025-07-22" },
				{ title: "여러 개 구매 희망", author: "민재", date: "2025-07-20" },
				{ title: "구매했던 내역 보고 싶어요", author: "승빈", date: "2025-07-18" }
			  ].map((post, index) => {
			    const isNotice = post.author === "SAJO 운영팀";
			    
			    return (
			      <div key={index} className="board-row" role="listitem">
			        <div className="board-title-cell">
			          <span className={`board-badge ${isNotice ? "" : "general"}`}>
			            {isNotice ? "공지" : "문의"}
			          </span>
			          <span className="board-title-text" title={post.title} onClick={() => navigate('/boardDetail')}>
			            {post.title}
			          </span>
			        </div>
			        <div className="board-meta">{post.author}</div>
			        <div className="board-meta board-date">{post.date}</div>
			      </div>
			    );
			  })}
			</div>
      	</div>
	  );
};

export default Board;