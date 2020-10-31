package model;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Sprite extends Circle {

	// Constants; Mass essentially controls the 'speed' at which they move. Larger
	// mass, slower speed
	static final double MAXVELOCITY = 3;
	double MASS;
	static double ATTRACTION_DISTANCE_MIN = 5;
	static double ATTRACTION_DISTANCE_MAX = 25.0;
	static double GRAVITATIONAL_CONSTANT = 0.004;

	// Vectors
	private Point2D location;
	private Point2D velocity;
	private Point2D acceleration;
	private Point2D exitLocation;
	private boolean atExit = false;

	// Radius of all Circles
	private double radius = 3.0;

	// Empty Constructor
	public Sprite() {

	}

	// Basically the Sprite Constructor, but because of For loops in GameManager, it
	// has to be it's own method.
	public void parameters(Point2D location, Point2D velocity, Point2D acceleration, int type) {
		this.location = location;
		this.velocity = velocity;
		this.acceleration = acceleration;

		setRadius(radius);
		
		if (type == 0) {

			setStroke(Color.BROWN);
			setFill(Color.BROWN);
			MASS = 20;
		} else if (type == 1) {
			setStroke(Color.PURPLE);
			setFill(Color.PURPLE);

			// Different Weight for different type
			MASS = 75;
		} else if (type == 2) {
			setStroke(Color.ORANGE);
			setFill(Color.ORANGE);

			// Different Weight for different type
			MASS = 150;
		} else {
			setStroke(Color.BLACK);
			setFill(Color.BLACK);
		}
		//System.out.println(MASS);
	}

	// How the passengers are attracted to the Exits
	public Point2D attract(Sprite exitSprite) {

		// force direction
		Point2D force = location.subtract(exitSprite.location);
		//double distance = force.magnitude();

		// constrain movement
		//distance = constrain(distance, ATTRACTION_DISTANCE_MIN,
		// ATTRACTION_DISTANCE_MAX);

		force = force.normalize();

		// force magnitude
		//double strength = (GRAVITATIONAL_CONSTANT * MASS * MASS) /
		//(distance * distance);
		
		//force = force.multiply(strength);
		
		return force;
	}

	// Applying force to the passengers (gonna have to make it a constant velocity I
	// think)
	public void applyForce(Point2D force) {
		Point2D f = new Point2D(force.getX(), force.getY());
		f = f.multiply(1 / MASS);
		//f = f.multiply(MASS);
		acceleration = acceleration.add(f);

	}

	// The Core move function
	public void move() {
		if (exitLocation.distance(location) < 10) {
			velocity = new Point2D(0, 0);
			acceleration = new Point2D(0, 0);
			location = exitLocation;
			atExit = true;			

		} else {

			// set velocity depending on acceleration
			velocity = velocity.add(acceleration);

			// limit velocity to max speed
			double mag = velocity.magnitude();
			
			if (mag > MAXVELOCITY) {
				velocity = velocity.normalize();
				velocity = velocity.multiply(MAXVELOCITY);
			}

			// change location depending on velocity
			location = location.add(velocity);

			// clear acceleration
			acceleration = new Point2D(0, 0);
		}
	}

	// Sets x,y location; used in GameLoop
	public void display() {
		setCenterX(location.getX());
		setCenterY(location.getY());
	}
	
	public void seperate(ArrayList<Sprite> passengerList) {
		double desiredSeperation = 7;
		double i = 0;
		Point2D sum = new Point2D(0,0), sumResult = new Point2D(0,0);
		
		for(Sprite other : passengerList) {
			double distance = location.distance(other.location);
			
			if((distance > 0) && (distance < desiredSeperation)) {
				Point2D diff = location.subtract(other.location);
				diff.normalize();
				sumResult = sum.add(diff);
				i++;
				
			}
		}
		if(i > 0) {
			double x, y;
			x = sumResult.getX()/i;
			y = sumResult.getY()/i;
			
			Point2D newSum = new Point2D(x, y);
			applyForce(newSum);
					
		}	
	}
	
	public void walls(ArrayList<Sprite> exitList) {
	
		//Exit One Wall
		/*
		if(location.getX() <= 224 && location.getY() >=351) {
			Point2D desired = new Point2D(MAXVELOCITY, MAXVELOCITY);
			Point2D steer = desired.subtract(velocity);
			applyForce(steer);
		}
		*/
		
		//Sets right wall
		if(location.getX() >= 410) {
			Point2D desired = new Point2D(MAXVELOCITY, velocity.getY());
			Point2D steer = velocity.subtract(desired);
			applyForce(steer);
		}
		//Sets left wall
		if(location.getX() <= 220) {
			Point2D desired = new Point2D(MAXVELOCITY, velocity.getY());
			Point2D steer = desired.subtract(velocity);
			applyForce(steer);
		}
		//Sets Bottom wall
		if(location.getY() >= 500) {
			Point2D desired = new Point2D(velocity.getX(), MAXVELOCITY);
			Point2D steer = velocity.subtract(desired);
			applyForce(steer);
		}
		//Sets top wall
		if(location.getY() <= 300) {
			Point2D desired = new Point2D(velocity.getX(), MAXVELOCITY);
			Point2D steer = desired.subtract(velocity);
			applyForce(steer);
		}
	}

	
	public int nearestExit(ArrayList<Sprite> exitList) {
		int listIndex = 0;
		
		Point2D tempExit, currentExit;
		
		for(int i = 0; i < exitList.size(); i++) {
			currentExit = exitList.get(listIndex).getLocation();
			tempExit = exitList.get(i).getLocation();
		
			if(currentExit.distance(location) > tempExit.distance(location)) {
				listIndex = i;
			}
			
		}
		
		return listIndex;
	}

	// Set vector location
	public void setLocation(Point2D location) {
		this.location = location;

	}

	// Change size of Circle (Exits are larger, will eventually be transparent)
	public void setRadius(int radius) {
		this.radius = radius;
	}
	

	public void setVisible() {
		setVisible(true);
	}
	
	public Point2D getLocation() {
		return location;
	}

	public void setExitLocation(Point2D exitLocation) {
		this.exitLocation = exitLocation;
		
	}
	
	public boolean getAtExit() {
		return atExit;
	}

}