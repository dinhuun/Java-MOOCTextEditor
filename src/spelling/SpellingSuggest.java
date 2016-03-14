package spelling;

import java.util.List;

/** method suggestions() changed to getSuggestions() here and in its subclasses 
 */

public interface SpellingSuggest {

	public List<String> getSuggestions(String word, int numSuggestions);
	
}
