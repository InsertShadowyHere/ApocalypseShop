/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package shdwy.apocshop;

/**
 *
 * @author 1053730
 */
import java.util.Random;

public class Worker {

    static String[] names = {"Jack", "Jill", "Bob", "Bill", "Larry", "Page", "Mufasta",
        "Olivia", "Amelia", "Emma", "Sophia", "Charlotte", "Isabella", "Ava", "Mia", "Ellie", "Luna", "Harper", "Aurora", "Evelyn", "Eliana", "Aria",
        "Noah", "Liam", "Oliver", "Elijah", "Mateo", "Lucas", "Levi", "Ezra", "Asher", "Leo", "James", "Luca", "Henry", "Hudson", "Ethan", "Mohammed",
        "Hotdog", "Peanut", "Sandwich", "Piano", "Dog", "Cat", "Coffee", "Donut", "Jelly", "Taco", "Gun", "Big Gun", "Hank 'The Tank' Oswald", "Benjamin Kwan", "Mohd Hatta Ramli"};
    Random rand = new Random();
    String name;
    int job = 5;

    public Worker() {
        name = names[rand.nextInt(names.length)];
    }

}
