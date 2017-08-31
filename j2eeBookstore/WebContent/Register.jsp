<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<script>

function checkUsername(){
	var check = false;
	var username = document.getElementById("userName");
	if(username.length > 10){
		document.getElementById("checktest1").innerHTML = " X 不要多于10位";
		check = false;
	}else{
		document.getElementById("checktest1").innerHTML = " ✅ ️";
		check = true;
	}
	return check;
}


</script>
</head>
<body>
<center>
	<form action="RegisterServlet" method="post" onsubmit="return check()">
			<b>用户注册</b>
			</br>
			
				<label>用户名：</label>
				<input type="text" name="userName" onchange="checkUsername()"/>
				<span id="checktext1"></span>
			</br>
			<label> 密码：</label>
			<input type="password" name="password" />
			
			</br>
			<input type="submit" value="注册" />
		
			
			
	</form>
	</center>
</body>
</html>