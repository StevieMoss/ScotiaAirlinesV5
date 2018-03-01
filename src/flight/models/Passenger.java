package flight.models;

public abstract class Passenger
{
	
	protected float discountAmount;
	protected String passengerName;
	
	//getter and setter
	public float getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(float amount) {
		discountAmount = amount;
	}
	
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String name) {
		passengerName = name;
	}
	
	//constructor
	public Passenger ()
	{
		
		discountAmount = 1;
		passengerName = "";
	}

	//overloaded constructor
	public Passenger (float amount)
	{
		discountAmount = amount;
		passengerName = "";
	}
	
}

