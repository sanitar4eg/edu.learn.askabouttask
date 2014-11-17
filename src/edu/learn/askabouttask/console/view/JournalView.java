package edu.learn.askabouttask.console.view;

import edu.learn.askabouttask.console.controller.MainAction;
import edu.learn.askabouttask.console.controller.StartAction;

public class JournalView
{

	public void printMainMenu() {
		System.out.println("Выберите что требуется сделать");
		for (MainAction text : MainAction.values()) {
			System.out.println((text.ordinal() + 1) + ". " + text.getDescription());
		}
	}

	public void printStartMenu() {
		System.out.println("Hello!");
		System.out.println("Что Вы хотите сделать?");
		for (StartAction text : StartAction.values()) {
			System.out.println((text.ordinal() + 1) + ". " + text.getDescription());
		}
	}
	
	public void enterNameJournal() {
		System.out.println("Введите имя планировщика");
	}
	
	public void showEmptyJournal(String name) {
		System.out.println("Планировщик " + name + " пуст");
	}
	
	public void showJournalInfo(String name, int count) {
		System.out.println("Планировщик " + name + 
				" содержит: " + count);
	}

	public void printSelectTaskName() {
		System.out.println("Введите название задачи");
	}

	public void printRemoveSuccesfull() {
		System.out.println("Успешное удаление");		
	}

	public void printRemoveFailed() {
		System.out.println("Задача не найдена");		
	}

	public void printNumberOfTasks(int i) {
		System.out.println("Задача №" + i); 		
	}
	
}
