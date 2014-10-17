package client;

import java.util.List;

import domain.Car;
import domain.Customer;
import domain.Merchant;
import domain.Sales;

/**
 * Client for sales service.
 * @author Aleksei_Ivshin
 *
 */
public interface RestSalesService {

	/**
	 * Sale car.
	 * @param customer customer 
	 * @param merchant merchant
	 * @param car car
	 * @return created sale item
	 */
	Sales saleCar(Customer customer, Merchant merchant, Car car);

	/**
	 * Get all sales.
	 * @return list of sales
	 */
	List<Sales> getAllSales();
}
