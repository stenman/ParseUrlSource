package com.example.parseurlsource.table;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.HttpStatusException;
import org.tepi.filtertable.FilterTable;

import com.example.parseurlsource.container.AgdqScheduleContainer;
import com.example.parseurlsource.parser.JsoupUrlParser;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public class AgdqScheduleTable extends FilterTable {

	private AgdqScheduleContainer container;
	private JsoupUrlParser jsoupUrlParser;

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

	public void refreshTable() throws HttpStatusException, IOException{
		refresh();
	}
	
	private void initTable() {
		this.setImmediate(true);
		this.setSizeFull();
		this.setRowHeaderMode(ROW_HEADER_MODE_INDEX);
		this.setFooterVisible(true);
		this.setFilterBarVisible(true);
		this.setSelectable(true);
	}

	private void initContainer() {
		container = new AgdqScheduleContainer();
		this.setContainerDataSource(container);
	}

	public void refresh() throws HttpStatusException, IOException {
		jsoupUrlParser = new JsoupUrlParser();
		jsoupUrlParser.setUrl("http://gamesdonequick.com/schedule");
		container.addItems(jsoupUrlParser.getScheduleItems());
	}

	private void activateColorMarkingOfRows() {
		this.setCellStyleGenerator(new FilterTable.CellStyleGenerator() {
			@Override
			public String getStyle(CustomTable source, Object itemId, Object propertyId) {
				if (propertyId == null) {

					// Styling for row
					Item item = getItem(itemId);
					String game = (String) item.getItemProperty("game").getValue();
					if (game.toLowerCase().startsWith("g")) {
						return "highlight-green";
					} else if (game.contains("o")) {
						return "highlight-red";
					} else {
						return null;
					}
				} else {
					// styling for column propertyId
					return null;
				}
			}
		});

	}
}
