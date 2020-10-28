package view;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Aircraft;
import model.AircraftEvacSimButton;
import model.AircraftEvacSubScene;
import model.AircraftPicked;

public class ViewManager {

	private static final int width = 800;
	private static final int height = 700;
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;

	private final static int MenuButtonStartX = 40;
	private final static int MenuButtonStartY = 150;

	private CheckBox exit1;
	private CheckBox exit2;
	private CheckBox exit3;
	private CheckBox exit4;

	private AircraftEvacSubScene aircraftSubScene;
	private AircraftEvacSubScene configurationSubScene;
	private AircraftEvacSubScene passengerSubScene;

	private AircraftEvacSubScene sceneToHide;

	List<AircraftEvacSimButton> menuButtons;
	List<AircraftPicked> aircraftList;
	private Aircraft choosenAircraft;

	private Spinner passengerSpinner = new Spinner();
	private Spinner passengerSpinner2 = new Spinner();
	private Spinner passengerSpinner3 = new Spinner();
	private Spinner passengerSpinner4 = new Spinner();
//  private Aircraft aircraft ;
	private int numOfPassengers = 50;
//  private double[][] seats = aircraft.getSeatCoordinates();

	public ViewManager() {
		menuButtons = new ArrayList<>();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, width, height);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		mainStage.setTitle("Aircraft Evacuation Simulator");
		createSubScenes();
		createButtons();
		createBackground();
		createLogo();
	}
	
	public void  startViewManager() {
		menuButtons = new ArrayList<>();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, width, height);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		mainStage.setTitle("Aircraft Evacuation Simulator");
		mainStage.show();
		createSubScenes();
		createButtons();
		createBackground();
		createLogo();
	}

	private void showSubScene(AircraftEvacSubScene subScene) {	
		if (sceneToHide != null) {
			sceneToHide.moveSubScene();
		}

		subScene.moveSubScene();
		sceneToHide = subScene;
	}

// create sub-scenes	
	private void createSubScenes() {

		createPassengerSubScene();
		createConfigurationSubScene();
		createAircraftSubScene();

	}

// sub-scene for passenger
	private void createPassengerSubScene() {
		passengerSubScene = new AircraftEvacSubScene();
		mainPane.getChildren().add(passengerSubScene);
		passengerSubsceneLabel();
	}

// set passenger count for passengerSpinner	
	private void numbOfPassenger() {
		if(choosenAircraft == choosenAircraft.airbus) {
			System.out.print("fuck");
			SpinnerValueFactory<Integer> passengerSpinnerValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 150);
			passengerSpinner.setValueFactory(passengerSpinnerValue);
			passengerSpinner.setPrefSize(60, 30);
			passengerSpinner.setLayoutX(95);
			passengerSpinner.setLayoutY(85);

		}
		else if(choosenAircraft == choosenAircraft.bombardier) {
			System.out.print("DUCK");
			SpinnerValueFactory<Integer> passengerSpinnerValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50);
			passengerSpinner.setValueFactory(passengerSpinnerValue);
			passengerSpinner.setPrefSize(60, 30);
			passengerSpinner.setLayoutX(95);
			passengerSpinner.setLayoutY(85);
		}
		else if(choosenAircraft == choosenAircraft.embraer) {
			System.out.print("Oops");
			SpinnerValueFactory<Integer> passengerSpinnerValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 69);
			passengerSpinner.setValueFactory(passengerSpinnerValue);
			passengerSpinner.setPrefSize(60, 30);
			passengerSpinner.setLayoutX(95);
			passengerSpinner.setLayoutY(85);
		}
		
	}

	
