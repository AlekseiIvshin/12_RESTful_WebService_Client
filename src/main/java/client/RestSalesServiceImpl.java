package client;

import java.util.List;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.type.TypeReference;

import domain.Car;
import domain.Customer;
import domain.JsonData;
import domain.Merchant;
import domain.Sales;

public class RestSalesServiceImpl extends RestClient implements
		RestSalesService {

	public RestSalesServiceImpl(String host) {
		super(host);
	}

	@Override
	public Sales saleCar(Customer customer, Merchant merchant, Car car) {
		WebTarget target = client.target(host + "/sales/new")
				.queryParam("customer", customer.getId())
				.queryParam("merchant", merchant.getId())
				.queryParam("car", car.getId());
		Invocation.Builder builder = target
				.request(MediaType.APPLICATION_JSON_TYPE);
		Response response = builder.get();
		JsonData<Sales> jsonSales = responseParser.parseResponse(response,
				new TypeReference<JsonData<Sales>>() {
				});
		if (jsonSales == null) {
			logger.debug("Recieved data is null");
			return null;
		}

		if (jsonSales.getException().haveError()) {
			logger.error("{}: {}",
					jsonSales.getException().getExceptionClass(), jsonSales
							.getException().getExceptionMessage());
			return null;
		}

		return jsonSales.getData();
	}

	@Override
	public List<Sales> getAllSales() {
		WebTarget target = client.target(host + "/sales/all");
		Invocation.Builder builder = target
				.request(MediaType.APPLICATION_JSON_TYPE);
		Response response = builder.get();
		JsonData<List<Sales>> sales = responseParser.parseResponse(response,
				new TypeReference<JsonData<List<Sales>>>() {
				});
		if (sales == null) {
			logger.debug("Recieved data is null");
			return null;
		}

		if (sales.getException().haveError()) {
			logger.error("{}: {}", sales.getException().getExceptionClass(),
					sales.getException().getExceptionMessage());
			return null;
		}

		return sales.getData();
	}

}
