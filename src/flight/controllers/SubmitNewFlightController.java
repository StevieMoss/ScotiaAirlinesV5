package flight.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import flight.models.Airline;
import flight.models.Flight;

@Controller
@RequestMapping("/submitNewFlight")

public class SubmitNewFlightController {
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView SubmitFlight (@RequestParam Map<String, String> requestParams, HttpServletRequest request)
	{
		String message1;
		try
	{
		Airline scotia = (Airline)request.getSession().getAttribute("airline"); //load session
		//get all details from text boxes
		String flightNumber = requestParams.get("txtFlightNumber");
		String departure = requestParams.get("txtDeparture");
		String arrival = requestParams.get("txtArrival");
		String stringRows = requestParams.get("txtRows");
		String stringColumns = requestParams.get("txtColumns");

		int rows = Integer.parseInt(stringRows);
		int columns = Integer.parseInt(stringColumns);
		
		Flight newFlight = new Flight (columns, rows);
		newFlight.setFlightDetails(flightNumber, departure, arrival);
			
		scotia.AddFlight(newFlight);
		scotia.clearFlightsFromDB();
		scotia.saveFlightsToDB();
		request.getSession().setAttribute("airline", scotia); //save session
		
		message1 = "Flight No: " + flightNumber + " added to Flights";
	}	
		
		catch (Exception ex)
		{
			message1 = "Problem Registering New Flight";
		}

		return new ModelAndView ("addFlight", "message", message1);
	}	
}




