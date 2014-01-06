package com.example.parseurlsource;

import javax.servlet.annotation.WebServlet;

import com.example.parseurlsource.view.AgdqScheduleView;
import com.example.parseurlsource.view.SourcePresenterView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "com.example.parseurlsource.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
    	new Navigator(this, this);
    	getNavigator().addView(SourcePresenterView.NAME, SourcePresenterView.class);
    	getNavigator().addView(AgdqScheduleView.NAME, AgdqScheduleView.class);
    }

}
