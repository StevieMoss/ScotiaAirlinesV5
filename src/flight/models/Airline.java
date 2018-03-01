package flight.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class Airline 
{
	private HashMap<String, Flight> flights;
	
	
	public Airline()
	{
		flights = new HashMap<String, Flight>();
	}

	//Hash Map that will store the collection of flights
	public HashMap<String, Flight> getFlights() 
	{
		return flights;
	}
	
	//adds flight to the collection
	public void AddFlight(Flight newFlight)
	{
		if 
		(!flights.containsKey(newFlight.getFlightNumber()))
		{
			flights.put(newFlight.getFlightNumber(), newFlight);
		}
	}

	//gets seat from a flight and passes the values of flight number and seat number
	public Seat getSeat(String flightNumber, String seatNumber)
	{
		if (flights.containsKey(flightNumber))
		{
			Flight currentFlight = flights.get(flightNumber);
		
			if (currentFlight.getSeats().containsKey(seatNumber))
			{
				//creates a temporary seat
				Seat tempseat = currentFlight.getSeats().get(seatNumber);
				return tempseat;
			}
			else
			{
				Seat tempseat = new Seat(seatNumber);
				currentFlight.getSeats().put(seatNumber, tempseat);
						return tempseat;
			}
		}
		else
		{
			return null;
		}
	}
	//loading the flights to the database
	public void loadFlightsFromDB()
	{
		try
		{
			// load the UCanAccess driver
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			 // connect to the database using the DriverManager
			Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Satanic_Braten\\Desktop\\ScotiaAirlinesV5\\Airline.accdb");
			Statement stmt  = conn.createStatement();
			//returning the results from the database query. 
			ResultSet rs = stmt.executeQuery("SELECT * FROM Flight");
			
			while (rs.next())
			{
				String Departure = rs.getString(1);
				String Arrival = rs.getString(2);
				int Rows = rs.getInt(3);
				int Columns = rs.getInt(4);
				String flightNumber = rs.getString(5);
				
				Flight newFlight = new Flight(Columns, Rows);
				newFlight.setFlightDetails(flightNumber, Departure, Arrival);
				flights.put(flightNumber, newFlight);
			}
		}
		catch (Exception ex)
		{
			String message = ex.getMessage();
		}
	}
	
	//clears flights from the database 
	 public void clearFlightsFromDB()
	 {
		try
		{
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Satanic_Braten\\Desktop\\ScotiaAirlinesV5\\Airline.accdb");
			Statement stmt  = conn.createStatement();
			stmt.executeUpdate("DELETE * FROM Flight");
		}
		
		catch (Exception ex)
		{
			String message = ex.getMessage();
		}
	}

	//saving the current session flights to the database
	public void saveFlightsToDB ()
	{
		try
		{
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Satanic_Braten\\Desktop\\ScotiaAirlinesV5\\Airline.accdb");
			Statement stmt  = conn.createStatement();
		
		for(Map.Entry<String, Flight> f : flights.entrySet())
		{
			Flight flight = f.getValue();
			stmt.executeUpdate("INSERT INTO Flight VALUES ('" + flight.getDeparture() + "','" + 
					flight.getArrival() + "','" + flight.getRows() + "','" + flight.getColumns() +  "','" + flight.getFlightNumber() +"')");
		}
	}
		catch (Exception ex)
		{
			String message = ex.getMessage();
		}
	}

	public void clearSeatsAndPassengersFromDB()
	 {
		try
		{
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Satanic_Braten\\Desktop\\ScotiaAirlinesV5\\Airline.accdb");
			Statement stmt  = conn.createStatement();
			stmt.executeUpdate("DELETE * FROM Seat");
			stmt.executeUpdate("DELETE * FROM Passenger");
		}
		
		catch (Exception ex)
		{
			String message = ex.getMessage();
		}
	 }
	
	//saves current session seats to the database
	 public void saveSeatsToDB()
	 {
	 try{
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Satanic_Braten\\Desktop\\ScotiaAirlinesV5\\Airline.accdb");
			Statement stmt  = conn.createStatement();
			
			for (Map.Entry<String,Flight> f : flights.entrySet())
			{
				Flight flight = f.getValue();
				for(Map.Entry<String, Seat> s : flight.getSeats().entrySet())
				{
					Seat seat = s.getValue();
					String passengerType = "";
					String passengerInfo = "";
					String passengerName = "";
				
				if (seat.getPassenger()!= null)
				{
					if (seat.getPassenger().getClass() == (new BusinessTraveller()).getClass())
					{
						passengerType = "B";
						passengerInfo = ((BusinessTraveller)seat.getPassenger()).getCompanyName();
						passengerName = seat.getPassenger().getPassengerName();
					}
					else if (seat.getPassenger().getClass() == (new IslandResident()).getClass())
					{
						passengerType = "I";
						passengerInfo = ((IslandResident)seat.getPassenger()).getIslandOfResidence();
						passengerName = seat.getPassenger().getPassengerName();
					}
					else
					{
						passengerType = "O";
						passengerInfo = String.valueOf(((OrdinaryPassenger)seat.getPassenger()).getCurrentPromotion());
						passengerName = seat.getPassenger().getPassengerName();
					}
				}
			
				stmt.executeUpdate("INSERT INTO Seat VALUES ('" + seat.getSeatNumber()
				+ "', '" + seat.getCurrentStatus() + "', '" + seat.getSeatTakings() + "', '" + flight.getFlightNumber() + "')");
	
				stmt.executeUpdate("INSERT INTO Passenger VALUES ('" + passengerType
				+ "', '" + passengerInfo + "', '" + passengerName + "', '" + seat.getSeatNumber() + "', '" + flight.getFlightNumber() + "')");
			}
		}
	 }
			catch (Exception ex)
			{
				String message = ex.getMessage();
			}
		}
	 
	 //loads the seats from the database
	 public void loadSeatsFromDB()

		{
			try
			{
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Satanic_Braten\\Desktop\\ScotiaAirlinesV5\\Airline.accdb");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM Seat");
				
				while(rs.next())
				{
					String seatNumber = rs.getString(1); 
					int currentStatus = rs.getInt(2);
					int seatTakings = rs.getInt(3);
					String flightNumber = rs.getString(4);
					
					Seat loadedSeat = getSeat(flightNumber, seatNumber);
					loadedSeat.setSeatStatus(currentStatus);
					loadedSeat.setSeatTakings(seatTakings);
				}
			}
			catch(Exception ex)
			{
				String message = ex.getMessage();
			}
		}
	 
	 //loads passengers from the database
	 public void loadPassengersFromDB()
	 {
	 try
		{
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Satanic_Braten\\Desktop\\ScotiaAirlinesV5\\Airline.accdb");
			Statement stmt  = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Passenger");
			
			
			while (rs.next())
			{
				String passengerType = rs.getString(1);
				String passengerInfo = rs.getString(2);
				String passengerName = rs.getString(3);
				String seatNumber = rs.getString(4);
				String flightNumber = rs.getString(5);
				
				Seat loadedSeat = getSeat (flightNumber, seatNumber);
				
				if(!passengerType.equals(""))
				{
					int fullSeatStatus = loadedSeat.changeSeatStatus(loadedSeat.getCurrentStatus(),
					loadedSeat.getSeatTakings(), passengerName,passengerType.charAt(0), passengerInfo);
					
					Flight loadedFlight = flights.get(flightNumber);
					loadedFlight.updateSeat(fullSeatStatus);
					loadedFlight.calculateTotalFlightTakings();
					System.out.print(loadedSeat.getPassenger().getPassengerName());
				}
			}
		}
		catch(Exception ex)
		{
			String message = ex.getMessage();
		}
	 }
			
}

		
			
	
