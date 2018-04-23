package bibtex;

import java.util.ArrayList;

/**
 * Represents a single unit value of the peronList field value
 * @author Patryk Wegrzyn
 *
 */
public class Person extends AbstractFieldValue {

	/**
	 * First name of the person
	 */
	private String firstName;
	/**
	 * Surname of the person 
	 */
	private String lastName;
	
	/**
	 * Constructs a new Person object
	 * @param firstName Initial firstName value
	 * @param lastName Initial lastName value
	 */
	public Person(String firstName, String lastName) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		
	}
	
	/**
	 * Alternative constructor, used with conjunction with observer design pattern (off by default)
	 * @param firstName Initial firstName value
	 * @param lastName Initial lastName value
	 * @param database Subject, which supposed to be observed
	 */
	public Person(String firstName, String lastName, Subject database) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.subject = database;
		
		database.register(this);
		
	}
	
	/**
	 * Retrieves the value of the firstName field
	 * @return Value of firstName field
	 */
	public String getFirstName() {
		return this.firstName;
	}
	
	/**
	 * Retrieves the value of the lastName field
	 * @return Value of the lastName field
	 */
	public String getLastName() {
		return this.lastName;
	}

	/* (non-Javadoc)
	 * @see bibtex.AbstractFieldValue#toString()
	 */
	@Override
	public String toString() {
		return this.firstName + " " + this.lastName;
	}

	/* (non-Javadoc)
	 * @see bibtex.Observer#update(java.util.ArrayList)
	 */
	@Override
	public void update(ArrayList<StringMacroEntry> macros) {
		
		for (StringMacroEntry macro : macros) {
			//if we have such a macro reference, dereference it, else skip
			if (macro.getEntryKey().trim().equalsIgnoreCase(firstName.trim())) {
				this.firstName = macro.getValue();
			}
			if (macro.getEntryKey().trim().equalsIgnoreCase(lastName.trim())) {
				this.firstName = macro.getValue();
			}
		}
		
	}

}
