package edu.learn.askabouttask;

import java.util.Scanner;

public class UserInterface extends Object{
	
	JournalInterface journalInterface;
	
	public static void main(String[] args) {
		System.out.println("Hello!");
		UserInterface userInterface = new UserInterface();
		userInterface.start();
	}
	
	void start() {
		System.out.println("Что Вы хотите сделать?");
		System.out.println("1. Создать планировщи задач");
		System.out.println("2. Открыть планировщик задач");
		System.out.println("3. Выход");
		int choice = ConsoleInterface.getInt(1,3);
		switch (choice) {
		case 1:
			journalInterface = new JournalInterface();
			journalInterface.createJournal();
			this.choiceOfAction();
			break;
		case 2: 
			break;
		case 3:
			return;
		}
	}
		
	void choiceOfAction() {
		while (true) {
			System.out.println("Выберите что требуется сделать");
			System.out.println("1. Вывести список задач");
			System.out.println("2. Добавить задачу");
			System.out.println("3. Удалить задачу");
			System.out.println("4. Посмотреть информацию о планировщике");
			System.out.println("5. Выход");
			int choice = ConsoleInterface.getInt(1 ,5);
			switch (choice) {
			case 1:
				journalInterface.viewTasks();
				break;
			case 2: 
				journalInterface.addTask();
				break;
			case 3:
				journalInterface.deleteTask();
				break;
			case 4 :
				journalInterface.viewInfo();
				break;
			case 5:
				return;
			}
		}
	}

}
