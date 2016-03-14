package spelling;


/** A class to time Dictionary implementations DictionaryLL vs DictionaryBST
 * @author UC San Diego Intermediate Programming MOOC team
 */

public class DictionaryBenchmarking {
	
	public static void main(String [] args) {

	    // More trials for more consistency (less noise) in time to run given dictFile
	    // you can try playing around with this number
	    int trials = 500;

	    // text to test on
	    String dictFile = "data/dict.txt";
		
	    // amount of words added for each increment, you can play around with this
		int increment = 2000;

		// number of increments, you can play around with this
		int numIncrements = 30;
		
		// number of words to start with, you can play around with this
		int start = 30000;
		
		String notInDictionary = "notaword";
		
		// TODO: Play around with the numbers above and graph the output to see trends in the data
		for (int numToCheck = start; numToCheck < numIncrements*increment + start; 
				numToCheck += increment) {
			// Time the creation of finding a word that is not in the dictionary.
			DictionaryLL dictLL = new DictionaryLL();
			DictionaryBST dictBST = new DictionaryBST();
			
			DictionaryLoader.loadDictionary(dictLL, dictFile, numToCheck);
			DictionaryLoader.loadDictionary(dictBST, dictFile, numToCheck);
			
			long startTime = System.nanoTime();
			for (int i = 0; i < trials; i++) {
				dictLL.isWord(notInDictionary);
			}
			long endTime = System.nanoTime();
			long timeLL = (endTime - startTime);  
			
			startTime = System.nanoTime();
			for (int i = 0; i < trials; i++) {
				dictBST.isWord(notInDictionary);
			}
			endTime = System.nanoTime();
			long timeBST = (endTime - startTime);
			
			System.out.println(numToCheck + "\t" + timeLL + "\t" + timeBST);	
		}	
	}
	
}
