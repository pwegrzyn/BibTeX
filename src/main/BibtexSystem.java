package bibtex;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

/**
 * I use the Commons CLI tool in this project
 * See http://commons.apache.org/proper/commons-cli/
 * for more details
 * 
 * This is an application, which is able to parse a passed BibTeX file, save its contents to an OOP version,
 * select its fields according to a particular pattern and print the selected entries into an ASCII table
 * @author Patryk Wegrzyn
 *
 */
public class BibtexSystem {

	/**
	 * Run the program without arguments for the help window
	 * @param args Passed command line arguments
	 */
	public static void main(String[] args) {
		
		try {
			
			//parse the passed arguments
			ArgumentParser argParser = new ArgumentParser(args);
			CommandLine configs = argParser.parse();
			
			//if no argument were passed exit the program
			//(printing the help screen)
			if(configs == null) return;
			
			//parse the passed BibTeX file
			BibtexParser bibParser = new BibtexParser();
			BibtexDatabase database = bibParser.generateDatabase(configs.getOptionValue("path"));
			
			//select the desired elements from the database
			//according to the command line configs
			Selector selector = new Selector(configs, database);
			
			//print the proper entries according to the selector
			Printer printer = new Printer(selector);
			printer.print();
			
		} catch (IOException e3) {
			System.err.println("IO error!");
			e3.printStackTrace();
		} catch (ParseException e1) {
			System.err.println("Parse error!");
			e1.printStackTrace();
		} catch (LackingRequiredFieldException e2) {
			System.err.println("File format error!");
			e2.printStackTrace();
		}
		
	}

}
