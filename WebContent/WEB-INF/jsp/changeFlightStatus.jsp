<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Change Flight Status</title>
</head>
<body bgcolor="#95B9C7">

	<h1>Scotia Airlines</h1>
	<h2>Change Flight Status</h2>
	
	${selectedFlight.flightNumber}
	<br/><br/>
	
	<form action="changeFlightStatusForm" method="POST">
	
	<input type="submit" value="Seats Available" name="changeFlightStatusChoice"/>
	<br/><br/>
	<input type="submit" value="Checking In" name="changeFlightStatusChoice"/>
	<br/><br/>
	<input type="submit" value="Boarding" name="changeFlightStatusChoice"/>
	<br/><br/>
	<input type="submit" value="Flight Closed" name="changeFlightStatusChoice"/>
	<br/><br/>
		
	<br></br>
	<input type="submit" value="Return To Main Menu" onclick="location.href='mainMenu'"/>
	<br></br>
	<input type="submit" value="Return to Flight Selection" onclick="location.href='adminOptions'"/>
	</form>
		<br />
		<br />
</body>
</html>

