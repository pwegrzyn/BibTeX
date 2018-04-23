package bibtex;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Represents a field value containing a list of people (used for the author and editor fields)
 * @author Patryk Wegrzyn
 *
 */
public class PersonList extends AbstractFieldValue {

	/**
	 * Main data structure of the class, contains all the people
	 */
	private LinkedHashSet<Person> people;
	
	/**
	 * Standard constructor, generates a new LinkedHashMap as the main data structure of the class
	 */
	public PersonList() {

		this.people = new LinkedHashSet<Person>();
		
	}
	
	/**
	 * Alternative constructor, used with conjunction with observer design pattern, not used by default
	 * @param database Subject, which is supposed to be observed
	 */
	public PersonList(Subject database) {
		
		this.people = new LinkedHashSet<Person>();
		this.subject = database;
		database.register(this);
		
	}
	
	/**
	 * Adds a new person the list
	 * @param person A new person, which is supposed to be added to the list
	 */
	public void addPersonToList(Person person) {
		
		this.people.add(person);
		
	}
	
	/**
	 * Retrieves the saved set of people
	 * @return HashSet of people
	 */
	public LinkedHashSet<Person> getList() {
		return this.people;
	}

	/* (non-Javadoc)
	 * @see bibtex.AbstractFieldValue#toString()
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("");
		for(Person person : this.people) {
			str.append(person.toString());
		}
		return str.toString();
	}

	/* (non-Javadoc)
	 * @see bibtex.Observer#update(java.util.ArrayList)
	 */
	@Override
	public void update(ArrayList<StringMacroEntry> macros) {
		
		for(Person person : this.people) {
			
			person.update(macros);
			
		}
		
	}

}
