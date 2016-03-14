package textgen;

import java.util.AbstractList;

/** A class that implements a doubly linked list
 * @author UC San Diego Intermediate Programming MOOC team
 * @param <E> The type of the elements stored in the list
 * null element can be added
 */

public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	private int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		head = new LLNode<E>();
		tail = new LLNode<E>();
		head.setNext(tail);
		tail.setPrev(head);
		size = 0;
	}
	
	/** Get size of list */
	public int size() {
		// TODO: Implement this method
		return size;
	}
	
	public LLNode<E> nodeAt(int index) {
		LLNode<E> n = head.getNext();
		for (int i = 0; i < index; i++) {
			n = n.getNext();
		}
		return n;
	}
	
	/** Get data of node at position index 
	 * @throws IndexOutOfBoundsException if index is out of bounds
	 */
	public E get(int index) {
		// TODO: Implement this method.
		checkIndex0(index);
		return nodeAt(index).getData();
	}

	/** Set data of node at position index to element
	 * @param index The index of the node to change
	 * @param element The new data of changed node
	 * @return The data replaced
	 * @throws IndexOutOfBoundsException if index is out of bounds */
	public E set(int index, E element) {
		// TODO: Implement this method
		checkIndex0(index);
		nodeAt(index).setData(element);
		return nodeAt(index).getData();
	}   
	
	/** Add an LLNode with data element to start of list */
	public boolean addStart(E element) {
		LLNode<E> n = new LLNode<E>(element);
		head.getNext().setPrev(n);
		n.setPrev(head);
		n.setNext(head.getNext());
		head.setNext(n);
		size++;
		return true;
	}
	
	/** Add an LLNode with data element to list at position index */
	public void add(int index, E element ) {
		// TODO: Implement this method
		checkIndex1(index);
		LLNode<E> m = new LLNode<E>(element);
		LLNode<E> n = nodeAt(index);
		n.getPrev().setNext(m);
		m.setPrev(n.getPrev());
		m.setNext(n);
		n.setPrev(m);
		size++;
	}
	 
	/** Add an LLNode with data element to end of list */
	public boolean addEnd(E element ) {
		// TODO: Implement this method
		LLNode<E> n = new LLNode<E>(element);
		tail.getPrev().setNext(n);
		n.setPrev(tail.getPrev());
		n.setNext(tail);
		tail.setPrev(n);
		size++;
		return true;
	}

	/** Remove an LLnode at position index and return its data element */
	public E remove(int index) {
		// TODO: Implement this method
		checkIndex0(index);
		LLNode<E> n = nodeAt(index);
		n.getPrev().setNext(n.getNext());
		n.getNext().setPrev(n.getPrev());;
		n.setPrev(null);
		n.setNext(null);
		size--;
		return n.getData();
	}
	
	// print all elements in list
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < size; i++) {
			sb.append(this.nodeAt(i).toString() + ", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append("]");
		return sb.toString();
	}
	
	// helper method to check if index is within bounds
	private void checkIndex0(int index) {
		if (index < 0 || size <= index) {
			throw new IndexOutOfBoundsException("index out of bounds");
		}
	}
	
	// helper method to check if index is within bounds	
	private void checkIndex1(int index) {
		if (index < 0 || size < index) {
			throw new IndexOutOfBoundsException("index out of bounds");
		}
	}

}

class LLNode<E> {
	private E data;
	private LLNode<E> prev;
	private LLNode<E> next;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor
	public LLNode() {
		this.data = null;
		this.prev = null;
		this.next = null;
	}
	
	public LLNode(E element) {
		this();
		this.data = element;
	}
	
	public LLNode(E element, LLNode<E> prevNode) {
		this(element);
		this.prev = prevNode;
	}
	
	public LLNode(E element, LLNode<E> prevNode, LLNode<E> nextNode) {
		this(element, prevNode);
		this.next = nextNode;		
	}
	
	public E getData() {
		return this.data;
	}
	
	public void setData(E element) {
		this.data = element;
	}
	
	public LLNode<E> getPrev() {
		return this.prev;
	}
	
	public void setPrev(LLNode<E> n) {
		this.prev = n;
	}
	
	public LLNode<E> getNext() {
		return this.next;
	}
	
	public void setNext(LLNode<E> n) {
		this.next = n;
	}
	
	@Override
	public String toString() {
		return data.toString();
	}
}
