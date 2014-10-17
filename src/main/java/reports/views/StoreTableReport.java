package reports.views;

import static org.rendersnake.HtmlAttributesFactory.charset;

import java.io.IOException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.List;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;

import domain.Store;


/**
 * Create store table report.
 * 
 * @author Aleksei_Ivshin
 *
 */
public class StoreTableReport implements Renderable {

	private List<Store> stores;

	public StoreTableReport(List<Store> stores) {
		this.stores = stores;
	}

	@Override
	public void renderOn(HtmlCanvas html) throws IOException {
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
				.content("Store report").table().tr().th().content("Store ID")
				.th().content("Car").th().content("Price").th()
				.content("Count")._tr();
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		for (Store store : stores) {
			html.tr()
					.td()
					.content(store.getId() + "")
					.td()
					.content(
							store.getCar().getMark() + " "
									+ store.getCar().getModel() + " "
									+ store.getCar().getModification()).td()
					.content(decimalFormat.format(store.getPrice())).td()
					.content(store.getQuantity() + "")._tr();
		}

		html._table()._body()._html().toHtml();
		return html;
	}
}
