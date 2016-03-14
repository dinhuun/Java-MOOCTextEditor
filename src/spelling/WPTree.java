/**
 * 
 */
package spelling;

import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * WPTree implements WordPath by dynamically creating a tree of words during a Breadth First
 * Search of Nearby words to create a path between two words. 
 * @author UC San Diego Intermediate MOOC team
 */

public class WPTree implements WordPath {
	// THRESHOLD number of words to look through for spelling suggestions (stops prohibitively long searching)
	// For use in the Optional Optimization in Part 2
	private static final int THRESHOLD = 1000000; 
	
	// this is the root node of the WPTree
	private WPTreeNode root;
	// used to search for nearby Words
	private NearbyWords nw; 
	
	// This constructor is used by the Text Editor Application
	// You'll need to create your own NearbyWords object here.
	public WPTree () {
		this.root = null;
		// TODO initialize a NearbyWords object
		Dictionary d = new DictionaryHashSet();
		DictionaryLoader.loadDictionary(d, "data/dict.txt");
		this.nw = new NearbyWords(d);
	}
	
	//This constructor will be used by the grader code
	public WPTree (NearbyWords nw) {
		this.root = null;
		this.nw = nw;
	}
	
	// see method description in WordPath interface
	public List<String> findPath(String word0, String word1) {
	    // TODO: Implement this method.
		root = new WPTreeNode(word0, null);
		Queue<WPTreeNode> queue = new LinkedList<WPTreeNode>();     // string to explore
		queue.add(root);
		HashSet<String> visited = new HashSet<String>();   // avoid exploring the same string multiple times
		visited.add(word0);
		WPTreeNode curr = root;
		List<String> neighbors = new ArrayList<String>();

		while ((!queue.isEmpty()) && (visited.size() < THRESHOLD)) {
			curr = queue.remove();
			neighbors = nw.distanceOne(curr.getWord(), true);			
			for (String t : neighbors) {
				if (! visited.contains(t)) {
					visited.add(t);
					WPTreeNode child = curr.addChild(t);
					queue.add(child);
					if (t.equals(word1)) {
						return child.buildPathToRoot();
					}
				}
			}
		}
	    return new LinkedList<String>();
	}
	
	// Method to print a queue of WPTreeNodes (useful for debugging)
	private String printQueue(List<WPTreeNode> list) {
		String ret = "[ ";
		for (WPTreeNode w : list) {
			ret+= w.getWord() + ", ";
		}
		ret+= "]";
		return ret;
	}
	
	   public static void main(String[] args) {
		   WPTree tree = new WPTree();
		   System.out.println(tree.findPath("talo", "tailor"));
	   }
	
}

/* Tree Node in a standard tree WordPath Tree
 * each node has any number of possible children
 * each node contains a word in the dictionary
 * distance between each parent and child is 1
*/
class WPTreeNode {
    private String word;
    private WPTreeNode parent;
    private List<WPTreeNode> children;
    
    /** Construct a node with word w and parent p, root is to be given parent null */
    public WPTreeNode(String w, WPTreeNode p) {
        this.word = w;
        this.parent = p;
        this.children = new LinkedList<WPTreeNode>();
    }
    
    /** Add a child containing word s for this node
     *  precondition: this node has no child containing word s yet
	 * @return new WPTreeNode child
	 */
    public WPTreeNode addChild(String s) {
        WPTreeNode child = new WPTreeNode(s, this);
        this.children.add(child);
        return child;
    }
    
    /** Get children of this node
     *  (pass a null parent to construct the root)  
	 * @return list of WPTreeNode children
	 */
    public List<WPTreeNode> getChildren() {
        return this.children;
    }
   
    /** Build a path from root to this node
     * @return list of strings starting at root and ending at this node
	 */
    public List<String> buildPathToRoot() {
        WPTreeNode curr = this;
        List<String> path = new LinkedList<String>();
        while(curr != null) {
            path.add(0,curr.getWord());
            curr = curr.parent; 
        }
        return path;
    }
    
    /** Get word of this node */
    public String getWord() {
        return this.word;
    }
    
    /** toString method for WPTreeNode that returns its word, its parent's word
     * and its children's words */
    @Override
    public String toString() {
        String ret = "Word: "+ word + ", parent = ";
        if(this.parent == null) {
           ret+= "null.\n";
        }
        else {
           ret += this.parent.getWord() + "\n";
        }
        ret+= "[ ";
        for(WPTreeNode curr: children) {
            ret+= curr.getWord() + ", ";
        }
        ret+=(" ]\n");
        return ret;
    }

}

