package client;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.type.TypeReference;

import domain.Car;
import domain.JsonData;

public class RestCarServiceImpl extends RestClient implements RestCarService {

	public RestCarServiceImpl(String host) {
		super(host);
	}

	@Override
	public Car getCarById(long id) {
		WebTarget target = client.target(host + "/car/id/" + id);
		Invocation.Builder builder = target
				.request(MediaType.APPLICATION_JSON_TYPE);
		Response response = builder.get();
		JsonData<Car> data = responseParser.parseResponse(response,
				new TypeReference<JsonData<Car>>() {
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
	public Car getCarByName(String markName, String modelName,
			String modification) {
		WebTarget target = client.target(host + "/car/" + markName + "/"
				+ modelName + "/" + modification);
		Invocation.Builder builder = target
				.request(MediaType.APPLICATION_JSON_TYPE);
		Response response = builder.get();
		JsonData<Car> data = responseParser.parseResponse(response,
				new TypeReference<JsonData<Car>>() {
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
	public List<Car> getAllMarks() {
		WebTarget target = client.target(host + "/car/marks");
		Invocation.Builder builder = target
				.request(MediaType.APPLICATION_JSON_TYPE);
		Response response = builder.get();
		JsonData<List<Car>> data = responseParser.parseResponse(response,
				new TypeReference<JsonData<List<Car>>>() {
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
	public List<Car> getAllModels(long markId) {
		WebTarget target = client.target(host + "/car/mark/" + markId
				+ "/models");
		Invocation.Builder builder = target
				.request(MediaType.APPLICATION_JSON_TYPE);
		Response response = builder.get();
		JsonData<List<Car>> data = responseParser.parseResponse(response,
				new TypeReference<JsonData<List<Car>>>() {
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
	public List<Car> getAllModifications(long modelId) {
		WebTarget target = client.target(host + "/car/model/" + modelId
				+ "/modifications");
		Invocation.Builder builder = target
				.request(MediaType.APPLICATION_JSON_TYPE);
		Response response = builder.get();
		JsonData<List<Car>> data = responseParser.parseResponse(response,
				new TypeReference<JsonData<List<Car>>>() {
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
	public long createCarAndGetId(Car data) {
		WebTarget target = client.target(host + "/car/new");
		Invocation.Builder builder = target
				.request(MediaType.APPLICATION_JSON_TYPE);
		String jsonCar = dataPacker.packToJsonString(data);
		if (jsonCar == null) {
			return -1;
		}
		logger.debug("Mapped data: {}", jsonCar);
		Response response = builder.post(Entity.entity(jsonCar,
				MediaType.APPLICATION_JSON));

		JsonData<Integer> resultData = responseParser.parseResponse(response,
				new TypeReference<JsonData<Integer>>() {
				});

		if (resultData == null) {
			logger.debug("Recieved data is null");
			return -1;
		} else {
			if (resultData.getException().haveError()) {
				logger.error("{}: {}", resultData.getException()
						.getExceptionClass(), resultData.getException()
						.getExceptionMessage());
				return -1;
			}
		}

		return resultData.getData();
	}

}
