/* @Created 20/12/2020/ - 09:41/
  * @project PrintingSystem
  * @author Hewa Chamika
  * This is the main class that creates and starts the student and technician threads, and
  * the mutually exclusive laser printer.
  * Date:      20/12/20
  * Version: 1.0
  */

import printer.LaserPrinter;
import student.Student;
import technician.PaperTechnician;
import technician.TonerTechnician;

public class PrintingSystem {

    public static void main(String[] args) {
        LaserPrinter laserPrinter = new LaserPrinter("LP001");

        ThreadGroup students = new ThreadGroup("students");
        ThreadGroup technicians = new ThreadGroup("technicians");

        Student student1 = new Student("JoeBloggs", laserPrinter, students);
        student1.start();
        Student student2 = new Student("Koothrappali", laserPrinter, students);
        student2.start();
        Student student3 = new Student("Sheldon", laserPrinter, students);
        student3.start();
        Student student4 = new Student("Leonard", laserPrinter, students);
        student4.start();

        PaperTechnician paperTechnician = new PaperTechnician("Paper Technician", laserPrinter, technicians);
        paperTechnician.start();
        TonerTechnician tonerTechnician = new TonerTechnician("Toner Technician", laserPrinter, technicians);
        tonerTechnician.start();

        // Wait till all the student threads have completed running
        while (students.activeCount() > 0) { /* DO NOTHING */ continue; }

        // Stop allowing threads to access the Laser Printer
        laserPrinter.terminate();

        // If Paper Technician thread is still alive then safely stop the thread
        if (paperTechnician.isAlive()) {
            // Safely terminate the Paper Technician thread
            paperTechnician.terminate();
            // Wait till the Paper Technician thread is terminated
            try {
                paperTechnician.join();
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }

        // If Toner Technician thread is still alive then safely stop the thread
        if (tonerTechnician.isAlive()) {
            // Safely terminate the Toner Technician thread
            tonerTechnician.terminate();
            // Wait till the Toner Technician thread is terminated
            try {
                tonerTechnician.join();
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }

        // Print out final status of the printer
        System.out.println("Final Laser Printer Status After All Documents Printed:");
        System.out.println(laserPrinter.toString());
    }

}