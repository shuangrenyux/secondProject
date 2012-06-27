package com.ericsson.entry;

import junit.framework.TestCase;

public class AddressTest extends TestCase {

	public Address address1 = null;
	public Address address2 = null;
	

	protected void setUp() throws Exception {
		super.setUp();
		address1 = new Address("xiao hong","15888888888","Beijing");
		address2 = new Address("xiao wang","15888888888","Beijing");
	}

	protected void tearDown() throws Exception {
		address1 = null;
		address2 = null;
		super.tearDown();
	}

	public void testAddress() {
		String name = "xiao ming";
		String mobile = "15888888888";
		String homeAddress = "Shanghai";
		Address address = new Address(name,mobile,homeAddress);
		assertNotNull(address);
	}

	public void testGetName() {
		String name = address1.getName();
		assertEquals("xiao hong", name);
		
	}

	public void testSetName() {
		address2.setName("xiao hong");
		assertSame(address1.getName(), address2.getName());
	}

	public void testGetMobileNo() {
		String mobileNo = address1.getMobileNo();
		assertEquals("15888888888", mobileNo);
	}

	public void testSetMobileNo() {
		address2.setMobileNo("15888888889");
		assertNotSame(address1.getMobileNo(), address2.getMobileNo());
		
	}

	public void testGetHomeAddress() {
		String homeAddress = address1.getHomeAddress();
		assertEquals("Beijing", homeAddress);
	}

	public void testSetHomeAddress() {
		address2.setHomeAddress("Shanghai");
		assertNotSame(address1.getHomeAddress(),address2.getHomeAddress());
	}

}
