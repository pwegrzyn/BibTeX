package test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import bibtex.BibtexDatabase;
import bibtex.RegularEntry;

class BibtexDatabaseTest {

	@Test
	void test() {
		
		BibtexDatabase database = new BibtexDatabase();
		assertNotNull(database);
		assertNotNull(database.getEntriesMap());
		assertNotNull(database.getStringMacros());
		assertFalse(database.removeEntry("test"));
		assertFalse(database.removeEntry("key"));
		RegularEntry reg = database.createRegularEntry("book", "key1");
		assertTrue(database.addEntry(reg));
		assertTrue(database.removeEntry("key1"));
		assertFalse(database.removeEntry(reg));
		assertEquals(database.createString("test").getContent(), "test");
		assertNotEquals(database.createString("test2").getContent(), "test1");
		assertNotNull(database.createPerson("asd", "sdf"));
		assertNotEquals(database.createPersonList(), database.createPersonList());
		assertEquals(database.createPerson("Jan", "Nowak").getFirstName(), "Jan");
		assertNotEquals(database.createPerson("Jan", "Nowak").getFirstName(), "jan");
		assertEquals(database.createPerson("Jan", "Nowak").getLastName(), "Nowak");
		assertNotEquals(database.createPerson("Jan", "Nowak").getLastName(), "nowak");
		
	}

}
