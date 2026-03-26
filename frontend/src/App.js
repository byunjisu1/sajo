import { Route, Routes } from 'react-router-dom';
import React,{ useState } from 'react';
import Header from './components/Header';
import Footer from './components/Footer';
import Home from './pages/Home';
import Likes from './pages/Likes';
import Cart from './pages/Cart';
import Login from './pages/Login';
import Board from './pages/Board';
import BoardDetail from './pages/BoardDetail';
import BoardEdit from './pages/BoardEdit';
import BoardWrite from './pages/BoardWrite';
import Rakuten from './pages/Rakuten';
import ItemDetail from './pages/ItemDetail';
import MyPage from './pages/MyPage';
import Address from './pages/Address';
import MemberUpdate from './pages/MemberUpdate';
import Payment from './pages/Payment';

function App() {
	const [ isLogin, setIsLogin ] = useState(() => {
		const testUser = sessionStorage.getItem("member_no");
		return testUser ? true : false;
	});
  return (
    <>
		<Header isLogin={ isLogin } setIsLogin={ setIsLogin }/>
		<Routes>
			<Route path="/" element={<Home isLogin={isLogin}/>}/>
			<Route path="/likes" element={<Likes/>}/>
			<Route path="/cart" element={<Cart/>}/>
			<Route path="/login" element={<Login setIsLogin={setIsLogin}/>}/>
			<Route path="/board" element={<Board/>}/>
			<Route path="/boardDetail/:boardIdx" element={<BoardDetail/>}/>
			<Route path="/boardEdit/:boardIdx" element={<BoardEdit/>}/>
			<Route path="/boardWrite" element={<BoardWrite/>}/>
			<Route path="/rakuten" element={<Rakuten/>}/>
			<Route path="/itemDetail" element={<ItemDetail/>}/>
			<Route path="/myPage" element={<MyPage/>}/>
			<Route path="/address" element={<Address/>}/>
			<Route path="/memberUpdate" element={<MemberUpdate/>}/>
			<Route path="/payment" element={<Payment/>}/>
			<Route path="/test" element={<Home/>}/>
		</Routes>
		<Footer/>
	</>
  );
}

export default App;