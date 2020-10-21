package view;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.AircraftDB;
import model.Sprite;

public class GameManager {
	final int WIDTH = 700;
	final int LENGTH = 900;
	
	private Stage gameStage = new Stage();
	private Canvas canvas = new Canvas();
	private BorderPane borderPane = new BorderPane();
	private Pane pane = new Pane();
	private Scene gameScene = new Scene(borderPane, WIDTH, LENGTH);
	private GraphicsContext gc = canvas.getGraphicsContext2D();
	private long lastNanoTime = System.nanoTime();
	
	private AircraftDB aircraftDB = new AircraftDB();
	private ArrayList<Sprite> passengerList = new ArrayList<Sprite>();
	private double[][] seats = aircraftDB.getSeatCoordinates();
	private int numOfPassengers = 50;
	
	GameManager() throws Exception {
		startStage(gameStage);
		
	}
	
	void startStage(Stage primaryStage) throws Exception{
		//Setting gameStage Scene (contains the canvas, pane, BorderPane)
		gameStage.setScene(gameScene);
		gameStage.show();
		gameStage.setTitle("Simulation");
		gameStage.setResizable(false);
		
		//BorderPane contains pane as center (allows for menu items on top)
		borderPane.setCenter(pane);
		
		//Pane contains the Canvas
		pane.getChildren().add(canvas);
		pane.setStyle("-fx-background-color: #96919e");
		
		//Canvas is bound to the panes width and height
		canvas.widthProperty().bind(pane.widthProperty());
		canvas.heightProperty().bind(pane.heightProperty());
		gameSetup();
	}
	
	void gameSetup() {
		initializePassenger();
		gameLoop();
	}
	
	
    public void gameLoop(){
        new AnimationTimer(){
            @Override
            public void handle(long currentNanoTime) {
                double elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;
                lastNanoTime = currentNanoTime;

                //passengerList.forEach(sprite -> sprite.setPositionX(sprite.getPositionX()+10));
                //passengerList.forEach(sprite -> sprite.setPositionY(sprite.getPositionY()+10));
                //passengerList.forEach(sprite -> sprite.update(elapsedTime));
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                for (Sprite passenger : passengerList)
                    passenger.render(gc);
            }
        }.start();
    }
    
    public void initializePassenger(){
        passengerList.clear();
        int j = 0;
        for (int i = 0; i < numOfPassengers; i++) {
            Sprite passenger = new Sprite();
            passenger.setPositionX(seats[i][0] + 200);
            System.out.println(passenger.getPositionX());
            for (j = 0; j < seats[i].length; j++) {
                passenger.setPositionY(seats[i][j] + 100);
                System.out.println(seats[i][j]);
            }
            passenger.setType(randomType(0, 2));
            passengerList.add(passenger);
        }
    }

    public int randomType(int min, int max) {
        int num = (int) (Math.random() * (max - min + 1) + min);
        return num;
    }

}
