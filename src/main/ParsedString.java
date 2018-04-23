package bibtex;

import java.util.ArrayList;
import java.util.StringJoiner;

/**
 * Represents a field value containing a String (literally a String wrapper)
 * @author Patryk Wegrzyn
 *
 */
public class ParsedString extends AbstractFieldValue {

	/**
	 * Main content of the object
	 */
	private String string;
	
	/**
	 * Default constructor
	 * @param string Initial value for the string field
	 */
	public ParsedString(String string) {

		this.string = string;
		
	}
	
	/**
	 * Alternative constructor, used in conjunction with observer design pattern (off by default)
	 * @param string Initial value for the string field
	 * @param database Subject to be observed
	 */
	public ParsedString(String string, Subject database) {

		this.string = string;
		this.subject = database;
		database.register(this);

	}
	
	/**
	 * Retrieves the content of the object
	 * @return Value of the string field
	 */
	public String getContent() {
		return this.string;
	}
	
	/**
	 * Sets the content of the object
	 * @param string New value for the string field
	 */
	public void setContent(String string) {
		this.string = string;
	}

	/* (non-Javadoc)
	 * @see bibtex.AbstractFieldValue#toString()
	 */
	@Override
	public String toString() {
		return this.string;
	}

	/* (non-Javadoc)
	 * @see bibtex.Observer#update(java.util.ArrayList)
	 */
	@Override
	public void update(ArrayList<StringMacroEntry> macros) {
		
		String[] splitFieldValue = this.string.split(" ");
		StringJoiner dereferencedFieldValue = new StringJoiner(" ");
		
		for (int j = 0; j < splitFieldValue.length; j++) {
			for (StringMacroEntry macro : macros) {
				//if we have such a macro reference, dereference it, else skip
				if (macro.getEntryKey().trim().equals(splitFieldValue[j].trim())) {
					splitFieldValue[j] = macro.getValue();
				}
			}
			dereferencedFieldValue.add(splitFieldValue[j]);
		}
		
		this.string = dereferencedFieldValue.toString();
		
	}
	
}
