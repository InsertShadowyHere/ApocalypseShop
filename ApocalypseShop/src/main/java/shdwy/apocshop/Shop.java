/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package shdwy.apocshop;

/**
 * @author Raphael Quarles
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Shop {

    Random rand = new Random();
    // Basic vars
    String name = "";
    int age = 0;
    int food = 50;
    int medicine = 0;
    int scrap = 0;
    int security = 0;

    // Complex vars
    List<Worker> workers = new ArrayList<>();
    String[] jobsNames = {"farmers", "scavengers", "medics", "guards", "expeditioners", "idle workers"};
    List<Integer> jobs = new ArrayList<>(); // Farmers 0, Scavengers 1, Medics 2, Security 3, Expeditioners 4, Unemployeds 5
    int workersCurrent = 0;
    int luck = 0;
    int rationing = 3;
    String[] rationTypes = {"meager", "adequate", "hearty"};
    int efficiency = 50 + rationing * 25;
    int maxRationing = 0;
    int starving = 0;
    double gathered = 0;

    // Modifier vars
    Scanner in = new Scanner(System.in);
    String scan = "";

    // ~ waits for arg[0] days - manage random events
    public void twiddle(int days) {
        while (true) {
            // Handle basic aging stuff ()
            age += 1;

            

            // Worker management (farmers generate food, scavengers scrap, process explorations, medicine)
            // Farmers, Scavengers, Medics, Security, Expeditioners, Unemployeds
            gathered = jobs.get(0) * 4 * efficiency * rand.nextDouble(0.7, 1.3) / 100;
            System.out.println("Your " + jobs.get(0) + " farmers gathered " + (int)gathered + " portions of food.");
            food += gathered;

            gathered = jobs.get(1) * 2 * efficiency * rand.nextDouble(0.7, 1.3) / 100;
            System.out.println("Your " + jobs.get(2) + " scavengers found " + (int)gathered + " pieces of scrap.");
            scrap += gathered;

            gathered = jobs.get(2) * 0.1 * efficiency * rand.nextDouble(0.7, 1.3) / 100;
            System.out.println("Your " + jobs.get(3) + " medics synthesized " + (int)gathered + " cLs of serum.");
            medicine += gathered;

            // Food management
            maxRationing = food / workers.size();

            // handle starvation
            if ((starving > 0) && (maxRationing >= 2)) {
                starving -= 1;
            }
            // * END GAME HERE
            else if (starving == 5) {
                System.out.println("Your workers have starved to death. Game over."); 
            }
            // enough food
            if (maxRationing >= rationing) {
                food -= workers.size() * rationing;
            } // 1 level ration degrade
            else if ((maxRationing >= (rationing - 1)) && (rationing > 1)) {
                food -= workers.size() * (rationing - 1);
                efficiency -= 25;
                rationing -= 1;
                System.out.println("Due to a food shortage, rationing has been lowered one level. (-25% efficiency)");
            } // 2 levels ration degrade 
            else if ((maxRationing >= (rationing - 2)) && (rationing == 3)) {
                food -= workers.size();
                efficiency -= 50;
                rationing = 1;
                System.out.println("Due to a food shortage, rationing has been lowered two levels. (-50% efficiency)");
            } // starving
            else {
                starving += 1;
                System.out.println("Your workers are starving.");
            }
        

            // handle randevents
            int tobreak = 0;
            // ~ new worker - add option to accept or remove, change amount of workers that show up
            if (rand.nextInt(100) <= 10) {
                workers.add(new Worker());
                System.out.println(workers.get(workers.size() - 1).name + " shows up!");
                jobs.set(5, jobs.get(5) + 1);
                tobreak = 1;
            } // raid
            else if (rand.nextInt(100) == 1) {

            } // trader
            else if (rand.nextInt(100) == 1) {

            } // random encounter
            else if (rand.nextInt(100) == 1) {

            } // 
            else if (rand.nextInt(100) == 1) {

            } // pass day
            else {
                System.out.println("Day passed.");
                try {
                    TimeUnit.MILLISECONDS.sleep(400);
                } catch (InterruptedException e) {
                }
            }
            // break loop
            if (tobreak == 1 || days == 1) {

                break;
            }

        }
    }

    // inspect shop
    public String inspect() {
        return name + " statistics:\n"
                + "It is " + age + " days old.\n"
                + "You currently have " + workers.size() + " workers.\n"
                + "The shop has !!! defenses " + security + ".\n"
                + "You have " + scrap + " pieces of scrap.\n"
                + "You have " + food + " food and " + (int)(food/(rationing*workers.size())) + " days of food.\n"
                + ((starving >= 1) ? "Your workers are starving." : "");

    }

    // Farmers 0, Scavengers 1, Medics 2, Security 3, Expeditioners 4, Unemployeds 5
    public void manageWorkers() {
        // ask which worker type to edit
        // give option to add or remove workers
        // if adding workers
        while (true) {
            System.out.println("You have " + jobs.get(0) + " farmers, "
                    + jobs.get(1) + " scavengers, "
                    + jobs.get(2) + " medics, "
                    + jobs.get(3) + " security agents, "
                    + jobs.get(4) + " expeditioners, and "
                    + jobs.get(5) + " jobless workers. "
                    + """    
            Would you like to edit your workers?
            1) Yes
            2) No
            """);

            scan = in.nextLine();
            // * CONVERT TO SWITCHES ONCE FINISHED
            if (scan.equals("1")) {
                workersCurrent = workers.size();
                for (int i = 0; i < 6; i++) {
                    System.out.println("You have " + workersCurrent + " total workers.");
                    System.out.println("How many " + jobsNames[i] + " would you like?");
                    scan = in.nextLine();
                    try {
                        if (Integer.parseInt(scan) <= workersCurrent) {
                            workersCurrent -= Integer.parseInt(scan);
                            jobs.set(i, Integer.parseInt(scan));
                            if (workersCurrent == 0) {
                                break;
                            }
                        } else {
                            System.out.println("You don't have that many workers.");
                            i--;
                        }
                    } catch (Exception e) {
                        System.out.println("That's not a valid number.");
                        i--;
                    }

                }
                break;
            } else if (scan.equals("2")) {
                break;
            } else {
                System.out.println("That's not an option.");
            }
        }
    }

    public void manageRations() {
        System.out.println(
                        "Current rationing is set to " + rationing + " food per day per person. " +
                        """
                        What would you like to change the rationing to?
                        1) Sparse - 1 food per day per person (-25% efficiency)
                        2) Adequate - 2 food per day per person
                        3) Hearty - 3 food per day per person (+25% efficiency)""");
                scan = in.nextLine();
                if (scan.matches("1|2|3")) {
                    rationing = Integer.parseInt(scan);
                    System.out.println("Rationing set to " + rationTypes[rationing-1] + "!");
                }
                else {
                    System.out.println("That's not an option.");
                }
    }
}
