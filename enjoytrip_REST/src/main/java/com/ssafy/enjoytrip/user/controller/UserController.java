package com.ssafy.enjoytrip.user.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.user.model.UserDto;
import com.ssafy.enjoytrip.user.model.service.UserService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/user")
@Api("유저 컨트롤러  API V1")
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/login")
	public ResponseEntity<UserDto> login(@RequestBody HashMap<String, String> map) throws Exception {
		System.out.println(map);
		UserDto userDto = userService.loginUser(map.get("userId"), map.get("userPw"));
		System.out.println(userDto);
		if (userDto == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			userDto.setPassword(map.get("userPw"));
			return new ResponseEntity<>(userDto, HttpStatus.OK);
		}
	}
	
	@PostMapping("/regist")
	public ResponseEntity<?> regist(UserDto userDto) throws Exception {
		try {
			userService.registUser(userDto);			
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>("회원가입 실패", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/editUser")
	public ResponseEntity<?> edit(@RequestBody UserDto userDto) throws Exception {
		System.out.println("수정" + "   " + userDto);
		userService.modifyUser(userDto);
		UserDto temp = userService.loginUser(userDto.getId(), userDto.getPassword());
		System.out.println(userDto);
		
		if (temp == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			userDto.setPassword(userDto.getPassword());
			return new ResponseEntity<>(temp, HttpStatus.OK);
		}
	}

}
