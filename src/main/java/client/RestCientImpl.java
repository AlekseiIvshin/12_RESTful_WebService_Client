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

public class RestCientImpl implements RestClient {

	static final Logger logger = LoggerFactory.getLogger(RestCientImpl.class);

	private final String host;
	private final Client client;
	private final ObjectMapper jsonMapper = new ObjectMapper();

	public RestCientImpl(String host) {
		this.host = host;
		client  = ClientBuilder.newClient();
	}

	@Override
	public Customer getCustomerByPassport(String series, String number) {
		
		WebTarget target = client.target(host + "/customer/passport/" + series
				+ "/" + number);
		Invocation.Builder builder = target.request(MediaType.APPLICATION_JSON_TYPE);
		Response response = builder.get();
		JsonCustomer data;
		if (response.getStatus() == 200) {

			try {
				String entity = response.readEntity(String.class);
				logger.debug("Recieved {}", entity);
				data = jsonMapper.readValue(entity, JsonCustomer.class);
			} catch (IOException e) {
				logger.error("Parse response entity error", e);
				return null;
			}
		} else {
			logger.error("URI: {}.Response status: {}", target.getUri(),
					response.getStatus());
			throw new HTTPException(response.getStatus());
		}

		if(data == null){
			logger.debug("Recieved data is null");
			return null;
		} else{
			if(data.getException().getStatus()>0){
				logger.error("{}: {}", data.getException().getExceptionClass(),
						data.getException().getExceptionMessage());
				return null;
			}
		}
		
		return data.getData();
	}

	@Override
	public Customer getCustomerById(int id) {
		WebTarget target = client.target(host + "/customer/" + id);
		Invocation.Builder builder = target.request(MediaType.APPLICATION_JSON_TYPE);
		Response response = builder.get();
		JsonCustomer data;
		if (response.getStatus() == 200) {

			try {
				String entity = response.readEntity(String.class);
				logger.debug("Recieved {}", entity);
				data = jsonMapper.readValue(entity, JsonCustomer.class);
			} catch (IOException e) {
				logger.error("Parse response entity error", e);
				return null;
			}
		} else {
			logger.error("URI: {}.Response status: {}", target.getUri(),
					response.getStatus());
			throw new HTTPException(response.getStatus());
		}

		if(data == null){
			logger.debug("Recieved data is null");
			return null;
		} else{
			if(data.getException().getStatus()>0){
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
		Invocation.Builder builder = target.request(MediaType.APPLICATION_JSON_TYPE);
		String jsonCustomer = "";
		try {
			jsonCustomer = jsonMapper.writeValueAsString(customer);
		} catch (IOException e1) {
			logger.error("Mapping exception",e1);
			return -1;
		}
		logger.debug("Mapped data: {}",jsonCustomer);
		Response response = builder.post(Entity.entity(jsonCustomer, MediaType.APPLICATION_JSON));
		JsonIntegerData data;
		if (response.getStatus() == 200) {

			try {
				String entity = response.readEntity(String.class);
				logger.debug("Recieved {}", entity);
				data = jsonMapper.readValue(entity, JsonIntegerData.class);
			} catch (IOException e) {
				logger.error("Parse response entity error", e);
				return -1;
			}
		} else {
			logger.error("URI: {}.Response status: {}", target.getUri(),
					response.getStatus());
			throw new HTTPException(response.getStatus());
		}

		if(data == null){
			logger.debug("Recieved data is null");
			return -1;
		} else{
			if(data.getException().getStatus()>0){
				logger.error("{}: {}", data.getException().getExceptionClass(),
						data.getException().getExceptionMessage());
				return -1;
			}
		}
		
		return data.getData();
	}

}
