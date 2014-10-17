package client;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import reports.ReportException;

public class MainClientTest {

	MainClient client;
	
	@Before
	public void setUp(){
		client = new MainClient("http://localhost:8080");
	}
	
	@Test
	public void testAddCustomerInfoReportByPassport() {
		client.addCustomerInfoReportByPassport("9100", "100100");
		client.addCarsReport();
		client.addSalesCarReports();
		try {
			client.createAddedReports();
		} catch (ReportException e) {
			fail(e.getMessage());
		}
	}

}
