package com.ssafy.enjoytrip.user.model.service;

import java.sql.SQLException;

import com.ssafy.enjoytrip.user.model.UserDto;

public interface UserService {
//	int idCheck(String userId) throws SQLException;
	int registUser(UserDto userDto) throws SQLException;
	UserDto loginUser(String userId, String userPw) throws SQLException;
	void modifyUser(UserDto userDto) throws SQLException;
	UserDto userInfo(String userid) throws Exception;
	void saveRefreshToken(String id, String refreshToken);
	void deleteRefreshToken(String userid);
	String findPw(UserDto userDto);
	void deleteUser(String userId);

	String getRefreshToken(String id);
}
