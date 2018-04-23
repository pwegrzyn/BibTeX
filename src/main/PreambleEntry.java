package bibtex;

/**
 * @deprecated
 * @author Patryk Wegrzyn
 *
 */
public class PreambleEntry extends AbstractEntry {

	private static PreambleEntry firstInstance = null;
	private String content;
	
	//private constructor - singleton class
	private PreambleEntry(String content) { 
		this.content = content;
	}
	
	private PreambleEntry() {}

	@Override
	public String getEntryKey() {
		return null;
	}

	@Override
	public String getEntryType() {
		return "preamble";
	}
	
	public String getContent() {
		return this.content;
	}
	
	//only allow to create an instance, if there's none other instance of preamble
	public static PreambleEntry getInstance(String content) {
		if(firstInstance == null) {
			firstInstance = new PreambleEntry(content);
		}
		return firstInstance;
	}
	
	public static PreambleEntry getInstance() {
		if(firstInstance == null) {
			firstInstance = new PreambleEntry();
		}
		return firstInstance;
	}

}
