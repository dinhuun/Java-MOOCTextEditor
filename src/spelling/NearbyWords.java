package spelling;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author UC San Diego Intermediate MOOC team
 * this.suggestions() changed to this.getSuggesttion()
 */

public class NearbyWords implements SpellingSuggest {
	// THRESHOLD number of words to look through for spelling suggestions (stops prohibitively long searching)
	// For use in the Optional Optimization in Part 2
	private static final int THRESHOLD = 1000; 

	Dictionary dict;

	public NearbyWords (Dictionary dict) {
		this.dict = dict;
	}

	/** Return list retList of strings distance 1 away from input string s 
	 * @param wordsOnly controls whether to return only words or any string
	 */
	public List<String> distanceOne(String s, boolean wordsOnly )  {
		   List<String> retList = new ArrayList<String>();
		   subsitutions(s, retList, wordsOnly);
		   insertions(s, retList, wordsOnly);
		   deletions(s, retList, wordsOnly);
		   return retList;
	}

	
	/** Add strings that are 1 character substitution away from input string s to currentList  
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any string
	 */
	public void subsitutions(String s, List<String> currentList, boolean wordsOnly) {
		// for each letter in s and for all possible replacement characters
		for(int index = 0; index < s.length(); index++) {
			for(int charCode = (int)'a'; charCode <= (int)'z'; charCode++) {
				// use StringBuffer for an easy interface to changing the letters in s
				StringBuffer sb = new StringBuffer(s);
				sb.setCharAt(index, (char)charCode);

				// if new string isn't in currentList, isn't s and is a real word (if wordsOnly is true)
				// add to currentList
				addToList(s, sb.toString(), currentList, wordsOnly);
			}
		}
	}
	
	/** Add strings that are one character insertion away from input string s to currentList
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any string
	 */
	public void insertions(String s, List<String> currentList, boolean wordsOnly ) {
		// TODO: Implement this method
		for (int index = 0; index <= s.length(); index++) {
			for (int charCode = (int)'a'; charCode <= (int)'z'; charCode++) {
				StringBuffer sb = new StringBuffer(s.substring(0, index));
				sb.append((char)charCode);
				sb.append(s.substring(index));
				
				addToList(s, sb.toString(), currentList, wordsOnly);
			}
			
		}
	}

	/** Add strings that are one character deletion away from input string s to currentList  
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 */
	public void deletions(String s, List<String> currentList, boolean wordsOnly ) {
		// TODO: Implement this method
		for (int index = 0; index < s.length(); index++) {
			StringBuffer sb = new StringBuffer(s);
			String t = sb.deleteCharAt(index).toString();
			addToList(s, t, currentList, wordsOnly);			
		}
	}
	
	// helper method for substitutions(), insertions() and deletions()
	public void addToList(String s, String t, List<String> list, boolean wordsOnly) {
		if(!list.contains(t) && (!wordsOnly || dict.isWord(t)) && !s.equals(t)) {
			list.add(t);
		}
	}

	/**
	 * return list suggestions of words distance 1 away from input string word  
	 * @param word the misspelled word
	 * @param numSuggestions the maximum size of suggestions 
	 */
	@Override
	public List<String> getSuggestions(String word, int numSuggestions) {
		List<String> suggestions = new LinkedList<String>();   // words to return
		Queue<String> queue = new LinkedList<String>();     // string to explore
		queue.add(word);
		HashSet<String> visited = new HashSet<String>();   // avoid exploring the same string multiple times
		visited.add(word);
		String curr = ""; 
		List<String> neighbors = new ArrayList<String>();
					
		// TODO: Implement the remainder of this method, see assignment for algorithm
		while ((!queue.isEmpty()) && (suggestions.size() < numSuggestions) && (visited.size() < THRESHOLD)) {
			curr = queue.remove();
			neighbors = distanceOne(curr, true);			
			for (String t : neighbors) {
				if (! visited.contains(t)) {
					visited.add(t);
					queue.add(t);
					if (dict.isWord(t)) {
						suggestions.add(t);
					}
				}
			}
		}	
		return suggestions;
	}	

   public static void main(String[] args) {
	   // Pass NearbyWords any Dictionary implementation you prefer
	   Dictionary d = new DictionaryHashSetMatchCase();
	   DictionaryLoader.loadDictionary(d, "data/dict.txt");
	   NearbyWords w = new NearbyWords(d);
	   
	   // test distanceOne()
	   String word = "i";
	   List<String> l = w.distanceOne(word, true);
	   System.out.println("Distance 1 away words for \"" + word + "\" are:");
	   System.out.println(l + "\n");
	   
	   // test getSuggestions()
	   word = "tailo";
	   List<String> suggestions = w.getSuggestions(word, 10);
	   System.out.println("Spelling Suggestions for \"" + word + "\" are:");
	   System.out.println(suggestions);
   }

}
