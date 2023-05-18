package com.ssafy.enjoytrip.board.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoytrip.board.model.BoardDto;

public interface BoardDao {

	List<BoardDto> announceList() throws SQLException;

	BoardDto viewAnnounce(int articleNo) throws SQLException;

	List<BoardDto> freeList() throws SQLException;

	BoardDto viewFree(int articleNo) throws SQLException;

	void writeFree(BoardDto boardDto) throws SQLException;

	void writeAnnounce(BoardDto boardDto) throws SQLException;

	void deleteFree(int articleNo) throws SQLException;

	void deleteAnnounce(int articleNo) throws SQLException;
	
	void modifyFree(BoardDto articleNo) throws SQLException;

	void modifyAnnounce(BoardDto boadrDto) throws SQLException;

}
