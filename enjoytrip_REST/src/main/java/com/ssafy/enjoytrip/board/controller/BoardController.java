package com.ssafy.enjoytrip.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.board.model.BoardDto;
import com.ssafy.enjoytrip.board.model.CommentDto;
import com.ssafy.enjoytrip.board.model.service.BoardService;

@RestController
@RequestMapping("/board")
public class BoardController{
	
	public BoardService boardService;
	
	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	@GetMapping("/announceList")
	public ResponseEntity<?> announceList() throws Exception {
		return new ResponseEntity<>(boardService.announceList(), HttpStatus.OK);
	}
	
	@GetMapping("/freeList")
	public ResponseEntity<?> freeList(Model model) throws Exception {
		return new ResponseEntity<>(boardService.freeList(), HttpStatus.OK);
	}
	
	@GetMapping("/{articleNo}")
	public ResponseEntity<?> announceView(@PathVariable int articleNo) throws Exception {
		BoardDto boardDto = boardService.viewAnnounce(articleNo);
		boardService.increaseHit(articleNo);
		return new ResponseEntity<>(boardDto, HttpStatus.OK);
	}


	@PostMapping("/write")
	public ResponseEntity<?> writeAnnounce(@RequestBody BoardDto boardDto) throws Exception {
		boardService.writeAnnounce(boardDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/freeList/write")
	public ResponseEntity<?> writeFree(@RequestBody BoardDto boardDto) throws Exception {
		boardService.writeFree(boardDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/delete/{articleNo}")
	public ResponseEntity<?> deleteAnnounce(@PathVariable int articleNo) throws Exception {
		boardService.deleteAnnounce(articleNo);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@PostMapping("/modify/{articleNo}")
	public ResponseEntity<?> modifyAnnounce(@PathVariable int articleNo, @RequestBody BoardDto boardDto) throws Exception {
		boardDto.setArticleNo(articleNo);
		boardService.modifyAnnounce(boardDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/search/{keyword}")
	public ResponseEntity<?> search(@PathVariable String keyword, @RequestHeader String type) throws Exception{
		return new ResponseEntity<>(boardService.search(keyword, type), HttpStatus.OK);
	}
	
	@GetMapping("/comment/{articleNo}")
	public ResponseEntity<?> getComments(@PathVariable int articleNo){
		return new ResponseEntity<>(boardService.getComments(articleNo), HttpStatus.OK);
	}
	
	@PostMapping("/comment/write")
	public ResponseEntity<?> writeComment(@RequestBody CommentDto commentDto){
		boardService.writeComment(commentDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/comment/delete")
	public ResponseEntity<?> deleteComment(@RequestBody int commentId){
		System.out.println("--------------commentId = "+commentId);
		boardService.deleteComment(commentId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
