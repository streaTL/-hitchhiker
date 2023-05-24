package com.ssafy.enjoytrip.board.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.board.model.BoardDto;
import com.ssafy.enjoytrip.board.model.CommentDto;
import com.ssafy.enjoytrip.board.model.dao.BoardDao;
import com.ssafy.enjoytrip.board.model.dao.BoardDaoImpl;
import com.ssafy.enjoytrip.board.model.mapper.BoardMapper;
@Service
public class BoardServiceImpl implements BoardService{
	
	public BoardDao boardDao;
	public BoardMapper boardMapper;
	
	@Autowired
	public BoardServiceImpl(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}
	
//	@Autowired
//	public BoardServiceImpl(BoardDao boardDao) {
//		this.boardDao = boardDao;
//	}

	@Override
	public List<BoardDto> announceList() throws Exception {
		return boardMapper.announceList();
	}

	@Override
	public BoardDto viewAnnounce(int articleNo) throws Exception {
		return boardMapper.viewAnnounce(articleNo);
	}

	@Override
	public List<BoardDto> freeList() throws Exception {
		return boardMapper.freeList();
	}

	@Override
	public BoardDto viewFree(int articleNo) throws Exception {
		return boardMapper.viewFree(articleNo);
	}

	@Override
	public void writeFree(BoardDto boardDto) throws Exception {
		boardMapper.writeFree(boardDto);
	}

	@Override
	public void writeAnnounce(BoardDto boardDto) throws Exception {
		boardMapper.writeAnnounce(boardDto);
	}

	@Override
	public void deleteFree(int articleNo) throws Exception {
		boardMapper.deleteFree(articleNo);
	}

	@Override
	public void deleteAnnounce(int articleNo) throws Exception {
		boardMapper.deleteAnnounce(articleNo);
		
	}

	@Override
	public void modifyFree(BoardDto boardDto) throws SQLException {
		boardMapper.modifyFree(boardDto);
	}

	@Override
	public void modifyAnnounce(BoardDto boardDto) throws SQLException {
		boardMapper.modifyAnnounce(boardDto);
	}

	@Override
	public List<BoardDto> search(String keyword, String type) throws SQLException {
		return boardMapper.search(keyword, type);		
	}

	@Override
	public void increaseHit(int articleNo) {
		boardMapper.increaseHit(articleNo);	
	}

	@Override
	public List<CommentDto> getComments(int articleNo) {
		return boardMapper.getComments(articleNo);
	}

	@Override
	public void writeComment(CommentDto commentDto) {
		boardMapper.writeComment(commentDto);
	}
}
