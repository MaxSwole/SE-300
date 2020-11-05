package view;

import java.util.ArrayList;
import java.util.stream.Stream;

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import model.AircraftDB;
import model.Sprite;

public class GameManager {
	final int WIDTH = 700;
	final int LENGTH = 900;

	private BorderPane borderPane = new BorderPane();
	private Scene gameScene = new Scene(borderPane, WIDTH, LENGTH);
	private Stage gameStage = new Stage();
	private Pane playPane = new Pane();
	private Pane layerPane = new Pane();
	private HBox bottomHBox = new HBox();
	private Button timeLabel = new Button();
	private Button pauseBt = new Button(), menuBt = new Button();
	private AnimationTimer gameLoop;
	private boolean isPaused = false;

	private AircraftDB aircraftDB = new AircraftDB();
	private ArrayList<Sprite> passengerList = new ArrayList<Sprite>();
	private ArrayList<Sprite> exitList = new ArrayList<Sprite>();
	private double[][] seats = aircraftDB.getSeatCoordinates();
//	private int numOfPassengers = 50;
	
	private int numOfPassengers;
	private int numOfExits;
	
	private ViewManager viewMan = new ViewManager();
	private int attractIndex = 0;
	private double pauseDuration = 3.0;

	GameManager() throws Exception {
		startStage(gameStage);
	}

	void startStage(Stage primaryStage) throws Exception {
		gameStage.setTitle("Simulation");
		gameStage.setResizable(false);
		gameStage.setScene(gameScene);
		gameStage.getIcons().add(new Image("view/resources/Icon.png"));
		gameStage.show();

		// layerPane contains the playPane which is in the center borderPane
		layerPane.getChildren().add(playPane);
		layerPane.setStyle("-fx-background-color: #FFFFE0");
		borderPane.setCenter(layerPane);
		buttonSetup();

	}

	public void gameLoop() {
		
		// Displays for a few paused seconds so user can see configuration
		// display() method is called so we set the circles to visible. They are now in
		// correct position.
		passengerList.forEach(Sprite::display);
		passengerList.forEach(Sprite::setVisible);
		PauseTransition pause = new PauseTransition(Duration.seconds(pauseDuration));
		pause.setOnFinished(e -> gameLoop.start());
		pause.play();

		// Once pause is finished, starts the below gameLoop
		gameLoop = new AnimationTimer() {
			long start = System.currentTimeMillis();
			@Override
			public void handle(long now) {
				int exitCount = 0;
		
				// Loop through the passengerList and attract all passengers to exits
				for (Sprite sp1 : passengerList) {
					attractIndex = sp1.nearestExit(exitList);
					sp1.setExitLocation(exitList.get(attractIndex).getLocation());
					
					Point2D force = exitList.get(attractIndex).attract(sp1);
					sp1.seperate(passengerList);
					sp1.walls(exitList);
					sp1.applyForce(force);
					
				}
				
				//Timer for the simulation
				float end = ((System.currentTimeMillis() - start) / 1000f);
				end = (float) (end - pauseDuration);
				end = (float) (Math.round(end * 10) / 10.0);
				timeLabel.setText("Elapsed Time: " + Float.toString(end));
				
				// Display and move the passengers
				passengerList.forEach(Sprite::display);
				passengerList.forEach(Sprite::move);
				for(int i = 0; i < passengerList.size(); i++) {
					if(passengerList.get(i).getAtExit() == true) {
						playPane.getChildren().remove(passengerList.get(i));
						exitCount++;
						if(exitCount == numOfPassengers) {
							this.stop();
						}
					}
				}
			}			
		};
	}

	public void addPassengers() {
		// Declaring Variables and clearing list to ensure its empty
		int i = 0, j = 0;
		double x, y;
		passengerList.clear();

		// Loop through the list and assign x seat and y seat coordinates
		for (i = 0; i < numOfPassengers; i++) {
			x = seats[i][0] + 200;
			Sprite passenger = new Sprite();
			for (j = 0; j < seats[i].length; j++) {
				y = seats[i][j] + 200;
				Point2D location = new Point2D(x, y);
				Point2D velocity = new Point2D(0, 0);
				Point2D acceleration = new Point2D(0, 0);
				passenger.parameters(location, velocity, acceleration, randomType(0, 2));

			}
			
			// Adds passengers to list, and sets them invisible since they are currently
			// positioned at 0,0 until the display() method is called.
			passengerList.add(passenger);
			passenger.setVisible(false);
			playPane.getChildren().add(passenger);
			
		}

	}

	// adds the user defined number of passengers
	public void initializePassenger() {
		for (int i = 0; i < numOfPassengers; i++) {
			addPassengers();
		}

		// adds exits
		addExits(225, 350);
		addExits(225, 450);
		addExits(225, 400);
		
		addExits(400, 400);
		addExits(400, 350);
		addExits(400, 450);
		
		
	}
	
	
	// Adds exits, exits still need to be derived from coordinates.
	// Need more than one exit
	public void addExits(double x, double y) {

		Sprite exit = new Sprite();
		exit.setRadius(5);
		Point2D location = new Point2D(x, y);
		Point2D velocity = new Point2D(0, 0);
		Point2D acceleration = new Point2D(0, 0);
		exit.parameters(location, velocity, acceleration, 25);
		exit.display();
		exitList.add(exit);
		playPane.getChildren().add(exit);

	}

	public void buttonSetup() {
		// Setting up HBox with Pause and Menu buttons
		pauseBt.setText("Pause Simulation");
		pauseBt.setOnAction(e -> pauseSimulation());

		menuBt.setText("Main Menu");
		menuBt.setOnAction(e -> switchMenu());
		
		timeLabel.setText("Elapsed Time: 0.0");


		bottomHBox.setAlignment(Pos.CENTER);
		bottomHBox.setPadding(new Insets(0, 10, 10, 0));
		bottomHBox.setMargin(menuBt, new Insets(0, 10, 0, 0));
		bottomHBox.setMargin(pauseBt, new Insets(0, 10, 0, 0));
		bottomHBox.setStyle("-fx-background-color: #FFFFE0");
		bottomHBox.getChildren().addAll(pauseBt, menuBt, timeLabel);
		borderPane.setBottom(bottomHBox);
	}

	public void switchMenu() {
		// System.exit(0);
		gameStage.close();
		gameLoop.stop();
		viewMan.startViewManager();

	}

	public void pauseSimulation() {
		if (isPaused == false) {
			gameLoop.stop();
			isPaused = true;
			System.out.println("Is Paused");
		} else {
			gameLoop.start();
			isPaused = false;
			System.out.println("Is NOT Paused");
		}
	}

	public BorderPane getBorderPane() {
		return borderPane;
	}

	// Random Method to define the 'type' of passenger.
	public int randomType(int min, int max) {
		int num = (int) (Math.random() * (max - min + 1) + min);
		return num;
	}

	
//*******************getters and Setters**********************************
	
	public int getNumOfPassengers() {
		return numOfPassengers;
	}

	// Sets the number of passengers
	public void setNumOfPassengers(int num) {
		numOfPassengers = num;
	}
	
	public int getNumOfExits() {
		return numOfExits;
	}
	
	public void setNumOfExits(int num) {
		numOfExits = num;
	}

}
