package model;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class AircraftPicked extends VBox{

	private ImageView circleImage;
	private ImageView aircraftImage;
	
	private String circleNotChoosen = "view/resources/chosenAircraft/circle_white.png";
	private String circleChoosen = "view/resources/chosenAircraft/circle_blue.png";
	
	private Aircraft aircraft;
	
	private boolean isCircleChoosen;
	
	public AircraftPicked(Aircraft aircraft){
		circleImage = new ImageView(circleNotChoosen);
		aircraftImage = new ImageView(aircraft.getUrlAircraft());
		this.aircraft = aircraft;
		isCircleChoosen = false;
		this.setAlignment(Pos.CENTER);
		this.setSpacing(20);
		this.getChildren().add(circleImage);
		this.getChildren().add(aircraftImage);
			
	}
	
	public Aircraft getAircraft(){
		return aircraft;	
	}
	
	
	public void setIsCircleChoosen(boolean isCircleChoosen){
		this.isCircleChoosen = isCircleChoosen;
		String imageToSet = this.isCircleChoosen ? circleChoosen : circleNotChoosen;
		circleImage.setImage(new Image(imageToSet));
	}
	
	
}
