package client;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import javax.xml.ws.http.HTTPException;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class RestClient {

	static final Logger logger = LoggerFactory.getLogger(RestClient.class);

	protected final String host;
	protected final Client client;
	protected final ObjectMapper jsonMapper;

	public RestClient(String host) {
		this.host = host;
		client = ClientBuilder.newClient();
		jsonMapper = new ObjectMapper();
	}

	protected <T> T parseResponse(Response response, TypeReference<?> type) {
		if (response.getStatus() == 200) {

			try {
				String entity = response.readEntity(String.class);
				return jsonMapper.readValue(entity, type);
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
