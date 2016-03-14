package document;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/** A class for timing the DocumentEfficient and DocumentBasic classes
 * @author UC San Diego Intermediate Programming MOOC team
 */

public class DocumentBenchmarking {
	
	public static void main(String [] args) {
	    // text to test on
	    String textFile = "data/warAndPeace.txt";
	    
		// number of characters to start with, you can play around with this
		int start = 20000;
		
	    // amount of characters added for each increment, you can play around with this
		int increment = 20000;

		// number of increments, 164 increments of 20000 push it past 3291629 characters in text
		// you can play around with this number
		int numIncrements = 164;
		
		// More trials for more consistency (less noise) in time to run given size
	    // you can play around with this number
	    int trials = 20;
		
		// TODO: Fill in the rest of this method so that it runs two loops
		// print out timing results as described in the assignment 
		for (int numToCheck = start; numToCheck < numIncrements*increment + start; 
				numToCheck += increment) {
			// numToCheck holds number of characters that you should read from the 
			// file to create both a DocumentBasic and an DocumentEfficient.  
			
			/* Each time through this loop you should:
			 * 1. Print out numToCheck followed by a tab (\t) (NOT a newline)
			 * 2. Read numToCheck characters from the file into a String
			 *     Hint: use the helper method below
			 * 3. Time a loop that runs trials times (trials is the variable above) that:
			 *     a. Creates a DocumentBasic 
			 *     b. Calls getFleschScore on this document
			 * 4. Print out the time it took to complete the loop in step 3 
			 *      (on the same line as the first print statement) followed by a tab (\t)
			 * 5. Time a loop that runs trials times (trials is the variable above) that:
			 *     a. Creates an DocumentEfficient 
			 *     b. Calls fleshScore on this document
			 * 6. Print out the time it took to complete the loop in step 5 
			 *      (on the same line as the first print statement) followed by a newline (\n) 
			 */
			System.out.print(numToCheck + "\t");
			String text = getStringFromFile(textFile, numToCheck);
			long startTime = System.nanoTime();
			for (int i = 0; i < trials; i++) {
				DocumentBasic docBasic = new DocumentBasic(text);
				docBasic.getFleschScore();
			}
			long endTime = System.nanoTime();
			long timeBasic = (long) ((endTime - startTime)/1000000000.0);
			
			startTime = System.nanoTime();
			for (int i = 0; i < trials; i++) {
				DocumentEfficient docEff = new DocumentEfficient(text);
				docEff.getFleschScore();
			}
			endTime = System.nanoTime();
			long timeEff = (long) ((endTime - startTime)/1000000000.0);		
			System.out.println(numToCheck + "\t" + timeBasic + "\t" + timeEff);
		}
	}
	
	/**
	 * Get a specified number of characters from a text file
	 * @param filename The file to read from
	 * @param numChars The number of characters to read
	 * @return The text string from the file with numChars characters
	 */
	public static String getStringFromFile(String filename, int numChars) {
		
		StringBuffer s = new StringBuffer();
		try {
			FileInputStream inputFile= new FileInputStream(filename);
			InputStreamReader inputStream = new InputStreamReader(inputFile);
			BufferedReader bis = new BufferedReader(inputStream);
			int val;
			int count = 0;
			while ((val = bis.read()) != -1 && count < numChars) {
				s.append((char)val);
				count++;
			}
			if (count < numChars) {
				System.out.println("Warning: End of file reached at " + count + " characters.");
			}
			bis.close();
		}
		catch(Exception e)
		{
		  System.out.println(e);
		  System.exit(0);
		}
		return s.toString();
	}
	
}
