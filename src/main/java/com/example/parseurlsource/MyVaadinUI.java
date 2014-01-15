package com.example.parseurlsource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.parseurlsource.view.AgdqScheduleView;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 * Entry point of the application. A Navigator is used to redirect between different views.
 * 
 * @author Roger
 * 
 */
@Component
@Scope("prototype")
@Title("Transaction Type Manager")
@PreserveOnRefresh
@Theme("agdqtheme")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI {

//	@WebServlet(value = "/*", asyncSupported = true)
//	@VaadinServletConfiguration(productionMode = true, ui = MyVaadinUI.class, widgetset = "com.example.parseurlsource.AppWidgetSet")
//	public static class Servlet extends VaadinServlet {
//	}

	@Override
	protected void init(VaadinRequest request) {
		new Navigator(this, this);
		getNavigator().addView(AgdqScheduleView.NAME, AgdqScheduleView.class);
	}

}
