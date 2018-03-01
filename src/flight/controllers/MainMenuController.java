package flight.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import flight.models.Airline;

@Controller
@RequestMapping ("/mainMenu")
public class MainMenuController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String loadMain(HttpServletRequest request)
			
	{
		//check if session is empty for airline - then load db
		if(request.getSession().getAttribute("airline") == null)
	{
			Airline scotia = new Airline();
			scotia.loadFlightsFromDB();
			scotia.loadSeatsFromDB();
			scotia.loadPassengersFromDB();
			request.getSession().setAttribute("airline", scotia);
		}
			return "mainMenu";
		}
	}

