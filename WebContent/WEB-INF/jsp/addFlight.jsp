<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Add New Flight</title>
</head>

<body bgcolor="#95B9C7">
		
		<h1>Scotia Airlines</h1>
		<h2>Add New Flight</h2>
		<br />
		<form action = "submitNewFlight" method = "POST">
		
		Flight Number: <br />
		<input type = "text" name = "txtFlightNumber" /> <br />
		<br />
		Departure: <br />
		<input type = "text" name = "txtDeparture" /> <br />
		<br />
		Arrival: <br />
		<input type = "text" name = "txtArrival" /> <br />
		<br />
		No. of Rows: <br />
		<input type = "text" name = "txtRows" /> <br />
		<br />
		No. of Columns: <br />
		<input type = "text" name = "txtColumns" /> <br />
		<br />		
		<h3>${message1}</h3>
		<input type = "submit" value = "Submit"/>
		
		<input type = "submit" value = "Clear" onclick="location.href='addFlight'"/>
		<br/><br/></form><p></p>
		<input type = "submit" value = "Return To Admin Options" onclick = "location.href='adminOptions'"/>
		
						
		
</body>
</html>
		

