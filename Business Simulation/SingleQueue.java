// teller queue class
// rename
import java.util.Random;
import structure5.*;

class SingleQueue extends BusinessSimulation {
    SinglyLinkedList<Customer> waitLine;
    int totalWait = 0;
// step(int timestep): every teller does 1 unit of service.
// as teller +, customer -, = time until completion.
// customer is removed from queue when completed. We need to know how long each customer waited for service.
// wait time is real time - arrival time (- service time length?)

    // waitline is where people wait until teller is open
    public SingleQueue(int numCustomers, int numServicePoints, int maxEventStart, int seed){
        super(numCustomers, numServicePoints, maxEventStart, seed);
        waitLine = new SinglyLinkedList<Customer>();
    }

        // four service points are set in the main

            // for queue in vector
            // get length or size
        // then add the customer to it from eventQueue
        // repeat
            // be mindful of space in the queue, customers can only populate as space opens up
            // will be ordered from eventQueue based on arrival time

    // this checks to see if arrival time == global time
    // and if it does, customer moves from priority queue to teller queue
    protected void updateQueue(){
        while (!eventQueue.isEmpty() && eventQueue.getFirst().getArrivalTime() == time) {
            waitLine.add(eventQueue.remove());
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
        return hasNoCustomers && eventQueue.isEmpty() && waitLine.isEmpty();
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
            // populate empty service points.
        for (SinglyLinkedList<Customer> teller : servicePoints){
            if (teller.isEmpty() && !waitLine.isEmpty()) {
                Customer c = waitLine.removeFirst();
                c.setTimeServiced(time);
                totalWait += c.getWaitTime();

                teller.add(c);

            }
        }
        // Iterate through the tellers within servicePoints
        for (SinglyLinkedList<Customer> teller: servicePoints){
            // if teller contains a customer
            if (!teller.isEmpty()){
            // service a customer
                teller.getFirst().service();
                // if service is over
                if (teller.getFirst().getServiceTime() == 0){
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
        System.out.println("the ending for the Single Queue time is " + time);

        double averageWait = (totalWait/numCustomers);
        System.out.println("the average wait time for the Single Queue simulation is " + averageWait);


    }

}

