package flight.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/adminOptions")

public class AdminOptionsController {

	@RequestMapping(method = RequestMethod.GET)
	public String loadDefault()
	{
		return "adminOptions";
	}
}
	
	

