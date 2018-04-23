package bibtex;

/**
 * @deprecated
 * @author Patryk Wegrzyn
 *
 */
public class CommentEntry extends AbstractEntry {

	private String content;
	private static int idCounter = 0;
	private int id;
	
	public CommentEntry(String content) {

		this.content = content;
		this.id = idCounter++;
		
	}

	@Override
	public String getEntryKey() {
		return null;
	}

	@Override
	public String getEntryType() {
		return "comment";
	}
	
	public int getCommentID() {
		return this.id;
	}
	
	public String getContent() {
		return this.content;
	}

}
