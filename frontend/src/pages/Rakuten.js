import React from 'react';
import './Rakuten.css';

// 상품 섹션 컴포넌트
const ProductSection = ({ title, products }) => {
    return (
        <section className="rk-product-section">
            <div className="rk-section-header">
                <h3 className="rk-section-title">{title}</h3>
                <span className="rk-section-side-more">전체보기</span>
            </div>
            
            <div className="rk-product-grid">
                {products.map((product, index) => (
                    <div className="rk-product-card" key={index}>
                        <div className="rk-product-img-box">
                            <img src={product.img} alt={product.name} />
                            <button className="rk-wish-icon"><svg stroke="currentColor" fill="none" stroke-width="2" viewBox="0 0 24 24" stroke-linecap="round" stroke-linejoin="round" class="card-bookmark-icon fill-blackopacity-100 hover:fill-blackopacity-200" height="24" width="24" xmlns="http://www.w3.org/2000/svg"><path d="m19 21-7-4-7 4V5a2 2 0 0 1 2-2h10a2 2 0 0 1 2 2v16z"></path></svg></button>
                        </div>
                        <div className="rk-product-info">
                            <p className="rk-item-name">{product.name}</p>
                            <p className="rk-item-price">{product.price}</p>
                        </div>
                    </div>
                ))}
            </div>

            <div className="rk-more-btn-container">
                <button className="rk-section-bottom-more">더보기</button>
            </div>
        </section>
    );
};

const Rakuten = () => {
    // 1. 피규어 데이터 (8개 개별 설정)
    const figures = [
        { name: '피규어 A', price: '₩32,534', img: 'https://assets.mercari-shops-static.com/-/small/plain/e3YmLUcDjsfMBvT3x3kX9c.webp@jpg' },
        { name: '피규어 B', price: '₩45,000', img: 'https://assets.mercari-shops-static.com/-/small/plain/NujUqwcd2TWWgnbUaYttjM.webp@jpg' },
        { name: '피규어 C', price: '₩28,900', img: 'https://assets.mercari-shops-static.com/-/small/plain/xG9TGKZRhnazUW8rmjkTKP.webp@jpg' },
        { name: '피규어 D', price: '₩51,200', img: 'https://assets.mercari-shops-static.com/-/small/plain/88cDFwfRUBDSvDTEZtqg5j.webp@jpg' },
        { name: '피규어 E', price: '₩12,000', img: 'https://assets.mercari-shops-static.com/-/small/plain/88cDFwfRUBDSvDTEZtqg5j.webp@jpg' },
        { name: '피규어 F', price: '₩39,800', img: 'https://assets.mercari-shops-static.com/-/small/plain/88cDFwfRUBDSvDTEZtqg5j.webp@jpg' },
        { name: '피규어 G', price: '₩67,000', img: 'https://assets.mercari-shops-static.com/-/small/plain/88cDFwfRUBDSvDTEZtqg5j.webp@jpg' },
        { name: '피규어 H', price: '₩22,500', img: 'https://assets.mercari-shops-static.com/-/small/plain/88cDFwfRUBDSvDTEZtqg5j.webp@jpg' },
    ];

    // 2. 신발 데이터 (8개 개별 설정)
    const shoes = [
        { name: '나이키 에어포스', price: '₩139,000', img: 'https://static.mercdn.net/thumb/item/jpeg/m53499847568_1.jpg?1745146310' },
        { name: '아디다스 슈퍼스타', price: '₩109,000', img: 'https://static.mercdn.net/thumb/item/jpeg/m23166969902_1.jpg?1756722470' },
        { name: '뉴발란스 530', price: '₩129,000', img: 'https://static.mercdn.net/thumb/item/jpeg/m53499847568_1.jpg?1745146310' },
        { name: '컨버스 척테일러', price: '₩95,000', img: 'https://static.mercdn.net/item/detail/orig/photos/m23166969902_1.jpg?1756722470' },
        { name: '반스 올드스쿨', price: '₩79,000', img: 'https://static.mercdn.net/thumb/item/jpeg/m23166969902_1.jpg?1756722470' },
        { name: '조던 1 로우', price: '₩159,000', img: 'https://static.mercdn.net/thumb/item/jpeg/m45716130573_1.jpg?1774188217' },
        { name: '아식스 젤카야노', price: '₩179,000', img: 'https://static.mercdn.net/thumb/item/jpeg/m45716130573_1.jpg?1774188217' },
        { name: '리복 클럽C', price: '₩89,000', img: 'https://static.mercdn.net/thumb/item/jpeg/m23166969902_1.jpg?1756722470' },
    ];

    // 3. 화장품 데이터 (8개 개별 설정)
    const cosmetics = [
        { name: '에스티로더 갈색병', price: '₩157,000', img: 'https://thumbnail.image.rakuten.co.jp/@0_mall/sonagimart/cabinet/12469757/aaadc1984014.jpg?_ex=300x300' },
        { name: '디올 립 글로우', price: '₩48,000', img: 'https://thumbnail.image.rakuten.co.jp/@0_mall/joshin-cddvd/cabinet/040/vdcd-6852.jpg?_ex=300x300' },
        { name: 'SK-II 피테라 에센스', price: '₩210,000', img: 'https://thumbnail.image.rakuten.co.jp/@0_mall/guruguru2/cabinet/904/wpcl-12947.jpg?_ex=300x300' },
        { name: '샤넬 넘버5', price: '₩190,000', img: 'https://static.mercdn.net/item/detail/orig/photos/m23166969902_1.jpg?1756722470' },
        { name: '키엘 수분크림', price: '₩65,000', img: 'https://static.mercdn.net/thumb/item/jpeg/m45716130573_1.jpg?1774188217' },
        { name: '설화수 윤조에센스', price: '₩120,000', img: 'https://static.mercdn.net/thumb/item/jpeg/m23166969902_1.jpg?1756722470' },
        { name: '랑콤 제니피끄', price: '₩145,000', img: 'https://static.mercdn.net/thumb/item/jpeg/m23166969902_1.jpg?1756722470' },
        { name: '헤라 블랙쿠션', price: '₩55,000', img: 'https://static.mercdn.net/thumb/item/jpeg/m45716130573_1.jpg?1774188217' },
    ];

    return (
        <div className="rk-rakuten-page">
            <div className="rk-rakuten-container">
                <ProductSection title="실시간 인기 피규어" products={figures} />
                <ProductSection title="지금 핫한 스니커즈" products={shoes} />
                <ProductSection title="놓치면 후회할 뷰티템" products={cosmetics} />
            </div>
        </div>
    );
};

export default Rakuten;