package bibtex;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import org.apache.commons.cli.CommandLine;

/**
 * Class, which handles the mechanism of selecting the desired entries from a bibtex database.
 * @author Patryk Wegrzyn
 *
 */
public class Selector {
	
	/**
	 * Instance of the CommandLine class, represents a parsed CL set of arguments
	 */
	private CommandLine configs;
	/**
	 * Parsed BibTeX files in a form of a OOP database
	 */
	private BibtexDatabase database;
	
	/**
	 * Standard constructor
	 * @param configs Parsed CL arguments
	 * @param database Parsed BibTeX file
	 */
	public Selector(CommandLine configs, BibtexDatabase database) {
		
		this.configs = configs;
		this.database = database;
		
	}
	
	/**
	 * Retrieves the saved configs field
	 * @return configs field
	 */
	public CommandLine getConfigs() {
		return this.configs;
	}
	
	/**
	 * Retrieves the saved database
	 * @return Parsed BibTeX file
	 */
	public BibtexDatabase getDatabase() {
		return this.database;
	}

	/**
	 * Selects all the entries from the saved BibTeX database
	 * @return Map of all entries
	 */
	public LinkedHashMap<String,AbstractEntry> selectAllEntires () {
		return this.database.getEntriesMap();
	}
	
	/**
	 * Selects all the entries from a particular category (i.e. book, manual)
	 * @param category Name of the desired category
	 * @return Map of the found entries
	 */
	public LinkedHashMap<String,AbstractEntry> selectEntriesByCategory(String category) {
		
		LinkedHashMap<String,AbstractEntry> result = new LinkedHashMap<>();
		LinkedHashMap<String,AbstractEntry> myMap = this.database.getEntriesMap();
		
		for (Map.Entry<String, AbstractEntry> entry : myMap.entrySet()) {
			
			if(((RegularEntry) entry.getValue()).getEntryType().equals(category.toLowerCase())) {
				result.put(entry.getKey(), entry.getValue());
			}
			
		}
		
		return result;
		
	}
	
	/**
	 * Selects all entries, which a have a particular value in a particular field
	 * @param field Name of the field
	 * @param value Desired value
	 * @return Map of found entries
	 */
	public LinkedHashMap<String,AbstractEntry> selectEntriesByField(String field, String value) {
		
		LinkedHashMap<String,AbstractEntry> result = new LinkedHashMap<>();
		LinkedHashMap<String,AbstractEntry> myMap = this.database.getEntriesMap();
		
		if(field.equals("author")) {
			
			for (Map.Entry<String, AbstractEntry> entry : myMap.entrySet()) {
				
				if(((RegularEntry) entry.getValue()).getFieldsMap().containsKey("author")) {
					
					LinkedHashMap<String,AbstractFieldValue> myFields = ((RegularEntry) entry.getValue()).getFieldsMap();
					for(Map.Entry<String, AbstractFieldValue> field2 : myFields.entrySet()) {
					
						String key2 = field2.getKey();
						if(key2.equals("author")) {
							
							LinkedHashSet<Person> people = ((PersonList) field2.getValue()).getList();
				    		for(Person per : people) {
				    			if(per.getLastName().equalsIgnoreCase(value)) {
				    				result.put(entry.getKey(), entry.getValue());
				    			}
				    		}
							
						}
						
					}
					
				}
				
			}
		
		} else if(field.equals("editor")) {
			
			for (Map.Entry<String, AbstractEntry> entry : myMap.entrySet()) {
				
				if(((RegularEntry) entry.getValue()).getFieldsMap().containsKey("editor")) {
					
					LinkedHashMap<String,AbstractFieldValue> myFields = ((RegularEntry) entry.getValue()).getFieldsMap();
					for(Map.Entry<String, AbstractFieldValue> field2 : myFields.entrySet()) {
					
						String key2 = field2.getKey();
						if(key2.equals("editor")) {
							
							LinkedHashSet<Person> people = ((PersonList) field2.getValue()).getList();
				    		for(Person per : people) {
				    			if(per.getLastName().equalsIgnoreCase(value)) {
				    				result.put(entry.getKey(), entry.getValue());
				    			}
				    		}
							
						}
						
					}
					
				}
				
			}
		
		}
		else {
			
			for(Map.Entry<String, AbstractEntry> entry : myMap.entrySet()) {
				
				if(((RegularEntry) entry.getValue()).getFieldsMap().containsKey(field.toLowerCase())) {
					
					LinkedHashMap<String,AbstractFieldValue> myFields = ((RegularEntry) entry.getValue()).getFieldsMap();
					for(Map.Entry<String, AbstractFieldValue> field2 : myFields.entrySet()) {
						
						String key2 = field2.getKey();
						if(key2.equals(field.toLowerCase()) && ((ParsedString)field2.getValue()).getContent().equalsIgnoreCase(value) ) {
							
							result.put(entry.getKey(), entry.getValue());
							
						}
						
					}
					
				}
				
			}
			
		}
		
		return result;
		
	}
	
}
