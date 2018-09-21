import structure5.*;
import java.util.Iterator;
/* CharacterIterator creates and prints substrings of input.
 */

public class CharacterIterator extends AbstractIterator<Character> {

int currentIndex = 0;
String str;

  /* CharacterIterator takes a string, str.
   */

  public CharacterIterator(String str) {
  this.str = str;

  }
  /* next() returns the next value to visit
   * as currentIndex iterates through the iput string
   */
  public Character next() {
        return str.charAt(currentIndex++);
  }

  /* hasNext() returns true if there is at least one more
   * value to visit.
   * Returns false if the currentIndex is equivalent to length,
   * as there are no more values left to visit.
   */
  public boolean hasNext() {
    if (currentIndex == str.length()){
        return false;
    } else {
        return true;
  }
}
  /* reset() returns the next current index to 0
   */
  public void reset() {
    currentIndex = 0;
    }

  /* get() returns the current value at index.
   */
  public Character get() {
    return str.charAt(currentIndex);
  }

  public static void main(String[] args) {
    CharacterIterator irt = new CharacterIterator("oh shit");
    // as long as the iterator has more characters to visit
    while (irt.hasNext()){
      // print the next character
      System.out.println(irt.next());
    }

  }
}