<%@page import="com.sjh.model.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.sjh.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	// 로그인하지 않은 사용자 처리
	String IdSession = (String)session.getAttribute("i_s");
	if(IdSession == null){
		response.sendRedirect("member_login_form.jsp");
	}
	
	// DB에서 전체 사원 리스트를 가져옵니다.
	// 다오 생성
	MemberDAO dao = MemberDAO.getinstance();
	ArrayList<MemberVO> memberList = dao.getAllMember();
	
	System.out.println(memberList);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>전체 사원 목록</h1>
	
	<table border="1">
		<thead>
			<tr>
				<th>사원 아이디</th>
				<th>사원 이름</th>
				<th>사원 이메일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="member" items="<%= memberList%>">
				<tr>
					<td>${member.m_Id}</td>
					<td>${member.m_Pw}</td>
					<td>${member.m_Email }</td>
				</tr>
			</c:forEach>				
	</tbody>
	
	</table>
</body>
</html>