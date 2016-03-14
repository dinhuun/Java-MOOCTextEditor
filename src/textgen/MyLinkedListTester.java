/**
 * 
 */
package textgen;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 * delete java.util.LinkedList
 */

public class MyLinkedListTester {

	private static final int longListSize = 10; 
	private static final int element = 1000;

	MyLinkedList<Integer> emptyList;
	MyLinkedList<String> shortList;
	MyLinkedList<Integer> mediumList;
	MyLinkedList<Integer> longList;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		emptyList = new MyLinkedList<Integer>();
		
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		
		mediumList = new MyLinkedList<Integer>();
		mediumList.add(1);
		mediumList.add(10);
		mediumList.add(100);
		
		longList = new MyLinkedList<Integer>();
		for (int i = 0; i < longListSize; i++) {
			longList.add(i);
		}
		
	}
	
	/** Test the size method */
	@Test
	public void testSize() {
		// TODO: implement this test
		assertEquals("Size: check if correct ", 0, emptyList.size());
		assertEquals("Size: check if correct ", 2, shortList.size());
		assertEquals("Size: check if correct ", 3, mediumList.size());
		assertEquals("Size: check if correct ", longListSize, longList.size());
	}

	/** Test the get method
	 * You should not need to add much here. We provide it as an example of a thorough test
	 */
	@Test
	public void testGet() {
		// test with regard to out of bounds
		try {
			emptyList.get(1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		try {
			longList.get(longListSize);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		// test with regard to content
		assertEquals("Check 0th node", "A", shortList.get(0));
		assertEquals("Check 1st node", "B", shortList.get(1));
		assertEquals("Check 2nd node", (Integer)100, mediumList.get(2));	
		for(int i = 0; i<longListSize; i++ ) {
			assertEquals("Check " + i + " node", (Integer)i, longList.get(i));
		}
	}
	
	/** Test the set method */
	@Test
	public void testSet() {
	    // TODO: implement this test
		// test with regard to out of bounds
		try {
			mediumList.set(3, element);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		// test with regard to content
		int position = 1;
		int a = mediumList.get(position - 1);
		int b = mediumList.get(position + 1);
		int c = mediumList.set(position, element);
		assertEquals("Check previous node", (Integer)a, mediumList.get(position - 1));
		assertEquals("Check changed node", element, c);
		assertEquals("Check previous node", (Integer)b, mediumList.get(position + 1));		
	}
	
	/** Test addStart */
	@Test
	public void testAddStart() {
        // TODO: implement this test
		int a = mediumList.size();
		mediumList.addStart(element);
		assertEquals("Add Start: check new size is correct ", a + 1, mediumList.size());
		assertEquals("Add Start: check element 0 is correct ", (Integer)element, mediumList.get(0));
		assertEquals("Add End: check element 1 is correct ", (Integer)1, mediumList.get(1));
	}

	/** Test add method */
	@Test
	public void testAdd() {
        // TODO: implement this test
		int position = 1;
		int a = mediumList.size();
		int b = mediumList.get(position - 1);
		int c = mediumList.get(position);
		mediumList.add(position, element);
		assertEquals("Add: check new size is correct ", a + 1, mediumList.size());
		assertEquals("Add: check previous node ", (Integer)b, mediumList.get(position - 1));
		assertEquals("Add: check added node ", (Integer)element, mediumList.get(position));
		assertEquals("Add: check next node ", (Integer)c, mediumList.get(position + 1));
	}

	/** Test adding an element to end of list, specifically public boolean addEnd(E element) */
	@Test
	public void testAddEnd() {
        // TODO: implement this test
		int a = mediumList.size();
		mediumList.addEnd(element);
		assertEquals("Add End: check new size is correct ", a + 1, mediumList.size());
		assertEquals("Add End: check element 2 is correct ", (Integer)100, mediumList.get(2));
		assertEquals("Add End: check element 3 is correct ", (Integer)element, mediumList.get(3));
	}

	/** Test removing an element from the list
	 * We've included the example from the concept challenge
	 * You will want to add more tests
	 */
	@Test
	public void testRemove() {
		int position = 1;
		int a = mediumList.size();
		int b = mediumList.get(position - 1);
		int c = mediumList.get(position);
		int d = mediumList.get(position + 1);
		int e = mediumList.remove(position);
		assertEquals("Remove: check new size is correct ", a - 1, mediumList.size());
		assertEquals("Remove: check previous node ", (Integer)b, mediumList.get(position - 1));
		assertEquals("Remove: check removed node ", c, e);
		assertEquals("Remove: check next node ", (Integer)d, mediumList.get(position));
		// TODO: Add more tests here
	}
	
	/** Test toStringing lists */
	@Test
	public void testToString() {
		assertEquals("toString: check if strings are equal ", "[A, B]", shortList.toString());
		assertEquals("toString: check if strings are equal ", "[1, 10, 100]", mediumList.toString());
	}
	
	// TODO: Optionally add more test methods.
}
