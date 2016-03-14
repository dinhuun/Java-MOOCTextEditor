package spelling;

import java.util.LinkedList;

/**
 * implements the Dictionary interface using a LinkedList
 */

public class DictionaryLL implements Dictionary {

	private LinkedList<String> dict;
	
    // TODO: Add a constructor
	public DictionaryLL() {
		dict = new LinkedList<String>();
	}

	/** convert String word to lowercase first for assignment
     * add and return true if word was not in dictionary 
     * do not add and return false if word was already in dictionary
     */
    public boolean addWord(String word) {
    	// TODO: Implement this method
    	String w = word.toLowerCase();
    	if (! dict.contains(w)) {
    		return dict.add(w); // always adds and returns true automatically
    	}
        return false;
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
