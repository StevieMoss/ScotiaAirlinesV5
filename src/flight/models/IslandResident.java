package flight.models;

public class IslandResident extends Passenger
{

	private String IslandOfResidence;

	//getter and setter
	public String getIslandOfResidence() {
		return IslandOfResidence;
	}

	public void setIslandOfResidence(String islandOfResidence) {
		IslandOfResidence = islandOfResidence;
	}
	
	//constructor
	public IslandResident()
	{
		super(0.9f);
		IslandOfResidence = "";
	}
	
	//overloaded constructor
	public IslandResident(String name, String island)
	{
		super(0.9f);
		passengerName = name;
		IslandOfResidence = island;
	}

}
