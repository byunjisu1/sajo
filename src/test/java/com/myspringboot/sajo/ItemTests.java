package com.myspringboot.sajo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.myspringboot.sajo.item.Item;
import com.myspringboot.sajo.item.ItemDto;
import com.myspringboot.sajo.item.ItemRepository;
import com.myspringboot.sajo.item.ItemService;
import com.myspringboot.sajo.item.PicItem;
import com.myspringboot.sajo.item.PicItemRepository;
import com.myspringboot.sajo.member.Member;
import com.myspringboot.sajo.member.MemberRepository;

@SpringBootTest
public class ItemTests {
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private ItemRepository itemRepo;
	@Autowired
	private PicItemRepository picRepo;
	@Autowired
	private ItemService ISvc;
	
	/**
	 * testInsertItem : Item 테이블 더미데이터 추가
	 */
	@Test
	void testInsertItem() {
		Item i = new Item();
		i.setItemName("[무료배송] 미드나잇 선 [원필, DAY6] [수입판] ▼/O.S.T [CD] [반품타입 A]");
		i.setItemDetail("품번：VDCD-6852 발매일：2021년 08월 27일 발매");
		i.setItemPrice(30000);
		i.setItemImg("https://tshop.r10s.jp/joshin-cddvd/cabinet/040/vdcd-6852.jpg");
		itemRepo.save(i);
		
		i = new Item();
		i.setItemName("DAY6 데니말츠 릴 패스 파우치 - 포에버 영 피날레/콘서트 MD/굿즈/플러시 장난감/4종/인형/키링/ 서울");
		i.setItemDetail("【주의 사항】\r\n"
				+ "・저희 가게에서 구입한 상품은, 원칙적으로, 「개인 수입」으로서의 취급이 되어, 모두 한국의 영인으로부터 손님에게 직송됩니다.\r\n"
				+ "・개인 수입되는 상품은, 모두 주문자 자신의 「개인 사용・개인 소비」가 전제가 되기 때문에, 주문된 상품을 제삼자에게 양도・전매하는 것은 법률로 금지되고 있습니다.\r\n"
				+ "・통관시 관세・수입 소비세가 과세될 가능성이 있습니다. 과세액은 주문시에는 확정되어 있지 않고, 통관시에 확정하므로, 상품의 수취시에 착불로 지불해 주세요. 자세한 내용은 여기 확인해 주세요.\r\n"
				+ "＊색이 있는 경우, 모니터의 발색의 상태에 의해 실제의 것과 색이 다른 경우가 있다.");
		i.setItemPrice(23000);
		i.setItemImg("https://tshop.r10s.jp/kpopmerch/cabinet/09444242/imgrc0122513069.jpg");
		itemRepo.save(i);
		
		i = new Item();
		i.setItemName("샤넬 향수 No.5 N°5 남성용 여성용 샤넬 오 드 퍼퓸 향수 오 드 퍼퓸 향수 오 드 퍼퓸 오드 퍼퓸 EDP 50ml 100ml 넘버 5 샤넬 브랜드 신제품 정품 선물 화이트 데이 반품 선물 남성 여성 실용적인 남자친구 생일 기념일");
		i.setItemDetail("-");
		i.setItemPrice(230000);
		i.setItemImg("https://tshop.r10s.jp/rush-mall/cabinet/image-nologo/chanel-088.jpg");
		itemRepo.save(i);
		
		i = new Item();
		i.setItemName("구찌 귀걸이 구찌 인터로킹 G 실버 925 여성용 223321 J8400 8106 실버 액세서리");
		i.setItemDetail("구찌 피어싱 인터로킹 G 액세서리 실버 925 여성 223321 J8400 8106 GUCCI 실버 ◆ 1921 년 구찌오 구찌는 가죽 제품 회사를 창업하고 피렌체에 작은 가방을 개업. 브랜드에 대한 그의 비전은 런던과 그가 사보이 호텔에서 일할 때 본 영국 귀족의 세련된 심미안에서 배 지갑이나 가죽 제품 외에도 액세서리와 손목 시계도 품질의 높이와 세련된 디자인이 인기로 단번에 고급 브랜드의 지위를 확립했습니다. 지금은 이름을 모르는 사람은 없을 정도로 세계적으로 유명한 브랜드입니다. 브랜드 로고를 따라 본 G 모티브는 존재감 발군. 그 날의 패션과 기분에 맞춘 코디를 즐길 수 있습니다.");
		i.setItemPrice(250000);
		i.setItemImg("https://tshop.r10s.jp/7ple/cabinet/accessory4/223321j84008106.jpg");
		itemRepo.save(i);
		
		i = new Item();
		i.setItemName("프라다 프라다 로퍼 2DE127 055 F0002 남성용 초콜릿 브러시드 레더 슈즈 레더 슈즈 트라이앵글 로고 메탈 네로");
		i.setItemDetail("· 브러쉬 가죽\r\n"
				+ "· 파라 스타일의 하프 구두창이있는 경량 고무 러그 구두창\r\n"
				+ "· 어퍼에 금속 트라이앵글 로고\r\n"
				+ "·인스텝에 신축성이 있는 거싯\r\n"
				+ "·솔 두께: 3.5cm");
		i.setItemPrice(1430000);
		i.setItemImg("https://tshop.r10s.jp/auc-marks-run/cabinet/suruzou_219/340727648_1.jpg");
		itemRepo.save(i);
		
		i = new Item();
		i.setItemName("비매품 MG 1/100 건담 베이스 한정 상품 RX-78-2 건담 Ver.3.0 [골드 코팅] 기동전사 건담");
		i.setItemDetail("新品・未組立\r\n"
				+ "※この商品は佐川急便より宅配便にて発送致します。\r\n"
				+ "※複数購入された場合、送料は追加で発生しません。\r\n"
				+ "\r\n"
				+ "ガンダムベースのポイントでのみ交換可能なオリジナル景品");
		i.setItemPrice(120000);
		i.setItemImg("https://tshop.r10s.jp/zootrope/cabinet/04969792/imgrc0132267075.jpg");
		itemRepo.save(i);
		
		i = new Item();
		i.setItemName("[재고 정리] 쿠폰 포함 3980엔, 열쇠가 달린 경량 휴대용 케이스, 잠금해제, S/M 사이즈, TSA 락, USB 포트, 더블 캐스터, 기내 반입 호환 컵 홀더, 지퍼 타입, 2박 3일, 4~7박용, 세련된 디자인, 1년 보증");
		i.setItemDetail("-");
		i.setItemPrice(75000);
		i.setItemImg("https://tshop.r10s.jp/kroeus/cabinet/09453199/11456757/imgrc0206216178.jpg");
		itemRepo.save(i);
	}
	
	/**
	 * testPicItem : pic_item 테이블 더미 데이터 추가
	 */
	@Test
	void testPicItem1() {
		Member m = memberRepo.findById(1).get();
		PicItem p = new PicItem();
		p.setMemberNo(m);
		p.setPicItemUrl("sdklfjdjjpg.12355");
		picRepo.save(p);
		
		m = memberRepo.findById(1).get();
		p = new PicItem();
		p.setMemberNo(m);
		p.setPicItemUrl("sdklfjdjjpg.12355");
		picRepo.save(p);
		
		m = memberRepo.findById(2).get();
		p = new PicItem();
		p.setMemberNo(m);
		p.setPicItemUrl("sdklfjdjjpg.12355");
		picRepo.save(p);
		
		m = memberRepo.findById(3).get();
		p = new PicItem();
		p.setMemberNo(m);
		p.setPicItemUrl("sdklfjdjjpg.12355");
		picRepo.save(p);
	}
	@Test
	void testItemDetail() {
		Integer ItemIdx = 1;
		ItemDto dto = ISvc.getItemDetail(ItemIdx);
		
		System.out.println(dto.getItemName()+"/"+dto.getItemDetail());
	}
}