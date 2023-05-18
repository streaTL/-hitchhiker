package com.ssafy.enjoytrip.user.model.dao;

import java.sql.SQLException;

import com.ssafy.enjoytrip.user.model.UserDto;

public interface UserDao {

	int idCheck(String userId) throws SQLException;
	int registUser(UserDto userDto) throws SQLException;
	UserDto loginUser(String userId, String userPw) throws SQLException;
	void modifyUser(UserDto userDto) throws SQLException;
}
