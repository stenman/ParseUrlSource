package com.example.parseurlsource.table;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.joda.time.DateTime;
import org.jsoup.HttpStatusException;
import org.tepi.filtertable.FilterTable;

import com.example.parseurlsource.container.AgdqScheduleContainer;
import com.example.parseurlsource.parser.JsoupUrlParser;
import com.vaadin.data.Item;
import com.vaadin.data.Property;

/**
 * The main table of this application
 * 
 * @author Roger
 * 
 */
@SuppressWarnings("serial")
public class AgdqScheduleTable extends FilterTable {

	private AgdqScheduleContainer container;
	private JsoupUrlParser jsoupUrlParser;

	@SuppressWarnings("rawtypes")
	@Override
	protected String formatPropertyValue(Object rowId, Object colId, Property property) {
		Object v = property.getValue();
		if (v instanceof Date) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return df.format(v);
		}
		return super.formatPropertyValue(rowId, colId, property);
	}

	public AgdqScheduleTable() {
		initContainer();
		initTable();
		activateColorMarkingOfRows();
	}

	@SuppressWarnings("deprecation")
	private void initTable() {
		this.setImmediate(true);
		this.setSizeFull();
		this.setRowHeaderMode(ROW_HEADER_MODE_INDEX);
		this.setFooterVisible(true);
		this.setFilterBarVisible(true);
		this.setSelectable(false);
	}

	private void initContainer() {
		container = new AgdqScheduleContainer();
		this.setContainerDataSource(container);
	}

	public void refresh() throws HttpStatusException, IOException {
		jsoupUrlParser = new JsoupUrlParser();
		jsoupUrlParser.setUrl("http://gamesdonequick.com/schedule");
		container.addItems(jsoupUrlParser.getScheduleItems());
		activateColorMarkingOfRows();
	}

	private void activateColorMarkingOfRows() {
		// TODO: Will use this method until I figure out how to mark rows by CSS/SASS-injection
		int currentSpeedrun = 0;
		for (Iterator<?> i = container.getItemIds().iterator(); i.hasNext();) {
			int iid = (Integer) i.next();
			Item item = container.getItem(iid);
			Date date = (Date) item.getItemProperty("startTime").getValue();
			DateTime startTime = new DateTime(date);

			if (startTime.isAfterNow()) {
				currentSpeedrun = iid - 1;
				break;
			}
		}
		this.select(currentSpeedrun);

		// TODO: the color marking below is currently not working, google "vaadin table row color example" for help
		// this.setCellStyleGenerator(new FilterTable.CellStyleGenerator() {
		// @Override
		// public String getStyle(CustomTable source, Object itemId, Object propertyId) {
		// if (propertyId == null) {
		//
		// // Styling for row
		// Item item = getItem(itemId);
		// String game = (String) item.getItemProperty("game").getValue();
		// if (game.toLowerCase().startsWith("g")) {
		// return "highlight-green";
		// } else if (game.contains("o")) {
		// return "highlight-red";
		// } else {
		// return null;
		// }
		// } else {
		// // styling for column propertyId
		// return null;
		// }
		// }
		// });
	}
}
