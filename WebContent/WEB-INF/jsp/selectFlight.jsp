<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Display All Flights</title>
</head>
<body bgcolor="#95B9C7">

	<h1>Scotia Airlines</h1>
	<h2>Please Select A Flight</h2>
		<br></br>

		<form action="afterSelectingFlight" method="POST"> 
								
		<c:forEach var="flightEntry" items="${scotia.flights}" >
		
		<input type="submit" name = "selectedFlight" value= "ID: ${flightEntry.value.flightNumber} 
		${flightEntry.value.departure} to ${flightEntry.value.arrival}"/>
		<br></br>
		</c:forEach>	
		<input type="submit" value="Return To Main Menu" onclick="location.href='mainMenu'"/>
		</form>
		
</body>
</html>

