package model;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Sprite {

    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private int type;

    private int width = 7, height = 7;

    public Sprite(){
        positionX = 0;
        positionY = 0;
        velocityX = 0;
        velocityY = 0;
    }

    public Sprite(double positionX, double positionY, double velocityX, double velocityY){
       this.positionX = positionX;
       this.positionY = positionY;
       this.velocityX = velocityX;
       this.velocityY = velocityY;
    }


    public void update(double time) {
        positionX += velocityX * time;
        positionY += velocityY * time;
    }


    public void render(GraphicsContext gc) {

        if(type == 0){
            gc.setFill(Color.BROWN);
        }
        else if(type == 1){
            gc.setFill(Color.PURPLE);
        }
        else if(type == 2){
            gc.setFill(Color.ORANGE);
        }
        else{
            gc.setFill(Color.BLACK);
        }
        gc.fillRect(positionX, positionY, width, height);
        //gc.strokeRect(positionX, positionY, width, height);

    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(positionX,positionY,width,height);
    }

    public boolean intersects(Sprite s) {
        return s.getBoundary().intersects( this.getBoundary());
    }

    public void setPosition(double x, double y) {
        positionX = x;
        positionY = y;
    }

    public void setPositionX(double x){
        positionX = x;
    }

    public void setPositionY(double y){
        positionY = y;
    }

    public double getPositionX(){
        return positionX;
    }


    public void setVelocity(double x, double y) {
        velocityX = x;
        velocityY = y;
    }

    public void addVelocity(double x, double y) {
        velocityX += x;
        velocityY += y;
    }

    public void setType(int type){
        this.type = type;
    }

    public int getType(){
        return type;
    }

    public double getPositionY() {
        return positionY;
    }
}