import React, { useState, useRef, useContext } from 'react';
import { AuthContext } from '../App';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './BoardWrite.css';

const BoardWrite = () => {
	const navigate = useNavigate();
	const fileInputRef = useRef(null);
	const { memberNo } = useContext(AuthContext);
	const [ selectedFiles, setSelectedFiles ] = useState([]);
	const [ isSaving, setIsSaving ] = useState(false);
	const [ board, setBoard ] = useState({title:'', writer:memberNo, content:''});
	const { title, writer, content } = board;
	
	const handleClickUploadButton = () => {
	    if (!fileInputRef.current) return;
	    fileInputRef.current.click();
	};
	
	const handleFileChange = (e) => {
		// 선택된 파일들을 배열로 변환해서 상태에 추가 (기존 파일 + 새 파일)
		const files = Array.from(e.target.files);
		    
	    if (selectedFiles.length + files.length > 5) {
	        alert("파일은 최대 5개까지 업로드 가능합니다.");
	        return;
	    }
		
		setSelectedFiles([...selectedFiles, ...files]);
	};
	  
	const handleChange = (e) => {
		const { name, value } = e.target;
		setBoard({...board, [name]: value});
	};
	
	const handleSubmit = (e) => {
		e.preventDefault();
		if(isSaving) return;
		setIsSaving(true);
		
		const formData = new FormData();
		formData.append("title", title);
		formData.append("content", content);
		formData.append("writer", writer);
		
		if(selectedFiles.length > 0) {
			selectedFiles.forEach(file => {
	            formData.append("files", file);
	        });
		}
		
		axios.post(`/sajo/board/write`, formData)
		.then(() => {
			alert("등록되었습니다.");
			navigate(`/board`);
		})
		.catch(err => {
			console.error("API err:", err);
			alert("에러 발생! 잠시 후 다시 시도해 주세요.");
		})
		.finally(() => {
			setIsSaving(false);
		});
	}
	
	return (
	    <form className="board-write" aria-label="문의 작성" onSubmit={handleSubmit}>
			<div className="board-write-header">
	          <h1 className="board-write-header-title">문의 등록</h1>
			</div>
	      <div className="board-write-group">
	        <label className="board-write-label" htmlFor="board-title">
	          문의 제목 <span className="board-required">*</span>
	        </label>
	        <div className="board-write-input-wrap">
	          <input id="board-title" name="title" value={title} onChange={handleChange} className="board-write-input" type="text" placeholder="제목을 입력해 주세요. (20자 이내)" required />
	        </div>
	      </div>

	      <div className="board-write-group">
	        <label className="board-write-label" htmlFor="board-content">
	          문의 내용 <span className="board-required">*</span>
	        </label>
	        <div className="board-write-textarea-wrap">
	          <textarea id="board-content" name="content" value={content} onChange={handleChange} className="board-write-textarea" required />
	        </div>
	      </div>

	      <div className="board-write-group">
	        <span className="board-write-label">파일 첨부</span>
	        <div className="board-write-file-row">
				<input type="file" ref={fileInputRef} onChange={handleFileChange} multiple hidden/>
	          <button type="button" className="board-write-file-btn" onClick={handleClickUploadButton}>
	            + 파일 첨부
	          </button>
	        </div>
			{selectedFiles.length > 0 && (
			    <ul className="file-list">
			        {selectedFiles.map((f, i) => (
			            <li key={i} className="file-item">
			                <span className="file-name">{f.name}</span>
			                <button type="button" className="file-remove-btn" onClick={() => setSelectedFiles(selectedFiles.filter((_, index) => index !== i))}>
			                    ✕
			                </button>
			            </li>
			        ))}
			    </ul>
			)}
	        <div className="board-write-file-note">
	          <span>첨부파일은 최대 5개까지 등록 가능합니다.</span>
	        </div>
	      </div>

	      <button type="submit" className="board-write-submit">문의등록</button>
	    </form>
	  );
};

export default BoardWrite;