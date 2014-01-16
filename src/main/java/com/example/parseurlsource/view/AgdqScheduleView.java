package com.example.parseurlsource.view;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.jsoup.HttpStatusException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

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
@Component
@Scope("prototype")
@VaadinView(value = AgdqScheduleView.NAME, cached = true)
@SuppressWarnings("serial")
public class AgdqScheduleView extends VerticalLayout implements View {

	@Inject
	private AgdqScheduleTable agdqScheduleTable;

	public static final String NAME = "";

	private HorizontalLayout menuLayout;
	private HorizontalLayout tableLayout;
	private Button refreshTable;

	public AgdqScheduleView() {
		initButtons();
	}

	@PostConstruct
	public void PostConstruct() {
		refreshTable();
		initLayout();
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}

	private void refreshTable() {
		try {
			agdqScheduleTable.refresh();
		} catch (HttpStatusException e) {
			Notification.show("A problem occured while trying to resolve the URL: " + e, Type.ERROR_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initLayout() {

		this.setSizeFull();

		menuLayout = new HorizontalLayout();
		tableLayout = new HorizontalLayout();

		menuLayout.addComponent(refreshTable);
		menuLayout.setComponentAlignment(refreshTable, Alignment.BOTTOM_LEFT);

		tableLayout.setSizeFull();

		tableLayout.addComponent(agdqScheduleTable);

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
			agdqScheduleTable.refresh();
			Notification.show("Table refreshed!", Type.HUMANIZED_MESSAGE);
		} catch (HttpStatusException e) {
			Notification.show("A problem occured while trying to resolve the URL: " + e, Type.ERROR_MESSAGE);
		} catch (IOException e) {
			Notification.show("A problem occured while trying to resolve the URL: " + e, Type.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

}
