/**
 * 
 */
package spelling;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 * delete java.util.LinkedList
 */

public class DictionaryLLTester {

	private String dictFile = "data/words.small.txt"; 
	DictionaryLL emptyDict; 
	DictionaryLL smallDict;
	DictionaryLL largeDict;
	
	/** @throws java.lang.Exception */
	@Before
	public void setUp() throws Exception {
		emptyDict = new DictionaryLL();
		
		smallDict = new DictionaryLL();
		smallDict.addWord("Hello");
		smallDict.addWord("HElLo");
		smallDict.addWord("help");
		smallDict.addWord("a");
		smallDict.addWord("subsequent");
		
		largeDict = new DictionaryLL();
		DictionaryLoader.loadDictionary(largeDict, dictFile);
	}

	
	/** Test if method size() works correctly */
	@Test
	public void testSize() {
		assertEquals("Testing size for emptyDict", 0, emptyDict.size());
		assertEquals("Testing size for smallDict", 4, smallDict.size());
		assertEquals("Testing size for largeDict", 4438, largeDict.size());
	}
	
	/** Test method isWord() */
	@Test
	public void testIsWord() {
		assertEquals("Testing isWord on emptyDict: Hello", false, emptyDict.isWord("Hello"));
		assertEquals("Testing isWord on smallDict: Hello", true, smallDict.isWord("Hello"));
		assertEquals("Testing isWord on largeDict: Hello", true, largeDict.isWord("Hello"));
		
		assertEquals("Testing isWord on smallDict: hello", true, smallDict.isWord("hello"));
		assertEquals("Testing isWord on largeDict: hello", true, largeDict.isWord("hello"));

		assertEquals("Testing isWord on smallDict: hellow", false, smallDict.isWord("hellow"));
		assertEquals("Testing isWord on largeDict: hellow", false, largeDict.isWord("hellow"));
		
		assertEquals("Testing isWord on emptyDict: empty string", false, emptyDict.isWord(""));
		assertEquals("Testing isWord on smallDict: empty string", false, smallDict.isWord(""));
		assertEquals("Testing isWord on largeDict: empty string", false, largeDict.isWord(""));
		
		assertEquals("Testing isWord on smallDict: no", false, smallDict.isWord("no"));
		assertEquals("Testing isWord on largeDict: no", true, largeDict.isWord("no"));
		
		assertEquals("Testing isWord on smallDict: subsequent", true, smallDict.isWord("subsequent"));
		assertEquals("Testing isWord on largeDict: subsequent", true, largeDict.isWord("subsequent"));			
	}
	
	/** Test method addWord() */
	@Test
	public void addWord() {		
		assertEquals("Asserting hellow is not in emptyDict", false, emptyDict.isWord("hellow"));
		assertEquals("Asserting hellow is not in smallDict", false, smallDict.isWord("hellow"));
		assertEquals("Asserting hellow is not in largeDict", false, largeDict.isWord("hellow"));
		
		emptyDict.addWord("hellow");
		smallDict.addWord("hellow");
		largeDict.addWord("hellow");

		assertEquals("Asserting hellow is in emptyDict", true, emptyDict.isWord("hellow"));
		assertEquals("Asserting hellow is in smallDict", true, smallDict.isWord("hellow"));
		assertEquals("Asserting hellow is in largeDict", true, largeDict.isWord("hellow"));

		assertEquals("Asserting xyzabc is not in emptyDict", false, emptyDict.isWord("xyzabc"));
		assertEquals("Asserting xyzabc is not in smallDict", false, smallDict.isWord("xyzabc"));
		assertEquals("Asserting xyzabc is in largeDict", false, largeDict.isWord("xyzabc"));

		emptyDict.addWord("XYZAbC");
		smallDict.addWord("XYZAbC");
		largeDict.addWord("XYZAbC");

		assertEquals("Asserting xyzabc is in emptyDict", true, emptyDict.isWord("xyzabc"));
		assertEquals("Asserting xyzabc is in smallDict", true, smallDict.isWord("xyzabc"));
		assertEquals("Asserting xyzabc is largeDict", true, largeDict.isWord("xyzabc"));
		
		assertEquals("Testing isWord on emptyDict: empty string", false, emptyDict.isWord(""));
		assertEquals("Testing isWord on smallDict: empty string", false, smallDict.isWord(""));
		assertEquals("Testing isWord on largeDict: empty string", false, largeDict.isWord(""));
		
		assertEquals("Testing isWord on smallDict: no", false, smallDict.isWord("no"));
		assertEquals("Testing isWord on largeDict: no", true, largeDict.isWord("no"));
		
		assertEquals("Testing isWord on smallDict: subsequent", true, smallDict.isWord("subsequent"));
		assertEquals("Testing isWord on largeDict: subsequent", true, largeDict.isWord("subsequent"));
	}	
	
}
