package spelling;

import java.util.HashMap;

/** 
 * Represents a node in a Trie
 * @author UC San Diego Intermediate Programming MOOC Team
 * delete java.util.Set
 * method insert() renamed method setChild()
 */

class TrieNode {
	private String text;  // Maybe omit for space
	private HashMap<Character, TrieNode> children; 
	private boolean isWord;
	
	/** constructor */
	public TrieNode() {
		text = "";
		children = new HashMap<Character, TrieNode>();
		isWord = false;
	}
	
	/** constructor with a given String text as data */
	public TrieNode(String text) {
		this();
		this.text = text;
	}
	
	/** Return the text string at this node */
    public String getText() {
		return text;
	}
	
    /** Set whether or not the text at this node is a word */
	public void setIsWord(boolean b) {
		isWord = b;
	}
	
	/** Return whether or not the text at this node is a word */
	public boolean getIsWord() {
		return isWord;
	}
	
	/** Return null if image of Character c has been set, else set and return it */
	public TrieNode setChild(Character c) {
		if (children.containsKey(c)) {
			return null; // no action taken
		}
		TrieNode child = new TrieNode(text + c.toString());
		children.put(c, child);
		return child;
	}
	
	/** Return image of Character c, null or not */
	public TrieNode getChild(Character c) {
		return children.get(c);
	}
	
	/** Return HashMap children */
	public HashMap<Character, TrieNode> getChildren() {
		return children;
	}
	
}

