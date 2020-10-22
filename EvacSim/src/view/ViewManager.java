package view;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
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
	
	CheckBox exit1;
	CheckBox exit2;
	CheckBox exit3;
	CheckBox exit4;
	
	private AircraftEvacSubScene aircraftSubScene;
	private AircraftEvacSubScene configurationSubScene;
	private AircraftEvacSubScene passengerSubScene;
	private AircraftEvacSubScene exitSubScene;
	
	private AircraftEvacSubScene sceneToHide;
	
	List<AircraftEvacSimButton> menuButtons;
	List<AircraftPicked> aircraftList;
	private Aircraft choosenAircraft;
	
	public ViewManager() {
		menuButtons = new ArrayList<>();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, width, height);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		
		createSubScenes();
		createButtons();
		createBackground();
		createLogo();
			
	}

	private void showSubScene(AircraftEvacSubScene subScene){
		if(sceneToHide != null){
			sceneToHide.moveSubScene();
		}
		
		subScene.moveSubScene();
		sceneToHide = subScene;
	}
	
// create sub-scenes
	
	private void createSubScenes(){

//		configurationSubScene = new AircraftEvacSubScene();
//		mainPane.getChildren().add(configurationSubScene);
	
//		passengerSubScene = new AircraftEvacSubScene();
//		mainPane.getChildren().add(passengerSubScene);

		exitSubScene = new AircraftEvacSubScene();
		mainPane.getChildren().add(exitSubScene);
			
		
		createPassengerSubScene();
		createAircraftSubScene();
		createConfigurationSubScene();
	}
	
// sub-scene for passenger
	private void createPassengerSubScene(){
		passengerSubScene = new AircraftEvacSubScene();
		mainPane.getChildren().add(passengerSubScene);
		passengerSubsceneLabel();
	}
	
	
// label for configuration sub-scene 	
	private void passengerSubsceneLabel(){
		Label text = new Label("PASSENGER COUNT: ");
		Label text2 = new Label("PASSENGER GENDER: ");
		text.setFont(Font.font("Calibri Light", 18));
		text.setLayoutX(10);
		text.setLayoutY(10);
		text2.setFont(Font.font("Calibri Light", 18));
		text2.setLayoutX(10);
		text2.setLayoutY(70);
		passengerSubScene.getPane().getChildren().addAll(text, text2);
	}	
	
// sub-scene for configuration
	private void createConfigurationSubScene(){
		configurationSubScene = new AircraftEvacSubScene();
		mainPane.getChildren().add(configurationSubScene);
		configurationSubScene.getPane().getChildren().addAll(createExitToChoose());
		configurationSubScene.getPane().getChildren().add(createNextButton());
		configurationSubsceneLabel();
	}
	
// method for checking number of exit 	
	private GridPane createExitToChoose(){
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
		grid.setPadding(new Insets(10,10,10,10));
		grid.setLayoutX(70);
		grid.setLayoutY(100);
		return grid ;  
	}
	
//	box status for configuration sub scene
	private void checkBoxStatus(){
		
		
	}
		
// label for configuration sub-scene 	
	private void configurationSubsceneLabel(){
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
		aircraftSubScene.getPane().getChildren().add(createNextButton());
		aircraftSubsceneLabel();	
	}
		
// label for aircraft sub-scene method
	private void aircraftSubsceneLabel(){
		Label text = new Label("CHOOSE YOUR AIRCRAFT");
		text.setFont(Font.font("Verdana", 20));
		text.setLayoutX(130);
		text.setLayoutY(10);
		aircraftSubScene.getPane().getChildren().add(text);
	}

