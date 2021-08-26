<%@page import="com.hgs.dept.model.DeptVO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	
	String m_name = request.getParameter("m_name");
	String m_id = request.getParameter("m_id");
	String m_pw = request.getParameter("m_pw");
	Integer dept_no = (Integer)session.getAttribute("dept_no");
	String m_phone = request.getParameter("m_phone");
	String m_email = request.getParameter("m_email");
	
	MembersDAO dao = MembersDAO.getinstance();
	
	MembersVO member = new MembersVO();
	
	
	dao.joinMember(member);
	dao.getMemberDept(1);
	*/
%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
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
td{padding-top;}
h1{font:12px;}
</style>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<script>
	function check_pw(){
		
		var pw = document.getElementById('mpw').value;
        var SC = ["!","@","#","$","*"];
        var check_SC = 0;

        if(pw.length < 6 || pw.length>16){
            window.alert('비밀번호는 6글자 이상, 16글자 이하만 이용 가능합니다.');
            document.getElementById('mpw').value='';
        }
        for(var i=0;i<SC.length;i++){
            if(pw.indexOf(SC[i]) != -1){
                check_SC = 1;
            }
        }
        if(check_SC == 0){
            window.alert('!,@,#,$,* 의 특수문자가 들어가 있지 않습니다.')
            document.getElementById('mpw').value='';
        }
        if(document.getElementById('mpw').value !='' && document.getElementById('repw').value!=''){
            if(document.getElementById('mpw').value==document.getElementById('repw').value){
                document.getElementById('check').innerHTML='비밀번호가 일치합니다.'
                document.getElementById('check').style.color='blue';
            }
            else{
                document.getElementById('check').innerHTML='비밀번호가 일치하지 않습니다.';
                document.getElementById('check').style.color='red';
			}
		}
	}
	
</script>
<body>
	<div id="header">
	<a href="https://www.naver.com" class="p c_logo" id="log.naver">
		<span class="blian">CCS</span>
		</a></div>
		<hr/>
	<h3>회원가입 창 </h3>
	<form action="/ccs/memberjoin.do"method="post">
	<table>
		<tr>
			<td><h3 class="text-success">아이디:</h3></td>
			<td><input type="text" name="m_id" id="m_id" placeholder="Id" class="form-control"required></td>
			<td><input type="button" value="중복확인"><br/></td>
		<tr>
			<td><h3 class="text-primary">비밀번호:</h3></td>
			<td><input type="password" name="m_pw" id="mpw" onchange="check_pw()"placeholder="소문자+특수문자(숫자포함 6~16)"><br/></td>
		</tr>
		<tr>
			<td><h3 class="text-primary">비밀번호 확인:</h3></td>
			<td><input type="password" name="m_pw1" id="repw" onchange="check_pw()">&nbsp;<span id="check"></span><br/></td>
		</tr>
		<tr>
			<td><h3 class="text-info">이름:</h3></td>
			<td><input type="text" name="m_name" placeholder="이름" class="form-contorl"><br/></td>
		</tr>
		<tr>
			<td><h3 class="text-outline-primary">부서번호:</h3></td>
			<td><input type="text" name="dept_no" id="dept_no" placeholder="부서번호" maxlength=6 class="form-control"><br/></td>
		</tr>
		<tr>
			<td><h3 class="text-info">핸드폰번호:</h3></td>
			<td><input type="tel" name="m_phone" id="m_phone" value="000-0000-0000" maxlength=13 class="form-control"><br/></td>
		</tr>
		<tr>
			<td><h3 class="text-info">이메일:</h3></td>
			<td><input type="email" name="m_email" id="m_email" placeholder="이메일" class="form-control"><br/></td>
		</tr>	
		
		<tr>
			<td><input type="submit" value="가입" class="btn btn-success"></td>
			<td><input type="reset" value="초기화" class="btn btn-warning">
		</tr>
	</table>
	<footer>
	
	</footer>
	</form>
</body>
</html>