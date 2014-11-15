package edu.learn.askabouttask.console.controller;

public enum MainAction {
	TASK_LIST("Вывести список задач"),
	ADD_TASK("Добавить задачу"),
	REMOVE_TASK("Удалить задачу");

	private String description;

	MainAction(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
