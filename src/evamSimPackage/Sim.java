package evamSimPackage;

import display.Display;

public class Sim implements Runnable {
  private Display display;
  public int width, height;
  private Thread thread;

  //Constructor for Sim class, Sim is called to create the initial frame.
  //Imports 'display' method, which creates frame and canvas.
  public Sim(String title, int width, int height){
    this.width = width;
    this.height = height;
    display = new Display(title, width, height);
  }

  //Initializes graphics for sim
  private void init(){

  }

  //Required when implementing Runnable, where majority of code will go.
  public void run(){
    init();
  }

  //Starts thread
  public synchronized void start(){
    //Thread class requires class to run
    thread = new Thread(this);
    thread.start();
  }

  //Stops thread
  public synchronized void stop(){
    try {
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
