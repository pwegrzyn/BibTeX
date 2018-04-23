package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bibtex.RegularEntry;

class RegularEntryTest {

	@Test
	void test() {
		
		RegularEntry ent = new RegularEntry("key", "type");
		assertNotNull(ent);
		assertNotNull(ent.getEntryKey());
		assertNotNull(ent.getEntryType());
		assertNotNull(ent.getFieldsMap());
		assertEquals(ent.getEntryKey(), "key");
		assertNotEquals(ent.getEntryKey(), "Key");
		assertEquals(ent.getEntryType(), "type");
		assertNotEquals(ent.getEntryType(), "asdf");
		assertNull(ent.getFieldValue("test"));
		
	}

}
