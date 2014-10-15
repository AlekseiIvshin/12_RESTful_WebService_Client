package client;

import java.util.List;

import domain.Car;

public interface RestCarService {
	Car getCarById(long id);

	Car getCarByName(String markName, String modelName, String modification);

	List<Car> getAllMarks();

	List<Car> getAllModels(long markId);

	List<Car> getAllModifications(long modelId);
	
	long createCarAndGetId(Car data);
}
