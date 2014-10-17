package client;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import domain.Car;

public class RestCarServiceImplTest {
	RestCarServiceImpl client;
	
	@Before
	public void setUp(){
		client = new RestCarServiceImpl("http://localhost:8081");
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
	
	@Test
	public void testGetAllModels(){
		List<Car> cars = client.getAllModels(1);
		assertNotNull(cars);
		assertFalse(cars.isEmpty());		
	}
	
	@Test
	public void testGetCarByName(){
		Car car = client.getCarByName("Audi","R8","6.2 MT (442 hs)");
		assertNotNull(car);
	}
	
	@Test
	public void testGetAllModifications(){
		List<Car> cars = client.getAllModifications(1);
		assertNotNull(cars);
		assertFalse(cars.isEmpty());	
	}
	
	@Test
	public void testCreateCarAndGetId(){
		Car car = new Car();
		Random rnd = new Random();
		car.setMark("Mark"+rnd.nextInt(50)+100);
		car.setModel("Model"+rnd.nextInt(50)+100);
		car.setModification("Modif"+rnd.nextInt(50)+100);
		long id = client.createCarAndGetId(car);
		assertTrue(id>=0);
		Car carCreated = client.getCarByName(car.getMark(),car.getModel(),car.getModification());
		assertNotNull(carCreated);
		
	}
}
