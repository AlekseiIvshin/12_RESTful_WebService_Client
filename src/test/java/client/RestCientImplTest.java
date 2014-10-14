package client;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.Customer;

public class RestCientImplTest {

	RestCientImpl client;
	
	@Before
	public void setUp(){
		client = new RestCientImpl("http://localhost:8082");
	}
	
	@Test
	public void testGetCustomerByPassport() {
		Customer customer = client.getCustomerByPassport("9100", "100100");
		assertNotNull(customer);
		assertEquals(customer.getPassportSeries(), "9100");
		assertEquals(customer.getId(), 1);
	}

}
