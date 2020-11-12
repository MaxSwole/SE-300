package view;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
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
	private CheckBox exit5;
	private CheckBox exit6;
	
	private ComboBox<String> aircraftSel = new ComboBox<String> ();
	ImageView iv1 = new ImageView();
	Image crj200specs = new Image("view/resources/CRJ200_specs.jpg");
	Image erj175specs = new Image("view/resources/ERJ175_specs.jpg");

	private AircraftEvacSubScene aircraftSubScene;
	private AircraftEvacSubScene configurationSubScene;
	private AircraftEvacSubScene passengerSubScene;

	private AircraftEvacSubScene sceneToHide;

	List<AircraftEvacSimButton> menuButtons;
	List<AircraftPicked> aircraftList;
	private Aircraft choosenAircraft;

	private Spinner<Integer> passenger = new Spinner<Integer>();
	private Spinner<Integer> passengerAdult = new Spinner<Integer>();
	private Spinner<Integer> passengerChild = new Spinner<Integer>();
	private Spinner<Integer> passengerElderly = new Spinner<Integer>();
	private Spinner<Integer> passengerDisability = new Spinner<Integer>();
	
	TextField adult = new TextField();
	TextField child = new TextField();
	TextField elderly = new TextField();
	TextField disability = new TextField();
//  private Aircraft aircraft ;
	private int numOfPassengers = 50;
//  private double[][] seats = aircraft.getSeatCoordinates();
	GameManager gameMan;
	

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
		mainStage.getIcons().add(new Image("view/resources/Icon.png"));
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
			passenger.setValueFactory(passengerSpinnerValue);
			passenger.setPrefSize(60, 30);
			passenger.setLayoutX(95);
			passenger.setLayoutY(85);

		}
		else if(choosenAircraft == choosenAircraft.bombardier) {
			System.out.print("DUCK");
			SpinnerValueFactory<Integer> passengerSpinnerValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50);
			passenger.setValueFactory(passengerSpinnerValue);
			passenger.setPrefSize(60, 30);
			passenger.setLayoutX(95);
			passenger.setLayoutY(85);
		}
		else if(choosenAircraft == choosenAircraft.embraer) {
			System.out.print("Oops");
			SpinnerValueFactory<Integer> passengerSpinnerValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 69);
			passenger.setValueFactory(passengerSpinnerValue);
			passenger.setPrefSize(60, 30);
			passenger.setLayoutX(95);
			passenger.setLayoutY(85);
		}
		
	}


	
// label for passenger sub-scene 	
	private void passengerSubsceneLabel() {
		
		TextField adult = new TextField();
		adult.setPrefSize(60, 10);
		adult.setLayoutX(30);
		adult.setLayoutY(170);
		
		TextField child = new TextField();
		child.setPrefSize(60, 10);
		child.setLayoutX(450);
		child.setLayoutY(170);
		
		TextField elderly = new TextField();
		elderly.setPrefSize(60, 10);
		elderly.setLayoutX(30);
		elderly.setLayoutY(210);
		
		TextField disability = new TextField();
		disability.setPrefSize(60, 10);
		disability.setLayoutX(450);
		disability.setLayoutY(210);
/*		
		TextField totalPassenger = new TextField();
		totalPassenger.setPrefSize(60, 10);
		totalPassenger.setLayoutX(410);
		totalPassenger.setLayoutY(280);
		
		
		totalPassenger.setText(String.valueOf( adult.getText() ));
*/
		
// spinner for adult	
		SpinnerValueFactory<Integer> passengerSpinnerValue2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50);
		passengerAdult.setValueFactory(passengerSpinnerValue2);
		passengerAdult.setPrefSize(60, 20);
		passengerAdult.setLayoutX(100);
		passengerAdult.setLayoutY(170);
		
		passengerAdult.valueProperty().addListener((observed,oldValue,newValue) ->{
			
			int total =  (( (int)  newValue) * passenger.getValue()) / 100;
			
			adult.setText(   String.valueOf(total)    );
		});
		
