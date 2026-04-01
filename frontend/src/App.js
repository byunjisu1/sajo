import { Route, Routes } from 'react-router-dom';
import React,{ useState, createContext } from 'react';
import Header from './components/Header';
import Footer from './components/Footer';
import Loading from './components/Loading';
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
import KakaoCallBack from './components/KakaoCallBack';
import GoogleCallBack from './components/GoogleCallBack';

export const AuthContext = createContext(null);

function App() {
	const [ isLogin, setIsLogin ] = useState(false);
	const [ memberNo, setMemberNo ] = useState('');
	const login = () => setIsLogin(true);
	const logout = () => setIsLogin(false);
	
	const authContextValues = {
		isLogin,
		setIsLogin,
		memberNo,
		setMemberNo,
		login,
		logout
	};
	
  return (
    <AuthContext.Provider value={authContextValues}>
		<Header/>
		<Routes>
			<Route path="/loading" element={<Loading/>}/>
			<Route path="/" element={<Home/>}/>
			<Route path="/likes" element={<Likes/>}/>
			<Route path="/cart" element={<Cart/>}/>
			<Route path="/login" element={<Login/>}/>
			<Route path="/board" element={<Board/>}/>
			<Route path="/boardDetail/:boardIdx" element={<BoardDetail/>}/>
			<Route path="/boardEdit/:boardIdx" element={<BoardEdit/>}/>
			<Route path="/boardWrite" element={<BoardWrite/>}/>
			<Route path="/rakuten/:searchValue?" element={<Rakuten/>}/>
			<Route path="/itemDetail/:itemIdx" element={<ItemDetail/>}/>
			<Route path="/myPage" element={<MyPage/>}/>
			<Route path="/address" element={<Address/>}/>
			<Route path="/memberUpdate" element={<MemberUpdate/>}/>
			<Route path="/payment" element={<Payment/>}/>
			<Route path="/test" element={<Home/>}/>
			<Route path="/oauth/kakao/callback" element={<KakaoCallBack/>}/>
			<Route path="/oauth/google/callback" element={<GoogleCallBack/>}/>
		</Routes>
		<Footer/>
	</AuthContext.Provider>
  );
}

export default App;