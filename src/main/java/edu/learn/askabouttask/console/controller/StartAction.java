package edu.learn.askabouttask.console.controller;

public enum StartAction {
	CREATE_JOURNAL("Создать планировщик задач"),
	OPEN_JOURNAL("Открыть планировщик задач"),
	EXIT("Выход");

	private String description;

	StartAction(String inDescription) {
		this.description = inDescription;
	}

	public String getDescription() {
		return description;
	}
}
