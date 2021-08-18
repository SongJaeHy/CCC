<%@page import="com.sjh.model.MembersDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
 int rst = 0;
 String id = (String)request.getParameter("id");
 MembersDAO dao = MembersDAO.getinstance();
 rst = dao.idCheck(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID 중복체크</title>
<body>
<% if(rst == 1){ %>
<img src="image/idno.png" width="290" height="166">
<!-- 아이디가 이미 존재할때 이미지 -->
<%}else{ %>
<img src="image/idok.png" width="290" height="166">
<!-- 아이디가 존재하지 않을 때 이미지 -->
<%} %>
</body>
</html>