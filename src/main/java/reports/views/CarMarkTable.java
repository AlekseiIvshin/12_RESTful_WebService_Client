package reports.views;

import static org.rendersnake.HtmlAttributesFactory.charset;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;

import domain.Car;

public class CarMarkTable implements Renderable {
	/**
	 * Cars data.
	 */
	private List<Car> cars;

	public CarMarkTable(List<Car> cars) {
		this.cars = cars;
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
				.content("Cars mark report").table().tr().th()
				.content("Mark ID").th().content("Mark name")._tr();
		for (Car car : cars) {
			html.tr().td().content(car.getId() + "").td()
					.content(car.getMark())._tr();
		}

		html._table()._body()._html().toHtml();
		return html;
	}
}
