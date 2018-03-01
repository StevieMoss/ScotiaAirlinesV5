package flight.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import flight.models.Airline;

@Controller
@RequestMapping("/getPassengerNameForm")
public class GetPassengerNameFormController {
	
	@RequestMapping(method = RequestMethod.POST)
	public String loadDefault (HttpServletRequest request, @RequestParam Map<String, String> requestParams)
	{
		//Get text from button clicked
		String name;
		name = requestParams.get("txtPassengerName");
		request.getSession().setAttribute("passengerName", name);
		
		String textFromButton = request.getParameter("passengerType");
		
		if (textFromButton.equals("Ordinary Passenger"))
		{
			return "getPromo";
		}
		
		else if (textFromButton.equals("Island Resident"))
		{
			return "getIslandName";
		}
		
		else if (textFromButton.equals("Business Traveller"))
		{
			return "getCompanyName";
		}
		
		else if (textFromButton.equals("Return to Booking Menu"))
		{
			return "bookingMenu";
		}
		else
		{
			return null;
		}
	}
}
