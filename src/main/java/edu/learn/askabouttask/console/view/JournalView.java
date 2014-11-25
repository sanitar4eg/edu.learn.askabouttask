package edu.learn.askabouttask.console.view;

import edu.learn.askabouttask.console.controller.MainAction;
import edu.learn.askabouttask.console.controller.StartAction;

public class JournalView {

	private static final String WRONG_INPUT = 
			"Неверные данные, попробуйте еще раз";

	public void printMainMenu() {
		System.out.println("Выберите что требуется сделать");
		for (MainAction text : MainAction.values()) {
			System.out.println((text.ordinal() + 1) + ". "
					+ text.getDescription());
		}
	}

	public void printStartMenu() {
		System.out.println("Hello!");
		System.out.println("Что Вы хотите сделать?");
		for (StartAction text : StartAction.values()) {
			System.out.println((text.ordinal() + 1) + ". "
					+ text.getDescription());
		}
	}

	public void printRequestForNameJournal() {
		System.out.println("Введите имя планировщика");
	}

	public void printEmptyJournal(String name) {
		System.out.println("Планировщик " + name + " пуст");
	}

	public void printJournalInfo(String name, int count) {
		System.out.println("Планировщик " + name
				+ " содержит cледующее количество задач: " + count);
	}

	public void printRequestForTaskName() {
		System.out.println("Введите название задачи");
	}

	public void printRemoveSuccesfull() {
		System.out.println("Успешное удаление");
	}

	public void printRemoveFailed() {
		System.out.println("Задача не найдена");
	}

	public void printNumberOfTasks(int number) {
		System.out.println("Задача №" + number);
	}

	public void printWrongInput() {
		System.out.println(WRONG_INPUT);
	}

	public void printErrorOpenJournal() {
		System.out.println("Неудалось открыть журнал");
	}

	public void printNoShedulledTasks() {
		System.out.println("В вашем журнале нет предстоящих задач");
	}

}
