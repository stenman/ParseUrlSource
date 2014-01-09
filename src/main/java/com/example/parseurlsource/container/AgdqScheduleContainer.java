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
		this.addContainerProperty("startTime", Date.class, null);
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
		this.removeAllContainerFilters();
		this.removeAllFilters();

		for (AgdqSchedule agdqSchedule : agdqSchedules) {
			Object itemId = addItem();
			getContainerProperty(itemId, "startTime").setValue(agdqSchedule.getStartTime());
			getContainerProperty(itemId, "game").setValue(agdqSchedule.getGame());
			getContainerProperty(itemId, "runners").setValue(agdqSchedule.getRunners());
			getContainerProperty(itemId, "runTime").setValue(agdqSchedule.getRunTime());
			getContainerProperty(itemId, "details").setValue(agdqSchedule.getDetails());
			getContainerProperty(itemId, "commentators").setValue(agdqSchedule.getCommentators());
			getContainerProperty(itemId, "prizes").setValue(agdqSchedule.getPrizes());
		}
	}
	
}
