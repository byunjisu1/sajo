package com.myspringboot.sajo;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.myspringboot.sajo.bell.Bell;
import com.myspringboot.sajo.bell.BellMsg;
import com.myspringboot.sajo.bell.BellMsgRepository;
import com.myspringboot.sajo.bell.BellRepository;
import com.myspringboot.sajo.board.Board;
import com.myspringboot.sajo.board.BoardRepository;
import com.myspringboot.sajo.cart.Cart;
import com.myspringboot.sajo.cart.CartRepository;
import com.myspringboot.sajo.item.Item;
import com.myspringboot.sajo.item.ItemRepository;
import com.myspringboot.sajo.likes.Likes;
import com.myspringboot.sajo.likes.LikesRepository;
import com.myspringboot.sajo.member.Member;
import com.myspringboot.sajo.member.MemberDto;
import com.myspringboot.sajo.member.MemberRepository;
import com.myspringboot.sajo.member.MemberService;
import com.myspringboot.sajo.member.MemberUpdateDto;
import com.myspringboot.sajo.order.OrderDetail;
import com.myspringboot.sajo.order.OrderDetailRepository;
import com.myspringboot.sajo.order.Orders;
import com.myspringboot.sajo.order.OrdersRepository;
import com.myspringboot.sajo.search.Search;
import com.myspringboot.sajo.search.SearchRepository;

@SpringBootTest
public class MemberTests {
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private OrdersRepository ordersRepo;
	@Autowired
	private OrderDetailRepository oDetailRepo;
	@Autowired
	private LikesRepository likesRepo;
	@Autowired
	private CartRepository cartRepo;
	@Autowired
	private SearchRepository searchRepo;
	@Autowired
	private BellRepository bellRepo;
	@Autowired
	private BellMsgRepository bellMsgRepo;
	@Autowired
	private ItemRepository itemRepo;
	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private MemberService mSvc;
	
	/**
	 * testInsertMember : Member 테이블 더미데이터 추가
	 */
	@Test
	void testInsertMember() {
		Member m = new Member();
		m.setNickname("김민재");
		m.setEmail("lim82378@gmail.com");
		m.setNameKor("김민재");
		m.setNameEng("KIMMINJAE");
		m.setBirth("20000402");
		m.setPhone("01038218237");
		memberRepo.save(m);
		
		m = new Member();
		m.setNickname("지수");
		m.setEmail("0902jisu@naver.com");
		m.setNameKor("변지수");
		m.setNameEng("BYUNJISU");
		m.setBirth("20020902");
		m.setPhone("01071879350");
		memberRepo.save(m);
		
		m = new Member();
		m.setNickname("배승빈");
		m.setEmail("seungbin4369@gmail.com");
		m.setNameKor("배승빈");
		m.setNameEng("BAESEUNGBIN");
		m.setBirth("19960226");
		m.setPhone("01047056832");
		memberRepo.save(m);
	}
	
	
	/**
	 * testInsertOrders : ORDERS 테이블 더미데이터 추가
	 */
	@Test
	void testInsertOrders() {
		Member m = memberRepo.findById(1).get();
		Orders o = new Orders();
		o.setOrderNo("202603161111");
		o.setOrdersMemberNo(m);
		o.setTotalPrice(520000);
		o.setOrderDate(LocalDateTime.now());
		o.setOrderStatus("order_ready");
		ordersRepo.save(o);
		
		m = memberRepo.findById(2).get();
		o = new Orders();
		o.setOrderNo("202603161112");
		o.setOrdersMemberNo(m);
		o.setTotalPrice(730000);
		o.setOrderDate(LocalDateTime.now());
		o.setOrderStatus("order_completed");
		ordersRepo.save(o);
		
		m = memberRepo.findById(3).get();
		o = new Orders();
		o.setOrderNo("202603161113");
		o.setOrdersMemberNo(m);
		o.setTotalPrice(1775000);
		o.setOrderDate(LocalDateTime.now());
		o.setOrderStatus("order_completed");
		ordersRepo.save(o);
	}
	
