package client;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.http.HTTPException;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Car;
import domain.JsonData;

public class RestCarServiceImpl implements RestCarService {
	static final Logger logger = LoggerFactory
			.getLogger(RestCarServiceImpl.class);

	private final String host;
	private final Client client;
	private final ObjectMapper jsonMapper = new ObjectMapper();

	public RestCarServiceImpl(String host) {
		this.host = host;
		client = ClientBuilder.newClient();
	}

	@Override
	public Car getCarById(long id) {

		WebTarget target = client.target(host + "/car/id/" + id);
		Invocation.Builder builder = target
				.request(MediaType.APPLICATION_JSON_TYPE);
		Response response = builder.get();
		JsonData<Car> data;

		if (response.getStatus() == 200) {

			try {
				String entity = response.readEntity(String.class);
				data = jsonMapper.readValue(entity,
						new TypeReference<JsonData<Car>>() {
						});
			} catch (IOException e) {
				logger.error("Parse response entity error", e);
				return null;
			}
		} else {
			logger.error("Response status: {}", response.getStatus());
			throw new HTTPException(response.getStatus());
		}

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
	public Car getCarByName(String markName, String modelName,
			String modification) {
		WebTarget target = client.target(host + "/car/" + markName + "/"
				+ modelName + "/" + modification);
		Invocation.Builder builder = target
				.request(MediaType.APPLICATION_JSON_TYPE);
		Response response = builder.get();
		JsonData<Car> data;

		if (response.getStatus() == 200) {

			try {
				String entity = response.readEntity(String.class);
				data = jsonMapper.readValue(entity,
						new TypeReference<JsonData<Car>>() {
						});
			} catch (IOException e) {
				logger.error("Parse response entity error", e);
				return null;
			}
		} else {
			logger.error("Response status: {}", response.getStatus());
			throw new HTTPException(response.getStatus());
		}
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
	public List<Car> getAllMarks() {
		WebTarget target = client.target(host + "/car/marks");
		Invocation.Builder builder = target
				.request(MediaType.APPLICATION_JSON_TYPE);
		Response response = builder.get();
		JsonData<List<Car>> data;

		if (response.getStatus() == 200) {

			try {
				String entity = response.readEntity(String.class);
				data = jsonMapper.readValue(entity,
						new TypeReference<JsonData<List<Car>>>() {
						});
			} catch (IOException e) {
				logger.error("Parse response entity error", e);
				return null;
			}
		} else {
			logger.error("Response status: {}", response.getStatus());
			throw new HTTPException(response.getStatus());
		}

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
	public List<Car> getAllModels(long markId) {
		WebTarget target = client.target(host + "/car/mark/" + markId
				+ "/models");
		Invocation.Builder builder = target
				.request(MediaType.APPLICATION_JSON_TYPE);
		Response response = builder.get();
		JsonData<List<Car>> data;

		if (response.getStatus() == 200) {

			try {
				String entity = response.readEntity(String.class);
				data = jsonMapper.readValue(entity,
						new TypeReference<JsonData<List<Car>>>() {
						});
			} catch (IOException e) {
				logger.error("Parse response entity error", e);
				return null;
			}
		} else {
			logger.error("Response status: {}", response.getStatus());
			throw new HTTPException(response.getStatus());
		}

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
	public List<Car> getAllModifications(long modelId) {
		WebTarget target = client.target(host + "/car/model/" + modelId
				+ "/modifications");
		Invocation.Builder builder = target
				.request(MediaType.APPLICATION_JSON_TYPE);
		Response response = builder.get();
		JsonData<List<Car>> data;

		if (response.getStatus() == 200) {

			try {
				String entity = response.readEntity(String.class);
				data = jsonMapper.readValue(entity,
						new TypeReference<JsonData<List<Car>>>() {
						});
			} catch (IOException e) {
				logger.error("Parse response entity error", e);
				return null;
			}
		} else {
			logger.error("Response status: {}", response.getStatus());
			throw new HTTPException(response.getStatus());
		}

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
	public long createCarAndGetId(Car data) {
		WebTarget target = client.target(host + "/car/new");
		Invocation.Builder builder = target
				.request(MediaType.APPLICATION_JSON_TYPE);
		String jsonCustomer = "";
		try {
			jsonCustomer = jsonMapper.writeValueAsString(data);
		} catch (IOException e1) {
			logger.error("Mapping exception", e1);
			return -1;
		}
		logger.debug("Mapped data: {}", jsonCustomer);
		Response response = builder.post(Entity.entity(jsonCustomer,
				MediaType.APPLICATION_JSON));

		JsonData<Integer> resultData;

		if (response.getStatus() == 200) {

			try {
				String entity = response.readEntity(String.class);
				resultData = jsonMapper.readValue(entity,
						new TypeReference<JsonData<Car>>() {
						});
			} catch (IOException e) {
				logger.error("Parse response entity error", e);
				return -1;
			}
		} else {
			logger.error("Response status: {}", response.getStatus());
			throw new HTTPException(response.getStatus());
		}

		if (resultData == null) {
			logger.debug("Recieved data is null");
			return -1;
		} else {
			if (resultData.getException().getStatus() > 0) {
				logger.error("{}: {}", resultData.getException()
						.getExceptionClass(), resultData.getException()
						.getExceptionMessage());
				return -1;
			}
		}

		return resultData.getData();
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
