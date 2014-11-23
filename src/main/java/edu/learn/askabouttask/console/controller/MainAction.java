package edu.learn.askabouttask.console.controller;

public enum MainAction {
	TASK_LIST("Вывести список задач"),
	ADD_TASK("Добавить задачу"),
	REMOVE_TASK("Удалить задачу"),
	SHOW_JOURNAL_INFO("Посмотреть информацию о планировщике"),
	SAVE_JOURNAL("Сохранить планировщик"),
	EXIT("Выход");

	private String description;

	MainAction(String inDescription) {
		this.description = inDescription;
	}

	public String getDescription() {
		return description;
	}
}
