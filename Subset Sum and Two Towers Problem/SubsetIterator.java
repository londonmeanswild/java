
import structure5.*;
import java.util.Iterator;
/* SubsetIterator
 * (c) Landon Marchant
 * Lab 10
 * Worked with Shivam
 */

 /* SubsetIterator extends AbstractIterator
  * Internally, a long value is used to represent the current subset.
  * Value increases from 0 (empty set) to 2^n - 1 (the entire set of values)
  */

public class SubsetIterator<E> extends AbstractIterator<Vector<E>> {

  /* Create a vector of generics named set, to track the current subset.
   * Create a variable currentIndex, of type long.
   */
  protected Vector<E> set;
  long currentIndex;


 /* SubsetIterator takes a vector s as a parameter.
  * When we call this, we reset the current index to 0.
  */
  public SubsetIterator(Vector<E> s) {
   set = s;
   reset();
 }

    /**
    * reset() resets subset counter to 0
    **/
    public void reset() {
      currentIndex = 0;
    }

    /**
    * hasNext() returns true if the current value is a reasonable representation
    * of a subset.
    **/
    public boolean hasNext() {
      return (currentIndex < 1<<set.size());
    }

    /**
    * get() returns a new Vector<E> of values that are part of the current subset.
    * If bit i of the current counter is 1, element i of the original Vector is included
    * in the resulting subset vector
    **/
    public Vector<E> get() {

      long num = currentIndex;
      // ret is the vector we are adding to
      Vector<E> ret = new Vector<E>();
      // iterate over the size of the set
      for (int i = 0; i < set.size(); i++){
        // if the bit in question is 1
        if ((num & 1) == 1){
          // add the element to vector ret
          ret.add(set.get(i));
        }
        //
        num >>= 1;
      }
      return ret;

    }

    /**
    * next() returns the current subset and increments the subset counter.
    **/
    public Vector<E> next() {
      currentIndex++;
      return get();

    }

    public static void main(String[] args) {
    Vector<Integer> data = new Vector<Integer>();
    data.add(1);
    data.add(13);
    data.add(5);
    data.add(200);
    data.add(19);

    // test subsetIterator
    SubsetIterator<Integer> subsetIterator = new SubsetIterator<Integer>(data);
    while (subsetIterator.hasNext()){
      System.out.println(subsetIterator.next());
    }
  }
}
