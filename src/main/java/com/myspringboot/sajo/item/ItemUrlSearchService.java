package com.myspringboot.sajo.item;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemUrlSearchService {
	@Autowired
    private TranslationService tSvc;
	@Autowired
	private ItemRepository itemRepo;
	
	 public Integer rakutenCrawlAndTranslate(String inputUrl) {
    	System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        
        // 차단 방지 및 실제 브라우저처럼 보이게 설정
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36");
        
        WebDriver driver = new ChromeDriver(options);
        tSvc.checkUsage();
        try {
            String url = inputUrl; 
            driver.get(url);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            
            // [추가] 지연 로딩 해결을 위한 스크롤 하향 (자바스크립트 실행)
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 500);");
            Thread.sleep(2000); // 렌더링 대기

            // 1. 제목 추출 (성공했으므로 유지)
            //WebElement titleElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h1")));
            WebElement titleElement = wait.until(
        	    ExpectedConditions.presenceOfElementLocated(By.xpath("(b"))
        	);
            String jpTitle = titleElement.getText();
            System.out.println("[jpTitle] " + jpTitle);

            // 2. 가격 추출 (셀렉터 보강)
            String jpPrice = "가격 정보 실패";  // (단위 : 엔yen)
            try {
            	// 1. 부모 클래스(productPrice) 안에 있고, itemprop 속성이 "price"인 span 태그를 찾습니다.
            	WebElement priceElement = wait.until(ExpectedConditions.presenceOfElementLocated(
            	    By.cssSelector(".productPrice span[itemprop='price']")
            	));

            	// 2. getText()가 아니라 "content" 속성값을 가져옵니다.
            	jpPrice = priceElement.getAttribute("content");
            } catch (Exception e) { System.out.println("가격 추출 실패"); }
            System.out.println("[jpPrice] " + jpPrice);
            
            // 3. 상세 설명 추출 (보정 버전)
            String jpDescription = ""; 
            try {
            	// 1. "내용소개" 가져오기 (h3 태그의 클래스 활용)
            	titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
            	    By.cssSelector(".sec-item__extra__title")
            	));
            	String contentTitle = titleElement.getText().trim(); // 결과: "내용소개"

            	// 2. "음악에 자유롭고..." 가져오기 (h3 바로 다음에 오는 b 태그 활용)
            	WebElement descElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
            	    By.cssSelector(".sec-item__extra__title + b")
            	));
            	String contentDescription = descElement.getText().trim(); // 결과: "음악에 자유롭고 싶다! ..."

            	// 3. 두 내용을 합치고 싶다면
            	jpDescription = contentTitle + "\n" + contentDescription;
            } catch (Exception e) { 
                System.out.println("설명 추출 실패 - 셀렉터나 로딩 문제");
                jpDescription = "상세 설명이 없습니다."; 
            }
            System.out.println("[jpDescription] " + jpDescription);
            
            ProductDto product = new ProductDto();
            // 4. 이미지 경로 추출 (보내주신 태그 기반 보정)
            String imageUrl = "이미지 경로 실패";
            // [수정] 여러 장의 이미지를 담을 리스트
            List<String> imageUrls = new ArrayList<>();
            try {
            	// 1. ISPager ISGallery 클래스 내부의 모든 img 태그를 찾거나, 
                //    정확하게 data-ratid가 item-img-tmb인 요소 안의 이미지를 찾습니다.
                List<WebElement> imgElements = driver.findElements(
                    By.cssSelector("ul.lSGallery.lSPager li a img")
                );
                
                for (WebElement img : imgElements) {
                    String src = img.getAttribute("src");
                    
                    if (src != null && !src.isEmpty()) {
                        // 2. 만약 프로토콜(http:)이 빠진 상대 경로(//shop.r10s.jp...)라면 붙여줍니다.
                        if (src.startsWith("//")) {
                            src = "https:" + src;
                        }
                        
                        if (!imageUrls.contains(src)) {
                            imageUrls.add(src);
                        }
                    }
                }
                
                // 첫 번째 이미지를 대표 이미지로 설정
                if (!imageUrls.isEmpty()) {
                	product.setAllImages(imageUrls); // 리스트 통째로 저장
                	product.setImageUrl(imageUrls.get(0)); // 그중 첫 번째만 대표로 저장 
                	System.out.println("추출된 이미지 개수: " + imageUrls.size());
                }
            } catch (Exception e) {
                System.out.println("이미지 목록 추출 실패");
            }
            for(String url1 : imageUrls) {
            	System.out.println("[이미지] " + url1);
            }
            
            // 5. 데이터 수집 완료 후 번역 및 DTO 세팅 시작
            System.out.println("데이터 수집 완료. 번역 시작...");

            // 제목 번역
            product.setTitle(tSvc.translateToKorean(jpTitle)); 

            // 가격 세팅
            product.setPrice(jpPrice); 

            // 이미지 URL은 위 try 블록에서 imageUrls가 있으면 이미 세팅됨. 없으면 기본값 유지.

            // [최종 체크] 설명 번역
            if (jpDescription == null || jpDescription.trim().isEmpty() || jpDescription.equals("상세 설명이 없습니다.")) {
                product.setDescription("상세 설명이 없습니다.");
            } else {
                // 실제 데이터가 있을 때만 DeepL API 호출
                product.setDescription(tSvc.translateToKorean(jpDescription));
            }

            // 6. 결과 확인
            System.out.println("\n==============================");
            System.out.println("제목: " + product.getTitle());
            System.out.println("가격: " + product.getPrice());
            System.out.println("이미지: " + product.getImageUrl());
            System.out.println("설명: " + (product.getDescription().length() > 50 ? product.getDescription().substring(0, 50) + "..." : product.getDescription()));
            System.out.println("==============================\n");
            
            Item item = new Item();
            item.setItemName(product.getTitle());
            item.setItemPrice(Integer.parseInt(product.getPrice()));
            item.setItemImg(product.getImageUrl());
            item.setItemDetail((product.getDescription().length() > 50 ? product.getDescription().substring(0, 50) + "..." : product.getDescription()));
            Item savedItem = itemRepo.save(item);
            Integer itemIdx = savedItem.getItemIdx();

	         System.out.println("방금 저장된 아이템의 IDX: " + itemIdx);
	         return itemIdx;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            driver.quit(); 
        }
    }
}
