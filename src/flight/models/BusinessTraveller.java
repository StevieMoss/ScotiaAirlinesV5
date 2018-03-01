package flight.models;

public class BusinessTraveller extends Passenger
{
	private String companyName;
	
	//getter and setter
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	//constructor
	public BusinessTraveller()
	{
		super(0.75f);
		companyName = "";
	}

	//overloaded constructor
	public BusinessTraveller(String name, String companyName)
	{
		super(0.75f);
		passengerName = name;
		this.companyName = companyName;
		
	}

}