	/**
	 * testInsertOrderDetail : Order_detail 테이블 더미데이터 추가
	 */
	@Test
	void testInsertOrderDetail() {
		Orders o = ordersRepo.findById("202603161111").get();
		Item i = itemRepo.findById(1).get();
		OrderDetail od = new OrderDetail();
		od.setOrderNo(o);
		od.setItemIdx(i);
		od.setQty(2);
		od.setOrderPrice(30000);
		oDetailRepo.save(od);
		
		o = ordersRepo.findById("202603161111").get();
		i = itemRepo.findById(2).get();
		od = new OrderDetail();
		od.setOrderNo(o);
		od.setItemIdx(i);
		od.setQty(2);
		od.setOrderPrice(23000);
		oDetailRepo.save(od);
		
		o = ordersRepo.findById("202603161112").get();
		i = itemRepo.findById(3).get();
		od = new OrderDetail();
		od.setOrderNo(o);
		od.setItemIdx(i);
		od.setQty(1);
		od.setOrderPrice(230000);
		oDetailRepo.save(od);
		
		o = ordersRepo.findById("202603161112").get();
		i = itemRepo.findById(4).get();
		od = new OrderDetail();
		od.setOrderNo(o);
		od.setItemIdx(i);
		od.setQty(2);
		od.setOrderPrice(250000);
		oDetailRepo.save(od);
		
		o = ordersRepo.findById("202603161113").get();
		i = itemRepo.findById(5).get();
		od = new OrderDetail();
		od.setOrderNo(o);
		od.setItemIdx(i);
		od.setQty(1);
		od.setOrderPrice(1430000);
		oDetailRepo.save(od);
		
		o = ordersRepo.findById("202603161113").get();
		i = itemRepo.findById(6).get();
		od = new OrderDetail();
		od.setOrderNo(o);
		od.setItemIdx(i);
		od.setQty(1);
		od.setOrderPrice(120000);
		oDetailRepo.save(od);
		
		o = ordersRepo.findById("202603161113").get();
		i = itemRepo.findById(7).get();
		od = new OrderDetail();
		od.setOrderNo(o);
		od.setItemIdx(i);
		od.setQty(3);
		od.setOrderPrice(75000);
		oDetailRepo.save(od);
	}
	
	/**
	 * testInsertCart : Cart 테이블 더미데이터 추가
	 */
	@Test
	void testInsertCart() {
		Member m = memberRepo.findById(1).get();
		Item i = itemRepo.findById(3).get();
		Cart c = new Cart();
		c.setCartMemberNo(m);
		c.setCartItemIdx(i);
		cartRepo.save(c);
		
		m = memberRepo.findById(1).get();
		i = itemRepo.findById(4).get();
		c = new Cart();
		c.setCartMemberNo(m);
		c.setCartItemIdx(i);
		cartRepo.save(c);
		
		m = memberRepo.findById(2).get();
		i = itemRepo.findById(1).get();
		c = new Cart();
		c.setCartMemberNo(m);
		c.setCartItemIdx(i);
		cartRepo.save(c);
		
		m = memberRepo.findById(2).get();
		i = itemRepo.findById(2).get();
		c = new Cart();
		c.setCartMemberNo(m);
		c.setCartItemIdx(i);
		cartRepo.save(c);
		
		m = memberRepo.findById(3).get();
		i = itemRepo.findById(4).get();
		c = new Cart();
		c.setCartMemberNo(m);
		c.setCartItemIdx(i);
		cartRepo.save(c);
	}
	
	/**
	 * TestInsertSearch : Search 테이블 더미데이터 추가
	 */
	@Test
	void TestInsertSearch() {
		Member m = memberRepo.findById(1).get();
		Search s = new Search();
		s.setSearchMemberNo(m);
		s.setSearchKeyword("신발");
		s.setSearchDate(LocalDateTime.now());
		searchRepo.save(s);
		
		m = memberRepo.findById(3).get();
		s = new Search();
		s.setSearchMemberNo(m);
		s.setSearchKeyword("프라다");
		s.setSearchDate(LocalDateTime.now());
		searchRepo.save(s);
		
		m = memberRepo.findById(2).get();
		s = new Search();
		s.setSearchMemberNo(m);
		s.setSearchKeyword("데이식스");
		s.setSearchDate(LocalDateTime.now());
		searchRepo.save(s);
		
		m = memberRepo.findById(1).get();
		s = new Search();
		s.setSearchMemberNo(m);
		s.setSearchKeyword("원필");
		s.setSearchDate(LocalDateTime.now());
		searchRepo.save(s);
		
		m = memberRepo.findById(3).get();
		s = new Search();
		s.setSearchMemberNo(m);
		s.setSearchKeyword("가방");
		s.setSearchDate(LocalDateTime.now());
		searchRepo.save(s);
		
		m = memberRepo.findById(2).get();
		s = new Search();
		s.setSearchMemberNo(m);
		s.setSearchKeyword("샤넬");
		s.setSearchDate(LocalDateTime.now());
		searchRepo.save(s);
	}
	
