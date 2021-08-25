<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
	String sessionId= (String)session.getAttribute("i_s");

	if(sessionId == null){
		response.sendRedirect("member_login_ok.jsp");
	}

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
</head>
<body>
	<h2>사원 탈퇴를 진행합니다.</h2>
	<form action="/ccs/delete.do" method="post">
	비밀번호를 한 번 더 입력하세요.<br/>
	삭제 진행 후에는 되돌릴 수 없으니 주의하세요.<br/>
	<table class="table">
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="mpw" id="m_pw" placeholder="삭제할 사원 비밀번호" class="form-control"><br/></td>
		</tr>
		<tr>
			<td><input type="submit" value="제출" class="btn btn-success"></td>
		</tr>
	</table>
	</form>
</body>
</html>