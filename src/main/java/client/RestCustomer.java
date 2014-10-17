package client;

import domain.Customer;

/**
 * Client for Customer RESTservice.
 * 
 * @author Aleksei_Ivshin
 *
 */
public interface RestCustomer {

	/**
	 * Get customer by id.
	 * 
	 * @param id
	 *            customer id
	 * @return customer, if founded
	 */
	public Customer getCustomerById(int id);

	/**
	 * Get customer by passport data.
	 * 
	 * @param series
	 *            passport series
	 * @param number
	 *            passport number
	 * @return customer, if founded
	 */
	public Customer getCustomerByPassport(String series, String number);

	/**
	 * Create new customer
	 * 
	 * @param customer
	 *            customer data
	 * @return new customer id
	 */
	public int createCustomerAndGetId(Customer customer);
//
//	/**
//	 * Find customer by id. If id is empty then search by passport data. If
//	 * customer isn't found then create new customer.
//	 * 
//	 * @param customer customer data
//	 * @return
//	 */
//	public Customer findOrCreate(Customer customer);
}
