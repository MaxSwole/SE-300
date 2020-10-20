package view;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameManager {
	private Stage gameStage = new Stage();
	//private Scene gameScene = new Scene(pane);
	private Canvas canvas = new Canvas();
	private Pane pane = new Pane();
	
	GameManager() throws Exception {
		startStage(gameStage);
		startGame();
		
	}
	
	void startStage(Stage primaryStage) throws Exception{
		
		gameStage.show();
	}
	
	void startGame(){
		
		System.out.println("Game Manager Run");
	}

}
