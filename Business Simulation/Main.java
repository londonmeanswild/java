/* Landon Marchant
 * Main class
 * Lab 9
 */

import structure5.*;
import java.util.Random;

/* Main class. Runs SingleQueue and MultiQueue.
 * Average and ending wait times for each simulation are printed from their respective classes.
 * maxEventStart, numServicePoints, numCustomers, and seed are all set in this class.
 */
public class Main {

    public static void main(String args[]){
        int maxEventStart = 20;
        int numServicePoints = 5;
        int numCustomers = 50;
        int seed = 20;


        BusinessSimulation single = new SingleQueue(numCustomers, numServicePoints, maxEventStart, seed);
        BusinessSimulation multi = new MultiQueue(numCustomers, numServicePoints, maxEventStart, seed);

        single.run();
        multi.run();

        System.out.println("We have " + numServicePoints + " service points.");
        System.out.println("We have " + numCustomers + " customers.");
    }
    }