// spinner for child
		SpinnerValueFactory<Integer> passengerSpinnerValue3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 17);
		passengerChild.setValueFactory(passengerSpinnerValue3);
		passengerChild.setPrefSize(60, 20);
		passengerChild.setLayoutX(380);
		passengerChild.setLayoutY(170);
		
		passengerChild.valueProperty().addListener((observed,oldValue,newValue) ->{
			
			int total =  (( (int)  newValue) * passenger.getValue()) / 100;
			
			child.setText(   String.valueOf(total)    );
			
		});
		
		
		
// spinner for elder
		SpinnerValueFactory<Integer> passengerSpinnerValue4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 18);
		passengerElderly.setValueFactory(passengerSpinnerValue4);
		passengerElderly.setPrefSize(60, 20);
		passengerElderly.setLayoutX(100);
		passengerElderly.setLayoutY(210);

		passengerElderly.valueProperty().addListener((observed,oldValue,newValue) ->{
			
			int total =  (( (int)  newValue) * passenger.getValue()) / 100;
			
			elderly.setText(   String.valueOf(total)    );
			
		});
		
// spinner for disable 
		SpinnerValueFactory<Integer> passengerSpinnerValue5 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 15);
		passengerDisability.setValueFactory(passengerSpinnerValue5);
		passengerDisability.setPrefSize(60, 20);
		passengerDisability.setLayoutX(380);
		passengerDisability.setLayoutY(210);

		passengerDisability.valueProperty().addListener((observed,oldValue,newValue) ->{
					
			int total =  (( (int)  newValue) * passenger.getValue()) / 100;
					
			disability.setText(   String.valueOf(total)    );
					
		});
						
		
		Label text = new Label("PASSENGER COUNT: ");
		Label text1 = new Label("DISABILITY % "); // have to finish this one
		Label text2 = new Label("PASSENGER GENDER: ");
		Label text3 = new Label("% ADULT");
		Label text4 = new Label("CHILD %");
		Label text5 = new Label("% ELDERY");
		text.setFont(Font.font("Calibri Light", 18));
		text.setLayoutX(10);
		text.setLayoutY(50);
		text1.setFont(Font.font("Calibri Light", 15));
		text1.setLayoutX(290);
		text1.setLayoutY(210);
		text2.setFont(Font.font("Calibri Light", 18));
		text2.setLayoutX(10);
		text2.setLayoutY(130);
		text3.setFont(Font.font("Calibri Light", 15));
		text3.setLayoutX(180);
		text3.setLayoutY(170);
		text4.setFont(Font.font("Calibri Light", 15));
		text4.setLayoutX(310);
		text4.setLayoutY(170);
		text5.setFont(Font.font("Calibri Light", 15));
		text5.setLayoutX(180);
		text5.setLayoutY(210);
		passengerSubScene.getPane().getChildren().addAll(text,text1, text2, text3, text4, text5, passenger,
				passengerAdult, adult,child,elderly,disability, passengerChild, passengerElderly, passengerDisability);
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
		
		exit1 = new CheckBox(" EXIT 1");
		
		exit2 = new CheckBox(" EXIT 2");
		
		exit3 = new CheckBox(" EXIT 3");		
		
		exit4 = new CheckBox(" EXIT 4");
		
		exit5 = new CheckBox(" EXIT 5");
		
		exit6 = new CheckBox(" EXIT 6");
		
		
		grid.add(exit1, 0, 0);
		grid.add(exit2, 1, 0);
		grid.add(exit3, 0, 1);
		grid.add(exit4, 1, 1);
		grid.add(exit5, 0, 2);
		grid.add(exit6, 1, 2);
		
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
	public void createAircraftSubScene() {
		aircraftSubScene = new AircraftEvacSubScene();
				
		GridPane gridPane = new GridPane();
		gridPane.setVgap(10);
		gridPane.setHgap(10);
		gridPane.setPadding(new Insets(10,10,10,10));
		gridPane.getRowConstraints().add(new RowConstraints(30));
		gridPane.getColumnConstraints().add(new ColumnConstraints(5));
		
		mainPane.getChildren().add(aircraftSubScene);
		
		ComboBox<String> aircraftSel = new ComboBox<String> (FXCollections 
                .observableArrayList("Bombardier CRJ-200", "Embraer ERJ-175"));
		
//		aircraftSel.getItems().addAll("Bombardier CRJ-200", "Embraer ERJ-175");
		
			
		gridPane.add(aircraftSel, 1, 1);
		gridPane.add(iv1, 1, 2);
		
/*		
		aircraftSel.setOnAction( e-> {
			aircraftSubSceneDrawImage();
		} );
*/
		
		aircraftSel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				if (aircraftSel.getSelectionModel().getSelectedIndex() == 0) {
					iv1.setImage(crj200specs);
					
					SpinnerValueFactory<Integer> passengerSpinnerValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50);
					passenger.setValueFactory(passengerSpinnerValue);
					passenger.setPrefSize(60, 30);
					passenger.setLayoutX(95);
					passenger.setLayoutY(85);
					
					exit5.setDisable(true);
					exit6.setDisable(true);
					
				} else if (aircraftSel.getSelectionModel().getSelectedIndex() == 1) {
					iv1.setImage(erj175specs);
					
					SpinnerValueFactory<Integer> passengerSpinnerValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 76);
					passenger.setValueFactory(passengerSpinnerValue);
					passenger.setPrefSize(60, 30);
					passenger.setLayoutX(95);
					passenger.setLayoutY(85);	
					
					exit5.setDisable(true);
					exit6.setDisable(true);
				}
			} 
			
		});

		
