<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body bgcolor="#95B9C7">

		<h1>Scotia Airlines</h1>
		<h2>Island</h2>
		<br/><br/>	
		
		
<form action="getIslandForm" method="POST">
Please enter name of island? : <input type="text" name = "txtIslandName"/>

<br/><br/>
<input type="submit" value = "Submit"	name="islandChoice"/>
<br/><br/>
<input type="submit" value = "Back To Passenger Name"	name="islandChoice"/>


</form>
</body>
</html>