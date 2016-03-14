package textgen;

/**
 *  The interface for the MarkovTextGenerator
 *  @author UC San Diego Intermediate Programming MOOC team
 *  method retrain() commented out
 */
public interface MarkovTextGenerator {
	
	/** Train the generator by adding the sourceText */
	public void train(String sourceText);
	
	/** Generate the text with the specified number of words */
	public String generateText(int numWords);
	
	/** Retrain the generator from scratch on the source text */
	// not used in MarkovTextGeneratorLoL.java main{}, kept for MarkovTextGeneratorGrader.java
	public void retrain(String sourceText);

}
