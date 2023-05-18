package com.ssafy.enjoytrip.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.enjoytrip.board.model.BoardDto;
import com.ssafy.enjoytrip.util.DBUtil;
@Repository
public class BoardDaoImpl implements BoardDao{

	public DBUtil dbUtil;
	
	@Autowired
	public BoardDaoImpl(DBUtil dbUtil) {
		this.dbUtil = dbUtil;
	}
	
	@Override
	public List<BoardDto> announceList() throws SQLException {
		List<BoardDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from board \n");
			sql.append("where type = 1");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDto boardDto = new BoardDto();
				boardDto.setArticleNo(rs.getInt("article_no"));
				boardDto.setContent(rs.getString("content"));
				boardDto.setSubject(rs.getString("subject"));
				boardDto.setHit(rs.getInt("hit"));
				boardDto.setUserId(rs.getString("user_id"));
				boardDto.setDate(rs.getString("register_time"));
				list.add(boardDto);
			}
		}finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public BoardDto viewAnnounce(int articleNo) throws SQLException {
		BoardDto boardDto = new BoardDto();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from board \n");
			sql.append("where article_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, articleNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				boardDto.setArticleNo(rs.getInt("article_no"));
				boardDto.setContent(rs.getString("content"));
				boardDto.setSubject(rs.getString("subject"));
				boardDto.setHit(rs.getInt("hit"));
				boardDto.setUserId(rs.getString("user_id"));
				boardDto.setDate(rs.getString("register_time"));
			}
		}finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return boardDto;
	}

	@Override
	public List<BoardDto> freeList() throws SQLException {
		List<BoardDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from board where type=2");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDto boardDto = new BoardDto();
				boardDto.setArticleNo(rs.getInt("article_no"));
				boardDto.setContent(rs.getString("content"));
				boardDto.setSubject(rs.getString("subject"));
				boardDto.setHit(rs.getInt("hit"));
				boardDto.setUserId(rs.getString("user_id"));
				boardDto.setDate(rs.getString("register_time"));
				list.add(boardDto);
			}
		}finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public BoardDto viewFree(int articleNo) throws SQLException {
		BoardDto boardDto = new BoardDto();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from board \n");
			sql.append("where article_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, articleNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				boardDto.setArticleNo(rs.getInt("article_no"));
				boardDto.setContent(rs.getString("content"));
				boardDto.setSubject(rs.getString("subject"));
				boardDto.setHit(rs.getInt("hit"));
				boardDto.setUserId(rs.getString("user_id"));
				boardDto.setDate(rs.getString("register_time"));
			}
		}finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return boardDto;
	}

	@Override
	public void writeFree(BoardDto boardDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into board(user_id, subject, content, type) \n");
			sql.append("value(?, ?, ?, ?) \n");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, boardDto.getUserId());
			pstmt.setString(2, boardDto.getSubject());
			pstmt.setString(3, boardDto.getContent());
			pstmt.setInt(4, 2);
			pstmt.executeUpdate();
		}finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public void writeAnnounce(BoardDto boardDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into board(user_id, subject, content, type) \n");
			sql.append("value(?, ?, ?, ?) \n");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, boardDto.getUserId());
			pstmt.setString(2, boardDto.getSubject());
			pstmt.setString(3, boardDto.getContent());
			pstmt.setInt(4, 1);
			pstmt.executeUpdate();
		}finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public void deleteFree(int articleNo) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("delete from free\n");
			sql.append("where article_no = ? \n");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, articleNo);
			pstmt.executeUpdate();
		}finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public void deleteAnnounce(int articleNo) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("delete from board\n");
			sql.append("where article_no = ? \n");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, articleNo);
			pstmt.executeUpdate();
		}finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public void modifyFree(BoardDto boardDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update board set ");
			sql.append("subject = ? , ");
			sql.append("content = ? \n");
			sql.append("where article_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, boardDto.getSubject());
			pstmt.setString(2, boardDto.getContent());
			pstmt.setInt(3, boardDto.getArticleNo());
			pstmt.executeUpdate();
		}finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public void modifyAnnounce(BoardDto boardDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update board set ");
			sql.append("subject = ? , ");
			sql.append("content = ? \n");
			sql.append("where article_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, boardDto.getSubject());
			pstmt.setString(2, boardDto.getContent());
			pstmt.setInt(3, boardDto.getArticleNo());
			pstmt.executeUpdate();
		}finally {
			dbUtil.close(pstmt, conn);
		}
	}

}
