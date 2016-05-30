<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="oracle.jdbc.driver.*"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Produktübersicht</title>
</head>
<body>
<% 		
Connection con = null;
Statement stmt = null;
ResultSet rs = null;


try {
    	Class.forName("oracle.jdbc.driver.OracleDriver");		
		String database = "jdbc:oracle:thin:@oracle-lab.cs.univie.ac.at:1521:lab";
  		String user = "a1363772";
  		String pass = "PRise16";
  		
  		con = DriverManager.getConnection(database, user, pass);
        stmt = con.createStatement();
        
        rs = stmt.executeQuery("SELECT * FROM ISE_Product");
        
        while( rs.next()){
        	out.print("<div class=\"product\">");
        	out.print("<h2>"+rs.getString("name")+"</h1>");
        	out.print("<p class=\"description\">"+rs.getString("description") +"</p>");
        	out.print("<p class=\"price\">"+rs.getString("price")+"</p>");
        	%> 
        	<form action="ShopController" method="GET">
							<input class="btnAdd2Cart" name="zumWarenkorb" type="submit"
								value="zum Warenkorb" />
			</form>
        	<%
        	out.print("</div>");
        }
	
	} catch (Exception e) {
	      System.err.println(e.getMessage());
	} finally {
        try {
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
%>
  		
</body>
</html>