package spelling;

import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * delete java.util.Set, java.util.Collection, java.util.HashMap
 * @DinhHuuNguyen
 * 02/23/2016
 */

public class AutoCompleteDictionaryTrie implements Dictionary, AutoComplete {

    private TrieNode root;
    private int size; // number of words in trie
    private int sizeNodes; // added and tested to know number of nodes for fun
    
    public AutoCompleteDictionaryTrie() {
		root = new TrieNode();
		size = 0;
		sizeNodes = 0;
	}
	
	/** Insert a word into the trie
	 * For part 2 of the assignment, ignore the word's case, convert all to lowercase
	 */
	public boolean addWord(String word) {
	    //TODO: Implement this method.
		TrieNode curr = root;
		String s = word.toLowerCase();
		char c = ' ';
		for (int i = 0; i < s.length(); i ++) {
			c = s.charAt(i);
			if (curr.getChildren().containsKey(c)) {
				curr = curr.getChild(c);
			}
			else {
				curr = curr.setChild(c);
				sizeNodes +=1; // number of nodes increases by 1
			}
		}
		if (! curr.getIsWord()) { // if this node is not yet a word
			curr.setIsWord(true); // then word now affirms that it is a word
			size +=1; // number of words increases by 1
			return true; // word added as new word
		}
	    return false; // this node is already a word, word is not new and not added
	}
	
	/** Return number of words in trie, which may not equal number of TrieNodes in trie */
	public int size() {
	    //TODO: Implement this method
	    return size;
	}
	
	public int sizeNodes() {
		return sizeNodes;
	}
	
	/** Returns whether String s is a word in trie */
	@Override
	public boolean isWord(String s) {
	    // TODO: Implement this method
		TrieNode curr = root;
		String t = s.toLowerCase();
		for (int i = 0; i < t.length(); i++) {
			curr = curr.getChild(t.charAt(i));
			if (curr == null) {
				return false;
			}
		}
		if (curr.getIsWord()) {
			return true;
		}
		return false;
	}

	/** 
	 * Return a list of length up to numCompletions of
	 * predictions for stem, including stem itself if it is a word
     * If stem is not in the trie, return null, i.e. no prediction given
     */
	@Override
    public List<String> predictCompletions(String stem, int numCompletions) {
    	// TODO: Implement this method by following this algorithm:
    	// 1. Find stem in trie. If stem is not in trie, return empty list
    	// 2. Once stem is found, perform breadth-first search to generate completions using the following algorithm:
    	//    Create a queue (LinkedList) and add the node that completes stem to the back of queue
    	//    Create an initially empty list of completions to return
    	//    While queue is not empty and there haven't been enough completions:
    	//       remove first node in queue
    	//       if it is a word, add it to the list of completions
    	//       add all of its child nodes to the back of queue
    	// Return list of completions
    	List<String> completions = new LinkedList<String>();
    	TrieNode curr = root;
 		String s = stem.toLowerCase();
 		for (int i = 0; i < s.length(); i++) {
 			curr = curr.getChild(s.charAt(i));
 			if (curr == null) {
 				return completions;
 			}
 		}
		Queue<TrieNode> q = new LinkedList<TrieNode>();
		q.add(curr);
		while ((!q.isEmpty()) && (completions.size() < numCompletions)) {
			curr = q.remove();
			if (curr.getIsWord()) {
				completions.add(curr.getText());
			}
			for (char d : curr.getChildren().keySet()) {
				q.add(curr.getChild(d));
			}
		}	
		return completions;
    }

 	// For debugging
 	public void printTree() {
 		printNode(root);
 	}
 	
 	/** Do a preorder traversal from this node down */
 	public void printNode(TrieNode curr) {
 		if (curr == null) { 
 			return;
 		}
 		System.out.println(curr.getText());
 		TrieNode next = null;
 		for (Character c : curr.getChildren().keySet()) {
 			next = curr.getChildren().get(c);
 			printNode(next);
 		}
 	}
	
}