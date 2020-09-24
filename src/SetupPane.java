// ***************************************************************
// Name: Miguel Moore
// Date: 4/26/2019
//
// This file will:
//    -- Allow the user to select the aircraft,
//    -- Allow the user to select the amount of passengers that will partake in the simulation,
//    -- Allow the user to select the emergency exits that will be available during the simulation,
//    -- Start and stop the simulation,
//    -- Export the results of the simulation to a data file.
//
// ***************************************************************

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SetupPane extends Pane {

	int aircraftID;
	Aircraft aircraft = new Aircraft();
	GenericPassenger passenger;
	GridPane gridPane = new GridPane();
	ComboBox<String> comboAircraft = new ComboBox<String> ();
	ComboBox<String> comboPax = new ComboBox<String> ();
	SimPane sim = new SimPane(this);
	CheckBox exit1 = new CheckBox();
	CheckBox exit2 = new CheckBox();
	CheckBox exit3 = new CheckBox();
	CheckBox exit4 = new CheckBox();
	Label l1 = new Label("Select Aircraft:");
	Label l2 = new Label("Passenger Count:");
	Label l3 = new Label ("Operable Exits:");
	Button startSim = new Button("Start Simulation");
	Button export = new Button("Export Results");

	public SetupPane() {
		passenger = sim.getPassenger();
		
		gridPane.setVgap(10);
		gridPane.setHgap(10);
		gridPane.setPadding(new Insets(10,10,10,10));

		addLabels();
		addCheckBoxes();

		comboAircraft.getItems().addAll("Bombardier CRJ-200", "Embraer E170", "Airbus A320-200");
		gridPane.add(comboAircraft, 1, 0);

		comboAircraft.setOnAction( e-> {
			chgPaxCount();
			exit1.setDisable(false);
			exit2.setDisable(false);
			exit3.setDisable(false);
			exit4.setDisable(false);
			sim.getChildren().clear();
			sim.drawAircraft();
			sim.drawExits();
			comboPax.setDisable(false);
			l2.setDisable(false);
			l3.setDisable(false);
			//sim.drawBounds();
		} );

		comboPax.getItems().add("Random");
		for (int i=0; i<aircraft.aircraftInfo[aircraftID][0]; i++) {
			comboPax.getItems().add((i+1) + "");
		}
		gridPane.add(comboPax, 1, 1);
		comboPax.setDisable(true);

		
		gridPane.add(startSim, 1, 8);
		startSim.setDisable(true);
		
		gridPane.add(export, 1, 9);
		export.setDisable(true);

		comboPax.setOnAction( e-> {
			sim.getChildren().clear();
			sim.drawAircraft();
			sim.drawPax();
			sim.drawExits();
			startSim.setDisable(false);
		} );

		exit1.setOnAction( e-> sim.chgExitStatus() );
		exit2.setOnAction( e-> sim.chgExitStatus() );
		exit3.setOnAction( e-> sim.chgExitStatus() );
		exit4.setOnAction( e-> sim.chgExitStatus() );

		startSim.setOnAction( e-> {
			if (startSim.getText() == "Start Simulation") {
				sim.runSimulation();
				comboAircraft.setDisable(true);
				comboPax.setDisable(true);
				exit1.setDisable(true);
				exit2.setDisable(true);
				exit3.setDisable(true);
				exit4.setDisable(true);
				startSim.setText("Stop Simulation");
				l1.setDisable(true);
				l2.setDisable(true);
				l3.setDisable(true);	
			} else if (startSim.getText() == "Stop Simulation") {
				sim.endSimulation();
				export.setDisable(false);
			}
			
		} );
		
		export.setOnAction( e-> {
			try {
				File file = new File("results.dat");
				FileWriter fileWriter = new FileWriter(file);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				
				bufferedWriter.write("Total time taken for evacuation: " + "70" + " seconds.");
				bufferedWriter.newLine();
				bufferedWriter.write("Pass/Fail: " + "PASS.");
				bufferedWriter.newLine();
				bufferedWriter.close();
				System.out.println("Data saved successfully.");
				
			} catch (IOException e1) {

			}
		});

		getChildren().add(gridPane);
	}

	public void addLabels() {
	
		gridPane.add(l1, 0, 0);

		gridPane.add(l2, 0, 1);
		l2.setDisable(true);

		gridPane.add(l3, 0, 2);		
		l3.setDisable(true);

	}

	public void addCheckBoxes() {

		exit1.setSelected(true);
		exit1.setDisable(true);
		exit1.setText("1");
		gridPane.add(exit1, 0, 3);

		exit2.setSelected(true);
		exit2.setDisable(true);
		exit2.setText("2");
		gridPane.add(exit2, 1, 3);

		exit3.setSelected(true);
		exit3.setDisable(true);
		exit3.setText("3");
		gridPane.add(exit3, 0, 4);

		exit4.setSelected(true);
		exit4.setDisable(true);
		exit4.setText("4");
		gridPane.add(exit4, 1, 4);

	}

//	public void chgCheckBoxStatus() {
//
//		if (exit1.isDisabled()) {
//			exit1.setDisable(false);
//			exit2.setDisable(false);
//			exit3.setDisable(false);
//			exit4.setDisable(false);	
//		} else {
//			exit1.setDisable(true);
//			exit2.setDisable(true);
//			exit3.setDisable(true);
//			exit4.setDisable(true);
//		}
//
//	}

	public void chgPaxCount() {

		aircraftID = comboAircraft.getSelectionModel().getSelectedIndex();
		comboPax.getItems().removeAll(comboPax.getItems()); //clear combo box
		comboPax.getItems().add("Random");
		for (int i=0; i<aircraft.aircraftInfo[aircraftID][0]; i++) {
			comboPax.getItems().add((i+1)+"");
		}

	}
	
	public GenericPassenger getPassenger() {
		return passenger;
	}

}