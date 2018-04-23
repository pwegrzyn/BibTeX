package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bibtex.StringMacroEntry;

class StringMacroEntryTest {

	@Test
	void test() {
		
		StringMacroEntry ent = new StringMacroEntry("key", "value");
		assertNotNull(ent);
		assertNotEquals(ent.getEntryType(), "StringMacro");
		assertNotNull(ent.getEntryType());
		assertEquals(ent.getEntryType(), "stringmacro");
		assertNotEquals(ent.getEntryKey(), "key1");
		assertNotEquals(ent.getEntryKey(), "KEY");
		assertNotEquals(ent.getEntryKey(), "Key");
		assertEquals(ent.getEntryKey(), "key");
		assertNotNull(ent.getValue());
		assertNotEquals(ent.getValue(), "asdf");
		assertEquals(ent.getValue(), "value");
		
	}

}
