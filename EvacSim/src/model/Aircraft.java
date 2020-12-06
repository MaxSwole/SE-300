package model;

public enum Aircraft {
	
	airbus("view/resources/chosenAircraft/A320.jpg"),
	bombardier("view/resources/chosenAircraft/CRJ200.jpg"),
	embraer("view/resources/chosenAircraft/ERJ170.jpg");
	
	private String urlAircraft;
	
	private Aircraft(String urlAircraft){
		this.urlAircraft= urlAircraft;
	}
	

	public String getUrlAircraft(){
		return this.urlAircraft;
	}
	
}
