<%@page import="com.sjh.model.MemberVO"%>
<%@page import="com.sjh.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%

// 0. post방식 받아오기 처리
  	response.setCharacterEncoding("utf-8");
	request.setCharacterEncoding("utf-8");  	

  	// 1. 아이디 비밀번호 받아오기
 	String m_id = request.getParameter("m_id");
  	String m_pw = request.getParameter("m_pw");

  // if~else문으로 try~catch~finally를 감싸서
  // 세션이 존재할 때는 DB에서 데이터를 가져오는 로직을 생략해주세요.
  	String idSession = (String)session.getAttribute("i_s");
  	if(idSession != null){
  		m_id = idSession;
  	} else{
  		// 1. dao 생성
  		MemberDAO dao = MemberDAO.getinstance();
  		
  		// 2. dao로 로그인 검사
		MemberVO member = new MemberVO();
		member.setM_id("m_id");
		member.setM_pw("m_pw");
		
		int loginResultNum = dao.login(m_id, m_pw);
		
		if(loginResultNum == 1){
			session.setAttribute("i_s", m_id);
			session.setAttribute("p_s", m_pw);
		} else if(loginResultNum == 0){
			response.sendRedirect("member_login_form.jsp");
		}
  		
  	}
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%=m_id %>님, 환영합니다.
	<h1>로그인이 완료되었습니다.</h1>
	<a href="member/member_logout.jsp" class="btn btn-danger" role="button">로그아웃하기</a><br/>
	<a href="member/member_berinfo.jsp" class="btn btn-info" role="button">사원 정보</a><br/>
	<a href="/ccs/memberdetail.do" class="btn btn-primary" role="button">전체 사원 리스트</a>
</body>
</html>