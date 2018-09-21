/* Landon Marchant
 * Customer Class
 * Lab 9
 */
import structure5.*;
import java.util.Random;


public class Customer implements Comparable<Customer> {
    int arrivalTime; // arrive in the Queue.
    int serviceTime;
    int waitTime;

    /**
     * Generate random eventTime and serviceTime. Count down in BusinessSimulation class
     * Creates a Customer that arrives at time step @eventTime and
     * requires @serviceTime time steps to be satisfied after
     * arriving at a service point.
     * int arrivalTime is the time when Customers arrive in the PriorityQueue.
     * int waitTime is the time they spend in the PriorityQueue to get to the teller. Set to 0.
     * int serviceStarts is when the service starts at the teller.
     * int serviceTime is the time length it'll take for service to conclude.  -1 each time step.
     * + or - serviceStarts, waitTime, and serviceTime each timeStep() in the serviceClasses.
     */

    public Customer(int arrivalTime, int serviceTime) {
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        // difference between arrival time and time serviced
        waitTime = -1;
    }
    /* Decriment service while customer object is at the teller.
     */
    public void service(){
        serviceTime--;
    }
    /* Calculate waitTime by finding when service starts for each customer object.
     */
    public void setTimeServiced(int serviceStarts){
        waitTime = serviceStarts - arrivalTime;
    }
    public int getWaitTime(){
      return waitTime;
    }

    /**
    * get serviceTime from customer.
    **/
    public int getServiceTime() {
        return serviceTime;
    }
    /**
    * get arrivalTime from customer.
    **/

    public int getArrivalTime(){
        return arrivalTime;
    }

    /**
     * Compares Customers by arrivalTime - arrival in queue.
     * Priority Queue needs this.
     */
    public int compareTo(Customer other) {
        if (other.arrivalTime - arrivalTime == 0){
            return 0;
        }
        if (arrivalTime - other.arrivalTime > 0){
            return 1;
            // need to return positive if this current one is larger than the other passed parameter
        }
        else {
            // other.arrivalTime - arrivalTime < 0
            return -1;
            // so we know if others are not true
        }

    }

    public String toString() {
      // customer's important information, a string made of int arrivalTime, int serviceTime.
        return ("the customer arrived in the service queue at " + this.arrivalTime +
          "\n their service started at " + (waitTime + arrivalTime) + "\n " + "their service took "
          + this.serviceTime+ " time steps");
    }
}