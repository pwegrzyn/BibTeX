package bibtex;

/**
 * Represents an abstract entry in the BibTeX file, i.e. regular entry (publication), comment, string macro, preamble
 * @author Patryk Wegrzyn
 */
public abstract class AbstractEntry {
	
	/**
	 * Gets the entry key associated with a particular entry
	 * @return Entry key
	 */
	public abstract String getEntryKey();
	
	
	/**
	 * Gets the entry type associated with a particular entry
	 * @return Entry type
	 */
	public abstract String getEntryType();

}
