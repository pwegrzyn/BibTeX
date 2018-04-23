package test;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.Test;

import bibtex.ArgumentParser;

class ArugmentParserTest {

	@Test
	void testSetArgs() {
		String args[] = {"--all", "t=*"};
		ArgumentParser par = new ArgumentParser(args);
		assertEquals("--all", par.getArgs()[0]);
		assertNotEquals("--all", par.getArgs()[1]);
		assertNotEquals("--All", par.getArgs()[0]);
		assertNotEquals("--ALL", par.getArgs()[0]);
	}

	@Test
	void testGetArgs() {
		String args[] = {"--all", "t=*"};
		ArgumentParser par = new ArgumentParser(args);
		assertEquals("t=*", par.getArgs()[1]);
		assertNotEquals("t=**", par.getArgs()[1]);
		assertNotEquals("t=", par.getArgs()[1]);
		assertNotEquals("T=*", par.getArgs()[1]);
	}

	@Test
	void testParse() throws ParseException {
		String args[] = {"--all"};
		ArgumentParser par = new ArgumentParser(args);
		CommandLine configs = par.parse();
		assertNull(configs);
	}

}
