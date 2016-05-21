<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	if(session.getAttribute("username")==null || session.getAttribute("username").equals("null")  ){
		System.out.println("HauptseiteMitarbeiter: nicht eingeloggt -> Login");
		response.sendRedirect("Login.jsp");
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content=""/>
<meta name="author" content="Gerhard"/>

<title>KundenHauptseite</title>

<!-- Bootstrap core CSS -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"/>

<!-- Bootstrap theme -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
	rel="stylesheet"/>

</head>
<body>
	<div class="container theme-showcase" role="main">

		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">

			<h1>Willkommen <%=session.getAttribute("username")%></h1> <!-- wenn null, dann darf man sowieso nicht auf die Hauptseite zugreifen -->

<%
	if(session.getAttribute("message")!=null){ %>
		<h2>Neue Meldung: <%=session.getAttribute("message") %></h2>
<% 		request.getSession().setAttribute("message", null);
	} 
	if(request.getSession().getAttribute("fehler")!=null){ %> 
		<h2>Achtung Fehler aufgetreten: <%=request.getSession().getAttribute("fehler")%></h2>
<% 		request.getSession().setAttribute("fehler", null);
	} 
%>

			<div>
				<form class="navbar-form navbar-left"
					action="SucheProduktController" method="post">
					<div class="form-group">
						<b>Produkt:</b> <input class="form-control" name="suchwert"
							type="text" size="80" />
					</div>
					<input class="btn btn-primary" type="submit" value="suchen" />
				</form>

			</div>


			<br />
			<br />


			<div>
				<form action="Logincontroller" method="GET">
					<input class="btn btn-primary" name="logout" type="submit"
						value="Logout" />
				</form>

			</div>

		</div>
	</div>
</body>
</html>