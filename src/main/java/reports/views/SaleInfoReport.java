package reports.views;

import static org.rendersnake.HtmlAttributesFactory.charset;

import java.io.IOException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Sales;

public class SaleInfoReport implements Renderable {

	static final Logger logger = LoggerFactory.getLogger(SaleInfoReport.class);

	/**
	 * Customer data.
	 */
	private final Sales data;

	public SaleInfoReport(Sales data) {
		this.data = data;
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
				.content("Sale #"+data.getId()+" info");
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		html.table();
		html.tr().td().content("Id").td().content(data.getId())._tr()
				.tr().td().content("Customer").td()
				.content(data.getCustomer().getSurname()+" "+data.getCustomer().getName())._tr().tr().td()
				.content("Merchant").td().content(data.getMerchant().getSurname()+" "+data.getMerchant().getName())._tr()
				.tr().td().content("Car").td()
				.content(data.getCar().getMark()+" "+data.getCar().getModel()+" "+data.getCar().getModification())._tr().tr().td()
				.content("Price").td()
				.content(decimalFormat.format(data.getPrice()))._tr().tr().td()
				.content("Date").td()
				.content(dateFormat.format(data.getSaleDate()))._tr();

		html._table()._body()._html().toHtml();
		return html;
	}
}
