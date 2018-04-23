package bibtex;

/**
 * Represents a single String macro, which is used for abbreviations
 * @author Patryk Wegrzyn
 *
 */
public class StringMacroEntry extends AbstractEntry {

	/**
	 * Key of the macro
	 */
	private String reference;
	/**
	 * Content of the macro
	 */
	private String value;
	
	/**
	 * Standard constructor
	 * @param reference Key of the macro
	 * @param value Content of the macro
	 */
	public StringMacroEntry(String reference, String value) {

		this.reference = reference;
		this.value = value;
		
	}
	
	/**
	 * Retrieves the content of a macro
	 * @return Content (full form) of the macro
	 */
	public String getValue() {
		return this.value;
	}

	/* (non-Javadoc)
	 * @see bibtex.AbstractEntry#getEntryKey()
	 */
	@Override
	public String getEntryKey() {
		return this.reference;
	}

	/* (non-Javadoc)
	 * @see bibtex.AbstractEntry#getEntryType()
	 */
	@Override
	public String getEntryType() {
		return "stringmacro";
	}
}
