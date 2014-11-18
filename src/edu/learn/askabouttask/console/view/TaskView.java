package edu.learn.askabouttask.console.view;

public class TaskView {

	public void enterTaskName() {
		System.out.println("Введите название задачи");
	}

	public void enterTaskDescription() {
		System.out.println("Введите описание");
	}

	public void enterTaskTime() {
		System.out.println("Введите время для напоминания");
	}

	public void enterTaskContacts() {
		System.out.println("Введите контакты");
	}
	
	public void showTask (String name, String description, String time, String contacts) {
		System.out.println(name);
		System.out.println("Описание: " + description);
		System.out.println("Время: " + time);
		System.out.println("Контакты: " + contacts);
	}
	
	
}