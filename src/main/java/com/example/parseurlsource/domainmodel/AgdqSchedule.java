package com.example.parseurlsource.domainmodel;

import java.util.Date;

/**
 * A simple POJO of AgqdSchedule entities.
 * 
 * @author Roger
 * 
 */
public class AgdqSchedule {

	private Date startTime;
	private String game;
	private String runners;
	private String runTime;
	private String details;
	private String commentators;
	private String prizes;

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public String getRunners() {
		return runners;
	}

	public void setRunners(String runners) {
		this.runners = runners;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getCommentators() {
		return commentators;
	}

	public void setCommentators(String commentators) {
		this.commentators = commentators;
	}

	public String getPrizes() {
		return prizes;
	}

	public void setPrizes(String prizes) {
		this.prizes = prizes;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getRunTime() {
		return runTime;
	}

	public void setRunTime(String runtime) {
		this.runTime = runtime;
	}

}
