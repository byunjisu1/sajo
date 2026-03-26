import React, { useState, useEffect } from 'react';
import './Address.css';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import AddressModal from '../components/AddressModal';
import AddressDetailModal from '../components/AddressDetailModal';

const Address = () => {

	const [isModalOpen, setIsModalOpen] = useState(false);
	const [isDetailModalOpen, setIsDetailModalOpen] = useState(false);
	const [selectedAddr, setSelectedAddr] = useState({ zonecode: '', address: '' }); // 상세주소 입력 전 임시 주소 
	const [addressList, setAddressList] = useState([]); // 최종 배송지 주소가 담길 state 

	const [ memberProfile, setMemberProfile ] = useState({nameKor:'',phone:''});
	const [ headerProfile, setHeaderProfile ] = useState({});
	
	const memberNo = sessionStorage.getItem("member_no");

	const handleAddressData = (data) => {
		console.log("여기서 첫번째 함수 실행");
		console.log(data.zonecode);
		console.log(data.address);
		setSelectedAddr({ zonecode: data.zonecode, address: data.address });
		setIsModalOpen(false);
		setIsDetailModalOpen(true);
	};

	const handleSaveAddress = (detail) => {
		console.log("여기서 두번째 함수 실행");
		
		const newAddress = {
			addressMemberNo: memberNo,
			postCode: selectedAddr.zonecode,
			address: `${selectedAddr.address}${detail}`
		};
		axios.post(`/sajo/addressInsert/${memberNo}`, newAddress)
		.then((res)=>{
			console.log(res.data);
			setAddressList([...addressList, res.data]);
			setIsDetailModalOpen(false);
		})
		.catch((err)=>{
			console.error("에러!")
			alert("주소 저장중 에러가 발생했습니다!");
		});
	};

	const getHeaderProfile = () => {
		axios.get(`/sajo/member/${memberNo}`)
			.then((res) => {
				console.log(res.data);
				setHeaderProfile(res.data);
			})
			.catch((err) => {
				console.error(err);
			})
	};
	
	const getMemberUpdateProfile=(memberNo)=>{
			axios.get(`/sajo/memberUpdate/${memberNo}`)
			.then((res)=>{
				console.log(res.data);
				setMemberProfile(res.data);
			})
			.catch((err)=>{
				console.error(err);
			})
		};
	useEffect(()=>{getMemberUpdateProfile(memberNo)},[]);
	useEffect(() => { getHeaderProfile(memberNo) }, []);
	useEffect(()=>{
		const getAddressList=()=>{
			axios.get(`/sajo/getAddressList/${memberNo}`)
			.then((res)=>{
				setAddressList(res.data);
			})
			.catch((err)=>{
				console.error("데이터 가져오기 실패",err);
			});
		};
		if(memberNo){ getAddressList(); }  
	},[memberNo]);
	
	const navigate = useNavigate();
	return (
		<div className="address-container">
			<header className="address-profile">
				<div className="address-profile-info">
					<span className="address-nickname">{headerProfile.profileImg}</span>
					<span className="address-user-name">{headerProfile.nickname} 님</span>
					<span className="address-chevron">〉</span>
				</div>
			</header>
			<div className="address-body">
				<aside className="address-sidebar">
					<div className="menu-group">
						<h3 className="menu-title">쇼핑</h3>
						<ul>
							<li onClick={() => navigate('/myPage')} className="active">주문 내역</li>
						</ul>
					</div>
					<div className="menu-group">
						<h3 className="menu-title">설정</h3>
						<ul>
							<li onClick={() => navigate(`/memberUpdate`)}>회원 정보 수정</li>
							<li onClick={() => navigate(`/address`)}>배송지 관리</li>
						</ul>
					</div>
				</aside>
				<div className="address-update-container">
					<h2 className="page-title">주소 추가 및 변경</h2>

					<div className="address-item">
						<div className="address-info">
							{addressList.length === 0 ? (
								
								
								<div className="empty-address"><p>저장된 주소가 없습니다.</p></div>
						
								) : (
								addressList.map((item) => (
									<div key={item.addressIdx} className="address-list-container">								
										<div className="user-contact">
											<span className="user-name">{memberProfile.nameKor}</span>
											<span className="divider">/</span>
											<span className="phone">{memberProfile.phone}</span>
										</div>
			
										<p className="address-text">
											({item.postCode})<br />
											{item.address}
										</p>
										<div className="address-actions">
											<button className="btn-outline">삭제</button>
											<button className="btn-dark">수정</button>
										</div>
									</div>))
							)}	
						 
						</div>

						
					</div>

					<hr className="section-divider" />

					<div className="add-address-wrapper">
						<button className="add-address-btn" onClick={() => setIsModalOpen(true)}>
							<span className="plus-icon">+</span> 배송지 추가
						</button>

						<AddressModal
							isOpen={isModalOpen}
							onClose={() => setIsModalOpen(false)}
							onComplete={handleAddressData}
						/>
						<AddressDetailModal
							isOpen={isDetailModalOpen}
							onClose={() => setIsDetailModalOpen(false)}
							onSave={handleSaveAddress}
						/>
					</div>
				</div>
			</div>
		</div>
	);
};

export default Address;