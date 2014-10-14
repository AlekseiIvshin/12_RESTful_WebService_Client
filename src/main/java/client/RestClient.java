package client;


import domain.Customer;

public interface RestClient {

	public Customer getCustomerById(int id);
	
	public Customer getCustomerByPassport(String series, String number);
	
	public int createCustomerAndGetId(Customer customer);
}
