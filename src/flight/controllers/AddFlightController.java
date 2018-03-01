package flight.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import flight.models.Airline;
import flight.models.Flight;
@Controller
@RequestMapping("/addFlight")
		
public class AddFlightController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String loadDefault()
	{
		return "addFlight";
	}
	
}

		

