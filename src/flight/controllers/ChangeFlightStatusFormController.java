package flight.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import flight.models.Airline;
import flight.models.Flight;

@Controller
@RequestMapping("/changeFlightStatusForm")
public class ChangeFlightStatusFormController {
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView loadDefault(HttpServletRequest request)
	{
		String textFromButton = request.getParameter("changeFlightStatusChoice");
		
		if(textFromButton.equals("Return to Flight Selection"))
		{
			request.getSession().setAttribute("menuChoice", "ChangeFlightStatus");
			Airline scotia;
			scotia = (Airline)request.getSession().getAttribute("airline");
			return new ModelAndView("selectFlight", "scotia", scotia);
		}
		else if(textFromButton.equals("Seats Available"))
		{
			Flight selectedFlight = (Flight)request.getSession().getAttribute("selectedFlight");
			selectedFlight.setFlightStatus(4);
			request.getSession().setAttribute("selectedFlight", selectedFlight);
			String output = "Flight " + selectedFlight.getFlightNumber() + " has seats now available";
			request.setAttribute("message", output);
			return new ModelAndView ("genericOutput", "message", output);
		}
		
		else if(textFromButton.equals("Checking In"))
		{
			Flight selectedFlight = (Flight)request.getSession().getAttribute("selectedFlight");
			selectedFlight.setFlightStatus(1);
			request.getSession().setAttribute("selectedFlight", selectedFlight);
			String output = "Flight " + selectedFlight.getFlightNumber() + " is now checking in";
			request.setAttribute("message", output);
			return new ModelAndView ("genericOutput", "message", output);
		}
		
		else if(textFromButton.equals("Boarding"))
		{
			Flight selectedFlight = (Flight)request.getSession().getAttribute("selectedFlight");
			selectedFlight.setFlightStatus(2);
			request.getSession().setAttribute("selectedFlight", selectedFlight);
			String output = "Flight " + selectedFlight.getFlightNumber() + " is now boarding";
			request.setAttribute("message", output);
			return new ModelAndView ("genericOutput", "message", output);
		}
		
		else 
		{
			Flight selectedFlight = (Flight)request.getSession().getAttribute("selectedFlight");
			selectedFlight.setFlightStatus(3);
			request.getSession().setAttribute("selectedFlight", selectedFlight);
			String output = "Flight " + selectedFlight.getFlightNumber() + " is now closed";
			request.setAttribute("message", output);
			return new ModelAndView ("genericOutput", "message", output);
		}
		
	
	}

}



