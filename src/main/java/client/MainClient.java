package client;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reports.ReportException;
import reports.ReportFacade;
import reports.html.ReportsHTML;
import reports.views.CarMarkTable;
import reports.views.CustomerInfo;
import reports.views.SalesTableReport;
import domain.Car;
import domain.Customer;
import domain.Sales;

public class MainClient {
	static final Logger logger = LoggerFactory.getLogger(MainClient.class);

	private RestCarService carService;
	private RestCustomer customerService;
	private RestSalesService saleService;

	private ReportFacade report;

	public MainClient(String host) {
		carService = new RestCarServiceImpl(host);
		customerService = new RestCustomerImpl(host);
		saleService = new RestSalesServiceImpl(host);
		report = new ReportsHTML(new File("bin/reportsHTML/"));
	}

	public void addCustomerInfoReportByPassport(String passportSeries,
			String passportNumber) {
		Customer customer = customerService.getCustomerByPassport(
				passportSeries, passportNumber);
		report.addReport("CustomerInfo", new CustomerInfo(customer));
	}

	public void addCarsReport() {
		List<Car> cars = carService.getAllMarks();
		report.addReport("All car marks", new CarMarkTable(cars));
	}

	public void createAddedReports() throws ReportException {
		report.createReports();
	}

	public void addSalesCarReports() {
		List<Sales> sales = saleService.getAllSales();
		report.addReport("Sales", new SalesTableReport(sales));
	}
}
