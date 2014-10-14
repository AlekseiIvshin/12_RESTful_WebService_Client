package client;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import domain.Customer;

public class RestCientImplTest {

	RestCientImpl client;
	
	@Before
	public void setUp(){
		client = new RestCientImpl("http://localhost:8088");
	}
	
	@Test
	public void testGetCustomerById(){

		Customer customer = client.getCustomerById(1);
		assertNotNull(customer);
		assertEquals(customer.getId(), 1);
	}

	@Test
	public void testGetCustomerByPassport() {
		Customer customer = client.getCustomerByPassport("9100", "100100");
		assertNotNull(customer);
		assertEquals(customer.getPassportSeries(), "9100");
		assertEquals(customer.getId(), 1);
	}
	
	@Test
	public void testGetCustomerByPassportExceptNull() {
		Customer customer = client.getCustomerByPassport("9102", "100100");
		assertNull(customer);
	}
	
	@Test
	public void testCreateCustomerAndGetId(){
		Customer customer = new Customer();
		Random rnd = new Random();
		customer.setName("Name");
		customer.setSurname("surname");
		customer.setPassportSeries((rnd.nextInt(10)+10)+""+(rnd.nextInt(10)+10));
		customer.setPassportNumber((rnd.nextInt(100)+100)+""+(rnd.nextInt(100)+100));
		customer.setBirthDate(new Date());
		
		int id = client.createCustomerAndGetId(customer);
		assertTrue(id>=0);
	}

}