// box for aircraft image
	private HBox createAircraftToChoose(){
		HBox box = new HBox();
		box.setSpacing(60);
		aircraftList = new ArrayList<>();
		for(Aircraft aircraft : Aircraft.values()){
			AircraftPicked aircraftToPick = new AircraftPicked(aircraft);
			aircraftList.add(aircraftToPick);
			box.getChildren().add(aircraftToPick);
			aircraftToPick.setOnMouseClicked(new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent event) {
					for(AircraftPicked aircraft : aircraftList){
						aircraft.setIsCircleChoosen(false);
					}
					aircraftToPick.setIsCircleChoosen(true);
					choosenAircraft = aircraftToPick.getAircraft();
				}	
			});
		}
		box.setLayoutX(64);
		box.setLayoutY(50);
		return box;
	}

	
	private Canvas startSimCanvas() {
		Canvas canvas = new Canvas();
		GraphicsContext gc = canvas.getGraphicsContext2D();
		canvas.setLayoutX(64);
		canvas.setLayoutY(50);
		gc.setFill(Color.ALICEBLUE);
		gc.fillRect(40, 50, 10, 10);
		
		
		
		return canvas;
	}
	
	
	public Stage getMainStage(){	
		return mainStage;
	}
	
// this method add menu button to the pane and set the layout X and Y
	private void addMenuButton(AircraftEvacSimButton button){
		button.setLayoutX(MenuButtonStartX);
		button.setLayoutY(MenuButtonStartY + menuButtons.size()*90);
		menuButtons.add(button);
		mainPane.getChildren().add(button);
	}
	
// add your buttons to the create buttons methods
	private void createButtons(){
		
		createAircraftButton();
		createConfigurationButton();
		createPassengerButton();
		createExitButton();
		createStartButton();	
	}
	
// create your buttons	
	private void createAircraftButton(){
		
		AircraftEvacSimButton aircraftButton = new AircraftEvacSimButton("AIRCRAFT");
		addMenuButton(aircraftButton);
		
		aircraftButton.setOnAction( new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				showSubScene(configurationSubScene);	
//				showSubScene(aircraftSubScene);	
				

			}	
		});
	}
	
	private void createConfigurationButton(){
		
		AircraftEvacSimButton configurationButton = new AircraftEvacSimButton("CONFIGURATION");
		addMenuButton(configurationButton);
		
		configurationButton.setOnAction( new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				showSubScene(aircraftSubScene);
//				showSubScene(configurationSubScene);
			}	
		});

	}
	
	private void createPassengerButton(){
		
		AircraftEvacSimButton passengerButton = new AircraftEvacSimButton("PASSENGER");
		addMenuButton(passengerButton);
		
		passengerButton.setOnAction( new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
//				showSubScene(aircraftSubScene);	
//				showSubScene(configurationSubScene);
//				showSubScene(passengerSubScene);	
				
			
			}	
		});
	}
	
	private void createExitButton(){
		
		AircraftEvacSimButton exitButton = new AircraftEvacSimButton("EXIT");
		addMenuButton(exitButton);
		
		exitButton.setOnAction( new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				mainStage.close();	
			}	
		});
	}

	private AircraftEvacSimButton createNextButton(){
		
		AircraftEvacSimButton nextButton = new AircraftEvacSimButton("NEXT");
		nextButton.setLayoutX(450);
		nextButton.setLayoutY(350);
		nextButton.setPrefSize(80, 20);
		nextButton.setOnAction( new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
//				createConfigurationButton();
				showSubScene(configurationSubScene);
			}	
		});
		return nextButton;
	}
	
	private void createViewButton(){
		AircraftEvacSimButton viewButton = new AircraftEvacSimButton("VIEW");
		addMenuButton(viewButton);
		
		viewButton.setOnAction( new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
					
			}	
		});
	}
	
	private void createStartButton(){
		
		AircraftEvacSimButton startButton = new AircraftEvacSimButton("START");
		startButton.setLayoutX(640);
		startButton.setLayoutY(600);
		startButton.setPrefSize(120, 10);
//		addMenuButton(startButton);
		mainPane.getChildren().add(startButton);
		startButton.setOnAction( new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				try {
					GameManager gameMan = new GameManager();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mainStage.close();
			}	
		});
	}
	
	
// create background
	
	private void createBackground(){
		
		Image backgroundImage = new Image("view/resources/background_image.jpg", 800,800,false,true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
	}

// create logo
	
	private void createLogo(){
		ImageView logo = new ImageView("/view/resources/aircraft_logo.png");
		logo.setLayoutX(340);
		logo.setLayoutY(0);
		
		logo.setOnMouseEntered( new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(new DropShadow());	
			}	
		});
		
		logo.setOnMouseExited( new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(null);	
			}	
		});
		mainPane.getChildren().add(logo);
	}
	
}
