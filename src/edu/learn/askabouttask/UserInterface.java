package edu.learn.askabouttask;

import edu.learn.askabouttask.interaction.ConsoleInterface;
import edu.learn.askabouttask.interaction.JournalInterface;

/**
 * Класс обеспчивающий взаимодействие с пользователем.
 *
 */
public class UserInterface extends Object{
	
	/**
	 * @see JournalInterface
	 */
	JournalInterface journalInterface;
	
	/**
	 * Запуск приожения происходит с этой точки, 
	 */
	public static void main(String[] args) {
		System.out.println("Hello!");
		UserInterface userInterface = new UserInterface();
		userInterface.start();
	}
	
	/**
	 * Метод обеспечивающий, либо создание нового планировщика, 
	 * либо загрузку существующего из XML файла
	 * @see JournalInterface
	 */
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
			journalInterface = new JournalInterface();
			journalInterface.openJournal();
			this.choiceOfAction();
			break;
		case 3:
			return;
		}
	}
		
	/**
	 * Метод предназначен для работы с Журналом
	 * @see JournalInterface
	 */
	void choiceOfAction() {
		while (true) {
			System.out.println("Выберите что требуется сделать");
			System.out.println("1. Вывести список задач");
			System.out.println("2. Добавить задачу");
			System.out.println("3. Удалить задачу");
			System.out.println("4. Посмотреть информацию о планировщике");
			System.out.println("5. Сохранить планировщик");
			System.out.println("6. Выход");
			int choice = ConsoleInterface.getInt(1 ,6);
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
				journalInterface.save();
				break;
			case 6:
				journalInterface.exit();
				return;
			}
		}
	}

}