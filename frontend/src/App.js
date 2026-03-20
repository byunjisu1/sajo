import { Route, Routes } from 'react-router-dom';
import React from 'react';
import Header from './components/Header';
import Footer from './components/Footer';
import Home from './pages/Home';
import Likes from './pages/Likes';
import Cart from './pages/Cart';
import Login from './pages/Login';
import Board from './pages/Board';
import Rakuten from './pages/Rakuten';

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
			<Route path="/rakuten" element={<Rakuten/>}/>
		</Routes>
		<Footer/>
	</>
  );
}

export default App;