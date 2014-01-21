package com.example.parseurlsource.container;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.parseurlsource.domainmodel.AgdqSchedule;
import com.example.parseurlsource.utils.DateConverter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContextTest.xml" })
public class AgdqScheduleContainerTest {

	// Naming convention: [UnitOfWork_StateUnderTest_ExpectedBehavior]

	@Inject
	private AgdqScheduleContainer agdqScheduleContainer;

	private List<AgdqSchedule> agdqSchedules;

	private static final int itemsAdded = 10;

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
		agdqScheduleContainer.addItems(agdqSchedules);
		assertEquals(itemsAdded, agdqScheduleContainer.size());
	}

	@Test
	public void AddItems_EmptyAgdqSchedule_EmptyContainer() {
		List<AgdqSchedule> empty = new ArrayList<AgdqSchedule>();
		agdqScheduleContainer.addItems(empty);
		assertEquals(0, agdqScheduleContainer.size());
	}

	@Test
	public void AddItems_Null_EmptyContainer() {
		agdqScheduleContainer.addItems(null);
		assertEquals(0, agdqScheduleContainer.size());
	}
}
