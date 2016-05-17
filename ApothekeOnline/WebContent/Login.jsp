<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<!-- To ensure proper rendering and touch zooming for mobile -->
	<meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap core CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

    <!-- Bootstrap theme -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" rel="stylesheet">

	<title>Login</title>
</head>
<body>
<div class="container theme-showcase">
<div class="jumbotron">
	<h1><font color="#EC2A3D">I</font><font color="#0064F0">B</font><font color="#F6AD06">u</font><font color="#85BA14">y</font></h1>
	<h2>Login</h2>
	<p>(user:admin1 | passwort:admin1)
<% if(request.getSession().getAttribute("fehler")!=null){ %> 
      <h2><%=request.getSession().getAttribute("fehler")%></h2>
 <% } %> 
	<br/>
	<form action="Logincontroller" method="POST">
		<table>
			<tr><td><b>Username:</b></td><td><input  type="text" name="username" /></td></tr>
			<tr><td><b>Passwort:</b></td><td><input type="password" name="password" /><br/></td></tr>
			<tr><td><input class="btn btn-primary" type="submit" value="Login"/></td></tr>
		</table>
	</form>
	<br/>
	<form action="Registriercontroller" method="GET">
		<input class="btn btn-primary" type="submit" value="Als Kunde Registrieren"/>
	</form>
	
</div>
</div>
</body>
</html>