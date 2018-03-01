package flight.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import flight.models.Airline;

@Controller
@RequestMapping("/genericOutput")

public class GenericOutputController {
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView loadGenericOutput (HttpServletRequest request)
	{
		String message;
		message = request.getSession().getAttribute("message").toString();
		return new ModelAndView ("genericOutput", "message", message);
	}
}



