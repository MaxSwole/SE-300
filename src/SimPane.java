// ***************************************************************
// Name: Miguel Moore
// Date: 4/26/2019
//
// This file will:
//    -- Display a graphical representation of the selected aircraft,
//    -- Display the location of the emergency exits and their status,
//    -- Display the location of the passengers,
//    -- Update in real time the location of the passengers as they move around the cabin toward the emergency exits.
//
// ***************************************************************

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public class SimPane extends Pane {

	Aircraft aircraft = new Aircraft();
	GenericPassenger passenger = new GenericPassenger(this);
	SetupPane setup;
	ImageView iv1 = new ImageView();
	Image crj200 = new Image("CRJ200.jpg");
	Image erj170 = new Image("ERJ170.jpg");
	Polygon exit1 = new Polygon();
	Polygon exit2 = new Polygon();
	Polygon exit3 = new Polygon();
	Polygon exit4 = new Polygon();
	Circle[][] circleLocation = new Circle[50][1];
	Timeline timeline = new Timeline();

	public SimPane(SetupPane sp) {

		this.setup = sp;
		
	}

	public void drawAircraft() {

		iv1.setFitWidth(280);
		iv1.setPreserveRatio(true);
		iv1.setSmooth(true);
		iv1.setCache(true);	
		
		if (setup.comboAircraft.getSelectionModel().getSelectedIndex() == 0) {
			iv1.setImage(crj200);
		} else if (setup.comboAircraft.getSelectionModel().getSelectedIndex() == 1) {
			iv1.setImage(erj170);
		} else if (setup.comboAircraft.getSelectionModel().getSelectedIndex() == 2) {
			try {
				Image a320 = new Image("A320.jpg");
				iv1.setImage(a320);
			} catch (Exception e) {
				System.out.println("Aircraft data not found.");
			}
		}
		getChildren().add(iv1);

	}

	public void drawPax() {

		for (int i=0; i<setup.comboPax.getSelectionModel().getSelectedIndex(); i++) {
			Circle pax = new Circle();
			pax.setLayoutX(passenger.sitPassenger()[i][0]);
			pax.setLayoutY(passenger.sitPassenger()[i][1]);
			pax.setRadius(5);
			circleLocation[i][0] = pax;
			getChildren().addAll(pax);
		}
	}

	public void drawExits() {

		exit1.getPoints().addAll(97.0, 107.0, 97.0, 117.0, 87.0, 112.0);
		exit1.setFill(Color.LAWNGREEN);
		exit1.setStroke(Color.BLACK);
		getChildren().add(exit1);

		exit2.getPoints().addAll(182.0, 107.0, 182.0, 117.0, 192.0, 112.0);
		exit2.setFill(Color.LAWNGREEN);
		exit2.setStroke(Color.BLACK);
		getChildren().add(exit2);

		exit3.getPoints().addAll(90.0, 325.0, 90.0, 335.0, 80.0, 330.0);
		exit3.setFill(Color.LAWNGREEN);
		exit3.setStroke(Color.BLACK);
		getChildren().add(exit3);

		exit4.getPoints().addAll(189.0, 325.0, 189.0, 335.0, 199.0, 330.0);
		exit4.setFill(Color.LAWNGREEN);
		exit4.setStroke(Color.BLACK);
		getChildren().add(exit4);

	}
	
	public void chgExitStatus() {
		if (setup.exit1.isSelected()) {
			exit1.setFill(Color.LAWNGREEN);
		} else {
			exit1.setFill(Color.ORANGERED);
		}

		if (setup.exit2.isSelected()) {
			exit2.setFill(Color.LAWNGREEN);
		} else {
			exit2.setFill(Color.ORANGERED);
		}

		if (setup.exit3.isSelected()) {
			exit3.setFill(Color.LAWNGREEN);
		} else {
			exit3.setFill(Color.ORANGERED);	
		}

		if (setup.exit4.isSelected()) {
			exit4.setFill(Color.LAWNGREEN);
		} else {
			exit4.setFill(Color.ORANGERED);
		}
		
	}	
	
	public void drawBounds() {
		
		double width = 18.5;
		double pitch = 27.25;
		double aisle = 67.0;
		double exitaisle = 7.0;
		double[] bounds = { 89.0, 141.0, 89.0, 159.0, 105.5, 159.0, 105.5, 141.0, 103.5, 141.0, 103.5, 157.0, 91.0, 157.0, 91.0, 141.0 };
		
		for (int i=0; i<7; i++) {
			for (int j=0; j<2; j++) {
				Polygon seat = new Polygon();
				seat.getPoints().addAll(bounds[0]+width*j, bounds[1]+pitch*i, bounds[2]+width*j, bounds[3]+pitch*i,
						bounds[4]+width*j, bounds[5]+pitch*i, bounds[6]+width*j, bounds[7]+pitch*i,
						bounds[8]+width*j, bounds[9]+pitch*i, bounds[10]+width*j, bounds[11]+pitch*i,
						bounds[12]+width*j, bounds[13]+pitch*i, bounds[14]+width*j, bounds[15]+pitch*i );		
				seat.setFill(Color.ORANGERED);
				getChildren().addAll(seat);	
			}
			for (int j=0; j<2; j++) {
				Polygon seat = new Polygon();
				seat.getPoints().addAll(bounds[0]+width*j+aisle, bounds[1]+pitch*i, bounds[2]+width*j+aisle, bounds[3]+pitch*i,
						bounds[4]+width*j+aisle, bounds[5]+pitch*i, bounds[6]+width*j+aisle, bounds[7]+pitch*i,
						bounds[8]+width*j+aisle, bounds[9]+pitch*i, bounds[10]+width*j+aisle, bounds[11]+pitch*i,
						bounds[12]+width*j+aisle, bounds[13]+pitch*i, bounds[14]+width*j+aisle, bounds[15]+pitch*i );		
				seat.setFill(Color.ORANGERED);
				getChildren().addAll(seat);	
			}
		}

		for (int i=7; i<12; i++) {
			for (int j=0; j<2; j++) {
				Polygon seat = new Polygon();
				seat.getPoints().addAll(bounds[0]+width*j, bounds[1]+pitch*i+exitaisle, bounds[2]+width*j, bounds[3]+pitch*i+exitaisle,
						bounds[4]+width*j, bounds[5]+pitch*i+exitaisle, bounds[6]+width*j, bounds[7]+pitch*i+exitaisle,
						bounds[8]+width*j, bounds[9]+pitch*i+exitaisle, bounds[10]+width*j, bounds[11]+pitch*i+exitaisle,
						bounds[12]+width*j, bounds[13]+pitch*i+exitaisle, bounds[14]+width*j, bounds[15]+pitch*i+exitaisle );		
				seat.setFill(Color.ORANGERED);
				getChildren().addAll(seat);
			}
			for (int j=0; j<2; j++) {
				Polygon seat = new Polygon();
				seat.getPoints().addAll(bounds[0]+width*j+aisle, bounds[1]+pitch*i+exitaisle, bounds[2]+width*j+aisle, bounds[3]+pitch*i+exitaisle,
						bounds[4]+width*j+aisle, bounds[5]+pitch*i+exitaisle, bounds[6]+width*j+aisle, bounds[7]+pitch*i+exitaisle,
						bounds[8]+width*j+aisle, bounds[9]+pitch*i+exitaisle, bounds[10]+width*j+aisle, bounds[11]+pitch*i+exitaisle,
						bounds[12]+width*j+aisle, bounds[13]+pitch*i+exitaisle, bounds[14]+width*j+aisle, bounds[15]+pitch*i+exitaisle );		
				seat.setFill(Color.ORANGERED);
				getChildren().addAll(seat);	
			}
		}
		
		for (int j=0; j<2; j++) {
			Polygon seat = new Polygon();
			int i=12;
			seat.getPoints().addAll(bounds[0]+width*j, bounds[1]+pitch*i+exitaisle, bounds[2]+width*j, bounds[3]+pitch*i+exitaisle,
					bounds[4]+width*j, bounds[5]+pitch*i+exitaisle, bounds[6]+width*j, bounds[7]+pitch*i+exitaisle,
					bounds[8]+width*j, bounds[9]+pitch*i+exitaisle, bounds[10]+width*j, bounds[11]+pitch*i+exitaisle,
					bounds[12]+width*j, bounds[13]+pitch*i+exitaisle, bounds[14]+width*j, bounds[15]+pitch*i+exitaisle );		
			seat.setFill(Color.ORANGERED);
			getChildren().addAll(seat);
		}
		

//		Rectangle r1 = new Rectangle(125, 80, 29.5, 450);
//		r1.setFill(Color.GREEN);
//		r1.setStroke(Color.GREEN);
//		r1.setVisible(false);
//		getChildren().add(r1);
//
//		Rectangle r2 = new Rectangle(87, 324, 106, 12);
//		r2.setFill(Color.GREEN);
//		r2.setStroke(Color.GREEN);
//		r2.setVisible(false);
//		getChildren().add(r2);
//
//		Rectangle r3 = new Rectangle(95, 105, 90, 12);
//		r3.setFill(Color.GREEN);
//		r3.setStroke(Color.GREEN);
//		r3.setVisible(false);
//		getChildren().add(r3);
//
//		Rectangle r4 = new Rectangle(91, 115, 98, 12);
//		r4.setFill(Color.GREEN);
//		r4.setStroke(Color.GREEN);
//		r4.setVisible(false);
//		getChildren().add(r4);
//
//		for (int i=0; i<8; i++) {
//			int initX = 87;
//			int initY = 130;
//
//			Rectangle r5 = new Rectangle(initX, initY+27.5*i, 106, 10);
//			r5.setFill(Color.GREEN);
//			r5.setStroke(Color.GREEN);
//			r5.setVisible(false);
//			getChildren().add(r5);
//		}
//
//		for (int i=0; i<7; i++) {
//			int initX = 93;
//			int initY = 135;
//
//			for (int j=0; j<2; j++) {
//				Rectangle r6 = new Rectangle(initX+18*j, initY+27*i, 10, 20);
//				r6.setFill(Color.GREEN);
//				r6.setStroke(Color.GREEN);
//				r6.setVisible(false);
//				getChildren().add(r6);
//			}
//
//			for (int k=0; k<2; k++) {
//				Rectangle r7 = new Rectangle(initX+67+18*k, initY+27*i, 10, 20);
//				r7.setFill(Color.GREEN);
//				r7.setStroke(Color.GREEN);
//				r7.setVisible(false);
//				getChildren().add(r7);
//			}
//
//		}
	}

	public void runSimulation() {

		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame keyframe = new KeyFrame(Duration.millis(50), action -> {
				passenger.move();
		});

		timeline.getKeyFrames().add(keyframe);
		timeline.play();

	}
	
	public void endSimulation() {
		
		timeline.stop();
		
	}
	
	public GenericPassenger getPassenger() {
		return passenger;
	}

}