<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>weather forecast</title>
</head>
<body>
<div style="background-color=#BEBEBE">
<form action="forward1" method="get" name="form1">
<tr>
<td>
<font color=#FF0000>Input city name: </font>
</td>
<td><input name="city" id="city" type="text"></td>
<td><input type="submit" value="search"></td>
</tr>
</form>
<br>
</div>
<div style="background-color: #FAEBD7;float:center">
<h2><%=session.getAttribute("city")%></h2>
<%=session.getAttribute("content")%>
</div>
</body>
</html>