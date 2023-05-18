package com.ssafy.enjoytrip.user.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.enjoytrip.user.model.UserDto;
import com.ssafy.enjoytrip.util.DBUtil;

@Repository
public class UserDaoImpl implements UserDao {


	private DBUtil dbUtil;
	
	@Autowired
	public UserDaoImpl(DBUtil dbUtil){
		this.dbUtil = dbUtil;
	}
	
	//해당되는 아이디가 있으면 양수, 없으면 0
	@Override
	public int idCheck(String userId) throws SQLException {
		
		int cnt = 1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder loginMember = new StringBuilder();
			loginMember.append("select count(user_id) \n");
			loginMember.append("from members \n");
			loginMember.append("where user_id = ? ");
			pstmt = conn.prepareStatement(loginMember.toString());
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return cnt;
	}

	@Override
	public int registUser(UserDto userDto) throws SQLException {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into members (user_id, user_name, user_password, email) \n");
			sql.append("values (?, ?, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userDto.getId());
			pstmt.setString(2, userDto.getName());
			pstmt.setString(3, userDto.getPassword());
			pstmt.setString(4, userDto.getEmail());
			cnt = pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		return cnt;
	}

	@Override
	public UserDto loginUser(String userId, String userPw) throws SQLException {
		UserDto userDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder loginMember = new StringBuilder();
			loginMember.append("select * \n");
			loginMember.append("from members \n");
			loginMember.append("where user_id = ? and user_password = ? \n");
			pstmt = conn.prepareStatement(loginMember.toString());
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				userDto = new UserDto();
				userDto.setId(rs.getString("user_id"));
				userDto.setName(rs.getString("user_name"));
				userDto.setEmail(rs.getString("email"));
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return userDto;
	}

	@Override
	public void modifyUser(UserDto userDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update members set ");
			sql.append("user_password = ?,");
			sql.append("email = ?\n");
			sql.append("where user_id = ?\n");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userDto.getPassword());
			pstmt.setString(2, userDto.getEmail());
			pstmt.setString(3, userDto.getId());
			pstmt.execute();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}
}