//		aircraftSubScene.getPane().getChildren().add(createAircraftToChoose());
		aircraftSubScene.getPane().getChildren().add(createAircraftNextButton());
		aircraftSubsceneLabel();
		aircraftSubScene.getPane().getChildren().addAll(gridPane);
		
	}
/*
	private void aircraftSubSceneDrawImage() {
		if (aircraftSel.getSelectionModel().getSelectedIndex() == 0) {
			iv1.setImage(crj200specs);
			
			System.out.print("DUCK");
			SpinnerValueFactory<Integer> passengerSpinnerValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50);
			passenger.setValueFactory(passengerSpinnerValue);
			passenger.setPrefSize(60, 30);
			passenger.setLayoutX(95);
			passenger.setLayoutY(85);
			
		} else if (aircraftSel.getSelectionModel().getSelectedIndex() == 1) {
			iv1.setImage(erj175specs);
			
			System.out.print("Oops");
			SpinnerValueFactory<Integer> passengerSpinnerValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 76);
			passenger.setValueFactory(passengerSpinnerValue);
			passenger.setPrefSize(60, 30);
			passenger.setLayoutX(95);
			passenger.setLayoutY(85);		
		}
	
	}
*/
	
	
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
		box.setLayoutY(75);
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
		nextButton.setLayoutY(370);
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
		resetButton.setLayoutY(600);
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
		startButton.setLayoutY(620);
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
		gameMan.setNumOfPassengers((int) passenger.getValue());
		
		gameMan.initializePassenger();

		// set exit coordinate
		if(exit1.isSelected()) {
			gameMan.addExits(95.5, 55);
		}
		if(exit2.isSelected()) {
			gameMan.addExits(185, 55);
		}
		if(exit3.isSelected()) {
			gameMan.addExits(88.5, 275);
		}
		if(exit4.isSelected()) {
			gameMan.addExits(192, 275);
		}
		if(exit5.isSelected()) {
			gameMan.addExits(400, 350);
		}
		if(exit6.isSelected()) {
			gameMan.addExits(400, 450);
		}
			
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
		logo.setLayoutX(400);
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
	

	public int getPassenger() {
		return (int) passenger.getValue();
	}
	
	public Stage getMainStage() {
		return mainStage;
	}


}
