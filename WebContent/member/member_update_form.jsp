<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>사원 정보 수정</h1>
	
	<form action="member_update_ok.jsp" method="post">
	<table>
		<tr>
			<td>아이디</td>
			<td><input type="text" name="mid" id="m_id" placeholder="아이디" readonly required><br/></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="mpw" id="m_pw" placeholder="비밀번호"><br/></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="email" name="memail" id="m_email" placeholder="이메일"required><br/></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><input type="tel" name="mphone" id="m_phone" placeholder="전화번호" 
		value="000-0000-0000" maxlength="13"required><br/></td>
		</tr>
		<tr>
			<td><input type="submit" value="사원정보 수정하기"></td>
			<td><input type="reset" value="초기화"></td>
		</tr>
	
		</table>
	</form>
</body>
</html>