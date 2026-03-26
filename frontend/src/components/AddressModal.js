import React, { useState } from 'react';
import DaumPostcode from 'react-daum-postcode';
import './AddressModal.css';



const AddressModal = ({isOpen, onClose, onComplete}) => {
	if (!isOpen) return null;
	return(
		<div className="modal-address">
			<div className="modal-content">
				<div className="modal-header">
					<span>주소 검색</span>
					<button className="x-btn" onClick={onClose}>x</button>
				</div>
				<div className="modal-body">
					<DaumPostcode
						onComplete={onComplete}
						style={{height: '450px' }}
					/>
				</div>
			</div>
		</div>	
	);
};
export default AddressModal;