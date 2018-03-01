<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Booking Menu</title>
</head>
<body bgcolor="#95B9C7">

			<h1>Scotia Airlines</h1>
			<h2>Booking Menu</h2>
			
			${selectedFlight.flightNumber}<br/>
			<br></br>									
		
		<form action="bookingMenuForm" method="POST">
	
		<input type="submit" value="Cancel A Reservation" name="bookingMenuChoice"/>
		<br/><br/>
		<input type="submit" value="Reserve A Seat" name="bookingMenuChoice"/>
		<br/><br/>
		<input type="submit" value="Book A Seat" name="bookingMenuChoice"/>
		<br/><br/>
		<input type="submit" value="Return to MainMenu" onclick="location.href='mainMenu'"/>
		</form>
			
		
</body>
</html>

