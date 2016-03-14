package document;

import java.util.List;

/** 
 * A class that represents a text document
 * It does one pass through the document to count the number of syllables, words, 
 * and sentences and then stores those values.
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class DocumentEfficient extends Document {

	private int numWords;  // The number of words in the document
	private int numSentences;  // The number of sentences in the document
	private int numSyllables;  // The number of syllables in the document
	
	public DocumentEfficient(String text)
	{
		super(text);
		processText();
	}
	
	
	/**
	 * Return true if this string is a word (as opposed to punctuation)
	 * @param tok The string to check
	 * @return true if tok is a word, false otherwise
	 */
	private boolean isWord(String tok)
	{
	    // Note: This is a fast way of checking whether a string is a word
	    // You probably don't want to change it.
		return !(tok.indexOf("!") >= 0 || tok.indexOf(".") >= 0 || tok.indexOf("?") >= 0);
	}
	
	
    /**
     * Passes through the text one time to count the number of words, syllables and 
     * sentences, and set the member variables appropriately.
     * Words, sentences and syllables are defined as described below. 
     */
	private void processText()
	{
		// Provide this first line in the starter code.  
		// Words are only strings of letters.  No numbers.
		List<String> tokens = getTokens("[!?.]+|[a-zA-Z]+");
		for (String token : tokens) {
			if (isWord(token) == true) {
				numWords += 1;
				numSyllables += countSyllables(token.toLowerCase());
			}
			else {
				numSentences += 1;
			}
        }
		if (tokens.size() > 0 && isWord(tokens.get(tokens.size() - 1)) == true) {
			numSentences += 1;
		}
	}
	
	
	/**
	 * Get the number of words in the document.
	 * defined as contiguous strings of alphabetic characters a-z or A-Z
	 * @return The number of words in the document.
	 */
	@Override
	public int getNumWords() {
		//TODO: write this method.  Hint: It's simple
	    return numWords;
	}

	/**
	 * Get the number of sentences in the document.
	 * defined as contiguous strings of characters ending in . ! or ? 
	 * or the last contiguous set of characters even if without punctuation mark.
	 * @return The number of sentences in the document.
	 */
	@Override
	public int getNumSentences() {
        //TODO: write this method.  Hint: It's simple
        return numSentences;
	}

	/**
	 * Get the number of syllables in the document.
	 * defined as contiguous sequence of vowels, except for a lone "e" at the 
	 * end of a word if the word already has a set of contiguous vowels 
	 * y is considered a vowel.
	 * @return The number of syllables in the document.
	 */
	@Override
	public int getNumSyllables() {
        return numSyllables;
	}
	
	// Can be used for testing
	// We encourage you to add your own tests here.
	public static void main(String[] args)
	{
	    testCase(new DocumentEfficient("This is a test.  How many???  "
                + "Senteeeeeeeeeences are here... there should be 5!  Right?"),
                16, 13, 5);
        testCase(new DocumentEfficient(""), 0, 0, 0);
        testCase(new DocumentEfficient("sentence, with, lots, of, commas.!  "
                + "(And some poaren)).  The output is: 7.5."), 15, 11, 4);
        testCase(new DocumentEfficient("many???  Senteeeeeeeeeences are"), 6, 3, 2); 
        testCase(new DocumentEfficient("Here is a series of test sentences. Your program should "
				+ "find 3 sentences, 33 words, and 49 syllables. Not every word will have "
				+ "the correct amount of syllables (example, for example), "
				+ "but most of them will."), 49, 33, 3);
		testCase(new DocumentEfficient("Segue"), 2, 1, 1);
		testCase(new DocumentEfficient("Sentence"), 2, 1, 1);
		testCase(new DocumentEfficient("Sentences?!"), 3, 1, 1);
		testCase(new DocumentEfficient("Lorem ipsum dolor sit amet, qui ex choro quodsi moderatius, nam dolores explicari forensibus ad."),
		         32, 15, 1);	
	}
	
}
