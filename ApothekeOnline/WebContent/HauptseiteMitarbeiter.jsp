<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="Gerhard">

<title>Hauptseite Mitarbeiter</title>

<!-- To ensure proper rendering and touch zooming for mobile -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap core CSS -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Bootstrap theme -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
	rel="stylesheet">

</head>

<body>

	<!-- Fixed navbar -->
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="https://www.univie.ac.at/en/">University
				of Vienna</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a href="http://royalcoconut.bplaced.net/">bPlaced</a></li>
				<li><a href="http://homepage.univie.ac.at/a1363761">Homepage1</a></li>
				<li class="active"><a
					href="http://wwwlab.cs.univie.ac.at/~a1363761/">Homepage2</a></li>
				<li><a
					href="http://tomcat01lab.cs.univie.ac.at:31637/IBuy/login">SWE</a></li>
				<li><a href="http://wwwlab.cs.univie.ac.at/~a1363761/dbs">DBS2015</a></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
	</nav>






	<div class="container theme-showcase" role="main">

		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">

			<h1 style="color: #0B2161">
				<b>Mitarbeiter Hauptseite</b>
			</h1>
			<h2>Gerhard Schmidt</h2>
			<p>"When the English language gets in my way, I walk over it."</p>


		</div>
		<form action="Logincontroller" method="GET">
			<table>
				<tr><td><input type="submit" value="Logout" /></td></tr>
			</table>
		</form>
	</div>
</body>
</html>
