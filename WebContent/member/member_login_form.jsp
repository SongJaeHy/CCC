<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
der .h_logo{
	display: blue;
	overflow: hidden;
	width: 231px;
	height: 44px;
	margin: 0 auto;
	background-position-x: -1px;
	background-position-y: -1px;
	font-size: 15px;
	color: transparent;
}
der .c_logo:before{
	content: '\00a1';
	display : blue;
	font-size:0;
	line-height: 0;
}	

</style>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<div id = "header"></div>
	<h1>
		<a href="https://www.naver.com" class="p c_logo" id="log.naver">
		<span class="blian">CCS</span>
		</a>
	</h1>
	
	<form action="member_login_ok.jsp" method="post">
	<table border="1">
		<tr>
			<td>아이디</td>
			<td><input type="text" name="mid" placeholder="Id"></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="mpw" maxlength=12 placeholder="Pw"></td>
		</tr>
		<tr>
			<td>
			<input type="submit" value="로그인">
			<a href="members_join_form.jsp">
			<input type="button" value="회원가입"></a>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>