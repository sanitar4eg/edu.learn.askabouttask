package edu.learn.askabouttask.console.controller;

import java.io.File;
import java.util.Date;
import java.util.Map;

import javax.xml.bind.JAXBException;

import edu.learn.askabouttask.addition.JAXBParser;
import edu.learn.askabouttask.addition.Parser;
import edu.learn.askabouttask.console.view.JournalView;
import edu.learn.askabouttask.entity.Journal;
import edu.learn.askabouttask.entity.Task;


/**
 * Класс обеспчивающий взаимодействие с пользователем.
 *
 */
public class JournalController
{
	private JournalView view = new JournalView(); // controller
	
	/**
	 * Данный объект может быть сохранен в XML файл, а так же загружен из него
	 * @see Journal
	 * @serialField 
	 */
	private Journal journal; // model

	/**
	 * Метод обеспечивающий, либо создание нового планировщика, 
	 * либо загрузку существующего из XML файла
	 * @see JournalInterface
	 */
	public void start() {
		view.printStartMenu();

		StartAction[] allActions = StartAction.values();
		int choice = ConsoleHelper.getInt(1, 3);
		StartAction selectedAction = allActions[choice];
		
		switch (selectedAction) {
			case CREATE_JOURNAL:
				createJournal();
				choiceOfAction();
				break;
			case OPEN_JOURNAL:
				openJournal();
				choiceOfAction();
				break;
			case EXIT:
				return;
		}
	}

	/**
	 * Метод предназначен для работы с Журналом
	 * @see JournalInterface
	 */
	void choiceOfAction() {
		while (true) {
			view.printMainMenu();
			
			int choice = ConsoleHelper.getInt(1 ,6);
			switch (choice) {
			case 1:
				taskController.viewTasks();
				break;
			case 2: 
				journal.addTask();
				break;
			case 3:
				taskController.deleteTask();
				break;
			case 4 :
				viewInfo();
				break;
			case 5:
				try {
					save();
				} catch (JAXBException e) {
					e.printStackTrace();
				}
				break;
			case 6:
				exit();
				return;
			}
		}
	}

	/**
	 * Метод создает новый журнал и помещает в поле current
	 */
	public void createJournal() {
		System.out.println("Введите имя планировщика");
		String name = ConsoleHelper.getString();
		journal = new Journal(name);
		journal.viewInfo();
	}

	/**
	 * @see Journal
	 */
	public void viewInfo() {
		journal.viewInfo();
	}

	/**
	 * Сохраняет объект в файл
	 * @throws JAXBException 
	 */
	public void save() throws JAXBException {
		Parser p = new JAXBParser();
		p.saveObject(journal, new File ("jaxb.xml"));
	}

	/**
	 * Открывает объект из файла
	 */
	public void openJournal() {
		Parser p = new JAXBParser();
		try {
			journal = (Journal) p.getObject(Journal.class, new File("jaxb.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see Journal
	 */
	public void exit() {
		journal.eraseTasks();
	}


	/**
	 * Метод добавляет новую задачу в журнал
	 * @see Journal
	 */
	public void addTask() {
		System.out.println("Введите название задачи");
		String name = ConsoleHelper.getString();
		System.out.println("Введите описание");
		String description = ConsoleHelper.getString();
		System.out.println("Введите время для напоминания");
		Date minderTime = ConsoleHelper.getDate();
		System.out.println("Введите контакты");
		String contacts = ConsoleHelper.getString();
		journal.addTask(name, description, minderTime, contacts);
	}

	/**
	 * Метод удаляет задачу из журнала 
	 * @see Journal
	 */
	public void deleteTask(Map<String, Task> allTasks) {
		System.out.println("Введите название удаляемой задачи");
		view.printSelectTaskMenu();

		String name = ConsoleHelper.getString();
		if (allTasks.containsKey(name)) {
			allTasks.remove(name);
			view.printRemoveSuccesfull();
			System.out.println("Успешное удаление");
		} else {
			System.out.println("Задача не найдена");
		}		
	}

	/**
	 * @see Journal
	 */
	public void viewTasks() {
		journal.viewTasks();
	}

}
