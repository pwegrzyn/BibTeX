package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import bibtex.Person;

class PersonTest {

	@Test
	void testGetFirstName() {
		Person person = new Person("Jan", "Kowalski");
		assertEquals(false, person.getFirstName().equals("Mucha"));
		assertTrue(person.getFirstName().equals("Jan"));
		assertEquals(person.getFirstName(), "Jan");
		assertNotEquals(person.getFirstName(), "jan");
		assertNotEquals(person.getFirstName(),"jaN");
	}

	@Test
	void testGetLastName() {
		Person person = new Person("Jan", "Mucha");
		assertEquals("Mucha", person.getLastName());
		assertFalse(person.getLastName().equals("Kawa"));
		assertEquals(person.getLastName(), "Mucha");
		assertNotEquals(person.getLastName(), "kawa");
		assertNotEquals(person.getLastName(), "KAWA");
		assertNotEquals(person.getLastName(), "mucha");
		assertNotEquals(person.getLastName(), "MUCHA");
		assertNotNull(person.getLastName());
	}

}
