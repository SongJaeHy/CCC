<%@page import="com.sjh.model.MemberVO"%>
<%@page import="com.sjh.model.MemberDAO"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
try{
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	
	String m_id = request.getParameter("m_id");
	String m_pw = request.getParameter("m_pw");
	String m_name = request.getParameter("m_name");
	String strdept_no=request.getParameter("dept_no");
	Integer dept_no = (Integer)session.getAttribute(strdept_no);
	String m_phone = request.getParameter("m_phone");
	String m_email = request.getParameter("m_email");
	
	MemberDAO dao = MemberDAO.getinstance();
	
	MemberVO member = new MemberVO();
	member.setM_Id(m_id);
	member.setM_Pw(m_pw);
	member.setM_Name(m_name);
	member.setDept_no(dept_no);
	member.setM_Phone(m_phone);
	member.setM_Email(m_email);
	dao.joinMember(member);
}catch(Exception e){
	e.printStackTrace();
}
%>
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
		<a href="https://www.nave.r.com" class="p c_logo" id="log.naver">
		<span class="blian">CCS</span>
		</a>
	</h1>
	
	<form action="/ccs/login.do" method="post">
	<table border="1">
		<tr>
			<td>아이디</td>
			<td><input type="text" name="m_id" id="m_id" placeholder="Id"></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="m_pw" id="m_pw" maxlength=12 placeholder="Pw"></td>
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