// label for passenger sub-scene 	
	private void passengerSubsceneLabel() {
		
		SpinnerValueFactory<Integer> passengerSpinnerValue2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 2);
		passengerSpinner2.setValueFactory(passengerSpinnerValue2);
		passengerSpinner2.setPrefSize(60, 20);
		passengerSpinner2.setLayoutX(100);
		passengerSpinner2.setLayoutY(170);
		SpinnerValueFactory<Integer> passengerSpinnerValue3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 2);
		passengerSpinner3.setValueFactory(passengerSpinnerValue3);
		passengerSpinner3.setPrefSize(60, 20);
		passengerSpinner3.setLayoutX(240);
		passengerSpinner3.setLayoutY(170);
		SpinnerValueFactory<Integer> passengerSpinnerValue4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 2);
		passengerSpinner4.setValueFactory(passengerSpinnerValue4);
		passengerSpinner4.setPrefSize(60, 20);
		passengerSpinner4.setLayoutX(100);
		passengerSpinner4.setLayoutY(210);

		Label text = new Label("PASSENGER COUNT: ");
		Label text1 = new Label("PASSENGERS "); // have to finish this one
		Label text2 = new Label("PASSENGER GENDER: ");
		Label text3 = new Label("ADULT");
		Label text4 = new Label("CHILD");
		Label text5 = new Label("ELDERY");
		text.setFont(Font.font("Calibri Light", 18));
		text.setLayoutX(10);
		text.setLayoutY(50);
		text2.setFont(Font.font("Calibri Light", 18));
		text2.setLayoutX(10);
		text2.setLayoutY(130);
		text3.setFont(Font.font("Calibri Light", 15));
		text3.setLayoutX(180);
		text3.setLayoutY(170);
		text4.setFont(Font.font("Calibri Light", 15));
		text4.setLayoutX(320);
		text4.setLayoutY(170);
		text5.setFont(Font.font("Calibri Light", 15));
		text5.setLayoutX(180);
		text5.setLayoutY(210);
		passengerSubScene.getPane().getChildren().addAll(text, text2, text3, text4, text5, passengerSpinner,
				passengerSpinner2, passengerSpinner3, passengerSpinner4);
	}

// sub-scene for configuration
	private void createConfigurationSubScene() {
		configurationSubScene = new AircraftEvacSubScene();
		mainPane.getChildren().add(configurationSubScene);
		configurationSubScene.getPane().getChildren().addAll(createExitToChoose());
		configurationSubScene.getPane().getChildren().add(createConfigurationNextButton());
		configurationSubsceneLabel();
	}

// method for checking number of exit 	
	private GridPane createExitToChoose() {
		GridPane grid = new GridPane();
		exit1 = new CheckBox(" 1");
		exit1.setSelected(false);
		exit2 = new CheckBox(" 2");
		exit3 = new CheckBox(" 3");
		exit4 = new CheckBox(" 4");

		grid.add(exit1, 0, 0);
		grid.add(exit2, 1, 0);
		grid.add(exit3, 0, 1);
		grid.add(exit4, 1, 1);

		grid.setVgap(50);
		grid.setHgap(100);
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setLayoutX(70);
		grid.setLayoutY(100);
		return grid;
	}

//	box status for configuration sub scene
	private void checkBoxStatus() {

	}

// label for configuration sub-scene 	
	private void configurationSubsceneLabel() {
		Label text = new Label("OPERABLE EXITS");
		Label text2 = new Label("CHOOSE NUMBER OF EXIT: ");
		text.setFont(Font.font("Verdana", 20));
		text.setLayoutX(180);
		text.setLayoutY(10);
		text2.setFont(Font.font("Calibri Light", 18));
		text2.setLayoutX(10);
		text2.setLayoutY(70);
		configurationSubScene.getPane().getChildren().addAll(text, text2);
	}

// sub-scene for aircraft
	private void createAircraftSubScene() {
		aircraftSubScene = new AircraftEvacSubScene();
		mainPane.getChildren().add(aircraftSubScene);
		aircraftSubScene.getPane().getChildren().add(createAircraftToChoose());
		aircraftSubScene.getPane().getChildren().add(createAircraftNextButton());
		aircraftSubsceneLabel();
	}

// label for aircraft sub-scene method
	private void aircraftSubsceneLabel() {
		Label text = new Label("CHOOSE YOUR AIRCRAFT");
		text.setFont(Font.font("Verdana", 20));
		text.setLayoutX(130);
		text.setLayoutY(10);
		aircraftSubScene.getPane().getChildren().add(text);
	}

// box for aircraft image
	private HBox createAircraftToChoose() {
		HBox box = new HBox();
		box.setSpacing(60);
		aircraftList = new ArrayList<>();
		for (Aircraft aircraft : Aircraft.values()) {
			AircraftPicked aircraftToPick = new AircraftPicked(aircraft);
			aircraftList.add(aircraftToPick);
			box.getChildren().add(aircraftToPick);
			aircraftToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					for (AircraftPicked aircraft : aircraftList) {
						aircraft.setIsCircleChoosen(false);
					}
					aircraftToPick.setIsCircleChoosen(true);
					choosenAircraft = aircraftToPick.getAircraft();
					
					numbOfPassenger();
				
				}
				
				
			});
		}
		box.setLayoutX(34);
		box.setLayoutY(50);
		return box;
	}
	

