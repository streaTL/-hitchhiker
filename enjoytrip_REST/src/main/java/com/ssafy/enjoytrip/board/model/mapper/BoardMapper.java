package com.ssafy.enjoytrip.board.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.board.model.BoardDto;

@Mapper
public interface BoardMapper {
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

	List<BoardDto> search(String keyword, String type) throws SQLException;

	void increaseHit(int articleNo);;

}
