import React, { useState } from 'react';
import './AddressDetailModal.css';;


const AddressDetailModal=({isOpen, onClose, onSave})=>{
	
	const [ detail, setDetail ] = useState('');
	if(!isOpen) return null;
	
	return(
		<div className="modal-detail-address">
			<div className="modal-detail-content">
				<div className="modal-detail-header">
					<button className="x-btn" onClick={onClose}>x</button>
				</div>
				<div className="modal-detail-body">
					<h2>상세 주소를 입력해주세요.</h2>
					<div>
						<input type="text" placeholder="상세 주소를 입력해주세요." 
						className="detail-input"
						value={detail}
						onChange={(e)=>{setDetail(e.target.value)}}
						/>
						<span>도로명 주소에 맞게 입력해주세요.</span>
					</div>
				</div>
				<div className="modal-detail-footer">
					<button className="next-btn" onClick={()=>onSave(detail)}>다음</button>
				</div>
			</div>
		</div>
	);
};

export default AddressDetailModal;