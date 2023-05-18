package com.ssafy.enjoytrip.user.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.user.model.UserDto;

@Mapper
public interface UserMapper {
//	int idCheck(String userId) throws SQLException;
	int registUser(UserDto userDto) throws SQLException;
	UserDto loginUser(String userId, String userPw) throws SQLException;
	void modifyUser(UserDto userDto) throws SQLException;
}
