package flight.models;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author M20008976
 *New Attribute 'seats' HashMap 
	(Collection of seats, Key is seat number)

	Update All Constructor for Flight-
	Give a new flight a new empty HashMap for 'seats'

	GetSeats- This will return the entire HashMap of Seats
 */
public class Flight {
	
	private String flightNumber;
	private String departure;
	private String arrival;
	private Date date;
	private int freeSeats;
	private int bookedSeats;
	private int reservedSeats;
	private int columns;
	private int rows;
	private boolean isFull;
	private boolean checkingIn;
	private boolean closed;
	private boolean boarding;
	private String statusMessage;
	private float totalFlightTakings;
	private HashMap<String, Seat> seats;
	
	//getters and setters
	public float getTotalFlightTakings() {
		return totalFlightTakings;
	}


	public void setTotalFlightTakings(float totalFlightTakings) {
		this.totalFlightTakings = totalFlightTakings;
	}

	
	public String getFlightNumber() {
		return flightNumber;
	}


	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}


	public String getDeparture() {
		return departure;
	}


	public void setDeparture(String departure) {
		this.departure = departure;
	}


	public String getArrival() {
		return arrival;
	}


	public void setArrival(String arrival) {
		this.arrival = arrival;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public int getFreeSeats() {
		return freeSeats;
	}


	public void setFreeSeats(int freeSeats) {
		this.freeSeats = freeSeats;
	}


	public int getBookedSeats() {
		return bookedSeats;
	}


	public void setBookedSeats(int bookedSeats) {
		this.bookedSeats = bookedSeats;
	}


	public int getReservedSeats() {
		return reservedSeats;
	}


	public void setReservedSeats(int reservedSeats) {
		this.reservedSeats = reservedSeats;
	}


	public int getColumns() {
		return columns;
	}


	public void setColumns(int columns) {
		this.columns = columns;
	}


	public int getRows() {
		return rows;
	}


	public void setRows(int rows) {
		this.rows = rows;
	}


	public boolean isFull() {
		return isFull;
	}


	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}


	public boolean isCheckingIn() {
		return checkingIn;
	}


	public void setCheckingIn(boolean checkingIn) {
		this.checkingIn = checkingIn;
	}


	public boolean isClosed() {
		return closed;
	}


	public void setClosed(boolean closed) {
		this.closed = closed;
	}


	public boolean isBoarding() {
		return boarding;
	}


	public void setBoarding(boolean boarding) {
		this.boarding = boarding;
	}


	public String getStatusMessage() {
		return statusMessage;
	}


	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	public HashMap<String, Seat> getSeats() {
		return seats;
	}
	
	public void setFlightDetails (String flightNumber, String departure, String arrival )
	{
		this.flightNumber = flightNumber;
		this.departure = departure;
		this.arrival = arrival;
	}
	
	
	public Flight(int columns, int rows)
	{
		flightNumber = "";
		departure = ""; 
		arrival = ""; 
		freeSeats = columns * rows;
		bookedSeats = 0;
		reservedSeats = 0;
		this.columns = columns;
		this.rows = rows;
		isFull = false;
		checkingIn = false;
		closed = false;
		boarding = false;
		statusMessage = "";
		date = new Date();
		seats = new HashMap<String, Seat>();
		totalFlightTakings = 0;
	}

	//calculating the total takings of each flight
	public float calculateTotalFlightTakings()
	{
		totalFlightTakings = 0;
		for(Map.Entry<String, Seat> tempSeat : seats.entrySet())
		{
			totalFlightTakings = totalFlightTakings + tempSeat.getValue().getSeatTakings();
		}
		return totalFlightTakings;
	}
	
	public void setFlightStatus(int statusCode)
	{
		switch(statusCode)
		{
		case 1: checkingIn = true;
		boarding = false;
		closed = false;
		statusMessage = "Checking in";
		break;
		
		case 2: checkingIn = false;
		boarding = true;
		closed = false;
		statusMessage = "Boarding";
		break;
		
		case 3: checkingIn = false;
		boarding = false;
		closed = true;
		statusMessage = "Flight Closed";
		break;	
		
		case 4: checkingIn = false;
		boarding = false;
		closed = false;
		statusMessage = "Seats Available";
		break;	
		}
	}
	
	//updates seats on each flight
	public void updateSeat(int bookingChoice)
	{
		
		switch(bookingChoice)
		{
			case 1: freeSeats++;
			reservedSeats--;
			if(!boarding && !closed)
			{
				statusMessage = "Seats available";
				isFull = false;
			}
			break;
			
			case 2: freeSeats++;
			bookedSeats--;
			if(!boarding && !closed)
			{
				statusMessage = "Seats available";
				isFull = false;
			}
			break;
			
			case 3: reservedSeats++;
			freeSeats--;
			break;
			
			case 4: bookedSeats++;
			freeSeats--;
			break;
			
			case 5: bookedSeats++;
			reservedSeats--;
			break;
			
			case -1: break;
		}
		
		if(freeSeats == 0)
		{
			isFull = true;
			statusMessage = "Flight Full";
		}
	}
			
	//displays the output information for each flight 
	public String displayFlightInfo()
	{
			
			String output = "";			
				
			 output = "<html> Flight No: " + flightNumber + "<br /> " + "Arrival Airport: "	+ arrival + 
					 "<br /> Departure Airport : " + departure + 
					 "<br /> Number of free seats : " + freeSeats + 
					 "<br /> Number of reserved seats: " + reservedSeats + 
					 "<br /> Number of booked seats: " + bookedSeats +
					 "<br /> Flight Status: " + statusMessage + 
					 "<br /> Total Flight Takings: £ " + totalFlightTakings + "</html>";
	

			 return output;
				 
		}
	
	
//checks that a seat is valid
public boolean IsValidSeatNumber (String seatNumber)
{
	String number = "";
	String letter = "";
	int checkIfNum;
	int element = -1;
	boolean shouldLeaveLoop = false;

	for (char c: seatNumber.toCharArray())
	{
		 if (!shouldLeaveLoop)
		 {
			 try
			 {
				 String character;
				 character = String.valueOf(c);
				 checkIfNum = Integer.parseInt(character);
				 number = number + c;
				 element++;
			 }
		 
			 catch (Exception e)
			 {
				 shouldLeaveLoop = true;
			 }
		 }
	
	}
	
	boolean lastPartIsCharacter = true;
	letter = seatNumber.substring (+1);
			
	if(letter.length() == 1)
	{
		char letterChar = letter.charAt(0);
		if(!Character.isLetter(letterChar))
		{
			lastPartIsCharacter = false;
		}
	}
	else
	{
		lastPartIsCharacter = false;
	}
			
	try
	{
		if (Integer.valueOf("number") >= columns || number == "" || letter.length() != 1 || lastPartIsCharacter == false)
			return false;
	
	else
	{
		return false;
	}
	}
	catch (Exception e)
	{
		return false;
	}
}

}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
