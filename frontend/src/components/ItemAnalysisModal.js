import React from 'react';
import './ItemAnalysisModal.css';

const ItemAnalysisModal = ({ isOpen, onClose, data }) => {
	if(!isOpen || !data) return null;
	
	return (
		<div className="item-modal-overlay" onClick={onClose}>
	      <div className="item-modal-content" onClick={(e) => e.stopPropagation()}>
	        <div className="item-modal-header">
	          <h2>🔍 AI 가품 분석 결과</h2>
	          <button className="close-btn" onClick={onClose}>&times;</button>
	        </div>
	        <div className="item-modal-body">
	          <div className="result-badge">
	            <strong>📊 가품 의심 정도 : </strong> 
	            <span className={data.isCounterfeit.includes('높음') ? 'danger' : 'safe'}>
	              {data.isCounterfeit}
	            </span>
	          </div>
	     
	          <div className="info-section">
	            <h4>🧐 주요 분석 : </h4>
	            <p>{data.reason}</p>
	          </div>
	
	          <div className="info-section">
	            <h4>⚠️ 주의해야 할 점 : </h4>
	            <ul>
	              {data.riskPoints && data.riskPoints.map((point, i) => (
	                <li key={i}>{point}</li>
	              ))}
	            </ul>
	          </div>
	
	          <div className="info-section advice-box">
	            <h4>💡 조언 : </h4>
	            <p>{data.advice}</p>
	          </div>
	        </div>
	        <div className="item-modal-footer">
	          <button className="confirm-btn" onClick={onClose}>확인</button>
	        </div>
	      </div>
	    </div>
	);
};

export default ItemAnalysisModal;