// this method add menu button to the pane and set the layout X and Y
	private void addMenuButton(AircraftEvacSimButton button) {
		button.setLayoutX(MenuButtonStartX);
		button.setLayoutY(MenuButtonStartY + menuButtons.size() * 90);
		menuButtons.add(button);
		mainPane.getChildren().add(button);
	}

// add your buttons to the create buttons methods
	private void createButtons() {

		createAircraftButton();
		createConfigurationButton();
		createPassengerButton();
		createExitButton();
		createStartButton();
		createResetButton();
	}

// create your buttons	
	private void createAircraftButton() {

		AircraftEvacSimButton aircraftButton = new AircraftEvacSimButton("AIRCRAFT");
		addMenuButton(aircraftButton);

		aircraftButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showSubScene(configurationSubScene);
			}
		});
	}

	private void createConfigurationButton() {

		AircraftEvacSimButton configurationButton = new AircraftEvacSimButton("CONFIGURATION");
		addMenuButton(configurationButton);

		configurationButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showSubScene(aircraftSubScene);
				
			}
			
		});

	}

	private void createPassengerButton() {

		AircraftEvacSimButton passengerButton = new AircraftEvacSimButton("PASSENGER");
		addMenuButton(passengerButton);

		passengerButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				configurationSubScene.moveSubScene();
			}
		});
	}

	private void createExitButton() {

		AircraftEvacSimButton exitButton = new AircraftEvacSimButton("EXIT");
		addMenuButton(exitButton);

		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mainStage.close();
			}
		});
	}

// create next button for aircraft sub-scene
	private AircraftEvacSimButton createAircraftNextButton() {

		AircraftEvacSimButton nextButton = new AircraftEvacSimButton("NEXT");
		nextButton.setLayoutX(450);
		nextButton.setLayoutY(350);
		nextButton.setPrefSize(80, 20);
		nextButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				aircraftSubScene.moveSubScene();

			}
		});
		return nextButton;
	}

// create next button for aircraft sub-scene
	private AircraftEvacSimButton createConfigurationNextButton() {
		AircraftEvacSimButton nextButton = new AircraftEvacSimButton("NEXT");
		nextButton.setLayoutX(450);
		nextButton.setLayoutY(350);
		nextButton.setPrefSize(80, 20);
		nextButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				configurationSubScene.moveSubScene();

			}
		});
		return nextButton;
	}

	private void createResetButton() {
		AircraftEvacSimButton resetButton = new AircraftEvacSimButton("RESET");
//		addMenuButton(resetButton);
		resetButton.setLayoutX(240);
		resetButton.setLayoutY(580);
		resetButton.setPrefSize(70, 10);
		mainPane.getChildren().add(resetButton);
		resetButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				createSubScenes();
			}
		});
	}

	private void createStartButton() {

		AircraftEvacSimButton startButton = new AircraftEvacSimButton("START");
		startButton.setLayoutX(640);
		startButton.setLayoutY(600);
		startButton.setPrefSize(120, 10);
//		addMenuButton(startButton);
		mainPane.getChildren().add(startButton);
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					startGameSetup();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// To pass information to GameManager
	// create a 'set' method in GameManager, then pass it through ViewManager here
	private void startGameSetup() throws Exception {
		
		//Creates gameManager and opens gameStage
		GameManager gameMan = new GameManager();
		mainStage.close();
		
		//Sets number of passengers in gameManager, and setup passengers
		gameMan.setPassengers((int) passengerSpinner.getValue());
		gameMan.initializePassenger();
		
		//Starts the gameLoop, this is the animation
		gameMan.gameLoop();

	}

// create background
	private void createBackground() {

		Image backgroundImage = new Image("view/resources/background_image.jpg", 800, 800, false, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
	}

// create logo	
	private void createLogo() {
		ImageView logo = new ImageView("/view/resources/aircraft_logo.png");
		logo.setLayoutX(340);
		logo.setLayoutY(0);

		logo.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(new DropShadow());
			}
		});

		logo.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(null);
			}
		});
		mainPane.getChildren().add(logo);
	}
	

	public int getPassengerSpinner() {
		return (int) passengerSpinner.getValue();
	}


	public Stage getMainStage() {
		return mainStage;
	}


}
