package reports.views;

import static org.rendersnake.HtmlAttributesFactory.charset;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Customer;

/**
 * Create customer info view.
 * 
 * @author Aleksei_Ivshin
 *
 */
public class CustomerInfo implements Renderable {
	static final Logger logger = LoggerFactory.getLogger(CustomerInfo.class);

	/**
	 * Customer data.
	 */
	private final Customer customerData;

	public CustomerInfo(Customer customerData) {
		this.customerData = customerData;
	}

	/**
	 * Render HTML report.
	 */
	@Override
	public void renderOn(HtmlCanvas html) throws IOException {
		if (html.getOutputWriter() == null) {
			throw new IOException("Cannot find writer.");
		}
		HtmlCanvas view = createView(html);
		writeView(view);
	}

	/**
	 * Write rendered view.
	 * 
	 * @param html
	 *            rendered view
	 * @throws IOException
	 */
	private void writeView(HtmlCanvas html) throws IOException {

		String text = html.toHtml();
		int lastPos = text.lastIndexOf("\n");
		Writer writer = null;
		try {
			writer = html.getOutputWriter();
			writer.write(html.toHtml(), 0, lastPos);
		} finally {
			writer.close();
		}
	}

	/**
	 * Render HTML view.
	 * 
	 * @param html
	 *            starting HTML
	 * @return result report view
	 * @throws IOException
	 */
	private HtmlCanvas createView(HtmlCanvas html) throws IOException {
		html.html().head().meta(charset("utf-8"))._head().body().h1()
				.content("Customer info");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		html.table();
		html.tr().td().content("Id").td().content(customerData.getId())._tr()
				.tr().td().content("Surname").td()
				.content(customerData.getSurname())._tr().tr().td()
				.content("Name").td().content(customerData.getName())._tr()
				.tr().td().content("Patronymic").td()
				.content(customerData.getPatronymic())._tr().tr().td()
				.content("Passport series").td()
				.content(customerData.getPassportSeries())._tr().tr().td()
				.content("Passport number").td()
				.content(customerData.getPassportNumber())._tr().tr().td()
				.content("Birth date").td()
				.content(dateFormat.format(customerData.getBirthDate()))._tr();

		html._table()._body()._html().toHtml();
		return html;
	}

}
