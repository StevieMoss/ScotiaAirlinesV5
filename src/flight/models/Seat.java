package flight.models;

public class Seat {
	
	private String seatNumber;
	private float seatPrice;
	private float seatTakings;
	private int currentStatus;
	private Passenger passenger;
	private int fullSeatStatus;
	
	//getters and setters
	
	public int getFullSeatStatus() {
		return fullSeatStatus;
	}
	public void setFullSeatStatus(int fullSeatStatus) {
		this.fullSeatStatus = fullSeatStatus;
	}
	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	public float getSeatPrice() {
		return seatPrice;
	}
	public void setSeatPrice(float seatPrice) {
		this.seatPrice = seatPrice;
	}
	public float getSeatTakings() {
		return seatTakings;
	}
	public void setSeatTakings(float seatTakings) {
		this.seatTakings = seatTakings;
	}
	public int getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(int status) {
		this.currentStatus = status;
	}
	public Passenger getPassenger() {
		return passenger;
	}
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	
	public void setSeatStatus(int status) 
	{
		currentStatus = status;
	}
	
	//constructor
	public Seat()
	{
		seatNumber = "";
		passenger = null;
		seatPrice = 100;
		seatTakings = 0;
		currentStatus = 1;
		fullSeatStatus = 0;
	}
	
	//overloaded constructor
	public Seat(String seatNumber)
	{
		passenger = null;
		seatPrice = 100;
		seatTakings = 0;
		currentStatus = 1;
		fullSeatStatus = 0;
		this.seatNumber = seatNumber;
	}

	//displays the output details of each seat
	public String displaySeatDetails()
	{
		String output = "";
		String seatStatus = "";
		
		switch(currentStatus)
	{ 
		case 1:
			seatStatus = "Free";
			break;
		case 2:
			seatStatus = "Reserved";
			break;
		case 3:
			seatStatus = "Booked";
			break;
	}
			
		output = "<html> Seat Number: " + seatNumber + "<br /> Current Status: " + seatStatus + ""
				+ "<br /> Seat Price: £" + seatPrice + "<br /> Seat Takings: £" + seatTakings;
		
		//if passenger is not null then
		if (passenger != null)
			
			output = output + "<br /> Passenger Name: "
					+ passenger.getPassengerName();
		
		output = output + "</html>";
		return output;
}
	

	//changes the seat status
	public int changeSeatStatus (int seatStatus, float currentSeatTakings, String passengerName,
	char passengerType, String passengerInfo)
	{
		fullSeatStatus = -1;
		seatTakings = currentSeatTakings;
		
		//if the passenger is an Island resident
		if(passengerType == 'I') 
		{			
				IslandResident newPassenger = new IslandResident (passengerName, passengerInfo);
				passenger = newPassenger;
				
				if (seatStatus == 2)
					{
					 currentStatus = 2;
					 fullSeatStatus = 3;
					}
				else if (seatStatus == 3)
				{
					currentStatus = 3;
					fullSeatStatus = 4;
				}
		}
		//or else if the passenger is a Business traveller
		else if(passengerType == 'B')
		{
			BusinessTraveller newPassenger = new BusinessTraveller(passengerName, passengerInfo);
			passenger = newPassenger;
			if(seatStatus == 2)
			{
				currentStatus = 2;
				fullSeatStatus = 3;
			}
			else if(seatStatus == 3)
			{
				currentStatus = 3;
				fullSeatStatus = 4;
			}
		}
		//else if the passenger is an ordinary passenger 
		else if(passengerType == 'O')
		{
			OrdinaryPassenger newPassenger = new OrdinaryPassenger(passengerName, passengerInfo.charAt(0));
			passenger = newPassenger;
			if(seatStatus == 2)
			{
				currentStatus = 2;
				fullSeatStatus = 3;
			}
			else if(seatStatus == 3)
			{
				currentStatus = 3;
				fullSeatStatus = 4;
			}
		}
		return fullSeatStatus;
	}

	//changes the seat status
	public int changeSeatStatus(Airline myAirline, int newStatus, Passenger newPassenger, Flight newFlight)
	{
		//cancelling a seat
		if (newStatus == 1)
		{
			//cancelling a free seat, returns error message
			if(currentStatus == 1) 
			{
				return -1;	
					
			}
			
			//cancelling a reserved seat
			else if (currentStatus == 2)
			{
				currentStatus = 1;
				passenger = null;
				fullSeatStatus = 1;
			}
			
			//cancelling a booked seat
			else if (currentStatus == 3)
			{
				currentStatus = 1;
				passenger = null;
				fullSeatStatus = 2;
			}
			
		}
			//reserving seats
			else if (newStatus == 2)
			{
				//trying to reserve a seat
				if (currentStatus == 1)
				{
					currentStatus = 2;
					passenger = newPassenger;
					fullSeatStatus = 3;
				}
			
		    //trying to reserve a reserved seat, returns error message
				else if (currentStatus == 2)
				{
					return -2;
				}
				
			//trying to reserve a booked seat, returns error message
				else if (currentStatus == 3)
				{
					return -3;
				}
			}
				//booking a seat
				else if (newStatus == 3)
				{
					 //booking a free Seat			
					if (currentStatus == 1) 
					{
						currentStatus = 3;
						passenger = newPassenger;
						fullSeatStatus = 4;	
						seatTakings = seatTakings + (passenger.getDiscountAmount() * seatPrice);
						
					}
					
				//booking a reserved seat
				else if (currentStatus == 2)   
				{
					if (passenger.getPassengerName().equals(newPassenger.getPassengerName()))
					{
						currentStatus = 3;			
						fullSeatStatus = 5;					
						seatTakings = seatTakings + (passenger.getDiscountAmount() * seatPrice);
					}
					
					//returns error if passenger name is not equal
					else
					{
						return -4;
					}  
				}
					else if (currentStatus == 3)
					{
						return -5;
					}
				}
				return fullSeatStatus;
			}
			
	}

















