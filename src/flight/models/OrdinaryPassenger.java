package flight.models;

public class OrdinaryPassenger extends Passenger
{
private char currentPromotion;
	
	//getter and setter
	public char getCurrentPromotion()
	{return currentPromotion;}
	
	public void setCurrentPromotion(char promo){
		this.currentPromotion = promo;
	}
	
	//constructor
	public OrdinaryPassenger()
	{
		super(1);
		currentPromotion = 'n';
	}
	//overloaded constructor
	public OrdinaryPassenger(String name, char promo)
	{
		super(1);
		passengerName = name;
		currentPromotion = promo;
	}
	
	public void findDiscount()
	{
		if (currentPromotion == 'y')
		{
			discountAmount = 0.95f;
		}
		else
		{
			discountAmount = 1;
		}
	}

}


