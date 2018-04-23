package bibtex;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Class, which represents the OOP version of the provided BibTeX file
 * @author Patryk Wegrzyn
 */
public class BibtexDatabase implements Subject {

	/**
	 * Map of all entries in the file (linked version, which means it saves the original ordering from the file
	 */
	private LinkedHashMap<String,AbstractEntry> entries;
	/**
	 * List a all string macros found in the file or provided in an different manner
	 */
	private ArrayList<StringMacroEntry> stringMacros;
	/**
	 * List of all observers
	 */
	private ArrayList<Observer> observers;
	
	/**
	 * Creates the necessary data structures and clears them
	 */
	public BibtexDatabase() {
		
		this.entries = new LinkedHashMap<String,AbstractEntry>();
		this.stringMacros = new ArrayList<StringMacroEntry>();
		entries.clear();
		observers = new ArrayList<Observer>();

	}
	
	/**
	 * Sets the list of string macros to a custom list, notifies interested observers, so they can adapt to this event
	 * @param macros Custom list of string macros, which a supposed to dereference the keys in the file
	 */
	public void setStringMacros(ArrayList<StringMacroEntry> macros) {
		this.stringMacros = macros;
		notifyObserver();
	}
	
	/**
	 * Retrieves the list of saved string macros
	 * @return List of currently saved string macros associated with this instance of BibtexDatabase
	 */
	public ArrayList<StringMacroEntry> getStringMacros() {
		return this.stringMacros;
	}
	
	/**
	 * Retrieves the Map of entries from this database
	 * @return Map of entries
	 */
	public LinkedHashMap<String,AbstractEntry> getEntriesMap() {
		return this.entries;
	}
	
	/**
	 * Adds a new entry to the map of entries
	 * @param entry Abstract entry, which the subject wants to add to this database
	 * @return Boolean value representing the state of the operation
	 */
	public Boolean addEntry(AbstractEntry entry) {
		
		if (entries.containsKey(entry.getEntryKey())) return false;
		entries.put(entry.getEntryKey(), entry);
		return true;
		
	}
	
	/**
	 * Removes an existing entry from the map of entries in this database
	 * @param key String-key which references the desired entry
	 * @return Boolean value representing the state of the operation
	 */
	public Boolean removeEntry(String key) {
		
		if (entries.containsKey(key)) {
			entries.remove(key);
			return true;
		}
		return false;
		
	}
	
	/**
	 * Removes an existing entry from the map of entries in this database
	 * @param entry Entry, which is supposed to be removed
	 * @return Boolean success value
	 */
	public Boolean removeEntry(AbstractEntry entry) {
		
		if(entries.containsValue(entry)) {
			entries.remove(entry.getEntryKey());
			return true;
		}
		return false;
		
	}
	
	/**
	 * This class kinda acts like a factory, it's better to create the desired objects using this creation methods.
	 * @param type Type of the new entry
	 * @param key Key of the new entry
	 * @return Created regular entry
	 */
	public RegularEntry createRegularEntry(String type, String key) {
		return new RegularEntry(key, type);
	}
	
	/**
	 * Generates a new ParsedString
	 * @param content Contents of the string
	 * @return Created parsed string
	 */
	public ParsedString createString(String content) {
		return new ParsedString(content);
	}
	
	/**
	 * Builds a new Person object given a first name and surname
	 * @param firstname First name of the person
	 * @param lastname Last name of the person
	 * @return Newly created person
	 */
	public Person createPerson(String firstname, String lastname) {
		return new Person(firstname, lastname);
	}
	
	/**
	 * Builds a new, empty list of people
	 * @return Newly created list of people
	 */
	public PersonList createPersonList() {
		return new PersonList();
	}

	/* (non-Javadoc)
	 * @see bibtex.Subject#register(bibtex.Observer)
	 */
	@Override
	public void register(Observer newObserver) {
		observers.add(newObserver);
	}

	/* (non-Javadoc)
	 * @see bibtex.Subject#unregister(bibtex.Observer)
	 */
	@Override
	public void unregister(Observer removeObserver) {
		
		int observerIndex = observers.indexOf(removeObserver);
		observers.remove(observerIndex);
		
	}

	/* (non-Javadoc)
	 * @see bibtex.Subject#notifyObserver()
	 */
	@Override
	public void notifyObserver() {
		
		for(Observer observer : observers) {
			
			observer.update(this.stringMacros);
			
		}
		
	}

}
