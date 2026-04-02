import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import './Rakuten.css';
import Loading from '../components/Loading';

const Rakuten = () => {
	const navigate = useNavigate();
	const { searchValue } = useParams();
	const finalSearchValue = searchValue || "売れ筋ランキング";
	const [ products, setProducts ] = useState([]); // 서버 데이터를 담을 그릇
	const [loading, setLoading] = useState(false); // 로딩 상태
	
	const handleItemDetail = (itemUrl) => {
		setLoading(true);
		axios.post('/sajo/item/url/search', { searchUrl: itemUrl })
		.then((res) => {
			console.log("서버 응답 데이터:", res.data);
			setLoading(false);
			const itemIdx = res.data;
	        navigate(`/itemDetail/${itemIdx}`);
		})
		.catch(err => {
			console.error("검색 전송 중 오류 발생:", err);
	        alert("서버와 통신 중 오류가 발생했습니다.");
			setLoading(false);
		});
	};
	
	useEffect(() => {
		setLoading(true);
		axios.post(`/sajo/item/keyword/search/${finalSearchValue}`)
		.then((res) => {
			console.log("서버 응답 데이터:", res.data);
			setProducts(res.data);
			setLoading(false);
		})
		.catch(err => {
			console.error("검색 전송 중 오류 발생:", err);
	        alert("서버와 통신 중 오류가 발생했습니다.");
			setLoading(false);
		});
	}, [finalSearchValue]);
	
	if (loading) {
		return <Loading />;
  	}

    return (
        <div className="rk-rakuten-page">
            <div className="rk-rakuten-container">
				<section className="rk-product-section">
		            <div className="rk-section-header">
		                <h3 className="rk-section-title">{searchValue ? `라쿠텐 '${searchValue}' 검색 결과` : "라쿠텐 인기 상품"}</h3>
		            </div>
		            
		            <div className="rk-product-grid">
		                {products.map((product, index) => (
		                    <div className="rk-product-card" key={index} onClick={() => handleItemDetail(product.itemUrl)}>
		                        <div className="rk-product-img-box">
		                            <img src={product.itemImg} alt={product.itemName} />
		                        </div>
		                        <div className="rk-product-info">
		                            <p className="rk-item-name">{product.itemName}</p>
		                            <p className="rk-item-price">₩{product.itemPrice.toLocaleString()}</p>
		                        </div>
		                    </div>
		                ))}
		            </div>
		        </section>
            </div>
        </div>
    );
};

export default Rakuten;