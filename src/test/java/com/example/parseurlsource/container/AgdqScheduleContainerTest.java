package com.example.parseurlsource.container;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.example.parseurlsource.domainmodel.AgdqSchedule;
import com.example.parseurlsource.utils.DateConverter;

@RunWith(JUnit4.class)
public class AgdqScheduleContainerTest {

	// Naming convention: [UnitOfWork_StateUnderTest_ExpectedBehavior]

	private List<AgdqSchedule> agdqSchedules;

	private static final int itemsAdded = 10;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		DateConverter dc = new DateConverter();
		agdqSchedules = new ArrayList<AgdqSchedule>();
		for (int i = 0; i < itemsAdded; i++) {
			AgdqSchedule as = new AgdqSchedule();
			as.setCommentators(String.format("Commentator%s", i));
			as.setDetails(String.format("Details%s", i));
			as.setGame(String.format("Game%s", i));
			as.setPrizes(String.format("Prizes%s", i));
			as.setRunners(String.format("Runners%s", i));
			as.setRunTime(String.format("RunTime%s", i));
			as.setStartTime(dc.addTimeToDate(new Date(), 0, 0, 0, i, 0, 0, 0));
			agdqSchedules.add(as);
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void AddItems_ValidAgdqSchedule_ItemsAdded() {
		AgdqScheduleContainer agdqScheduleContainer = new AgdqScheduleContainer();
		agdqScheduleContainer.addItems(agdqSchedules);
		assertEquals(itemsAdded, agdqScheduleContainer.size());
	}

	@Test
	public void AddItems_EmptyAgdqSchedule_EmptyContainer() {
		AgdqScheduleContainer agdqScheduleContainer = new AgdqScheduleContainer();
		List<AgdqSchedule> empty = new ArrayList<AgdqSchedule>();
		agdqScheduleContainer.addItems(empty);
		assertEquals(0, agdqScheduleContainer.size());
	}

	@Test
	public void AddItems_Null_EmptyContainer() {
		AgdqScheduleContainer agdqScheduleContainer = new AgdqScheduleContainer();
		agdqScheduleContainer.addItems(null);
		assertEquals(0, agdqScheduleContainer.size());
	}
}
