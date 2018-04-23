package bibtex;

import java.util.LinkedHashMap;

/**
 * Represents a real, regular entry in a BibTeX file (a publication), for example a book
 * @author Patryk Wegrzyn
 *
 */
public class RegularEntry extends AbstractEntry  {

	/**
	 * Name of the type of the entry
	 */
	private String entryType;
	/**
	 * Key of the entry
	 */
	private String entryKey;
	
	/**
	 * Map of all the fields of the entry
	 */
	private LinkedHashMap<String,AbstractFieldValue> fields;
	
	/**
	 * Standard constructor, generates the map of fields
	 * @param key Key of the new entry
	 * @param type Type of the new entry
	 */
	public RegularEntry(String key, String type) {
		
		this.entryKey = key;
		this.entryType = type;
		fields = new LinkedHashMap<String,AbstractFieldValue>();
		
	}
	
	/* (non-Javadoc)
	 * @see bibtex.AbstractEntry#getEntryKey()
	 */
	@Override
	public String getEntryKey() {
		return this.entryKey;
	}
	
	/* (non-Javadoc)
	 * @see bibtex.AbstractEntry#getEntryType()
	 */
	@Override
	public String getEntryType() {
		return this.entryType.toLowerCase().trim();
	}
	
	/**
	 * Sets the entry type of the entry
	 * @param type New custom type of the entry
	 */
	void setEntryType(String type) {
		this.entryType = type;
	}
	
	/**
	 * Sets a new key for the entry
	 * @param key New key
	 */
	void setEntryKey(String key) {
		this.entryKey = key;
	}
	
	/**
	 * Retrieves the map of all fields of the entry
	 * @return Map of all fields
	 */
	public LinkedHashMap<String,AbstractFieldValue> getFieldsMap() {
		return this.fields;
	}
	
	/**
	 * Automatically retrieves the value of a given field, returns null if no such field has been found
	 * @param name Name of the desired field
	 * @return Value associated with the given field name
	 */
	public AbstractFieldValue getFieldValue(String name) {
		return (AbstractFieldValue) fields.get(name);
	}
	
	/**
	 * Automatically sets a particular field to a particular value
	 * @param name Name of the field
	 * @param value New value for the field
	 */
	void setField (String name, AbstractFieldValue value) {
		fields.put(name,value);
	}

}
