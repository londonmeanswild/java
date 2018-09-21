import structure5.*;
import java.lang.Math;

/* TwoTowers
 * (c) Landon Marchant
 * Lab 10
 * Worked with Shivam
 */

 /* TwoTowers uses SubsetIterator.
  * TwoTowers solves the subset sum problem when presented with the square roots of {1...n}
  */

public class TwoTowers {

public static void main(String[] args) {

    /* Create a new vector of integers.
     * n = arg[0] = the number of blocks
     * Initialize the sum variable.
     */
    Vector<Integer> blocks = new Vector<Integer>();
    int n = Integer.parseInt(args[0]);
    double sum = 0;

    /* Iterate through (1...n), and sum the square root of each i
     * Add each to the blocks vector.
    */

    for (int i = 1; i <= n; i++){
        sum += Math.sqrt(i);
        blocks.add(i);
    }
    /* The optimal height for each tower is the sum of the squre root of all blocks,
     * divided by two.
     */
    double optimalHeight = (sum/2);

    /* Initialize an integer SubsetIterator to iterate over blocks.
     * Initialize solution vector to be empty
     */
    SubsetIterator<Integer> towers = new SubsetIterator<Integer>(blocks);
    // Vector solution is initialized empty
    Vector<Integer> solution = new Vector<Integer>();

    /* While the SubsetIterator towers has a next value, build a subset
     * and store it in currentSubset.
     */

    while (towers.hasNext()){
        Vector<Integer> currentSubset = towers.next();

        /* Compute the absolute value, and every time there is a new smallest difference
         * assign the solution to be that current subset.
         * From there, print the solution subset and the height of the solution subset.
         */

        if (Math.abs(computeHeight(currentSubset) - optimalHeight) <
            Math.abs(computeHeight(solution) - optimalHeight)){
            solution = currentSubset;
        }

    }

    System.out.println("these are the contents of one tower " + solution);
    System.out.println("this is the height of the best tower " + computeHeight(solution));
}

/* computeHeight() computes the height of all blocks, when the square roots are added together.
 * Takes a vector of integers, blocks, as a paramter.
 * Returns height, the total of all square roots of the values within the vector blocks.
 */

public static Double computeHeight(Vector<Integer> blocks){
    // initialize height to be zero
    double height = 0;
    // for every integer num in blocks
    for (int num : blocks){
        // take the square root of each value num and add it to height
        height += Math.sqrt(num);
    }
    // return the total
    return height;

}
}