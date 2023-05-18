package com.ssafy.enjoytrip.user.model.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.user.model.UserDto;
import com.ssafy.enjoytrip.user.model.dao.UserDao;
import com.ssafy.enjoytrip.user.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

//	public UserDao userDao;
	public UserMapper userMapper;
	
//	@Autowired
//	private UserServiceImpl(UserDao userDao) {
//		this.userDao = userDao;
//	}
	
	@Autowired
	private UserServiceImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
//	@Override
//	public int idCheck(String userId) throws SQLException {
//		return userMapper.idCheck(userId);
//	}

	@Override
	public int registUser(UserDto userDto) throws SQLException {
		return userMapper.registUser(userDto);
	}

	@Override
	public UserDto loginUser(String userId, String userPw) throws SQLException {
		return userMapper.loginUser(userId, userPw);
	}

	@Override
	public void modifyUser(UserDto userDto) throws SQLException {
		userMapper.modifyUser(userDto);
	}

}
