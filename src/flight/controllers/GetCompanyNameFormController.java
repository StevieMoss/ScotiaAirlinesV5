package flight.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import flight.models.Airline;
import flight.models.Flight;
import flight.models.BusinessTraveller;
import flight.models.Seat;

@Controller
@RequestMapping("/getCompanyNameForm")
public class GetCompanyNameFormController  {
	
	@RequestMapping(method = RequestMethod.POST)
	public String loadDefault (HttpServletRequest request, @RequestParam Map<String, String> requestParams)
	{
		String textFromButton = request.getParameter("companyChoice");
		if (textFromButton.equals("Submit"))
		{
			String output = "";
			String name;
			name = request.getSession().getAttribute("passengerName").toString(); //Make the name the passengers name from the session
			
			String company;
			company = requestParams.get("txtCompanyName");
			
			BusinessTraveller newPassenger = new BusinessTraveller(name, company);
			
			int choice;
			choice =  (int) request.getSession().getAttribute("bookingChoice");
			
			Airline scotia;
			scotia = (Airline)request.getSession().getAttribute("airline");
			
			Flight selectedFlight;
			selectedFlight = 
			(Flight)request.getSession().getAttribute("selectedFlight");
						
			Seat selectedSeat;
			selectedSeat = (Seat)request.getSession().getAttribute("selectedSeat");
			//selectedSeat.setPassenger(newPassenger);
			
			int fullSeatStatus;
			fullSeatStatus = selectedSeat.changeSeatStatus(scotia, choice, newPassenger, selectedFlight);
			
			selectedFlight.updateSeat(fullSeatStatus);
			selectedFlight.calculateTotalFlightTakings();
			
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
				output = selectedSeat.getSeatNumber() +  " has now been reserved by " + newPassenger.getPassengerName();
			}
			
			else if (fullSeatStatus == -2 || fullSeatStatus == -4)
			{
				output = "Error " + selectedSeat.getSeatNumber() + " is already reserved by " + selectedSeat.getPassenger().getPassengerName();
			}
			
			else if (fullSeatStatus == -3 || fullSeatStatus == -5)
			{
				output = "Error " + selectedSeat.getSeatNumber() + " is already booked by " + selectedSeat.getPassenger().getPassengerName();
			}
			else if(fullSeatStatus == 4 || fullSeatStatus == 5) //booking is successful
			{
				output = selectedSeat.getSeatNumber() + " has now been booked by " + newPassenger.getPassengerName();
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

