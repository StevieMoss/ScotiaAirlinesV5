package flight.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import flight.models.Airline;

@Controller
@RequestMapping("/selectFlight")
public class SelectFlightController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView loadSelectFlight(HttpServletRequest request)
	{
		//Load session "airline" into 'scotia' variable
		Airline scotia = (Airline)request.getSession().getAttribute("scotia");
		return new ModelAndView ("selectFlight", "scotia", scotia); 
	}
	
	
}


