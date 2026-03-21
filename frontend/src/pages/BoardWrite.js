import React from 'react';
import './BoardWrite.css';

const BoardWrite = () => {
	return (
	    <section className="board-write" aria-label="문의 작성">
			<div className="board-write-header">
	          <h1 className="board-write-header-title">문의 등록</h1>
			</div>
	      <div className="board-write-group">
	        <label className="board-write-label" htmlFor="board-title">
	          문의 제목 <span className="board-required">*</span>
	        </label>
	        <div className="board-write-input-wrap">
	          <input id="board-title" className="board-write-input" type="text" placeholder="제목을 입력해 주세요. (20자 이내)" readOnly />
	          <span className="board-write-count">
	            <strong>0</strong> / 20
	          </span>
	        </div>
	      </div>

	      <div className="board-write-group">
	        <label className="board-write-label" htmlFor="board-content">
	          문의 내용 <span className="board-required">*</span>
	        </label>
	        <div className="board-write-textarea-wrap">
	          <textarea id="board-content" className="board-write-textarea" readOnly />
	          <span className="board-write-count board-write-count--textarea">
	            <strong>75</strong> / 300
	          </span>
	        </div>
	      </div>

	      <div className="board-write-group">
	        <span className="board-write-label">파일 첨부</span>
	        <div className="board-write-file-row">
	          <button type="button" className="board-write-file-btn">
	            + 파일 첨부
	          </button>
	        </div>
	        <div className="board-write-file-note">
	          <span>첨부파일은 최대 5개, 30MB까지 등록 가능합니다.</span>
	          <span className="board-write-file-size">
	            <strong>0MB</strong> / 30MB
	          </span>
	        </div>
	      </div>

	      <button type="button" className="board-write-submit">
	        문의등록
	      </button>
	    </section>
	  );
};

export default BoardWrite;