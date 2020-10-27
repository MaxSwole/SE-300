package model;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


public class Sprite extends Circle {

    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private int type;
    static final double MAXVELOCITY = 20;
    static final double MASS = 10;
    
	private Point2D location;
	private Point2D velocity;
	private Point2D acceleration;

    private double radius = 3.0;

    public Sprite(){
        positionX = 0;
        positionY = 0;
        velocityX = 0;
        velocityY = 0;
    }

    public Sprite(Point2D location, Point2D velocity, Point2D acceleration) {
    	this.location = location;
    	this.velocity = velocity;
    	this.acceleration = acceleration;
    	
    	setRadius(radius);
    	System.out.println(radius);
    	
    	type = randomType(0,2);
    	if(type == 0){

    		setStroke(Color.BROWN);
            setFill(Color.BROWN);
        }
        else if(type == 1){
            setStroke(Color.PURPLE);
            setFill(Color.PURPLE);
        }
        else if(type == 2){
            setStroke(Color.ORANGE);
            setFill(Color.ORANGE);
        }
        else{
            setStroke(Color.BLACK);
            setFill(Color.BLACK);
        }
    }

    public void parameters(Point2D location, Point2D velocity, Point2D acceleration, int type) {
    	this.location = location;
    	this.velocity = velocity;
    	this.acceleration = acceleration;
    	
    	setRadius(radius);
    	
    	if(type == 0){

    		setStroke(Color.BROWN);
            setFill(Color.BROWN);
        }
        else if(type == 1){
            setStroke(Color.PURPLE);
            setFill(Color.PURPLE);
        }
        else if(type == 2){
            setStroke(Color.ORANGE);
            setFill(Color.ORANGE);
        }
        else{
            setStroke(Color.BLACK);
            setFill(Color.BLACK);
        }
    }
    
    public Point2D attract(Sprite exitSprite) {

		// force direction
		Point2D force = location.subtract(exitSprite.location);
		double distance = force.magnitude();
		
		// constrain movement
		//distance = constrain(distance, Settings.ATTRACTION_DISTANCE_MIN, Settings.ATTRACTION_DISTANCE_MAX);
		
		force = force.normalize();

		// force magnitude
		//double strength = (Settings.GRAVITATIONAL_CONSTANT * MASS * m.mass) / (distance * distance);
		//force = force.multiply(strength);

		return force;
	}
    
    
	public void applyForce(Point2D force) {

		Point2D f = new Point2D( force.getX(), force.getY());
		f = f.multiply(1/MASS);
		
		acceleration = acceleration.add(f);
	}
    
    public void move() {

		// set velocity depending on acceleration
		velocity = velocity.add(acceleration);

		// limit velocity to max speed
		double mag = velocity.magnitude();
		if( mag > MAXVELOCITY) {
			velocity = velocity.normalize();
			velocity = velocity.multiply(mag);
		}

		// change location depending on velocity
		location = location.add(velocity);

		// clear acceleration
		acceleration = new Point2D(0,0);
	}

	public void display() {
		setCenterX(location.getX());
		setCenterY(location.getY());
	}	
    
    public int randomType(int min, int max) {
        int num = (int) (Math.random() * (max - min + 1) + min);
        return num;
    }


   

    public double getPositionX(){
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

	public void setLocation(Point2D location) {
		this.location = location;
		
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
	}

}