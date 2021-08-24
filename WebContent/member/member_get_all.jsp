<%@page import="com.sjh.model.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.sjh.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
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
				<th>사원 부서번호</th>
				<th>사원 아이디</th>
				<th>사원 이름</th>
				<th>사원 이메일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="member" items="${memberList }">
				<tr>
					<td>${member.dept_no}</td>
					<td>${member.m_id}</td>
					<td>${member.m_name}</td>
					<td>${member.m_email }</td>
				</tr>
			</c:forEach>				
	</tbody>
	</table>
	<c:if test="${pageDTO.hasMember()}">
	<ul class="pagination">
		<%-- 이전 --%>
			<c:if test="${pageDTO.startPage > 10}" >
			<li class="page-item"><a class="page-link" href="/ccs/memberselect.do?page=${pageDTO.startPage -10}">«
				«
			</a></li>
				
			
		</c:if>
		
		<%-- 페이지 번호 10개 묶음을 깔아주는 부분 --%>
		<c:forEach var="pNo" begin="${pageDTO.startPage }" end="${pageDTO.endPage }">
			<li class="page-item"><a class="page-link" href="/ccs/memberselect.do?page=${pNo }">1
			${pNo }
			</a></li>
			
		</c:forEach>
		
		<%-- 다음으로 가기 버튼을 표시할지 말지 결정하는 부분 --%>
		<c:if test="${pageDTO.endPage < pageDTO.totalPages }">
		    <li class="page-item"><a class="page-link" href="/ccs/memberselect.do?page=${pageDTO.startPage+10 }">»
				»
		    </a></li>
		</c:if>
		</ul>
		</c:if>
		<br/>
		<a href="/ccs/logout.do">로그아웃</a>
</body>
</html>