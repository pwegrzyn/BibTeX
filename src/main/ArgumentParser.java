package bibtex;

import org.apache.commons.cli.*;

/**
 * Class used handle the Commons CLI command line parser. Has the ability to expand the list of program options.
 * @author Patryk Wegrzyn
 */
public class ArgumentParser {
	
	/**
	 * Program arguments passed from the CL, need to be parsed
	 */
	private String[] args;
	
	/**
	 * Binds a particular set of CL args with an instance of a parser.
	 * @param args Command line arguments passed to the program.
	 */
	public ArgumentParser(String[] args) {
		
		this.args = args;
		
	}
	
	/**
	 * Sets the args field with a custom set of arguments.
	 * @param args Custom command line arguments.
	 */
	public void setArgs(String[] args) {
		this.args = args;
	}
	
	/**
	 * Retrieves the array of CL arguments associated with an particular instance of a parser.
	 * @return Saved command line arguments
	 */
	public String[] getArgs() {
		return this.args;
	}
	
	
	/**
	 * Main function of this class: first, it creates a custom set of program options, then it parsers the args field accoring to them.
	 * Handles the absence of any arguments by printing the usage help interface.
	 * @return Commons CLI class which represents CL arguments parsed according to the set of created options.
	 * @throws ParseException Represents an error, which occurred during the process of parsing. Comes from the Commons CLI library.
	 */
	public CommandLine parse() throws ParseException {

		CommandLineParser parser = new DefaultParser();
		
		//create options
		Options options = new Options();
		options.addOption(Option.builder("p").longOpt("path").desc("Path to the BibTeX file").argName("path").hasArg().build());
		options.addOption(Option.builder("A").longOpt("all").desc("Print all entries in the BibTeX file").build());
		options.addOption(Option.builder("a").longOpt("author").desc("Print all entries of the following authors").argName("authors").hasArgs().valueSeparator().build());
		options.addOption(Option.builder("c").longOpt("category").desc("Print all entries from the following category").argName("categories").hasArgs().valueSeparator().build());
		options.addOption(Option.builder("t").longOpt("tablechar").desc("The char used to create the tables").argName("tablechar").hasArg().build());
		options.addOption(Option.builder("Y").longOpt("year").desc("rok").argName("year").hasArg().build());
		
		if(this.args.length < 2) {
			
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("BibtexSystem", options);
			System.out.println();
			System.out.println("If an argument can have multiple values, separete them with =");
			System.out.println("If a value has an space inside, put the whole value in quotation mars");
			return null;
			
		}
		
		CommandLine configs = parser.parse(options, args, true);
		return configs;
		
	}
	
	
}
