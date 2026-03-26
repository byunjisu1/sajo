import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import './BoardDetail.css';

const BoardDetail = () => {
	const navigate = useNavigate();
	const { boardIdx } = useParams();
	const [ board, setBoard ] = useState({});
	const getBoard = async() => {
		const resp = await axios.get(`/sajo/board/${boardIdx}`);
		setBoard(resp.data);
	};
	const memberNo = sessionStorage.getItem("member_no");
	
	useEffect(() => {
		getBoard();
	}, [])
	
	return (
	    <section className="board-detail-page" aria-label="게시글 상세보기">
	      <header className="board-detail-header">
	        <span className="board-detail-badge">게시글 상세보기</span>
	        <h1 className="board-detail-title">{board.title}</h1>
			<div className="board-detail-writer">{board.nickname}</div>
	        <div className="board-detail-date">{board.boardDate}</div>
	      </header>

	      <div className="board-detail-scroll">
		  	<p>{board.content}</p>
            {board.fileList && board.fileList.length > 0 && (
                <div className="board-detail-images">
					{board.fileList.map((file, index) => (
	                      <div key={index} className="detail-image-item">
	                          <img 
	                              src={`http://localhost:9090/sajo${file.fileUrl}`} 
	                              alt={file.originName} 
	                              className="detail-view-img"
	                               
	                          />
	                      </div>
	                  ))}
                </div>
            )}
	      </div>
		  <div className="board-detail-buttons">
			  <button className="board-detail-back" onClick={() => navigate('/board')}>목록으로 돌아가기</button>
			  {memberNo && Number(memberNo) === board.memberNo && (
	              <button className="board-detail-edit" onClick={() => navigate(`/boardEdit/${boardIdx}`)}>수정하기</button>
	          )}
		  </div>
	    </section>
	  );
};

export default BoardDetail;