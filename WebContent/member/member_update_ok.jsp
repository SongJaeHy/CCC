<%@page import="com.sjh.model.MemberVO"%>
<%@page import="com.sjh.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	response.setCharacterEncoding("utf-8");
	request.setCharacterEncoding("utf-8");  
	// 1. 폼에서 던져준 데이터를 받습니다.
	String m_id = request.getParameter("m_id");
	String m_pw = request.getParameter("m_pw");
	String m_name = request.getParameter("m_name");
	String m_email = request.getParameter("m_email");
	
	MemberVO member = new MemberVO();
	
	MemberDAO dao = MemberDAO.getinstance();
	
	int updateResultNum = dao.UpdateMember(member);
	
	if(updateResultNum==0){
		response.sendRedirect("member_update_fail.jsp");
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>수정에 완료되었습니다.</h1>
	<a href="member_login_form.jsp">로그인으로</a>
</body>
</html>