	/**
	 * testInsertBellMsg : bell_msg 테이블 더미데이터 추가
	 */
	@Test
	void testInsertBellMsg() {
		BellMsg bm = new BellMsg();
		bm.setBellType("D");
		bm.setBellMsg("찜한 !상품의 가격이 하락되었습니다.");
		bellMsgRepo.save(bm);
		
		bm = new BellMsg();
		bm.setBellType("Z");
		bm.setBellMsg("찜한 !상품이 품절되었습니다.");
		bellMsgRepo.save(bm);
		
		bm = new BellMsg();
		bm.setBellType("C");
		bm.setBellMsg("문의하신 게시글에 답변이 등록되었습니다.");
		bellMsgRepo.save(bm);
	}
	
	/**
	 * testInsertBell : Bell 테이블 더미데이터 추가
	 */
	@Test
	void testInsertBell() {
		Member m = memberRepo.findById(1).get();
		BellMsg bm = bellMsgRepo.findById("D").get();
		Likes l = likesRepo.findById(5).get();
		Bell b = new Bell();
		b.setBellMemberNo(m);
		b.setBellType(bm);
		b.setLikeIdx(l);
		b.setBellTime(LocalDateTime.now());
		bellRepo.save(b);
		
		m = memberRepo.findById(2).get();
		bm = bellMsgRepo.findById("C").get();
		Board bd = boardRepo.findById(1).get();
		b = new Bell();
		b.setBellMemberNo(m);
		b.setBellType(bm);
		b.setBellBoardIdx(bd);
		b.setBellTime(LocalDateTime.now());
		bellRepo.save(b);
		
		m = memberRepo.findById(3).get();
		bm = bellMsgRepo.findById("C").get();
		bd = boardRepo.findById(3).get();
		b = new Bell();
		b.setBellMemberNo(m);
		b.setBellType(bm);
		b.setBellBoardIdx(bd);
		b.setBellTime(LocalDateTime.now());
		bellRepo.save(b);
		
		m = memberRepo.findById(1).get();
		bm = bellMsgRepo.findById("Z").get();
		l = likesRepo.findById(1).get();
		b = new Bell();
		b.setBellMemberNo(m);
		b.setBellType(bm);
		b.setLikeIdx(l);
		b.setBellTime(LocalDateTime.now());
		bellRepo.save(b);
		
		m = memberRepo.findById(3).get();
		bm = bellMsgRepo.findById("D").get();
		l = likesRepo.findById(3).get();
		b = new Bell();
		b.setBellMemberNo(m);
		b.setBellType(bm);
		b.setLikeIdx(l);
		b.setBellTime(LocalDateTime.now());
		bellRepo.save(b);
	}
	
	/**
	 * testMemberHeaderProfile : memberNo에 해당하는 Header Profile 가져오기
	 */
	@Test
	void testMemberHeaderProfile() {
		//Given
		int memberNo = 2;
		//When
		MemberDto dto = mSvc.getMemberHeaderProfile(memberNo);
		//Then
		System.out.println(dto.getNickname() + " : " + dto.getProfile_img());
	}
	/*
	 * testMemberUpdateProfile : 회원정보 가져오기 
	 */
	@Test
	void testGetMemberUpdateProfile() {
		//Given
		int MemberNo = 1;
		//When
		MemberUpdateDto dto = mSvc.getMemberUpdateProfile(MemberNo);
		//Then
		System.out.println(dto.getBirth() + " : " + "생년월일" + dto.getEmail() + " : " + "이메일");
		 
	}
	/*
	 * testUpdateMemberProfile : memberNo에 해당하는 회원정보수정하기
	 */
	@Test
	@Transactional
	void testUpdateMemberProfile() {
		//Given
		int memberNo = 2;
		MemberUpdateDto dto = new MemberUpdateDto();
		dto.setNickname("민재짱");
		//When
		mSvc.modifyMemberProfile(memberNo, dto);
		//Then
		System.out.println(" 수정된 닉네임 : "+ dto.getNickname());
	}
}
