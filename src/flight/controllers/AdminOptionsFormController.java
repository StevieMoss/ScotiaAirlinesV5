package flight.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import flight.models.Airline;


@Controller
@RequestMapping("/adminOptionsForm")

public class AdminOptionsFormController {

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView AdminChoice(HttpServletRequest request) 
	{
		//Get text from button clicked
		String textFromButton = request.getParameter("adminOptionsChoice");
		
		if(textFromButton.equals("Change Flight Status"))
		{
			request.getSession().setAttribute("menuChoice", "ChangeFlightStatus");
		}
		
		Airline scotia;
		scotia = (Airline)request.getSession().getAttribute("airline");
		return new ModelAndView ("selectFlight", "scotia", scotia);
	}

}

