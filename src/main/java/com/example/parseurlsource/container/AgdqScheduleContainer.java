package com.example.parseurlsource.container;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.parseurlsource.domainmodel.AgdqSchedule;
import com.vaadin.data.util.IndexedContainer;

/**
 * Contains AGDQSchedule data
 * 
 * @author Roger
 *
 */
@Component
@Scope("prototype")
@SuppressWarnings("serial")
public class AgdqScheduleContainer extends IndexedContainer {

	private static final Logger logger = LoggerFactory.getLogger(AgdqScheduleContainer.class);
	
	public AgdqScheduleContainer() {
		addProperties();
		logger.info("Properties added to container.");
	}

	/**
	 * Adds properties to the container. Properties could be seen as "columns" in a table.
	 */
	private void addProperties() {
		this.addContainerProperty("startTime", Date.class, null);
		this.addContainerProperty("game", String.class, "N/A");
		this.addContainerProperty("runners", String.class, "N/A");
		this.addContainerProperty("runTime", String.class, "N/A");
		this.addContainerProperty("details", String.class, "N/A");
		this.addContainerProperty("commentators", String.class, "N/A");
		this.addContainerProperty("prizes", String.class, "N/A");
	}

	/**
	 * Removes the current items and filters from the container, then adds the provided list of objects (AgdqSchedules) as Items to the container. An
	 * item can be seen as a "row" in a table.
	 * 
	 * @param agdqSchedules
	 */
	@SuppressWarnings("unchecked")
	public void addItems(List<AgdqSchedule> agdqSchedules) {
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
