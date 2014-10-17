package reports.html;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.rendersnake.HtmlCanvas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reports.ReportException;
import reports.ReportFacade;

/**
 * Create report to HTML pages.
 * 
 * @author Aleksei_Ivshin
 *
 */
public class ReportsHTML extends ReportFacade {

	static final Logger logger = LoggerFactory.getLogger(ReportsHTML.class);

	/**
	 * Directory where create HTML files
	 */
	private final File destDirectory;

	/**
	 * Initialize HTML report creator.
	 * 
	 * @param destDirectory
	 *            destination directory
	 */
	public ReportsHTML(File destDirectory) {
		super();
		this.destDirectory = destDirectory;
	}

	public void createReports() throws ReportException {
		for (String reportName : reports.keySet()) {
			createReport(reportName);
		}
	}

	public void createReport(String... name) throws ReportException {
		for (String reportName : name) {
			createReport(reportName);
		}
	}

	public void createReport(String reportName) throws ReportException{
		checkDestPath();
		if (!reports.containsKey(reportName)) {
			return;
		}
		try {
			reports.get(reportName).renderOn(
					new HtmlCanvas(new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(destDirectory + File.separator
									+ reportName + ".html"), "utf-8"))));
		} catch (IOException e) {
			logger.error("Report create error", e);
			throw new ReportException(e);
		}
	}

	private void checkDestPath() throws ReportException {
		if (!destDirectory.exists()) {
			destDirectory.mkdir();
		}
		if (!destDirectory.isDirectory()) {
			logger.error("Destination path is not directory: '{}'",
					destDirectory.toPath());
			throw new ReportException("Destination path is not directory '"
					+ destDirectory + "'");
		}
	}
}
