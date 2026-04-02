import React from 'react';
import './Loading.css';

const LoadingPage = () => {
  return (
    <div className="loading-container">
      <div className="loading-content">
        
        <div className="box-section">
          <div className="box-visual">
            <div className="box-tape">
              <span className="tape-text">SAJO SAJO SAJO SAJO SAJO SAJO SAJO SAJO</span>
            </div>
            <div className="box-logo">SAJO</div>
          </div>
          <div className="box-shadow"></div>
        </div>

        <div className="text-section">
          <p className="status-text">잠시만 기다려주세요...</p>
          <h1 className="main-title">
            고객님을 위한<br />
            상품 정보를 가져오는 중입니다!<br/>
			URL 검색의 경우 약 1분의 시간이 소요될 수 있습니다.
          </h1>
          
          <div className="tip-box">
            <span className="tip-badge">✨ TIP</span>
            <p className="tip-desc">
              일본 상품의 URL을 직접 입력하여 상품을 확인할 수<br /> 있습니다.
            </p>
          </div>
        </div>

      </div>
    </div>
  );
};

export default LoadingPage;