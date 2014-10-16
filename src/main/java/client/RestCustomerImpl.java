package client;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.type.TypeReference;

import domain.Customer;
import domain.JsonData;

public class RestCustomerImpl extends RestClient implements RestCustomer {

	public RestCustomerImpl(String host) {
		super(host);
	}

	@Override
	public Customer getCustomerByPassport(String series, String number) {

		WebTarget target = client.target(host + "/customer/passport/" + series
				+ "/" + number);
		Invocation.Builder builder = target
				.request(MediaType.APPLICATION_JSON_TYPE);
		Response response = builder.get();
		JsonData<Customer> data = responseParser.parseResponse(response,
				new TypeReference<JsonData<Customer>>() {
				});

		if (data == null) {
			logger.debug("Recieved data is null");
			return null;
		} else {
			if (data.getException().haveError()) {
				logger.error("{}: {}", data.getException().getExceptionClass(),
						data.getException().getExceptionMessage());
				return null;
			}
		}

		return data.getData();
	}

	@Override
	public Customer getCustomerById(int id) {
		WebTarget target = client.target(host + "/customer/id/" + id);
		Invocation.Builder builder = target
				.request(MediaType.APPLICATION_JSON_TYPE);
		Response response = builder.get();

		JsonData<Customer> data = responseParser.parseResponse(response,
				new TypeReference<JsonData<Customer>>() {
				});
		if (data == null) {
			logger.debug("Recieved data is null");
			return null;
		} else {
			if (data.getException().haveError()) {
				logger.error("{}: {}", data.getException().getExceptionClass(),
						data.getException().getExceptionMessage());
				return null;
			}
		}

		return data.getData();
	}

	@Override
	public int createCustomerAndGetId(Customer customer) {
		WebTarget target = client.target(host + "/customer/new");
		Invocation.Builder builder = target
				.request(MediaType.APPLICATION_JSON_TYPE);
		String jsonCustomer = dataPacker.packToJsonString(customer);
		if (jsonCustomer == null || jsonCustomer.isEmpty()) {
			return -1;
		}
		logger.debug("Mapped data: {}", jsonCustomer);
		Response response = builder.post(Entity.entity(jsonCustomer,
				MediaType.APPLICATION_JSON));

		JsonData<Integer> data = responseParser.parseResponse(response,
				new TypeReference<JsonData<Integer>>() {
				});
		if (data == null) {
			logger.debug("Recieved data is null");
			return -1;
		} else {
			if (data.getException().haveError()) {
				logger.error("{}: {}", data.getException().getExceptionClass(),
						data.getException().getExceptionMessage());
				return -1;
			}
		}

		return data.getData();
	}

}
