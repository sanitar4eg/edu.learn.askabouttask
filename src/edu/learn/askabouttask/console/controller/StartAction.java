package edu.learn.askabouttask.console.controller;

public enum StartAction {
	CREATE_JOURNAL("Создать планировщи задач"),
	OPEN_JOURNAL(""),
	EXIT("");

	private String description;

	StartAction(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
