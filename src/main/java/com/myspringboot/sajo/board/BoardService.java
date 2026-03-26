package com.myspringboot.sajo.board;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myspringboot.sajo.member.Member;
import com.myspringboot.sajo.member.MemberRepository;

import jakarta.transaction.Transactional;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepo;
	@Autowired
	private MemberRepository memberRepo;
	
	@Value("${file.upload-dir}")
    private String uploadDir;
	
	// 문의게시판 목록 전체 불러오기
	public List<BoardListDto> getBoardListAll(int pageNum) {
		int endRow = pageNum * 8;
		int startRow = endRow - 7;
		
		List<Board> listBoard = boardRepo.getListFromStartToEnd(startRow, endRow);
		
		List<BoardListDto> listBoardDto = listBoard.stream().map(BoardListDto::new).collect(Collectors.toList());
		return listBoardDto;
	}
	
	// 문의게시판 상세보기
	public BoardListDto getBoardByIdx(Integer boardIdx) {
		Optional<Board> ob = boardRepo.findById(boardIdx);
		if(ob.isEmpty()) {
			return null;
		}
		
		return new BoardListDto(ob.get());
	}
	
	// 문의게시판 등록하기
	@Transactional
	public void write(String title, String content, Integer writer, List<MultipartFile> files) {
		System.out.println("--- 파일 업로드 디버깅 ---");
	    if (files == null) {
	        System.out.println("파일 리스트가 null입니다!");
	    } else {
	        System.out.println("받은 파일 개수: " + files.size());
	    }
		
		Board b = new Board();
		b.setTitle(title);
		b.setContent(content);
		b.setBoardDate(LocalDateTime.now());
		Optional<Member> om = memberRepo.findById(writer);
		if(om.isPresent()) {
			b.setWriter(om.get());
		} else {
			b.setWriter(null);
		}
		
		if(files != null && !files.isEmpty()) {
			//String uploadPath = "D:\\Temp\\upload2";
			
			for(MultipartFile file : files) {
				if(!file.isEmpty()) {
					try {
						String originalName = file.getOriginalFilename();
						String saveName = System.currentTimeMillis() + "_" + originalName;
						
						file.transferTo(new File(uploadDir, saveName));	// 서버 폴더에 물리적 저장
						
						BoardFile bf = new BoardFile();
						bf.setFileUrl("/upload2/" + saveName);	// 나중에 불러올 경로
						
						b.addFile(bf);	// 연관관계 편의 메서드 호출. (b.getFileList().add(bf) + bf.setBoard(b)
					} catch(IOException e) {
						throw new RuntimeException("파일 저장 중 오류 발생 : " + file.getOriginalFilename(), e);
					}
				}
			}
		}
		boardRepo.save(b);
	}
	
	// 문의게시판 마지막페이지번호 조회
	public int getLastPageNumber() {
		List<Board> listBoard = boardRepo.findAll();
		return listBoard.size()/8 + (listBoard.size()%8==0 ? 0 : 1);
	}
}
