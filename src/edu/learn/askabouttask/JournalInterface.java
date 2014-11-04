package edu.learn.askabouttask;

import java.util.Date;
import java.util.Scanner;

public class JournalInterface {
	
	private Journal current;

	void createJournal() {
		System.out.println("Введите имя планировщика");
		String name = ConsoleInterface.getString();
		current = new Journal(name);
		current.viewInfo();
	}
	
	void addTask() {
		System.out.println("Введите название задачи");
		String name = ConsoleInterface.getString();
		System.out.println("Введите описание");
		String description = ConsoleInterface.getString();
		System.out.println("Введите время для напоминания");
		Date minderTime = ConsoleInterface.getDate();
		System.out.println("Введите контакты");
		String contacts = ConsoleInterface.getString();
		current.addTask(name, description, minderTime, contacts);
	}
	
	void deleteTask() {
		System.out.println("Введите название удаляемой задачи");
		String name = ConsoleInterface.getString();
		if (current.deleteTask(name)) {
			System.out.println("Успешное удаление");
		} else {
			System.out.println("Задача не найдена");
		}		
	}
	
	void viewTasks() {
		current.viewTasks();
	}
	
	
	void viewInfo () {
		current.viewInfo();
	}
}
