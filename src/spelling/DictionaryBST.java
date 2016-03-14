package spelling;

import java.util.TreeSet;

/**
 * @author UC San Diego Intermediate MOOC team
 */

public class DictionaryBST implements Dictionary {
   private TreeSet<String> dict;
	
    // TODO: Implement the dictionary interface using a TreeSet.  
 	// You'll need a constructor here
   public DictionaryBST() {
	   dict = new TreeSet<String>();
   }
    
    /** convert String word to lowercase first for assignment
     * add and return true if word was not in dictionary 
     * (it wasn't already there)
     */
    public boolean addWord(String word) {
    	// TODO: Implement this method
    	return dict.add(word.toLowerCase()); // checks containment before adding/not adding and returns boolean automatically
    }

    /** return the number of words in the dictionary */
    public int size() {
    	// TODO: Implement this method
        return dict.size();
    }

    /** return whether String s is contained in dictionary */
    public boolean isWord(String s) {
    	//TODO: Implement this method
        return dict.contains(s.toLowerCase());
    }

}
