package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.Test;

import bibtex.ArgumentParser;
import bibtex.BibtexDatabase;
import bibtex.BibtexParser;
import bibtex.LackingRequiredFieldException;
import bibtex.ParsedString;
import bibtex.Printer;
import bibtex.RegularEntry;
import bibtex.Selector;


//This class assumes that a xampl.bib BibTeX file exists in the project directory

class IntegrityTest {

	@Test
	void test() {
		
		String[] args = {"--path=xampl.bib", "--all", "--author=\"Knuth E.\"", "--category=book", "--tablechar=*"};
		try {
			
			assertNotEquals(args.length, 2);
			assertEquals(args.length, 5);
			
			ArgumentParser argParser = new ArgumentParser(args);
			assertNotNull(argParser);
			assertEquals(argParser.getArgs(), args);
			
			CommandLine configs = argParser.parse();
			assertNotNull(configs);
			
			//Argument parser tests
			assertTrue(configs.hasOption("path"));
			assertTrue(configs.hasOption("all"));
			assertTrue(configs.hasOption("author"));
			assertTrue(configs.hasOption("category"));
			assertFalse(configs.hasOption("year"));
			assertTrue(configs.hasOption("tablechar"));
			assertNotEquals(configs.getOptionValue("tablechar"), "%");
			assertNotEquals(configs.getOptionValue("tablechar"), "");
			assertNotEquals(configs.getOptionValue("tablechar"), "**");
			assertNotEquals(configs.getOptionValue("tablechar"), ".");
			assertEquals(configs.getOptionValue("tablechar"), "*");
			
			BibtexParser bibParser = new BibtexParser();
			assertNotNull(bibParser);
			
			BibtexDatabase database = bibParser.generateDatabase(configs.getOptionValue("path"));
			assertNotNull(database);
			
			//BibTeX parser tests
			assertTrue(database.getEntriesMap().containsKey("whole-set"));
			assertFalse(database.getEntriesMap().containsKey("ThisIsNotThere"));
			assertTrue(database.getEntriesMap().containsKey("book-full"));
			assertTrue(database.getEntriesMap().containsKey("article-full"));
			assertTrue(database.getEntriesMap().containsKey("article-crossref"));
			assertNull(database.getEntriesMap().get("asdsdfs"));
			assertNull(database.getEntriesMap().get("BOOK-full"));
			assertNull(database.getEntriesMap().get("Book-Full"));
			assertNull(database.getEntriesMap().get("book-Full"));
			assertNotNull(database.getEntriesMap().get("article-minimal"));
			assertEquals(database.getEntriesMap().get("article-full").getEntryType(), "article");
			assertEquals(database.getEntriesMap().get("article-crossref").getEntryType(), "article");
			assertEquals(database.getEntriesMap().get("whole-collection").getEntryType(), "book");
			assertNotEquals(database.getEntriesMap().get("manual-full").getEntryType(), "book");
			assertEquals(database.getEntriesMap().get("manual-full").getEntryType(), "manual");
			assertEquals(database.getEntriesMap().get("whole-collection").getEntryKey(), "whole-collection");
			assertNotEquals(database.getEntriesMap().get("whole-collection").getEntryKey(), "WHOLE-collection");
			assertNotEquals(database.getEntriesMap().get("whole-collection").getEntryKey(), "Whole-collection");
			assertNotEquals(database.getEntriesMap().get("whole-collection").getEntryKey(), "whole-Collection");
			assertNotEquals(database.getEntriesMap().get("whole-collection").getEntryKey(), "WHOLE-COLLECTION");
			assertNotNull(database.getEntriesMap().get("misc-minimal"));
			assertEquals(database.getEntriesMap().get("misc-minimal").getEntryType(),"misc");
			assertEquals(((ParsedString)((RegularEntry)database.getEntriesMap().get("misc-minimal")).getFieldValue("key")).getContent(), "Missilany");
			assertEquals(((ParsedString)((RegularEntry)database.getEntriesMap().get("misc-minimal")).getFieldValue("note")).getContent(), "This is a minimal MISC entry");
			assertEquals(((ParsedString)((RegularEntry)database.getEntriesMap().get("mastersthesis-full")).getFieldValue("title")).getContent(), "Mastering Thesis Writing");
			assertEquals(((ParsedString)((RegularEntry)database.getEntriesMap().get("mastersthesis-full")).getFieldValue("school")).getContent(), "Stanford University");
			
			//Selector tests
			Selector selector = new Selector(configs, database);
			assertNotNull(selector);
			assertNotNull(database);
			assertNotNull(configs);
			assertEquals(selector.getConfigs(), configs);
			assertNotEquals(selector.getDatabase(), new BibtexDatabase());
			assertEquals(selector.getDatabase(), database);
			assertNotNull(selector.selectAllEntires());
			assertEquals(selector.selectAllEntires(), database.getEntriesMap());
			assertNotNull(selector.selectEntriesByCategory("book"));
			assertNotNull(selector.selectEntriesByField("year", "123"));
			assertNotNull(selector.selectEntriesByCategory("article"));
			assertNotEquals(selector.selectAllEntires(), selector.selectEntriesByCategory("article"));
			assertEquals(selector.selectAllEntires(), selector.selectAllEntires());
			assertTrue(selector.selectAllEntires().equals(selector.selectAllEntires()));
			assertEquals(selector.selectEntriesByCategory("book"), selector.selectEntriesByCategory("book"));
			assertFalse(selector.selectEntriesByCategory("book").equals(selector.selectEntriesByCategory("article")));
			assertTrue(true);	//tests are still working :)
			assertNotNull(selector.selectEntriesByField("year", ""));
			assertEquals(selector.selectEntriesByField("year", "123"), selector.selectEntriesByField("year", "123"));
			assertEquals(selector.selectEntriesByField("year", "123"), selector.selectEntriesByField("year", "124"));
			
			Printer printer = new Printer(selector);
			assertNotNull(printer);			
			
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
