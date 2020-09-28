package model;

public enum Aircraft {
	airbus("view/resources/chosenAircraft/Airbus1_A380.jpg"),
	bombardier("view/resources/chosenAircraft/bombardier1_crj200.png"),
	embraer("view/resources/chosenAircraft/Embraer1_175.jpg");
	
	private String urlAircraft;
	
	private Aircraft(String urlAircraft){
		this.urlAircraft= urlAircraft;
	}
	
	public String getUrlAircraft(){
		return this.urlAircraft;
	}
	
}
