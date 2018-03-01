package flight.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import flight.models.Airline;
import flight.models.Flight;

@Controller
@RequestMapping("/afterSelectingFlight")

public class AfterSelectingFlightController {
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView loadDefault (HttpServletRequest request) 
	{
		//get text from button clicked
		String textFromButton = request.getParameter("selectedFlight");
		//separating ID from rest of text
		int firstSpace = textFromButton.indexOf(" ");
		String textFromButtonMinusFirstPart = textFromButton.substring(firstSpace+1);
		
		int secondSpace = textFromButtonMinusFirstPart.indexOf(" ");
		String id = textFromButtonMinusFirstPart.substring(0, secondSpace);
		
		//load airline from session variable and get specific flight from airline using id
		Airline scotia = (Airline)request.getSession().getAttribute("airline");
		Flight selectedFlight = scotia.getFlights().get(id);
		//save found Flight into session called selectedFlight
		request.getSession().setAttribute("selectedFlight", selectedFlight);
		
		//choosing BookingMenujsp (if "menuChoice" session = "Booking")
		// or we go to changeFlightStatus jsp (if "menuOption" session = "changeFlightStatus")
		if(request.getSession().getAttribute("menuChoice").equals("Booking"))
		{
			return new ModelAndView ("bookingMenu", "selectedFlight", selectedFlight);
		}
		else if (request.getSession().getAttribute("menuChoice").equals("ChangeFlightStatus")) 
		{
			return new ModelAndView ("changeFlightStatus", "selectedFlight", selectedFlight);
		}
		
		//choosing displayseats will show menu options
		else if (request.getSession().getAttribute("menuChoice").equals("DisplaySeats")) 
		{
			String[] output = new String [3];
			
			output[0] = "Enter Seat Number to View";
			output[1] = "Submit";
			output[2] = "Return to Main Menu";
			
			return new ModelAndView ("getSeatNumber", "output", output);
		}
		
		else if (request.getSession().getAttribute("menuChoice").equals("DisplayFlights"))
		{
			String message;
			message = selectedFlight.displayFlightInfo();
			request.getSession().setAttribute("message", message);
			return new ModelAndView ("genericOutput", "message", message);
		}
						
		else 
		{
		return null;
		}
		
	}
}

















