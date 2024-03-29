package student; /*
 * @Created 20/12/2020/ - 09:41/
 * @project PrintingSystem
 * @author Hewa Chamika
 * This represents a particular student who need to print several documents.
 * Date:      19/12/20
 * Version: 1.0
 */

import document.Document;
import printer.LaserPrinter;
import sleeper.ThreadSleeper;

public class Student extends Thread {

    private String name;
    private LaserPrinter laserPrinter;

    private Document document1;
    private Document document2;
    private Document document3;
    private Document document4;
    private Document document5;

    public Student(String name, LaserPrinter laserPrinter, ThreadGroup threadGroup) {
        super(threadGroup, name);
        this.name = name;
        this.laserPrinter = laserPrinter;
        document1 = new Document(name, "cwk1", generateRandomNoOfPages());
        document2 = new Document(name, "cwk2", generateRandomNoOfPages());
        document3 = new Document(name, "assignment5", generateRandomNoOfPages());
        document4 = new Document(name, "physics paper1", generateRandomNoOfPages());
        document5 = new Document(name, "research paper1", generateRandomNoOfPages());
    }

    /**
     * Returns a random integer between a given range.
     *
     * @return Random integer generated
     */
    private int generateRandomNoOfPages() {
        int min = 10;
        int max = 20;
        return (int) ((Math.random() * (max - min)) + min);
    }

    /**
     * Method executed when the Thread is started.
     */
    @Override
    public void run() {
        laserPrinter.printDocument(document1);
        ThreadSleeper.sleepRandom(1000, 4000);
        laserPrinter.printDocument(document2);
        ThreadSleeper.sleepRandom(1000, 4000);
        laserPrinter.printDocument(document3);
        ThreadSleeper.sleepRandom(1000, 4000);
        laserPrinter.printDocument(document4);
        ThreadSleeper.sleepRandom(1000, 4000);
        laserPrinter.printDocument(document5);
        System.out.println(name + " has completed printing it's documents\n");
    }

}
