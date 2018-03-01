package flight.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import flight.models.Airline;
import flight.models.Flight;
import flight.models.IslandResident;
import flight.models.Seat;

@Controller
@RequestMapping("/getIslandForm")

public class GetIslandFormController {

	@RequestMapping(method = RequestMethod.POST)
	public String loadDefault(HttpServletRequest request, @RequestParam Map<String,String> requestParams)
	{
		
		String textFromButton = request.getParameter("islandChoice");
		//user selects submit button
		if(textFromButton.equals("Submit"))	
			
		{
			
		String output = "";   //default output
		String name;		//declaring name
		//Make the name the passengers name from the session
		name = request.getSession().getAttribute("passengerName").toString(); 
		//make island = the text box from txtIslandName
		String island = requestParams.get("txtIslandName");  
		//create a new Island Resident with name and island
		IslandResident newPassenger = new IslandResident(name,island); 
				
		int choice;
		choice =  (int) request.getSession().getAttribute("bookingChoice"); //Make choice equal to session bookingChoice
		
		Airline scotia;
		scotia = (Airline)request.getSession().getAttribute("airline");		//Load the session airline into scotia
		
		Flight selectedFlight;
		selectedFlight = (Flight)request.getSession().getAttribute("selectedFlight");	//Make selectedFlight = Session variable selectedFlight 
				
		Seat selectedSeat;
		selectedSeat = (Seat)request.getSession().getAttribute("selectedSeat")	;   //Make selectedSeat = session variable selectedSeat
		//selectedSeat.setPassenger(newPassenger);
		
		int fullSeatStatus;
		//Make fullseatStatus change the selected seats status passing scotia,choice,newPassenger and selectedFlight
		fullSeatStatus = selectedSeat.changeSeatStatus(scotia,choice,newPassenger,selectedFlight);  
				
		//selectedSeat.setPassenger(newPassenger);
		selectedFlight.updateSeat(fullSeatStatus);  //Update the seat using fullSeatStatus pulling from selectedFlight
		selectedFlight.calculateTotalFlightTakings(); // recalculates the total taking for the flight
		
		//scotia.getFlights().get(selectedFlight).getSeats().put(selectedSeat.getSeatNumber(), selectedSeat);

		scotia.clearSeatsAndPassengersFromDB();
		scotia.saveSeatsToDB();
		
		if (fullSeatStatus == -1)
		{
			output = "Error " + selectedSeat.getSeatNumber() + " is already free";
		}
		
		else if (fullSeatStatus == 1)
		{
			output = selectedSeat.getSeatNumber() + " has been cancelled";
		}
		
		else if (fullSeatStatus == 2)
		{
			output = selectedSeat.getSeatNumber() + " has been cancelled without a refund";
		}
		
		else if (fullSeatStatus == 3)
		{
			output = selectedSeat.getSeatNumber() + " has now been reserved by " + newPassenger.getPassengerName();
		}
		
		else if (fullSeatStatus == -2 || fullSeatStatus == -4)
		{
			output = "Error " + selectedSeat.getSeatNumber() + " is already reserved by " + selectedSeat.getPassenger().getPassengerName();
		}
		
		else if (fullSeatStatus == -3 || fullSeatStatus == -5)
		{
			output = "Error " + selectedSeat.getSeatNumber() + " is already booked by " + selectedSeat.getPassenger().getPassengerName();
		}
		
		else if(fullSeatStatus == 4 || fullSeatStatus == 5) //Booking successful
		{
			output = selectedSeat.getSeatNumber() + " has now been booked by "  + newPassenger.getPassengerName();
		}
		
		request.getSession().setAttribute("message", output);
		return "genericOutput";
		}
		else
		{
			return "getPassengerName";
		}
	
	}
	
}
	

