package technician; /*
 * @Created 20/12/2020/ - 09:41/
 * @project PrintingSystem
 * @author Hewa Chamika
 * Contents:  6SENG002W CWK
 * Date:      19/12/20
 * Version:   1.0
 */

import printer.LaserPrinter;
import sleeper.ThreadSleeper;

public class PaperTechnician extends Thread {

    private String name;
    private LaserPrinter laserPrinter;
    private volatile boolean running;

    public PaperTechnician(String name, LaserPrinter laserPrinter, ThreadGroup threadGroup) {
        super(threadGroup, name);
        this.name = name;
        this.laserPrinter = laserPrinter;
        this.running = true;
    }

    /**
     * Method that is executed when the thread is started.
     */
    @Override
    public void run() {
        int c = 0;
        while (running && c < 3) {
            laserPrinter.refillPaper();
            c++;
            if (c != 2) {
                ThreadSleeper.sleepRandom(1000, 4000);
            }
        }
        System.out.println(name + " has completed it's round(s) of paper refills\n");
    }

    /**
     * Method to safely stop running the thread.
     */
    public void terminate() {
        running = false;
    }

}
