package com.example.parseurlsource.table;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.joda.time.DateTime;
import org.jsoup.HttpStatusException;
import org.jsoup.nodes.Document;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.tepi.filtertable.FilterTable;

import com.example.parseurlsource.container.AgdqScheduleContainer;
import com.example.parseurlsource.parser.JsoupUrlParser;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.ui.CustomTable;

/**
 * The main table of this application
 * 
 * @author Roger
 * 
 */
@Component
@Scope("prototype")
@SuppressWarnings("serial")
public class AgdqScheduleTable extends FilterTable {

	@Inject
	private AgdqScheduleContainer agdqScheduleContainer;
	@Inject
	private JsoupUrlParser jsoupUrlParser;

	private int currentSpeedrun = 0;

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

	@PostConstruct
	public void PostConstruct() {
		initContainer();
		initTable();
	}

	@SuppressWarnings("deprecation")
	private void initTable() {
		this.setImmediate(true);
		this.setSizeFull();
		this.setRowHeaderMode(ROW_HEADER_MODE_INDEX);
		this.setFooterVisible(true);
		this.setFilterBarVisible(true);
		this.setSelectable(true);
	}

	private void initContainer() {
		this.setContainerDataSource(agdqScheduleContainer);
	}

	/**
	 * Updates the table with fresh data.
	 * 
	 * @throws HttpStatusException
	 * @throws IOException
	 */
	public void refresh() throws HttpStatusException, IOException {
		// TODO: Until I find a better way of switching between environments...
		// PRODUCTION
		jsoupUrlParser.setUrl("http://gamesdonequick.com/schedule");
		Document doc = jsoupUrlParser.setDoc();
		agdqScheduleContainer.addItems(jsoupUrlParser.getScheduleItems(doc));
		// PRODUCTION

		// DEVELOPMENT
		// File file = new File("c:/temp/agdqschedule.html");
		// Document doc = Jsoup.parse(file, "UTF-8", "http://example.com/");
		// agdqScheduleContainer.addItems(jsoupUrlParser.getScheduleItems(doc));
		// DEVELOPMENT

		activateColorMarkingOfRows();
	}

	/**
	 * Marks the row of the current speedrun with a green background.
	 */
	private void activateColorMarkingOfRows() {
		for (Iterator<?> i = agdqScheduleContainer.getItemIds().iterator(); i.hasNext();) {
			int iid = (Integer) i.next();
			Item item = agdqScheduleContainer.getItem(iid);
			Date date = (Date) item.getItemProperty("startTime").getValue();
			DateTime startTime = new DateTime(date);

			if (startTime.isAfterNow()) {
				currentSpeedrun = iid - 1;
				break;
			}
		}

		this.setCellStyleGenerator(new FilterTable.CellStyleGenerator() {
			@Override
			public String getStyle(CustomTable source, Object itemId, Object propertyId) {
				if (propertyId == null) {

					// Styling for row
					if ((Integer) itemId == currentSpeedrun) {
						return "highlight-green";
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
