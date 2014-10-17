package client;

import java.util.List;

import domain.Car;

/**
 * Client for Car RESTful service.
 * @author Aleksei_Ivshin
 *
 */
public interface RestCarService {
	/**
	 * Get car by id.
	 * @param id car id
	 * @return found car. If car is not exist with current id, then return null 
	 */
	Car getCarById(long id);

	/**
	 * Get a car by name.
	 * @param markName mark name
	 * @param modelName model name 
	 * @param modification car modification
	 * @return found car or null
	 */
	Car getCarByName(String markName, String modelName, String modification);

	/**
	 * Get all car marks.
	 * @return list of car marks
	 */
	List<Car> getAllMarks();

	/**
	 * Get all car models for target mark.
	 * @param markId car mark id
	 * @return list of car models
	 */
	List<Car> getAllModels(long markId);

	/**
	 * Get all car modifications for target model.
	 * @param modelId model id
	 * @return list of marks
	 */
	List<Car> getAllModifications(long modelId);
	
	/**
	 * Create new car in data storage.
	 * @param data new car info
	 * @return id of created car
	 */
	long createCarAndGetId(Car data);
}
