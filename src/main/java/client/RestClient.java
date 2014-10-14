package client;


import domain.Customer;

public interface RestClient {

	public Customer getCustomerByPassport(String series, String number);
}
