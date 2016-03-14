package demos;

import java.util.LinkedList;
import java.util.Queue;

public class TreeList<E extends Comparable<? super E>> {
	// variables
	TreeNode<E> root;
	
	// constructors
	public TreeList(E element) {
		root = new TreeNode<E>(element, null);
	}
	
	private void preOrder(TreeNode<E> node) {
		if (node != null) {
			node.visit();
			preOrder(node.getLeft());
			preOrder(node.getRight());
		}
	}
	
	private void postOrder(TreeNode<E> node) {
		if (node != null) {
			postOrder(node.getLeft());
			postOrder(node.getRight());
			node.visit();
		}
	}
	
	private void inOrder(TreeNode<E> node) {
		if (node != null) {
			postOrder(node.getLeft());
			node.visit();
			postOrder(node.getRight());
		}
	}
	
	public void preOrder() {
		this.preOrder(root);
	}
	
	public void postOrder() {
		this.postOrder(root);
	}
	
	public void inOrder() {
		this.inOrder(root);
	}
	
	// traverse the whole tree by using Queue
	// to remove each level member at the front of queue while adding all its sublevel members at the back
	public void levelOrder() {
		Queue<TreeNode<E>> q = new LinkedList<TreeNode<E>>();
		TreeNode<E> curr = root;
		q.add(root);
		while (! q.isEmpty()) {
			curr = q.remove();
			if (curr != null) {
				curr.visit();
				if (curr.getLeft() != null) {
					q.add(curr.getLeft());
				}
				if (curr.getRight() != null) {
					q.add(curr.getRight());
				}
			}
		}
	}
	
	// can be done with recursion as well
	public boolean contains(E element) {
		TreeNode<E> curr = root;
		while (curr != null) {
			int difference = element.compareTo(curr.getData());
			if (difference < 0) {
				curr = curr.getLeft();
			}
			else if (difference > 0) {
				curr = curr.getRight();
			}
			else {
				return true;
			}
		}
		return false;
	}
	
	// perhaps a parameter for starting point instead of always root, useful for remove
	public boolean insert(E element) {
		TreeNode<E> curr = root;
		int difference = element.compareTo(curr.getData());
		while ((difference < 0 && curr.getLeft() != null) || (difference > 0 && curr.getRight() != null)) {
			if (difference < 0) {
				curr = curr.getLeft();
			}
			else {
				curr = curr.getRight();
			}
			difference = element.compareTo(curr.getData());
		}
		if (difference < 0) {
			curr.addLeft(element);
			return true;
		}
		else if (difference > 0) {
			curr.addRight(element);
			return true;
		}
		else {
			return false; // difference = 0, tree already contains element
		}
	}
	
	public boolean remove(E element) {
		// find least upper bound
		// replace element with least upper bound
		// delete least upper bound in right subtree
		return false;
	}
	
@SuppressWarnings("hiding")
class TreeNode<E> {
	private E data;
	private TreeNode<E> parent;
	private TreeNode<E> left;
	private TreeNode<E> right;
	
	// all nodes must have parent, element could be null, root has parent p = null
	public TreeNode(E element, TreeNode<E> p) {
		this.data = element;
		this.parent = p;
	}
	
	public E getData() {
		return this.data;
	}
	
	public void setData(E element) {
		this.data = element;
	}
	
	public TreeNode<E> getParent() {
		return this.parent;
	}
	
	public TreeNode<E> addLeft(E element) {
		this.left = new TreeNode<E>(element, this);
		return this.left;
	}
	
	public TreeNode<E> getLeft() {
		return this.left;
	}
	
	public TreeNode<E> addRight(E element) {
		this.right = new TreeNode<E>(element, this);
		return this.right;
	}
	
	public TreeNode<E> getRight() {
		return this.right;
	}
	
	public void visit() {
		
	}	
}

}
