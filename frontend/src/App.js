import { Route, Routes } from 'react-router-dom';
import React from 'react';
import Header from './components/Header';
import Footer from './components/Footer';
import Home from './pages/Home';
import Likes from './pages/Likes';
import Cart from './pages/Cart';
import Login from './pages/Login';
import Board from './pages/Board';
import BoardDetail from './pages/BoardDetail';
import BoardWrite from './pages/BoardWrite';
import Rakuten from './pages/Rakuten';
import ItemDetail from './pages/ItemDetail';
import MyPage from './pages/MyPage';
import Address from './pages/Address';
import MemberUpdate from './pages/MemberUpdate';

function App() {
  return (
    <>
		<Header/>
		<Routes>
			<Route path="/" element={<Home/>}/>
			<Route path="/likes" element={<Likes/>}/>
			<Route path="/cart" element={<Cart/>}/>
			<Route path="/login" element={<Login/>}/>
			<Route path="/board" element={<Board/>}/>
			<Route path="/boardDetail" element={<BoardDetail/>}/>
			<Route path="/boardWrite" element={<BoardWrite/>}/>
			<Route path="/rakuten" element={<Rakuten/>}/>
			<Route path="/itemDetail" element={<ItemDetail/>}/>
			<Route path="/myPage" element={<MyPage/>}/>
			<Route path="/address" element={<Address/>}/>
			<Route path="/memberUpdate" element={<MemberUpdate/>}/>
		</Routes>
		<Footer/>
	</>
  );
}

export default App;