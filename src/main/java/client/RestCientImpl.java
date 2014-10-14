package client;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.http.HTTPException;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Customer;

public class RestCientImpl implements RestClient {

	static final Logger logger = LoggerFactory.getLogger(RestCientImpl.class);

	private final String host;
	private final ObjectMapper mapper; 
	
	public RestCientImpl(String host){
		this.host = host;
		mapper = new ObjectMapper();
	}
	
	@Override
	public Customer getCustomerByPassport(String series, String number) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client
				.target(host+"/customer/passport/" + series
						+ "/" + number);
		Invocation.Builder builder = target
				.request(MediaType.TEXT_PLAIN_TYPE);
		Response response = builder.get();
		if(response.getStatus() == 200){
			try {
				return mapper.readValue(response.readEntity(String.class),Customer.class);
			} catch (IOException e) {
				logger.error("Parse response entity error",e);
			}
		} else {
			logger.error("URI: {}.Response status: {}",target.getUri(),response.getStatus());
			throw new HTTPException(response.getStatus());
		}
		return null;
	}

}
