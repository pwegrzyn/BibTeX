package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bibtex.ParsedString;

class ParsedStringTest {

	@Test
	void test() {
		
		ParsedString str = new ParsedString("content");
		assertNotNull(str);
		assertEquals(str.getContent(), "content");
		assertNotEquals(str.getContent(), "asdf");
		str.setContent("new Content");
		assertNotEquals(str.getContent(), "content");
		assertEquals(str.getContent(), "new Content");
		assertNotNull(str.getContent());
		assertNotEquals(str.toString(), "newContent");
		assertEquals(str.toString(), str.getContent());
		
	}

}
