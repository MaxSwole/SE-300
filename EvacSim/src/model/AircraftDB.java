package model;

public class AircraftDB {
	public double[][] getSeatCoordinates() {
		
		double seatAx = 97;
		double seatBx = 104;
		double seatCx = 124;
		double seatDx = 131;
		double initialY = 148;
		double yGap = 12;
		
		//double[][] seatCoordinates = { {seatAx,initialY+(0*yGap)}, {seatBx,initialY+(0*yGap)}, {seatCx,initialY+(0*yGap)}, {seatDx,initialY+(0*yGap)}, //row 1
		//		{seatAx,initialY+(1*yGap)}, {seatBx,initialY+(1*yGap)}, {seatCx,initialY+(1*yGap)}, {seatDx,initialY+(1*yGap)}, //row 2
		//		{seatAx,initialY+(2*yGap)}, {seatBx,initialY+(2*yGap)}, {seatCx,initialY+(2*yGap)}, {seatDx,initialY+(2*yGap)}, //row 3
		//		{seatAx,initialY+(3*yGap)}, {seatBx,initialY+(3*yGap)}, {seatCx,initialY+(3*yGap)}, {seatDx,initialY+(3*yGap)}, //row 4
		//		{seatAx,initialY+(4*yGap)}, {seatBx,initialY+(4*yGap)}, {seatCx,initialY+(4*yGap)}, {seatDx,initialY+(4*yGap)}, //row 5
		//		{seatAx,initialY+(5*yGap)}, {seatBx,initialY+(5*yGap)}, {seatCx,initialY+(5*yGap)}, {seatDx,initialY+(5*yGap)}, //row 6
		//		{seatAx,initialY+(6*yGap)}, {seatBx,initialY+(6*yGap)}, {seatCx,initialY+(6*yGap)}, {seatDx,initialY+(6*yGap)}, //row 7
		//		{seatAx,initialY+(7*yGap)}, {seatBx,initialY+(7*yGap)}, {seatCx,initialY+(7*yGap)}, {seatDx,initialY+(7*yGap)}, //row 8
		//		{seatAx,initialY+(8*yGap)}, {seatBx,initialY+(8*yGap)}, {seatCx,initialY+(8*yGap)}, {seatDx,initialY+(8*yGap)}, //row 9
		//		{seatAx,initialY+(9*yGap)}, {seatBx,initialY+(9*yGap)}, {seatCx,initialY+(9*yGap)}, {seatDx,initialY+(9*yGap)}, //row 10
		//		{seatAx,initialY+(10*yGap)}, {seatBx,initialY+(10*yGap)}, {seatCx,initialY+(10*yGap)}, {seatDx,initialY+(10*yGap)}, //row 11
		//		{seatAx,initialY+(11*yGap)}, {seatBx,initialY+(11*yGap)}, {seatCx,initialY+(11*yGap)}, {seatDx,initialY+(11*yGap)}, //row 12
		//		{seatAx,initialY+(12*yGap)}, {seatBx,initialY+(12*yGap)} }; //row 13
		
		double[][] seatCoordinates = { {-101.5,-115}, {-84,-115}, {-36,-115}, {-18.5,-115}, //row 1
				{-101.5,-87.8}, {-84,-87.8}, {-36,-87.8}, {-18.5,-87.8}, //row 2
				{-101.5,-60.6}, {-84,-60.6}, {-36,-60.6}, {-18.5,-60.6}, //row 3
				{-101.5,-33.4}, {-84,-33.4}, {-36,-33.4}, {-18.5,-33.4}, //row 4
				{-101.5,-6.2}, {-84,-6.2}, {-36,-6.2}, {-18.5,-6.2}, //row 5
				{-101.5,21}, {-84,21}, {-36,21}, {-18.5,21}, //row 6
				{-101.5,48.2}, {-84,48.2}, {-36,48.2}, {-18.5,48.2}, //row 7
				{-101.5,102}, {-84,102}, {-36,102}, {-18.5,102}, //row 8
				{-101.5,129.2}, {-84,129.2}, {-36,129.2}, {-18.5,129.2}, //row 9
				{-101.5,156.4}, {-84,156.4}, {-36,156.4}, {-18.5,156.4}, //row 10
				{-101.5,183.6}, {-84,183.6}, {-36,183.6}, {-18.5,183.6}, //row 11
				{-101.5,210.8}, {-84,210.8}, {-36,210.8}, {-18.5,210.8}, //row 12
				{-101.5,238}, {-84,238} }; //row 13
		
		
		/*
		int numRows = 10;
		int numCols = 4;
		double isleLocation = 3;
		double initialX = 97;
		initialY = 148;
		double seatSize = 7; //square size in pixels taken from Sprite.java
		double isleGap = 12;
		double seatGap = 0;
		double[][] coordinates = new double[numRows][numCols];
		
		for(int yCt=0; yCt<numRows; yCt++) {
			for(int xCt=0; xCt<numCols; xCt++) {
				coordinates[numRows][numCols] = {initialX+(seatGap*xCt)+(seatSize*xCt)+(isleGap*Math.floor(xCt/isleLocation)),initialY+()}; //unfinished logic {initial, gap between seats, prevents overlap, adds isle gap}
			}
		}
		*/
		
		return seatCoordinates;
	}
}
