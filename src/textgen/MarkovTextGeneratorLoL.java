package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team
 * java.util.ArrayList, java.util.ListIterator, java.util.regex.Matcher,java.util.regex.Pattern deleted
 */

public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> leads; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator) {
		starter = "";
		rnGenerator = generator;
		leads = new LinkedList<ListNode>();
	}
	
	
	/** Train MarkovTextGeneratorLo by creating list of leads and follows from trainingText */
	@Override
	public void train(String trainingText) {
		// TODO: Implement this method
		if (trainingText.length() == 0) {
			System.out.println("no text to train");
			return;
		}
		String[] trainingWords = trainingText.split("\\W+");
        starter = trainingWords[0];
        String prevWord = starter;
        String w;
        ListNode lead;
        for (int i = 1; i < trainingWords.length; i++) {
        	w = trainingWords[i];
        	lead = ownerOf(prevWord);
        	if (lead == null) {
        		ListNode n = new ListNode(prevWord);
        		n.addNextWords(w);
        		leads.add(n);
        	}
        	else {
        		lead.addNextWords(w);
        	}
        	prevWord = w;
        }
        lead = ownerOf(prevWord);
        if (lead == null) {
        	ListNode n = new ListNode(prevWord);
    		n.addNextWords(trainingWords[0]);
    		leads.add(n);
        }
        else {
        	lead.addNextWords(trainingWords[0]);
        } 
	}
	
	/** Generate a text of size numWords */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
		String currWord = starter;
		String output = currWord;
		ListNode lead;
		int numWordsGenerated = 1;
		while (numWordsGenerated < numWords) {
			lead = ownerOf(currWord);
			String follow = lead.getRandomNextWord(rnGenerator);
			output = output + " " + follow;
			currWord = follow;
			numWordsGenerated++;
		}
		return output;
	}
	
	/** Retrain MarkovTextGeneratorLo from scratch with new trainingText */
	@Override
	// not used here, kept for MarkovTextGeneratorGrader.java
	public void retrain(String trainingText){
		// TODO: Implement this method.
		leads.clear();
		train(trainingText);
	}
	
	// TODO: Add any private helper methods you need here.
	private ListNode ownerOf(String w) {
		for (ListNode lead : leads) {
			if (lead.getWord().equals(w)) {
				return lead;
			}
		}
		return null;
	}
	
	// toString for class MarkovTextGeneratorLoL in fact prints its list of leads and their follows
	// Can be helpful for debugging method train
	@Override
	public String toString() {
		String returned = "";
		for (ListNode lead : leads) {
			returned += lead.toString();
		}
		return returned;
	}
	
	// could be used to run generation many times without having to retrain or repeat code
	// there is a problem with static/nonstatic, hence seed is the same but sequence of numbers is different, hence so is result
	public static void runMarkov (MarkovTextGeneratorLoL markov, int seed, String trainingText, int generatedsize) {
		System.out.println("training text:" + "\n" + trainingText);
		markov.train(trainingText);
		System.out.print("leads and their follows:" + "\n" + markov);
		System.out.println("generated text of size " + generatedsize + " with seed " + seed + "\n" + markov.generateText(generatedsize) + "\n");
	}
	
	public static void main(String[] args) {
		int seed = 42;
		int generatedsize = 20;
				
		String trainingText1 = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";		
		MarkovTextGeneratorLoL markov1 = new MarkovTextGeneratorLoL(new Random(seed));
		runMarkov(markov1, seed, trainingText1, generatedsize);
		
		String trainingText2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		MarkovTextGeneratorLoL markov2 = new MarkovTextGeneratorLoL(new Random(seed));
		runMarkov(markov2, seed, trainingText2, generatedsize);
		MarkovTextGeneratorLoL markov3 = new MarkovTextGeneratorLoL(new Random(seed));
		runMarkov(markov3, seed, trainingText2, generatedsize);
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode {
    // The word that links to the next words
	private String word;
	// The words that follow
	private List<String> nextWords;
	
	ListNode(String w) {
		this.word = w;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord() {
		return word;
	}
	
	public void setWord(String w) {
		this.word = w;
	}

	public void addNextWords(String nw) {
		nextWords.add(nw);
	}
	
	public String getRandomNextWord(Random generator) {
		// TODO: Implement this method
	    // The random number generator should be passed from the MarkovTextGeneratorLoL class
		int position = generator.nextInt(nextWords.size());
		// System.out.println(position); // print sequence of random numbers for debugging
	    return nextWords.get(position);
	}

	public String toString() {
		String toReturn = word + ": ";
		for (String nw : nextWords) {
			toReturn += nw + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


