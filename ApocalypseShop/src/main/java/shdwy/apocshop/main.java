/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package shdwy.apocshop;

/**
 *
 * @author 1053730
 */
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class main {

    @SuppressWarnings("ConvertToStringSwitch")
    public static void main(String[] args) {
        // main class init
        Shop shop = new Shop();

        // initialize jobs var (idk how to do it better)
        for (int i = 0; i < 6; i++) {
            shop.jobs.add(0);
        }
        
        // ! TEMPORARY - creates test Workers
        for (int i = 0; i < 5; i++) {
            shop.workers.add(new Worker());
            shop.jobs.set(0, shop.jobs.get(0)+1);
        }


        // Scans for input
        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to the apocalypse! You have a shop. What would you like to name it?");
        String scan = in.nextLine();
        shop.name = scan;

        // Game loop begins here
        while (true) {
            System.out.println("""
                               Welcome to the apocalypse. Would you like to 
                               1) Wait 1 day
                               2) Wait until something happens
                               3) Inspect shop
                               4) Manage workers
                               5) Set rations
                               6) Manage trade routes
                               7) Send an expedition
                               """);
            scan = in.nextLine();
            // * Wait 1 day - CONVERT TO SWITCHES ONCE FINISHED
            if (scan.equals("1")) {
                shop.twiddle(1);
            } 
            // Wait until something happens
            else if (   scan.equals("2")) {
                shop.twiddle(-1);
            } 
            // ~ Inspect shop - need to update once more information is available
            else if (scan.equals("3")) {
                System.out.println(shop.inspect());
            } 
            // Manage workers
            else if (scan.equals("4")) {
                shop.manageWorkers();
            } 
            // Set rations
            else if (scan.equals("5")) {
               shop.manageRations();
            } 
            // ! Manage trade routes
            else if (scan.equals("6")) {
                System.out.println("oops i broke");
            } 
            // ! Send an expedition
            else if (scan.equals("7")) {
                System.out.println("oops i broke");
            }
            // Set delay
            try {
                TimeUnit.MILLISECONDS.sleep(400);
            } catch (InterruptedException e) {
            }
        }

    } 
}
