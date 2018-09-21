import structure5.*;
import java.util.Iterator;
/* SubWords
 * (c) Landon Marchant
 * Lab 10
 * Worked with Shivam
 */

 /* SubWords uses LexiconTrie, Lexicon, SubsetIterator, and ospd2.txt
  * Create subsets of the user input.
  * Finds, prints, and counts how many subsets of the user input word
  * are contained in the Oxford English Dictionary.
  * Best if used on words with a character count > 5. Tested on
  * pneumonoultramicroscopicsilicovolcanoconiosis, which has 53249 total subsets.
  * 123 of those are also words in the Oxford English Dictionary.
  */

public class SubWords {

    public static void main(String[] args) {
        int countWords = 0;
        // initialize dictionary to be an empty LexiconTrie
        LexiconTrie dictionary = new LexiconTrie();
        // add words to dictionary from the oxford dictionary txt file
        dictionary.addWordsFromFile("ospd2.txt");

        // word we are trying to find subwords of.
        // Vectorize because subset iterator needs it
        Vector<String> wordHolder = new Vector<String>();

        // this is where we get the word
        String word = args[0];

        for (int i = 0; i < word.length(); i++){
            wordHolder.add(word.substring(i, i+1));
        }
        // subset iterator builds sub-words of args[0] (word)
        SubsetIterator<String> iter = new SubsetIterator<String>(wordHolder);
        // while there is a next sub-word in our iterator
        // build another sub-word and see if it's a word
        while (iter.hasNext()){
            String charString = "";
            // current subword
            Vector<String> current = iter.next();
            // append to charString in order to build a string of characters
            for (String letter : current){
                charString += letter;
            }
            // if the string of characters matches a word in the dictionary
            // increment the counter by one
            if (dictionary.containsWord(charString)){
                countWords++;
                // print the word
                System.out.println(charString);
            }
        }
            // print the total count of dictionary words
            // that can be made from subsets of input text
                System.out.println(countWords);


    }



}