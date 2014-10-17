package reports;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.rendersnake.Renderable;

/**
 * Facade for reports.
 * 
 * @author Aleksei_Ivshin
 *
 */
public abstract class ReportFacade {

	/**
	 * Map of reports.
	 */
	protected final Map<String, Renderable> reports;

	public ReportFacade() {
		reports = new HashMap<String, Renderable>();
	}

	/**
	 * Add report to list.
	 * 
	 * @param name
	 *            name of report.
	 * @param report
	 *            report render object
	 */
	public void addReport(String name, Renderable report) {
		if (reports.containsKey(name)) {
			reports.remove(name);
		}
		reports.put(name, report);
	}

	/**
	 * Remove report by name.
	 * 
	 * @param name
	 *            report name
	 */
	public void removeReport(String name) {
		if (reports.containsKey(name)) {
			reports.remove(name);
		}
	}

	/**
	 * Start process of creating reports.
	 * 
	 * @throws ReportException
	 */
	public abstract void createReports() throws ReportException;

	/**
	 * Create reports by names
	 * 
	 * @param name
	 *            selected names
	 * @throws ReportException 
	 */
	public abstract void createReport(String... name) throws IOException, ReportException;

	/**
	 * Create one report by name
	 * 
	 * @param reportName
	 *            targer report name
	 * @throws ReportException 
	 */
	public abstract void createReport(String reportName) throws ReportException;
}
