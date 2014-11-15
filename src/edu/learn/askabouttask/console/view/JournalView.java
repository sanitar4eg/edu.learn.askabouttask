package edu.learn.askabouttask.console.view;

import edu.learn.askabouttask.console.controller.MainAction;

public class JournalView
{

	public void printMainMenu() {
		System.out.println("Выберите что требуется сделать");
		for (MainAction a : MainAction.values()) {
			System.out.println((a.ordinal() + 1) + ". " + a.getDescription());
		}
		System.out.println("4. Посмотреть информацию о планировщике");
		System.out.println("5. Сохранить планировщик");
		System.out.println("6. Выход");
	}

	public void printStartMenu() {
		System.out.println("Hello!");
		System.out.println("Что Вы хотите сделать?");
		System.out.println("1. Создать планировщи задач");
		System.out.println("2. Открыть планировщик задач");
		System.out.println("3. Выход");
	}

	public static void main(String[] args) {
		new JournalView().printMainMenu();
	}
}
