package document;

import java.util.List;

/**
 * A naive implementation of the Document abstract class.
 * @author UC San Diego Intermediate Programming MOOC team
 **/

public class DocumentBasic extends Document {
	
    /** Create a new DocumentBasic object
     * @param text The full text of the Document.
     */
    public DocumentBasic(String text) {
        super(text);
    }
   
   
    /**
	 * Get the number of words in the document.
	 * defined as contiguous strings of alphabetic characters a-z or A-Z
	 * @return The number of words in the document.
	 */
    @Override
    public int getNumWords() {
        List<String> tokens = getTokens("[a-zA-Z]+");
        return tokens.size();
    }
   
    /**
	 * Get the number of sentences in the document.
	 * defined as contiguous strings of characters ending in . ! or ? 
	 * or the last contiguous set of characters even if without punctuation mark.
	 * @return The number of sentences in the document.
	 */
    @Override
    public int getNumSentences() {
        List<String> tokens = getTokens("[^!?.]+");
        return tokens.size();
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
        int numSyllablesInText = 0;
        List<String> words = getTokens("[a-zA-Z]+");
        for (String word : words) {
            numSyllablesInText += countSyllables(word.toLowerCase());
        }
        return numSyllablesInText;
    }
   
    public static void main(String[] args) {
        testCase(new DocumentBasic("This is a test.  How many???  "
                + "Senteeeeeeeeeences are here... there should be 5!  Right?"),
                16, 13, 5);
        testCase(new DocumentBasic(""), 0, 0, 0);
        testCase(new DocumentBasic("sentence, with, lots, of, commas.!  "
                + "(And some poaren)).  The output is: 7.5."), 15, 11, 4);
        testCase(new DocumentBasic("many???  Senteeeeeeeeeences are"), 6, 3, 2);
        testCase(new DocumentBasic("Here is a series of test sentences. Your program should "
                + "find 3 sentences, 33 words, and 49 syllables. Not every word will have "
                + "the correct amount of syllables (example, for example), "
                + "but most of them will."), 49, 33, 3);
        testCase(new DocumentBasic("Segue"), 2, 1, 1);
        testCase(new DocumentBasic("Sentence"), 2, 1, 1);
        testCase(new DocumentBasic("Sentences?!"), 3, 1, 1);
        testCase(new DocumentBasic("Lorem ipsum dolor sit amet, qui ex choro quodsi moderatius, nam dolores explicari forensibus ad."),
                 32, 15, 1);   
    }
   
}