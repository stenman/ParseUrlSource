package com.example.parseurlsource.container;

import java.util.Date;
import java.util.List;

import com.example.parseurlsource.AgdqSchedule.AgdqSchedule;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;

@SuppressWarnings("serial")
public class AgdqScheduleContainer extends IndexedContainer {

	public AgdqScheduleContainer() {
		addProperties();
	}
	
	private void addProperties() {
		this.addContainerProperty("startTime", Date.class, "N/A");
		this.addContainerProperty("game", String.class, "N/A");
		this.addContainerProperty("runners", String.class, "N/A");
		this.addContainerProperty("runTime", String.class, "N/A");
		this.addContainerProperty("details", String.class, "N/A");
		this.addContainerProperty("commentators", String.class, "N/A");
		this.addContainerProperty("prizes", String.class, "N/A");
	}
	
	@SuppressWarnings("unchecked")
	public void addItems(List<AgdqSchedule> agdqSchedules){

		this.removeAllItems();
		
		for (AgdqSchedule agdqSchedule : agdqSchedules) {
			Object itemId = this.addItem();
			
			Item item = this.getItem(itemId);

			Property<Date> startTimeProperty = item.getItemProperty("startTime");
			startTimeProperty.setValue(agdqSchedule.getStartTime());
			
			Property<String> gameProperty = item.getItemProperty("game");
			gameProperty.setValue(agdqSchedule.getGame());
			
			Property<String> runnersProperty = item.getItemProperty("runners");
			runnersProperty.setValue(agdqSchedule.getRunners());
			
			Property<String> runTimeProperty = item.getItemProperty("runTime");
			runTimeProperty.setValue(agdqSchedule.getRunTime());
			
			Property<String> detailsProperty = item.getItemProperty("details");
			detailsProperty.setValue(agdqSchedule.getDetails());
			
			Property<String> commentatorsProperty = item.getItemProperty("commentators");
			commentatorsProperty.setValue(agdqSchedule.getCommentators());
			
			Property<String> prizesProperty = item.getItemProperty("prizes");
			prizesProperty.setValue(agdqSchedule.getPrizes());
		}
	}
	
}
