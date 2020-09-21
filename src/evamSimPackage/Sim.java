package evamSimPackage;

import display.Display;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class Sim implements Runnable {
  private Display display;
  public int width, height;
  public String title;
  private boolean running = false;
  private Thread thread;

  private BufferStrategy bs;
  private Graphics g;

  //Temp
  private BufferedImage testImage;

  //Constructor for Sim class, Sim is called to create the initial frame.
  //Imports 'display' method, which creates frame and canvas.
  public Sim(String title, int width, int height){
    this.width = width;
    this.height = height;
    this.title = title;
  }

  //Initializes graphics for sim
  private void init(){
    display = new Display(title, width, height);
    testImage = ImageLoader.loadImage("airbus.png");
  }

  private void tick(){

  }

  private void render(){
    try {
      TimeUnit.MILLISECONDS.sleep(20);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    bs = display.getCanvas().getBufferStrategy();
    if(bs == null){
      display.getCanvas().createBufferStrategy(3);
      return;
    }
    g = bs.getDrawGraphics();
    //Clearing Screen
    g.clearRect(0,0, width, height);

    //Draw here!
    g.drawImage(testImage, 0, 0, null);

    //End Drawing!
    bs.show();
    g.dispose();
  }

  //Required when implementing Runnable, where majority of code will go.
  public void run(){
    init();

    while(running){
      tick();
      render();
    }
    stop();
  }

  //Starts thread
  public synchronized void start(){
    if(running) {
      return;
    }
    running = true;
    //Thread class requires class to run
    thread = new Thread(this);
    thread.start();
  }

  //Stops thread
  public synchronized void stop(){
    if(!running) {
      return;
    }
    running = false;
    try {
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
