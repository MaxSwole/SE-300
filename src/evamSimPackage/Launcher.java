package evamSimPackage;

public class Launcher {
    public static void main(String[] args){
        //Starting the Sim,
        Sim sim = new Sim("Evac Simulator", 400, 400);
        sim.start();
    }
}
