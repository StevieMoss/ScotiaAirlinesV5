package flight.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import flight.models.Airline;
import flight.models.Flight;
import flight.models.Seat;

@Controller
@RequestMapping("/getSeatNumberForm")
public class GetSeatNumberFormController {

	@RequestMapping(method = RequestMethod.POST)
	public String loadDefault (HttpServletRequest request, @RequestParam Map<String, String> requestParams)
	{
		Airline scotia;
		scotia = (Airline)request.getSession().getAttribute("airline");
		Flight selectedFlight;
		selectedFlight = (Flight)request.getSession().getAttribute("selectedFlight");
		
		Seat selectedSeat = scotia.getSeat(selectedFlight.getFlightNumber(), requestParams.get("txtSeatNumber"));
		request.getSession().setAttribute("selectedSeat", selectedSeat);
		
		
	 if (request.getSession().getAttribute("menuChoice").equals("DisplaySeats"))
		{
			String seatDetails;
			seatDetails = selectedSeat.displaySeatDetails();
			request.getSession().setAttribute("message", seatDetails);
			return "genericOutput";
		}
	 
	 else
	 {
		 return "getPassengerName";
	 }
}
}
