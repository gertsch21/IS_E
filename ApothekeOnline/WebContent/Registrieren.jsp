<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<!-- To ensure proper rendering and touch zooming for mobile -->
	<meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Bootstrap theme -->
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
	
	<title>Registrierung</title>
</head>
<body>
<div class="container theme-showcase" role="main">
<div class="jumbotron">
	<h1>Registrieren sie sich:</h1>
<% if(request.getSession().getAttribute("fehler")!=null){ %> 
      <h2><%=request.getSession().getAttribute("fehler")%></h2>
 <% } %> 
	<form action="Registriercontroller" method="POST">	
		<table class="table">
			<tr><th>Parameter</th><th>Wert</th></tr>
			
			<tr><td>Vorname:</td> <td><input type="text" name="vorname" value="vorname"/></td>
			<tr><td>Nachname:</td> <td><input type="text" name="nachname" value="nachname" /></td>
			<tr><td>Email:</td> <td><input type="text" name="email" value="email"/></td>
			<tr><td>Land:</td> <td><input type="text" name="land" value="land"/></td>
			<tr><td>PLZ:</td> <td><input type="text" name="plz" value="1234"/></td>
			<tr><td>Wohnort:</td> <td><input type="text" name="wohnort" value="ort"/></td>
			<tr><td>Strasse:</td> <td><input type="text" name="strasse" value="strasse"/></td>
			<tr><td>HausNr:</td> <td><input type="text" name="nummer" value="1"/></td>
			<tr><td>Username:</td> <td><input type="text" name="username" value="unamee"/></td>
			<tr><td>Password:</td> <td><input type="password" name="password" /></td>
			<tr><td>Password wiederholen:</td> <td><input type="password" name="passwordW" /></td>
			<tr><td><input type="submit" value="Send" /></td><td></td></tr>
		</table>
	</form>
	<form action="Logincontroller" method="get">
		<table>
			<tr><td><input type="submit" value="Back to login" /></td><td></td></tr>
		</table>
	</form>
</div>
</div>
</body>
</html>