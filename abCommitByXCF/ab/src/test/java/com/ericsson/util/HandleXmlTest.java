package com.ericsson.util;

import java.io.File;

import junit.framework.TestCase;

import com.ericsson.entry.Address;

public class HandleXmlTest extends TestCase {
	public String filename = null;
	public Address address = null;
	public String option = null;
	public String value = null;
	public static File file = null;

	protected void setUp() throws Exception {
		super.setUp();
		filename = "target\\AddressBook.xml";
		address = new Address("xiao zhang", "13866666666", "Guangzhou");
	}

	protected void tearDown() throws Exception {
		filename = null;
		address = null;
		option = null;
		value = null;
		super.tearDown();
	}

	public void testAdd() {
		try {
			int expectedReturn = 1;
			int acturalReturn = HandleXml.verifyAdd(filename, address);
			assertEquals(expectedReturn, acturalReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testSearch1() {
		try {
			option = "name";
			value = "xiao zhang";
			int expectedReturn = 1;
			int acturalReturn = HandleXml.verifySearch(filename, option, value);
			assertEquals(expectedReturn, acturalReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testSearch2() {
		try {
			option = "name";
			value = "xiao.*";
			int expectedReturn = 1;
			int acturalReturn = HandleXml.verifySearch(filename, option, value);
			assertEquals(expectedReturn, acturalReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testSearch3() {
		try {
			option = "mobile";
			value = "13866666666";
			int expectedReturn = 2;
			int acturalReturn = HandleXml.verifySearch(filename, option, value);
			assertEquals(expectedReturn, acturalReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testSearch4() {
		try {
			option = "mobile";
			value = ".*3866.*6666";
			int expectedReturn = 2;
			int acturalReturn = HandleXml.verifySearch(filename, option, value);
			assertEquals(expectedReturn, acturalReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testSearch5() {
		try {
			option = "address";
			value = "Guangzhou";
			int expectedReturn = 3;
			int acturalReturn = HandleXml.verifySearch(filename, option, value);
			assertEquals(expectedReturn, acturalReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testSearch6() {
		try {
			option = "address";
			value = ".*ang.*o.*";
			int expectedReturn = 3;
			int acturalReturn = HandleXml.verifySearch(filename, option, value);
			assertEquals(expectedReturn, acturalReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testSearch7() {
		try {
			option = "othercommand";
			value = ".*ang.*o.*";
			int expectedReturn = 4;
			int acturalReturn = HandleXml.verifySearch(filename, option, value);
			assertEquals(expectedReturn, acturalReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testDelete1() {
		try {
			option = "name";
			value = "xiao zhang";
			int expectedReturn = 1;
			int acturalReturn = HandleXml.verifyDelete(filename, option, value);
			assertEquals(expectedReturn, acturalReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testDelete2() {
		try {
			option = "name";
			value = "xiao m.*";
			int expectedReturn = 1;
			int acturalReturn = HandleXml.verifyDelete(filename, option, value);
			assertEquals(expectedReturn, acturalReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testDelete3() {
		try {
			option = "mobile";
			value = "13866666668";
			int expectedReturn = 2;
			int acturalReturn = HandleXml.verifyDelete(filename, option, value);
			assertEquals(expectedReturn, acturalReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testDelete4() {
		try {
			option = "mobile";
			value = "138.*9";
			int expectedReturn = 2;
			int acturalReturn = HandleXml.verifyDelete(filename, option, value);
			assertEquals(expectedReturn, acturalReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testDelete5() {
		try {
			option = "address";
			value = "Guangzhou";
			int expectedReturn = 3;
			int acturalReturn = HandleXml.verifyDelete(filename, option, value);
			assertEquals(expectedReturn, acturalReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testDelete6() {
		try {
			option = "address";
			value = "Gua.*zhou";
			int expectedReturn = 3;
			int acturalReturn = HandleXml.verifyDelete(filename, option, value);
			assertEquals(expectedReturn, acturalReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testDelete7() {
		try {
			option = "othercommand";
			value = "Gua.*zhou";
			int expectedReturn = 4;
			int acturalReturn = HandleXml.verifyDelete(filename, option, value);
			assertEquals(expectedReturn, acturalReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testVerifyInitFile() {
		try {
			file = new File("Junit\\test.xml");
			int expectedReturn;
			if(file.exists())
				expectedReturn = 2;
			else
				expectedReturn = 1;
			int acturalReturn = HandleXml.verifyInitFile(file);
			assertEquals(expectedReturn, acturalReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
