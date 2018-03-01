package flight.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import flight.models.Airline;
@Controller
@RequestMapping("/mainMenuForm")

public class MainMenuFormController {

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView loaddefault(HttpServletRequest request)
	{ 
		String textFromButton = request.getParameter("mainMenuChoice");

		if(textFromButton.equals("Booking Menu"))
		{
			request.getSession().setAttribute("menuChoice", "Booking");
		}
		
		else if (textFromButton.equals("Display Seat Details"))
		{
			request.getSession().setAttribute("menuChoice", "DisplaySeats");
		}
		
		else if (textFromButton.equals("Display Flight Details"))
		{
			request.getSession().setAttribute("menuChoice", "DisplayFlights");
		}
				
			Airline scotia;
			scotia = (Airline)request.getSession().getAttribute("airline");
			return new ModelAndView ("selectFlight", "scotia", scotia);
		}
}
