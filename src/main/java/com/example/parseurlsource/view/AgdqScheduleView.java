package com.example.parseurlsource.view;

import com.example.parseurlsource.parser.JsoupUrlParser;
import com.example.parseurlsource.table.AgdqScheduleTable;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings("serial")
public class AgdqScheduleView extends VerticalLayout implements View {

	public static final String NAME = "";

	private HorizontalLayout menuLayout;
	private HorizontalLayout tableLayout;
	private Button refreshTable;
	private AgdqScheduleTable table;

	public AgdqScheduleView() {
		initButtons();
		initTable();
		initLayout();
	}

	private void initTable() {
		table = new AgdqScheduleTable();
	}

	private void initLayout() {

		this.setSizeFull();

		menuLayout = new HorizontalLayout();
		tableLayout = new HorizontalLayout();

		menuLayout.setMargin(true);

		menuLayout.addComponent(refreshTable);
		menuLayout.setComponentAlignment(refreshTable, Alignment.BOTTOM_LEFT);

		tableLayout.setSizeFull();

		tableLayout.addComponent(table);

		this.addComponent(menuLayout);
		this.addComponent(tableLayout);

		this.setExpandRatio(tableLayout, 1);
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}

	private void initButtons() {
		refreshTable = new Button("Refresh table");

		refreshTable.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				updateTable("http://gamesdonequick.com/schedule");
			}
		});
	}

	private void updateTable(String url) {
		JsoupUrlParser urlParser = new JsoupUrlParser();
		urlParser.setUrl(url);
		table.refresh();
		Notification.show("Table refreshed!", Type.HUMANIZED_MESSAGE);
	}

}
