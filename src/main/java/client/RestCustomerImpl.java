package client;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.http.HTTPException;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Customer;
import domain.JsonCustomer;
import domain.JsonIntegerData;

public class RestCustomerImpl implements RestCustomer {

	static final Logger logger = LoggerFactory
			.getLogger(RestCustomerImpl.class);

	private final String host;
	private final Client client;
	private final ObjectMapper jsonMapper = new ObjectMapper();

	public RestCustomerImpl(String host) {
		this.host = host;
		client = ClientBuilder.newClient();
	}

	@Override
	public Customer getCustomerByPassport(String series, String number) {

		WebTarget target = client.target(host + "/customer/passport/" + series
				+ "/" + number);
		Invocation.Builder builder = target
				.request(MediaType.APPLICATION_JSON_TYPE);
		Response response = builder.get();
		JsonCustomer data = parseResponse(response,JsonCustomer.class);

		if (data == null) {
			logger.debug("Recieved data is null");
			return null;
		} else {
			if (data.getException().getStatus() > 0) {
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

		JsonCustomer data = parseResponse(response,JsonCustomer.class);

		if (data == null) {
			logger.debug("Recieved data is null");
			return null;
		} else {
			if (data.getException().getStatus() > 0) {
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
		String jsonCustomer = "";
		try {
			jsonCustomer = jsonMapper.writeValueAsString(customer);
		} catch (IOException e1) {
			logger.error("Mapping exception", e1);
			return -1;
		}
		logger.debug("Mapped data: {}", jsonCustomer);
		Response response = builder.post(Entity.entity(jsonCustomer,
				MediaType.APPLICATION_JSON));
		
		JsonIntegerData data = parseResponse(response, JsonIntegerData.class);

		if (data == null) {
			logger.debug("Recieved data is null");
			return -1;
		} else {
			if (data.getException().getStatus() > 0) {
				logger.error("{}: {}", data.getException().getExceptionClass(),
						data.getException().getExceptionMessage());
				return -1;
			}
		}

		return data.getData();
	}

	private <T> T parseResponse(Response response, Class<T> readClass) {
		if (response.getStatus() == 200) {

			try {
				String entity = response.readEntity(String.class);
				return jsonMapper.readValue(entity, readClass);
			} catch (IOException e) {
				logger.error("Parse response entity error", e);
				return null;
			}
		} else {
			logger.error("Response status: {}", response.getStatus());
			throw new HTTPException(response.getStatus());
		}
	}

}
