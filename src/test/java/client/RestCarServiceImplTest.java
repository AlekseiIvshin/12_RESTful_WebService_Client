package client;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.Car;

public class RestCarServiceImplTest {
	RestCarServiceImpl client;
	
	@Before
	public void setUp(){
		client = new RestCarServiceImpl("http://localhost:8088");
	}
	@Test
	public void testGetAllMarks() {
		List<Car> marks = client.getAllMarks();
		assertNotNull(marks);
		assertFalse(marks.isEmpty());
	}
	
	@Test
	public void testGetById(){
		Car car = client.getCarById(1);
		assertNotNull(car);
		assertEquals(car.getId(), 1);
	}

}
