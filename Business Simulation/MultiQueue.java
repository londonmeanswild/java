/* Landon Marchant
 * MultiQueue class
 * Lab 9
 */
import java.util.Random;
import structure5.*;

/* MultiQueue class handles the grocery store simulation.
 * Customer objects are sorted into the shortest teller queue, as they arrive.
 * numServicePoints are set in the Main method
 */

class MultiQueue extends BusinessSimulation {
    int totalWait = 0;

    public MultiQueue(int numCustomers, int numServicePoints, int maxEventStart, int seed){
        super(numCustomers, numServicePoints, maxEventStart, seed);
    }
    // this checks to see if arrival time == global time
    // and if it does, customer moves from priority queue to teller queue
    protected void updateQueue(){
        // assume first line is shortest we've seen. as we go on, we replace
        SinglyLinkedList<Customer> shortestLine = servicePoints.getFirst();
        int smallestLine = shortestLine.size();

        while (!eventQueue.isEmpty() && eventQueue.getFirst().getArrivalTime() == time) {
            for (SinglyLinkedList<Customer> teller : servicePoints){
                if (teller.size() < smallestLine){
                    smallestLine = teller.size();
                    shortestLine = teller;
                }
            }
            Customer c = eventQueue.remove();
            shortestLine.add(c);
        }
    }

    // check if all customers have been serviced
    public boolean simulationCompleted() {
        boolean hasNoCustomers = true;
        // if we break out of loop, we have customers to serve
        // if we return true, simulation is over

        // for each SLL called teller, inside servicepoints, do:
        for (SinglyLinkedList<Customer> teller : servicePoints){
            // this is just to make sure tellers don't have customers
            if (!teller.isEmpty()){
                // if queue isn't empty, we have customers
                hasNoCustomers = false;
                break;
            }
        }
        return hasNoCustomers && eventQueue.isEmpty();
    }
    /**
     * Advances 1 time step through the simulation.
     * step tells whether we move from wait to teller line
     * @post the simulation has advanced 1 time step
     * @return true if the simulation is over, false otherwise
     */
    public boolean step() {

        // if teller has customers
        if (simulationCompleted()){

            return true;
        }

        for (SinglyLinkedList<Customer> teller: servicePoints){
            if (!teller.isEmpty()){
            // serviced a customer
                teller.getFirst().service();
                if (teller.getFirst().getServiceTime() == 0){
                // System.out.println("about to remove first from teller");

                    teller.getFirst().setTimeServiced(time - teller.getFirst().getServiceTime());
                    totalWait += teller.getFirst().getWaitTime();
                    teller.removeFirst();
                }
            }
        }

        time ++;
        return false;
    }

    // calls updateQueue, as long as step returns false
    public void run(){
        updateQueue();
        while (!step())
            updateQueue();
        System.out.println("the ending time for the Multi Queue simulation is " + time);

        double averageWait = (totalWait/numCustomers);
        System.out.println("the average wait time for the Multi Queue simulation is " + averageWait);


    }

}

