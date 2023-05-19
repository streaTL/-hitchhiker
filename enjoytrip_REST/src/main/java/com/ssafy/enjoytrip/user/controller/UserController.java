package com.ssafy.enjoytrip.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.user.model.UserDto;
import com.ssafy.enjoytrip.user.model.service.JwtServiceImpl;
import com.ssafy.enjoytrip.user.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/user")
@Api("유저 컨트롤러  API V1")
public class UserController {
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	private UserService userService;

	@Autowired
	private JwtServiceImpl jwtService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(
			@RequestBody UserDto userDto) throws Exception {
		System.out.println(userDto);
		HttpStatus status = null;
		Map<String, Object> resultMap = new HashMap<>();
		try {
			UserDto loginUser = userService.loginUser(userDto.getId(), userDto.getPassword());			
			if(loginUser != null) {
				String accessToken = jwtService.createAccessToken("userid", loginUser.getId());// key, data
				String refreshToken = jwtService.createRefreshToken("userid", loginUser.getId());// key, data
				userService.saveRefreshToken(loginUser.getId(), refreshToken);
				logger.debug("로그인 accessToken 정보 : {}", accessToken);
				logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", "success");
				status = HttpStatus.ACCEPTED;
			}else {
				resultMap.put("message", "fail");
				status = HttpStatus.ACCEPTED;
			}
		}catch (Exception e) {
			logger.error("로그인 실패: {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>> (resultMap, status);
	}
	
	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid,
			HttpServletRequest request) {
		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		System.out.println(request.getHeader("access-token"));
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				UserDto memberDto = userService.userInfo(userid);
				resultMap.put("userInfo", memberDto);
				resultMap.put("message", "success");
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "로그아웃", notes = "회원 정보를 담은 Token을 제거한다.", response = Map.class)
	@GetMapping("/logout/{userid}")
	public ResponseEntity<?> removeToken(@PathVariable("userid") String userid) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			userService.deleteRefreshToken(userid);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

//	@ApiOperation(value = "Access Token 재발급", notes = "만료된 access token을 재발급받는다.", response = Map.class)
//	@PostMapping("/refresh")
//	public ResponseEntity<?> refreshToken(@RequestBody UserDto memberDto, HttpServletRequest request)
//			throws Exception {
//		Map<String, Object> resultMap = new HashMap<>();
//		HttpStatus status = HttpStatus.ACCEPTED;
//		String token = request.getHeader("refresh-token");
//		logger.debug("token : {}, memberDto : {}", token, memberDto);
//		if (jwtService.checkToken(token)) {
//			if (token.equals(userService.getRefreshToken(memberDto.getId()))) {
//				String accessToken = jwtService.createAccessToken("userid", memberDto.getId());
//				logger.debug("token : {}", accessToken);
//				logger.debug("정상적으로 액세스토큰 재발급!!!");
//				resultMap.put("access-token", accessToken);
//				resultMap.put("message", SUCCESS);
//				status = HttpStatus.ACCEPTED;
//			}
//		} else {
//			logger.debug("리프레쉬토큰도 사용불!!!!!!!");
//			status = HttpStatus.UNAUTHORIZED;
//		}
//		return new ResponseEntity<Map<String, Object>>(resultMap, status);
//	}
	
	
	@PostMapping("/regist")
	public ResponseEntity<?> regist(@RequestBody UserDto userDto) throws Exception {
		System.out.println(userDto);
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
			return new ResponseEntity<>(temp, HttpStatus.OK);
		}
	}
	
	@PostMapping("/findPw")
	public ResponseEntity<?> findPw(@RequestBody UserDto userDto){
		System.out.println(userDto);
		String password = userService.findPw(userDto);
		HashMap<String, String> result = new HashMap<>();
		if(password != null) {
			result.put("password", password);
			result.put("message", "success");
		}else {
			System.out.println("실패");
			result.put("message", "fail");
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/delete/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable String userId){
		System.out.println(userId);
		HashMap<String, String> result = new HashMap<>();
		userService.deleteUser(userId);
		result.put("message", "success");
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
