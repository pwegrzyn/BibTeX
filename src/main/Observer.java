package bibtex;

import java.util.ArrayList;

/**
 * Interface, which is the observer part of the observer design pattern, which itself is used to dynamically dereference String macros
 * @author Patryk Wegrzyn
 *
 */
public interface Observer {

	/**
	 * Updates the field, so it becomes synchronized with the passed macro
	 * @param macros List of String macros
	 */
	public void update(ArrayList<StringMacroEntry> macros);
	
}
