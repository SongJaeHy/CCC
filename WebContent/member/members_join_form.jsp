<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script>

</script>
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
	<h1>회원가입 창</h1>
	<form action="member_login_form.jsp"method="post">
	<table>
		<tr>
			<td>아이디:</td>
			<td><input type="text" name="mid" placeholder="Id" required onclick = "idcheck()"></td>
			<td><input type="button" value="중복확인" onclick="idcheck()"><br/></td>
		<tr>
			<td>비밀번호:</td>
			<td><input type="password" name="memPw" id="mpw" onchange="check_pw()"><br/></td>
		</tr>
		<tr>
			<td>비밀번호 확인:</td>
			<td><input type="password" name="memPw1" id="repw" onchange="check_pw()">&nbsp;<span id="check"></span><br/></td>
		</tr>
		<tr>
			<td>이름:</td>
			<td><input type="text" name="mname" placeholder="이름"><br/></td>
		</tr>
		<tr>
			<td>사원번호:</td>
			<td><input type="text" name="mno" placeholder="사번" maxlength=8 required><br/></td>
		</tr>
		<tr>
			<td>부서번호:</td>
			<td><input type="text" name="mdeptno" placeholder="부서명" maxlength=6><br/></td>
		</tr>
		<tr>
			<td>핸드폰번호:</td>
			<td><input type="tel" name="mphone" value="000-0000-0000" maxlength=13><br/></td>
		</tr>
		<tr>
			<td>이메일:</td>
			<td><input type="email" name="memail" placeholder="이메일"><br/></td>
		</tr>	
		<tr>
			<td><input type="submit" value="가입">></td>
		</tr>
	
	</table>
	</form>
</body>
</html>