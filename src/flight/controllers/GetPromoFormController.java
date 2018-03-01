package flight.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import flight.models.Airline;
import flight.models.Flight;
import flight.models.OrdinaryPassenger;
import flight.models.BusinessTraveller;
import flight.models.Seat;

@Controller
@RequestMapping("/getPromoForm")
public class GetPromoFormController  {
	
	@RequestMapping(method = RequestMethod.POST)
	public String loadDefault (HttpServletRequest request, @RequestParam Map<String, String> requestParams)
	{
		String textFromButton = request.getParameter("promoChoice");
		if (textFromButton.equals("Submit"))
		{
			//declaring output
			String output = "";
			//declaring name
			String name;
			//gets session variable
			name = request.getSession().getAttribute("passengerName").toString();
			
			char promo = requestParams.get("txtPromo").charAt(0);
			OrdinaryPassenger newPassenger = new OrdinaryPassenger(name, promo);
								
			//int choice;
			//gets session variable
			int choice =  (int) request.getSession().getAttribute("bookingChoice");
				
			//gets session variable
			Airline scotia = (Airline)request.getSession().getAttribute("airline");
			//get session variable
			Flight selectedFlight = (Flight)request.getSession().getAttribute("selectedFlight");
			//get session variable
			Seat selectedSeat = (Seat)request.getSession().getAttribute("selectedSeat");
			//selectedSeat.setPassenger(newPassenger);
			
			int fullSeatStatus = selectedSeat.changeSeatStatus (scotia, choice, newPassenger, selectedFlight);
			
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


