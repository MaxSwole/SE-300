// ***************************************************************
// Name: Miguel Moore
// Date: 4/26/2019
//
// This file will:
//    -- Create an array (list) with the passengers that will board the aircraft and their parameters (age, sex, and disability status),
//    -- Assign a seat to each passenger by shuffling the seatCoordinates array found in the Aircraft class,
//    -- Sit passengers at their assigned seats,
//    -- Calculate the closest emergency exit for each passenger,
//    -- Move each passenger based on their parameters.
//
// ***************************************************************

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GenericPassenger {

	Aircraft aircraft = new Aircraft();
	int paxCount = 50;
	int[][] list = new int[50][4]; //[planeCapacity][attributes]
	double[][] assignSeats = new double[50][2]; //same as aircraft.seatCoordinates, but randomly shuffled
	double[][] distanceToExit = new double[50][3];
	ArrayList<Integer> randomize = new ArrayList<Integer>();
	SimPane simPane;

	public GenericPassenger(SimPane sp) {
		simPane = sp;
		assignSeats();
		
	}

	public GenericPassenger() {

	}

	public void list() {
		Random r = new Random();
		for (int i=0; i<50; i++) {
			list[i][0] = i+1; //passenger ID
			list[i][1] = (int) Math.abs(Math.round(r.nextGaussian()*20+35)); //age
			list[i][2] = (int) Math.round(Math.random()); //sex
			list[i][3] = (int) Math.abs(Math.round(r.nextGaussian()*0.25)); //disability status
		}
	//	System.out.println(Arrays.deepToString(list));

	}
	
	public void assignSeats() {
		
		for (int i=0; i<50; i++) {
			randomize.add(i);
		}
		Collections.shuffle(randomize);

		for (int i=0; i<50; i++) {
			assignSeats[i][0] = aircraft.seatCoordinates()[randomize.get(i)][0];
			assignSeats[i][1] = aircraft.seatCoordinates()[randomize.get(i)][1];
		}		
	}

	public double[][] sitPassenger() {

		return assignSeats;

	}

	public void chooseExit() {
		for (int i=0; i<paxCount; i++) {
			distanceToExit[i][0] = 999999999.0;
		}

		for (int i=0; i<paxCount; i++) {
			for (int j=0; j<aircraft.getExitLocation().length; j++) {
				double tempDistance = Math.sqrt(Math.pow(assignSeats[i][0]-aircraft.getExitLocation()[j][0], 2) + Math.pow(assignSeats[i][1]-aircraft.getExitLocation()[j][1], 2));
				if (tempDistance < distanceToExit[i][0]) {
					distanceToExit[i][0] = tempDistance; //distance to closest exit
					distanceToExit[i][1] = aircraft.getExitLocation()[j][0]; //x-coordinate of such exit
					distanceToExit[i][2] = aircraft.getExitLocation()[j][1]; //y-coordinate of such exit
				}
			}
		}

	}

	public void move() {
		simPane.circleLocation[0][0].setCenterX(simPane.circleLocation[0][0].getCenterX()+0);
		simPane.circleLocation[0][0].setCenterY(simPane.circleLocation[0][0].getCenterY()-1);
	}

	public void openOverwingExit() {

	}

	public void exitAircraft() {

	}

	public void reenterAircraft() {

	}

	public void helpPax() {

	}

}