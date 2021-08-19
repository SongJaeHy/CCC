<%@page import="com.hgs.dept.model.DeptVO"%>
<%@page import="com.sjh.model.MembersVO"%>
<%@page import="com.sjh.model.MembersDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	
	String m_id = request.getParameter("m_id");
	String m_pw = request.getParameter("m_pw");
	String m_name = request.getParameter("m_name");
	Integer dept_no = (Integer)session.getAttribute("dept_no");
	String m_phone = request.getParameter("m_phone");
	String m_email = request.getParameter("m_email");
	
	MembersDAO dao = MembersDAO.getinstance();
	
	MembersVO member = new MembersVO();
	member.setM_Id(m_id);
	
	DeptVO dept = new DeptVO();
	
	dao.joinMember(member, dept);
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
<script>
    function submit() {
        document.abcd.submit();        
</script>
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