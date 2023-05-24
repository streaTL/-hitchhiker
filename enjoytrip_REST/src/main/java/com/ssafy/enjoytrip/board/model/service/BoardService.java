package com.ssafy.enjoytrip.board.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoytrip.board.model.BoardDto;
import com.ssafy.enjoytrip.board.model.CommentDto;

public interface BoardService {

	List<BoardDto> announceList() throws Exception;

	BoardDto viewAnnounce(int articleNo) throws Exception;

	List<BoardDto> freeList() throws Exception;

	BoardDto viewFree(int articleNo) throws Exception;

	void writeFree(BoardDto boardDto) throws Exception;

	void writeAnnounce(BoardDto boardDto) throws Exception;

	void deleteFree(int articleNo) throws Exception;

	void deleteAnnounce(int articleNo) throws Exception;

	void modifyFree(BoardDto articleNo) throws SQLException;

	void modifyAnnounce(BoardDto articleNo) throws SQLException;

	List<BoardDto> search(String keyword, String type) throws SQLException;

	void increaseHit(int articleNo);

	List<CommentDto> getComments(int articleNo);

	void writeComment(CommentDto commentDto);

	void deleteComment(int commentId);
	
}
