package bibtex;

/**
 * Supportive class, represents a new exception, which is thrown when a entry doesn't have all the required fields
 * @author Patryk Wegrzyn
 *
 */
public class LackingRequiredFieldException extends Exception {

	/**
	 * ID of the object
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Empty constructor
	 */
	public LackingRequiredFieldException() {}

	/**
	 * Constructor which saves the passed String
	 * @param arg0 Passed String
	 */
	public LackingRequiredFieldException(String arg0) {
		super(arg0);
	}

	/**
	 * Alternative constructor, used in exception chaining
	 * @param cause Cause of the exception
	 */
	public LackingRequiredFieldException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor which is mixture of the both previous types
	 * @param message Passed String message, description of the occurred event
	 * @param cause Cause of the exception
	 */
	public LackingRequiredFieldException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * All of the above functionalities plus flag about the ability to write the StackTrace and handles suppression
	 * @param message Passed message
	 * @param cause Cause of the exception
	 * @param enableSuppression Flag
	 * @param writableStackTrace Flag
	 */
	public LackingRequiredFieldException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
