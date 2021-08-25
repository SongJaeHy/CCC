<%@page import="com.sjh.model.MemberVO"%>
<%@page import="com.sjh.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
</head>
<body>
	<h1>사원 정보 수정</h1>
	
	<form action="/ccs/updateok.do" method="post">
	<table class="table table-bordered">
		<tr>
			<td><h3 class="text-info">아이디</h3></td>
			<td><input type="text" name="m_id" id="m_id" placeholder="아이디" readonly required 
			class="form-control" disabled>${member.mId }<br/></td>
		</tr>
		<tr>
			<td><h4 class="text-primary">비밀번호</h4></td>
			<td><input type="password" name="m_pw" id="m_pw" placeholder="비밀번호" class="form-control">${member.mPw }<br/></td>
		</tr>
		<tr>
			<td><h3 class="text-info">이메일</h3></td>
			<td><input type="email" name="m_email" id="m_email" placeholder="이메일"required class="form-control">${memeber.mEmail }<br/></td>
		</tr>
		<tr>
			<td><h3 class="text-info">전화번호</h3></td>
			<td><input type="tel" name="m_phone" id="m_phone"  
		placeholder="000-0000-0000" maxlength="13"required class="form-control">${member.mPhone}<br/></td>
		</tr>
		<tr>
			<td><input type="submit" value="사원정보 수정하기" class="btn btn-success"></td>
			<td><input type="reset" value="초기화" class= "btn btn-danger"></td>
		</tr>
	
		</table>
	</form>
</body>
</html>