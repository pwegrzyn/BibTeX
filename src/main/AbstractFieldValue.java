package bibtex;

/**
 * Represents an abstract value of a particular field in an entry
 * @author Patryk Wegrzyn
 *
 */
public abstract class AbstractFieldValue implements Observer {
	
	/**
	 * Represents the subject, which this observer is observing, in this case it's the database, with which this field is associated
	 */
	protected Subject subject;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public abstract String toString();
	
}
