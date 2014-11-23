package edu.learn.askabouttask.console.view;

import java.util.Date;

import edu.learn.askabouttask.console.controller.ConsoleHelper;

public class TaskView {

	private final static String WRONG_INPUT = "Неверные данные, попробуйте еще раз";

	public void printRequestForTaskName() {
		System.out.println("Введите название задачи");
	}

	public void printRequestForTaskDescription() {
		System.out.println("Введите описание");
	}

	public void printRequestForTaskTime() {
		System.out.println("Введите время для напоминания");
		System.out.println("Формат для ввода даты: "
				+ ConsoleHelper.DATE_FORMAT);
	}

	public void printRequestForTaskContacts() {
		System.out.println("Введите контакты");
	}

	public void showTaskInfo(String name, String description, Date time,
			String contacts) {
		System.out.println(name);
		System.out.println("Описание: " + description);
		System.out.println("Время: " + ConsoleHelper.formater.format(time));
		System.out.println("Контакты: " + contacts);
	}

	public void printWrongInput() {
		System.out.println(WRONG_INPUT);
	}

}
