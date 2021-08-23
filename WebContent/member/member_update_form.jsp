<%@page import="com.sjh.model.MemberVO"%>
<%@page import="com.sjh.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 1 세션을 통해 아이디를 가져옵니다.
	//	 만약 로그인 상태가 아니면 로그인창으로 리다이렉트합니다.
 	String sessionId = (String)session.getAttribute("i_s");
	if(sessionId == null){
 		response.sendRedirect("member_login_form.jsp");
	}
	// 2. dao를 통해 MemberVO를 가지고 와야 합니다.
	MemberDAO dao = MemberDAO.getinstance();
	MemberVO member = new MemberVO();
	member.setM_Id(sessionId);
	
	
	// 3. 들고온 MemberVO를 이용해 아래 html태그의 value속성에 표현식을 이용해
	//		MemberVO의 아이디, 이름, 이메일을 기입하게 만들어줍니다.
	MemberVO resultData = dao.getMemberInfo(member);
	System.out.println("DB에서 가져온 데이터 :"+ resultData);
	
	// resultData내부의 데이터가 null인 경우는 조회가 실패한 경우이므로 로그인창으로 돌아가기
	if(resultData.getM_Id() == null){
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