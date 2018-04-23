package bibtex;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;

/**
 * Class which handles all the printing to the console of the program
 * @author Patryk Wegrzyn
 *
 */
public class Printer {

	/**
	 * Saved selector (holds all the needed information)
	 */
	private Selector selector;
	/**
	 * ASCII char used for generating the console tables
	 */
	private String tc;
	
	/**
	 * Standard constructor
	 * @param selector Initial selector value
	 */
	public Printer(Selector selector) {

		this.selector = selector;
		
	}

	/**
	 * Main function of the class, calls proper print sub-methods of the class using the saved selector
	 */
	public void print() {
		
		CommandLine configs = this.selector.getConfigs();
		
		this.tc = configs.hasOption("tablechar") ? configs.getOptionValue("tablechar") : "*";

		for(Option arg : configs.getOptions()) {
			
			if(arg.getLongOpt().equals("path"))
				continue;
			
			if(arg.getLongOpt().equals("all") && configs.hasOption("all")) {
				
				System.out.println("Printing all entries...");
				System.out.println();
				print(this.selector.selectAllEntires());
				
			}
			
			else if(arg.getLongOpt().equals("category") && configs.hasOption("category")) {
				
				String [] categories = configs.getOptionValues("category");
				for(String singleCat : categories) {
					
					System.out.println("Printing all entries from the category " + singleCat.trim().toLowerCase() + "...");
					System.out.println();
					print(this.selector.selectEntriesByCategory(singleCat.trim().toLowerCase()));
					
				}
				
			}
			
			else if (configs.hasOption(arg.getLongOpt()) && !arg.getLongOpt().equals("tablechar")) {
				
				String[] values = configs.getOptionValues(arg.getLongOpt());
				for(String singleVal : values) {
					
					System.out.println("Printing all entries which have the value " + singleVal.trim() + " in the field " + arg.getLongOpt().trim().toLowerCase() + "...");
					System.out.println();
					print(this.selector.selectEntriesByField(arg.getLongOpt().trim().toLowerCase(), singleVal.trim().toLowerCase()));
					
				}
				
			}
			
		}
		
	}
	
	/**
	 * Print a ASCII table of passed entries
	 * @param entries Map of entries, which are supposed to be printed to STD.OUT
	 */
	private void print(LinkedHashMap<String,AbstractEntry> entries) {
		
		LinkedHashMap<String,AbstractEntry> myMap = entries;
		
		for (Map.Entry<String, AbstractEntry> entry : myMap.entrySet()) {
		    
			String key = entry.getKey();
		    RegularEntry value = (RegularEntry) entry.getValue();
		    
		    printLineN();
		    
		    String format2 = tc + " %-91s " + tc + "%n";
		    String format3 = tc + " %-15s " + tc;
		    String format4 = " %-73s " + tc + "%n";
		    
		    String head = "  " + value.getEntryType().toUpperCase() + " (" + key + ")";
		    
		    System.out.format(format2, " ");
		    System.out.format(format2, head);
		    System.out.format(format2, " ");
		    
		    printLineN();
		    
		    LinkedHashMap<String,AbstractFieldValue> myFields = value.getFieldsMap();
		    for(Map.Entry<String, AbstractFieldValue> field : myFields.entrySet()) {
		    	
		    	String key2 = field.getKey();
		    	
		    	System.out.format(format3, key2);
		    	
		    	if( field.getValue() instanceof ParsedString ) {
		    		System.out.format(format4, ((ParsedString) field.getValue()).getContent() );
		    		printLine();
		    	}
		    	if( field.getValue() instanceof PersonList ) {
		    		LinkedHashSet<Person> people = ((PersonList) field.getValue()).getList();
		    		boolean flag = true;
		    		for(Person per : people) {
		    			if(flag) {
		    				System.out.format(format4, per.toString());
		    				flag = false;
		    			} else {
		    				System.out.format(format3, " ");
		    				System.out.format(format4, per.toString());
		    			}
		    		}
		    		printLine();
		    	}
		    	System.out.println();
		    }
		    System.out.println();
		    System.out.println();
		    System.out.println();
		    System.out.println();
		}
		
	}
	
	private void printLine() {
		for(int i=0; i< 95; i++) {
			System.out.print(this.tc);
		}
	}
	
	private void printLineN() {
		for(int i=0; i< 95; i++) {
			System.out.print(this.tc);
		}
		System.out.println();
	}
	

}
