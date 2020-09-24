// ***************************************************************
// Name: Miguel Moore
// Date: 4/26/2019
//
// This file will:
//    -- Create a borderPane consisting of setupPane to the left, and simPane to the right,
//    -- Set and show stage.
//
// ***************************************************************

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SimGUI extends Application {
	
	Aircraft aircraft = new Aircraft();
	SetupPane setupPane = new SetupPane();
	GenericPassenger passenger = setupPane.getPassenger();
	
	@Override
	public void start(Stage simStage) {
	
		BorderPane borderPane = new BorderPane();
		borderPane.setLeft(setupPane);
		borderPane.setCenter(setupPane.sim);
		
		Scene simScene = new Scene(borderPane, 600, 1000);
		simStage.setTitle("Aicraft Evacuation Simulator");
		simStage.setScene(simScene);
		simStage.show();
		
		passenger.sitPassenger();
		passenger.chooseExit();
		//System.out.println(Arrays.deepToString(passenger.paxLocation));
		//System.out.println(Arrays.deepToString(passenger.distanceToExit));

	}

	public static void main(String[] args) {
		launch(args);
	}	

}