// ***************************************************************
// Name: Miguel Moore
// Date: 4/26/2019
//
// This file will:
//    -- Create an array aircraftInfo with aircraft parameters such as maximum capacity and number of emergency exits,
//    -- Provide the x and y locations of the seats for the different aircraft by means of an array,
//    -- Provide the x and y locations of the emergency exits for the different aircraft by means of an array.
//
// ***************************************************************

public class Aircraft {
	
	int[][] aircraftInfo = { {50, 4}, //CRJ-200 {capacity, exits}
			{69, 4}, //ERJ-170 {capacity, exits}
			{120, 8} //A320-200 {capacity, exits}
	}; 

	public double[][] seatCoordinates() {
		double[][] seatCoordinates = { {97.5,148}, {116,148}, {164.5,148}, {183,148}, //row 1
				{97.5,175.2}, {116,175.2}, {164.5,175.2}, {183,175.2}, //row 2
				{97.5,202.4}, {116,202.4}, {164.5,202.4}, {183,202.4}, //row 3
				{97.5,229.6}, {116,229.6}, {164.5,229.6}, {183,229.6}, //row 4
				{97.5,256.8}, {116,256.8}, {164.5,256.8}, {183,256.8}, //row 5
				{97.5,284.0}, {116,284.0}, {164.5,284.0}, {183,284.0}, //row 6
				{97.5,311.2}, {116,311.2}, {164.5,311.2}, {183,311.2}, //row 7
				{97.5,346.0}, {116,346.0}, {164.5,346.0}, {183,346.0}, //row 8
				{97.5,373.2}, {116,373.2}, {164.5,373.2}, {183,373.2}, //row 9
				{97.5,400.4}, {116,400.4}, {164.5,400.4}, {183,400.4}, //row 10
				{97.5,427.6}, {116,427.6}, {164.5,427.6}, {183,427.6}, //row 11
				{97.5,454.8}, {116,454.8}, {164.5,454.8}, {183,454.8}, //row 12
				{97.5,482.0}, {116,482.0} }; //row 13
		return seatCoordinates;
	}

	public double[][] getExitLocation() {
		double[][] exitLocation = { {96,107}, {184,107}, {86,325}, {193,325} }; //exit1{x,y}, exit 2{x,y}, exit 3{x,y}, exit 4{x,y}
		return exitLocation;
	}

}