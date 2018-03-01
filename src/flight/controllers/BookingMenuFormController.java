package flight.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import flight.models.Airline;
import flight.models.Flight;

@Controller
@RequestMapping("/bookingMenuForm")

public class BookingMenuFormController {
	
		String[] output = new String[3];
		
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView loadDefault(HttpServletRequest request)
 		{		
			String textFromButton = request.getParameter("bookingMenuChoice");
		
			if(textFromButton.equals("Cancel A Reservation"))
			{
				Flight selectedFlight = (Flight)request.getSession().getAttribute("selectedFlight");
				String flightNumber = selectedFlight.getFlightNumber();
					
			if (selectedFlight.isBoarding() || selectedFlight.isClosed())
			{
				String output = "Cancellations not available. Flight " + flightNumber + " is " + selectedFlight.getStatusMessage();
				request.getSession().setAttribute("message", output);
				return new ModelAndView ("genericOutput", "message", output);
			}
			
			else
			{			
				request.getSession().setAttribute("menuChoice", "Cancel");
				request.getSession().setAttribute("bookingChoice", 1);
				setOutputs (request);
				return new ModelAndView ("getSeatNumber", "output", output);
			}
		}
		else if (textFromButton.equals("Reserve A Seat"))
		{
			Flight selectedFlight = (Flight)request.getSession().getAttribute("selectedFlight");
			String FlightNumber = selectedFlight.getFlightNumber();
			
			if (selectedFlight.isBoarding() || selectedFlight.isClosed() || selectedFlight.isFull()
			|| selectedFlight.isCheckingIn())

			{
				String output = "Reservations not available. Flight " + FlightNumber + " is " + selectedFlight.getStatusMessage();
				request.getSession().setAttribute("message", output);
				return new ModelAndView ("genericOutput", "message", output);
			}
			else
			{
				request.getSession().setAttribute("menuChoice", "Reserve");
				request.getSession().setAttribute("bookingChoice", 2);
				setOutputs (request);
				return new ModelAndView ("getSeatNumber", "output", output);
			}
		}		
		
		else if(textFromButton.equals("Book A Seat"))
		{
			Flight selectedFlight = (Flight)request.getSession().getAttribute("selectedFlight");
			String flightNumber = selectedFlight.getFlightNumber();
		
			if (selectedFlight.isBoarding() || selectedFlight.isClosed() || selectedFlight.isFull())
			{
				String output = "Bookings not available. Flight " + flightNumber + " is " + selectedFlight.getStatusMessage();
				request.getSession().setAttribute("message", output);
				return new ModelAndView ("genericOutput", "message", output);
			}
		
		else 
		{
			request.getSession().setAttribute("menuChoice", "Book");
			request.getSession().setAttribute("bookingChoice", 3);
			setOutputs (request);
			return new ModelAndView ("getSeatNumber", "output", output);
		}
		}
		
		else if (textFromButton.equals("Return to flight selection"))
		{
			request.getSession().setAttribute("menuChoice", "Booking");	
			Airline scotia;
			scotia = (Airline)request.getSession().getAttribute("airline");
			return new ModelAndView ("selectFlight", "scotia", scotia);
		}
		else 
		{
			return null;
		}
		}
 		
  		 		
		public void setOutputs(HttpServletRequest request) 
		{
			if (request.getSession().getAttribute("menuChoice").equals("DisplaySeats"))	
			{
				output [0] = "Enter Seat Number to View";
			}
			
			else if (request.getSession().getAttribute("menuChoice").equals("Cancel"))	
			{
				output [0] = "Enter Seat Number to Cancel";
			}
			
			else if (request.getSession().getAttribute("menuChoice").equals("Reserve"))
			{
				output [0] = "Enter Seat Number to Reserve";
			}
			
			else if (request.getSession().getAttribute("menuChoice").equals("Book"))
			{
				output [0] = "Enter Seat Number to Book";
			}
		
		if (request.getSession().getAttribute("menuChoice").equals("DisplaySeats"))	
			{
				output [1] = "Submit";
			}
			
			else if (request.getSession().getAttribute("menuChoice").equals("Cancel"))	
			{
				output [1] = "Submit Cancellation";
			}
			
			else if (request.getSession().getAttribute("menuChoice").equals("Reserve"))
			{
				output [1] = "Submit Reservation";
			}
			
			else if (request.getSession().getAttribute("menuChoice").equals("Book"))
			{
				output [1] = "Submit Booking";
			}
		
		if (request.getSession().getAttribute("menuChoice").equals("DisplaySeats"))	
		{
			output [2] = "Return to Main Menu";
		}
		
		else 	
		{
			output [2] = "Return to Booking Menu";
		}
	}
}

 
