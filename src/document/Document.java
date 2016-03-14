package document;

/**
 * A class that represents a text document
 * @author UC San Diego Intermediate Programming MOOC team
 **/

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Document {

    private String text;
   
    /** Create a new document from the given text.
     * Because this class is abstract, this is used only from subclasses.
     * @param text The text of the document.
     */
    protected Document(String text) {
        this.text = text;
    }
   
    /** Returns the tokens that match the regex pattern from the document
     * text string.
     * @param pattern A regular expression string specifying the
     *   token pattern desired
     * @return A List of tokens from the document text that match the regex
     *   pattern
     */
    protected List<String> getTokens(String pattern) {
        ArrayList<String> tokens = new ArrayList<String>();
        Pattern tokSplitter = Pattern.compile(pattern);
        Matcher m = tokSplitter.matcher(text);
        while (m.find()) {
            tokens.add(m.group());
        }
        return tokens;
    }
   
    // This is a helper function that returns the number of syllables in a word.
    // You will probably NOT need to add a countWords or a countSentences method here.
    // The reason we put countSyllables here because we'll use it again
    // next week when we implement the EfficientDocument class.
    // Both subclasses BasicDocument and EfficientDocument can call this protected static method
    protected static int countSyllables(String word)
	{
	    //System.out.print("Counting syllables in " + word + "...");
		int numSyllables = 0;
		boolean newSyllable = true;
		String vowels = "aeiouy";
		char[] cArray = word.toCharArray();
		for (int i = 0; i < cArray.length; i++)
		{
		    if (i == cArray.length-1 && Character.toLowerCase(cArray[i]) == 'e' 
		    		&& newSyllable && numSyllables > 0) {
                numSyllables--;
            }
		    if (newSyllable && vowels.indexOf(Character.toLowerCase(cArray[i])) >= 0) {
				newSyllable = false;
				numSyllables++;
			}
			else if (vowels.indexOf(Character.toLowerCase(cArray[i])) < 0) {
				newSyllable = true;
			}
		}
		//System.out.println( "found " + numSyllables);
		return numSyllables;
	}
    
    /*
     * My way
    protected int countSyllables(String word) {
        int numSyllablesInWord = 0;
        ArrayList<String> vowelClusters = new ArrayList<String>();
        Pattern vowSplitter = Pattern.compile("[aeiouy]+[bcdfghjklmnpqrstvwxz]*");
        Matcher ma = vowSplitter.matcher(word);
        while (ma.find()) {
            vowelClusters.add(ma.group());
        }
        int numVowelClusters = vowelClusters.size();
        if (numVowelClusters > 1 && vowelClusters.get(numVowelClusters - 1).equalsIgnoreCase("e")) {
            numSyllablesInWord += numVowelClusters - 1;
        }
        else {
            numSyllablesInWord += numVowelClusters;
        }
        return numSyllablesInWord;
    }
    */
   
    /** A method for testing
     *
     * @param doc The Document object to test
     * @param syllables The expected number of syllables
     * @param words The expected number of words
     * @param sentences The expected number of sentences
     * @return true if the test case passed.  False otherwise.
     */
    public static boolean testCase(Document doc, int syllables, int words, int sentences) {
        System.out.println("\ntesting text: ");
        System.out.println(doc.getText());
        boolean passed = true;
        int syllFound = doc.getNumSyllables();
        int wordsFound = doc.getNumWords();
        int sentFound = doc.getNumSentences();
        if (syllFound != syllables) {
            System.out.println("\nIncorrect number of syllables.  Found " + syllFound
                    + ", expected " + syllables);
            passed = false;
        }
        if (wordsFound != words) {
            System.out.println("\nIncorrect number of words.  Found " + wordsFound
                    + ", expected " + words);
            passed = false;
        }
        if (sentFound != sentences) {
            System.out.println("\nIncorrect number of sentences.  Found " + sentFound
                    + ", expected " + sentences);
            passed = false;
        }
        if (passed) {
            System.out.println("passed");
        }
        else {
            System.out.println("failed");
        }
        System.out.println("Flesch score " + doc.getFleschScore());
        return passed;
    }
   
   
    /** Return the number of words in this document */
    public abstract int getNumWords();
   
    /** Return the number of sentences in this document */
    public abstract int getNumSentences();
   
    /** Return the number of syllables in this document */
    public abstract int getNumSyllables();
   
    /** Return the entire text of this document */
    public String getText() {
        return this.text;
    }
   
    /** return the Flesch readability score of this document */
    public double getFleschScore() {
    	double wordCount = (double)getNumWords();
        double FleschScore = 206.835 - 1.015 * wordCount / getNumSentences() - 84.6 * getNumSyllables() / wordCount;
        return FleschScore;
    }
   
}

