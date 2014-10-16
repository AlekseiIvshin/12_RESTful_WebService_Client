package client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import client.helper.RequestDataPackerImpl;
import client.helper.RequsetDataPacker;
import client.helper.ResponseParser;
import client.helper.ResponseParserImpl;

public abstract class RestClient {

	static final Logger logger = LoggerFactory.getLogger(RestClient.class);

	protected final String host;
	protected final Client client;
	protected final ResponseParser responseParser;
	protected final RequsetDataPacker dataPacker;

	public RestClient(String host) {
		this.host = host;
		client = ClientBuilder.newClient();
		responseParser = new ResponseParserImpl();
		dataPacker = new RequestDataPackerImpl();
	}


}
