package model;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Sprite extends Circle {

	// Constants; Mass essentially controls the 'speed' at which they move. Larger
	// mass, slower speed (can be used for overweight passengers?).
	static final double MAXVELOCITY = 20;
	double MASS = 10;
	static double ATTRACTION_DISTANCE_MIN = 5;
	static double ATTRACTION_DISTANCE_MAX = 25.0;
	static double GRAVITATIONAL_CONSTANT = 0.004;

	// Vectors
	private Point2D location;
	private Point2D velocity;
	private Point2D acceleration;
	private int type;

	// Raidus of all Circles
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
		this.type = type;

		setRadius(radius);
		setStyle("-fx-padding: 10 10 10 10;");

		if (type == 0) {

			setStroke(Color.BROWN);
			setFill(Color.BROWN);
		} else if (type == 1) {
			setStroke(Color.PURPLE);
			setFill(Color.PURPLE);
			
			//Different Weight for different type
			MASS += 10;
		} else if (type == 2) {
			setStroke(Color.ORANGE);
			setFill(Color.ORANGE);
			
			//Different Weight for different type
			MASS += 15;
		} else {
			setStroke(Color.BLACK);
			setFill(Color.BLACK);
		}
	}

	// How the passengers are attracted to the Exits
	public Point2D attract(Sprite exitSprite) {

		// force direction
		Point2D force = location.subtract(exitSprite.location);
		// double distance = force.magnitude();

		// constrain movement
		// distance = constrain(distance, ATTRACTION_DISTANCE_MIN,
		// ATTRACTION_DISTANCE_MAX);

		force = force.normalize();

		// force magnitude
		// double strength = (GRAVITATIONAL_CONSTANT * MASS * exitSprite.MASS) /
		// (distance * distance);
		// force = force.multiply(strength);

		return force;
	}

	// Applying force to the passengers (gonna have to make it a constant velocity I
	// think)
	public void applyForce(Point2D force) {

		Point2D f = new Point2D(force.getX(), force.getY());
		f = f.multiply(1 / MASS);
		acceleration = acceleration.add(f);
	}

	// The Core move function
	public void move() {

		// set velocity depending on acceleration
		velocity = velocity.add(acceleration);

		// limit velocity to max speed
		double mag = velocity.magnitude();
		if (mag > MAXVELOCITY) {
			velocity = velocity.normalize();
			velocity = velocity.multiply(mag);
		}

		// change location depending on velocity
		location = location.add(velocity);

		// clear acceleration
		acceleration = new Point2D(0, 0);
	}

	// Sets x,y location; used in GameLoop
	public void display() {
		setCenterX(location.getX());
		setCenterY(location.getY());
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
}