<%@page import="com.ssafy.enjoytrip.user.model.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%
UserDto userInfo = (UserDto) session.getAttribute("userinfo");

if(userInfo != null) {
%>
	
<%
} else {
%>
	<script>
	alert("로그인이 필요한 페이지입니다.");
	location.href = "${root}/";
	</script>
<%	
}
%>