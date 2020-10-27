package view;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.AircraftDB;
import model.Sprite;

public class GameManager {
	final int WIDTH = 700;
	final int LENGTH = 900;
	
	private Stage gameStage = new Stage();
	private Canvas canvas = new Canvas();
	private BorderPane borderPane = new BorderPane();
	private Pane playPane = new Pane();
	private Pane layerPane = new Pane();
	private Scene gameScene = new Scene(borderPane, WIDTH, LENGTH);
	private GraphicsContext gc = canvas.getGraphicsContext2D();
	private long lastNanoTime = System.nanoTime();
	
	private AircraftDB aircraftDB = new AircraftDB();
	private ArrayList<Sprite> passengerList = new ArrayList<Sprite>();
	private ArrayList<Sprite> exitList = new ArrayList<Sprite>();
	private double[][] seats = aircraftDB.getSeatCoordinates();
	private int numOfPassengers;
	//private double x,y;
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
		borderPane.setCenter(layerPane);
		
		//Pane contains the Canvas
		layerPane.getChildren().add(playPane);
		layerPane.setStyle("-fx-background-color: #96919e");
		
		//Canvas is bound to the panes width and height
		//canvas.widthProperty().bind(playPane.widthProperty());
		//canvas.heightProperty().bind(playPane.heightProperty());
	
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
                //gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                //passengerList.forEach(Sprite::display);
                //passengerList.forEach(playPane.getChildren().add(passenger));
                    
                for(Sprite sp1 : passengerList) {
                	Point2D force = exitList.get(0).attract(sp1);
                	sp1.applyForce(force);
                }
                
                passengerList.forEach(Sprite::move);
                passengerList.forEach(Sprite::display);
       
            }
        }.start();
    }
    
    public void addPassengers() {
    	int i = 0, j = 0;
    	double x,y;
    	passengerList.clear();
    	
    	for(i = 0; i < numOfPassengers; i++) {
    		x = seats[i][0] + 200;
    		Sprite passenger = new Sprite();
    		for (j = 0; j < seats[i].length; j++) {
            	y = seats[i][j] + 200;
            	Point2D location = new Point2D(x, y);
            	Point2D velocity = new Point2D(0,0);
            	Point2D acceleration = new Point2D(0,0);
            	passenger.parameters(location, velocity, acceleration, randomType(0,2));
         
            }
    		//passenger.display();
    		passengerList.add(passenger);
    		playPane.getChildren().add(passenger);
    	}
    	
    }
    
    public void initializePassenger(){
    	for(int i = 0; i < numOfPassengers; i++) {
    		addPassengers();
    	}
    	
    	addExits();
    	
    	
    	
    	/*int j = 0;
    	
    	//Clears the list to run the simulation again if needed (no longer a functionality of the program, but it could be)
        passengerList.clear();
        
        //Prints number of passengers to console for testing
        System.out.println("************ " + numOfPassengers);
        
        //Adds a passenger to the passengerList for as many specified by the user
        for (int i = 0; i < numOfPassengers; i++) {
          	
            Sprite passenger = new Sprite();
            x = seats[i][0] + 200;

            //System.out.println(passenger.getPositionX());
            for (j = 0; j < seats[i].length; j++) {
            	y = seats[i][j] + 100;
            	Point2D location = new Point2D(x, y);
            	System.out.println(location.getX() + " " + location.getY());
            	passenger.setLocation(location);
            	passenger.display();
                //System.out.println(seats[i][j]);
            }
            
            //Sprite Data
            //Point2D location = new Point2D(x, y);
    		Point2D velocity = new Point2D(0, 0);
    		Point2D acceleration = new Point2D(0, 0);
                   
            //passenger.setType(randomType(0, 2));
            //passenger.setLocation(location);
            passengerList.add(passenger);
            playPane.getChildren().add(passenger);
        }
        */
    }
    
    public void addExits() {
    	double x = 200;
    	double y = 400;
    	
    	
    	Sprite exit = new Sprite();
    	exit.setRadius(10);
    	Point2D location = new Point2D(x, y);
    	Point2D velocity = new Point2D(0,0);
    	Point2D acceleration = new Point2D(0,0);
    	exit.parameters(location, velocity, acceleration, 25);
    	exit.display();
    	exitList.add(exit);
    	playPane.getChildren().add(exit);
    	
    }

    public int randomType(int min, int max) {
        int num = (int) (Math.random() * (max - min + 1) + min);
        return num;
    }
    
    public void setPassengers(int num) {
    	numOfPassengers = num;
    }

}
