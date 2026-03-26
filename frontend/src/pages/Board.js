import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './Board.css';

const Board = () => {
	const navigate = useNavigate();
	const [ boardList, setBoardList ] = useState([]);
	const [ page, setPage ] = useState(1);
	const [ lastPageNumber, setLastPageNumber ] = useState(0);
	
	const getBoardList = (page) => {
		axios.get(`/sajo/board/list/${page}`)
		.then(res => {
			console.log(res.data);
			setBoardList(res.data.list);
			setLastPageNumber(res.data.lastPageNumber);
		})
		.catch(err => {
			console.error(err);
		});
	};
	
	useEffect(() => {
		getBoardList(page);
	}, [page]);
	
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
			  {boardList.map(({boardIdx, title, nickname, boardDate}) => {
			    const isNotice = nickname === "SAJO 운영팀";
			    
			    return (
			      <div key={boardIdx} className="board-row" role="listitem">
			        <div className="board-title-cell">
			          <span className={`board-badge ${isNotice ? "" : "general"}`}>
			            {isNotice ? "공지" : "문의"}
			          </span>
			          <span className="board-title-text" title={title} onClick={() => navigate(`/boardDetail/${boardIdx}`)}>
			            {title}
			          </span>
			        </div>
			        <div className="board-meta">{nickname}</div>
			        <div className="board-meta board-date">{boardDate}</div>
			      </div>
			    );
			  })}
			</div>
			
			<div className="board-pagination">
		        <button className="paging-btn" onClick={() => setPage(p => Math.max(1, p-1))} disabled={page === 1}>이전</button>
		        <span className="current-page">{page} 페이지</span>
		        <button className="paging-btn" onClick={() => setPage(p => p+1)} disabled={page+1 > lastPageNumber}>다음</button>
		    </div>
      	</div>
	  );
};

export default Board;