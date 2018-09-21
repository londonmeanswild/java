/* Landon Marchant
 * Business Simulation Class
 * Lab 9
 */

import java.util.Random;
import structure5.*;

/* BusinessSimulation holds all information shared by MultiQueue and SingleQueue classes.
 */
public abstract class BusinessSimulation {

    /* sequence of customers, prioritized by randomly-generated event time
     * Size is based on customers, which is set in the Main.
     */
    protected PriorityVector<Customer> eventQueue;

    /* series of service points where customers queue and are served
     * vector means we can count number of customer objects in it
     */
    protected Vector<SinglyLinkedList<Customer>> servicePoints;

    /* Current time step in the simulation. Increments with step() function.
     * Is not real time.
     */
    protected int time;

    /* seed for Random() so that simulation is repeatable */
    protected  int seed;

    /* size of servicePoints Vector, set in Main */
    protected int numServicePoints;

    /* set in Main */
    protected int numCustomers;

    /* set in Main */
    protected int maxEventStart;

    /* Calculated for each simulation */
    protected int totalWait;

    /* Used to bound the range of service times that Customers require.
     */
    static final int MIN_SERVICE_TIME = 1; //TODO: set appropraitely
    static final int MAX_SERVICE_TIME = 6; //TODO: set appropriately

    /**
     * Creates a BusinessSimulation.
     * @post the step() function may be called.
     * @param numCustomers number of random customers that appear in the simulation
     * @param numSerivicePoints number of tellers in this simulation
     * @param maxEventStart latest timeStep that a Customer may appear in the simulation
     * @param seed used to seed a Random() so that simulation is repeatable.
     */
    public BusinessSimulation(int numCustomers, int numServicePoints, int maxEventStart, int seed) {

        this.numCustomers = numCustomers;
        this.numServicePoints = numServicePoints;
        servicePoints = generateServicePoints(numServicePoints);
        eventQueue = generateCustomerSequence(numCustomers, maxEventStart, seed);
        // Increments with step function
        time = 0;
        }


    /**
     * Generates a sequence of Customer objects, stored in a PriorityQueue.
     * Customer priority is determined by arrival time
     * @param numCustomers number of customers to simulate, created by Random(seed)
     * @param maxEventStart maximum timeStep that a customer arrives in @eventQueue
     * @param seed use Random(seed) to make customer sequence repeatable
     * @return A PriorityQueue that represents Customer arrivals, ordered by Customer arrival time
     */
    public static PriorityVector<Customer> generateCustomerSequence(int numCustomers, int maxEventStart, int seed) {
        Random rand = new Random(seed);

        // Creates a queue of customer objects
        PriorityVector eventQueue = new PriorityVector<Customer>();

        for (int i = 0; i < numCustomers; ++i){
            Customer newCustomer = new Customer(rand.nextInt(maxEventStart), rand.nextInt(MAX_SERVICE_TIME - MIN_SERVICE_TIME) + MIN_SERVICE_TIME);
            // add new customers to the queue.
            eventQueue.add(newCustomer);
        }
        return eventQueue;
    }
    public static Vector<SinglyLinkedList<Customer>> generateServicePoints(int numServicePoints){
        // ret is the object we are returning
        Vector<SinglyLinkedList<Customer>> ret = new Vector<SinglyLinkedList<Customer>>(numServicePoints);
        for (int i =0; i <numServicePoints; i++){
            ret.add(new SinglyLinkedList<Customer>());
        }
        return ret;
    }


    /**
     * Advances @timeSteps time steps through the simulation.
     * Very different between the two classes. Not true to real time.
     * updates global clock
     * @post the simulation has advanced @timeSteps time steps
     * @return true if the simulation is over, false otherwise
     */
    public abstract boolean step();

    /* Slightly different between two classes.
    */
    public abstract void run();

    /**
     * @return a string representation of the simulation
     */
    public String toString() {

        String str = "Time: " + time + "\n";
        str = str + "Event Queue: ";
        if (eventQueue != null) {
            str = str + eventQueue.toString();
        }
        str = str + "\n";

        if (servicePoints != null)  {
            for (SinglyLinkedList<Customer> sp : servicePoints) {
                str = str + "Service Point: " + sp.toString() + "\n";
            }
        }

        return str;
    }

}
