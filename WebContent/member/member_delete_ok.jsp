<%@page import="com.sjh.model.MemberVO"%>
<%@page import="com.sjh.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	response.setCharacterEncoding("utf-8");
	request.setCharacterEncoding("utf-8");
	// 세션쪽 아이디, 비밀번호, 그리고 사용자가 폼으로 보낸 비밀번호 얻기
	String spw = (String)session.getAttribute("p_s");
	String dpw =  request.getParameter("dpw");
	
	String sessionId = (String)session.getAttribute("i_s");
	
	// 1.DAO를 생성하고 
	MemberDAO dao = MemberDAO.getinstance();
	// 2. MembersVO를 생성하되, spw, sessionId만 setter로 넣어주세요.
	MemberVO member = new MemberVO();
	
	member.setM_Pw(sessionId);
	member.setM_Id(spw);
	
	// 3. DAO의 deleteMember 기능을 호출하면서 파라미터로 적절한 자료를
	// 넘겨주세요.
	int deleteResultNum = dao.DeleteMember(member, dpw);
	
	// 4. 경과에 따라 세션만 파기할지 redirect까지 해 줄지 결정
	if(deleteResultNum == 1){
		session.invalidate();
	} else if(deleteResultNum == 0){
		session.invalidate();
		response.sendRedirect("member_login_form.jsp");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>계정 삭제가 완료되었습니다</h1>
	<a href="member_login_form.jsp">삭제 확인 및 돌아가기</a>
</body>
</html>