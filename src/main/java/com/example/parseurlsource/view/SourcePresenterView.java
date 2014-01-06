package com.example.parseurlsource.view;

import com.example.parseurlsource.parser.JsoupUrlParser;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings("serial")
public class SourcePresenterView extends VerticalLayout implements View {

	public static final String NAME = "Source_Presenter_View";

	private VerticalLayout menuLayout;
	private HorizontalLayout menuItem1;
	private HorizontalLayout tableLayout;
	private Button parseUrl;
	private TextField urlToParse;
	private Label label = new Label("");

	public SourcePresenterView() {
		initButtons();
		initTextFields();
		initLayout();
	}

	private void initLayout() {

		menuLayout = new VerticalLayout();
		menuItem1 = new HorizontalLayout();
		tableLayout = new HorizontalLayout();

		menuLayout.setMargin(true);
		menuItem1.setMargin(true);

		menuItem1.addComponent(parseUrl);
		menuItem1.setComponentAlignment(parseUrl, Alignment.BOTTOM_LEFT);
		menuItem1.addComponent(urlToParse);
		
		tableLayout.addComponent(label);
		
		menuLayout.addComponent(menuItem1);
		
		this.addComponent(menuLayout);
		this.addComponent(tableLayout);

		this.setExpandRatio(tableLayout, 1);
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}

	private void initTextFields() {
		urlToParse = new TextField("Enter the URL you want to parse");
	}

	private void initButtons() {
		parseUrl = new Button("Click to parse URL");

		parseUrl.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				updateTable(urlToParse.getValue());
			}
		});
	}

	private void updateTable(String url) {
		JsoupUrlParser urlParser = new JsoupUrlParser();
		urlParser.setUrl(url);
		label.setValue(urlParser.getTitle());
	}

}
