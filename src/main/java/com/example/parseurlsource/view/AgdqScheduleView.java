package com.example.parseurlsource.view;

import java.io.IOException;

import org.jsoup.HttpStatusException;

import com.example.parseurlsource.table.AgdqScheduleTable;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;

/**
 * Presents a table that contains the schedule of AGDQ and a refresh button
 * 
 * @author Roger
 * 
 */
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

	@Override
	public void enter(ViewChangeEvent event) {
	}

	private void initTable() {
		table = new AgdqScheduleTable();
		try {
			table.refresh();
		} catch (HttpStatusException e) {
			Notification.show("A problem occured while trying to resolve the URL: " + e, Type.ERROR_MESSAGE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	private void initButtons() {
		refreshTable = new Button("Refresh table");

		refreshTable.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				updateTable();
			}
		});
	}

	private void updateTable() {
		try {
			table.refresh();
			Notification.show("Table refreshed!", Type.HUMANIZED_MESSAGE);
		} catch (HttpStatusException e) {
			Notification.show("A problem occured while trying to resolve the URL: " + e, Type.ERROR_MESSAGE);
		} catch (IOException e) {
			Notification.show("A problem occured while trying to resolve the URL: " + e, Type.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

}
