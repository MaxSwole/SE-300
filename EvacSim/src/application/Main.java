package application;

import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewManager;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		//THIS IS MIGUEL
		//this is miles 3
		//This is ya boy. 
		//this Yvann
		ViewManager manager = new ViewManager();
		primaryStage = manager.getMainStage();
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}



}
