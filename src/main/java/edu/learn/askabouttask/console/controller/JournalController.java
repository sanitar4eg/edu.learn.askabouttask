package edu.learn.askabouttask.console.controller;

import java.io.File;

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
public class JournalController {

	private JournalView view = new JournalView(); // controller

	private TaskController taskController = new TaskController();

	/**
	 * Данный объект может быть сохранен в XML файл, а так же загружен из него.
	 * 
	 * @see Journal
	 * @serialField
	 */
	private Journal journal; // model

	/**
	 * Метод обеспечивающий, либо создание нового планировщика, либо загрузку
	 * существующего из XML файла.
	 * 
	 * @see JournalInterface
	 */
	public void start() {
		view.printStartMenu();

		StartAction[] allActions = StartAction.values();
		Integer choice = null;
		while ((choice = ConsoleHelper.
				getInt(1, StartAction.values().length)) == null) {
			view.printWrongInput();
		}
		StartAction selectedAction = allActions[choice - 1];

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
		default:
			view.printWrongInput();
		}
	}

	/**
	 * Метод предназначен для работы с Журналом.
	 * 
	 * @see JournalInterface
	 */
	void choiceOfAction() {
		while (true) {
			view.printMainMenu();

			Integer choice = null;
			while ((choice = ConsoleHelper.getInt(1, 
					MainAction.values().length)) == null) {
				view.printWrongInput();
			}
			MainAction[] allActions = MainAction.values();
			MainAction selectedAction = allActions[choice - 1];

			switch (selectedAction) {
			case TASK_LIST:
				viewTasks();
				break;
			case ADD_TASK:
				addTask();
				break;
			case REMOVE_TASK:
				deleteTask();
				break;
			case SHOW_JOURNAL_INFO:
				viewInfo();
				break;
			case SAVE_JOURNAL:
				try {
					save();
				} catch (JAXBException e) {
					e.printStackTrace();
				}
				break;
			case EXIT:
				exit();
				return;
			default:
				view.printWrongInput();
			}
		}
	}

	/**
	 * Метод создает новый журнал и помещает в поле current.
	 */
	public void createJournal() {
		view.printRequestForNameJournal();
		String name = null;
		while ((name = ConsoleHelper.getString()) == null) {
			view.printWrongInput();
		}
		journal = new Journal(name);
		view.printJournalInfo(name, journal.getCount());
	}

	/**
	 * Открывает объект из файла.
	 */
	public void openJournal() {
		Parser parser = new JAXBParser();
		try {
			journal = (Journal) parser
					.getObject(Journal.class, new File("jaxb.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		view.printJournalInfo(journal.getName(), journal.getCount());
	}

	/**
	 * Show brief information about journal.
	 * 
	 * @see Journal
	 */
	public void viewInfo() {
		if (journal.isEmpty()) {
			view.printEmptyJournal(journal.getName());
		} else {
			view.printJournalInfo(journal.getName(), journal.getCount());
		}
	}

	public void viewTasks() {
		if (journal.isEmpty()) {
			view.printEmptyJournal(journal.getName());
		} else {
			int number = 1;
			for (Task task : journal.getTasks()) {
				view.printNumberOfTasks(number++);
				taskController.showTask(task);
			}
		}

	}

	/**
	 * Метод добавляет новую задачу в журнал.
	 * 
	 * @see Journal
	 */
	public void addTask() {
		journal.addTask(taskController.createTask());
	}

	/**
	 * Метод удаляет задачу из журнала.
	 * 
	 * @see Journal
	 */
	public void deleteTask() {
		view.printRequestForTaskName();
		String name = null;
		while ((name = ConsoleHelper.getString()) == null) {
			view.printWrongInput();
		}
		if (journal.deleteTask(name)) {
			view.printRemoveSuccesfull();
		} else {
			view.printRemoveFailed();
		}
	}

	/**
	 * Сохраняет объект в файл.
	 * 
	 * @throws JAXBException JAXBException
	 */
	public void save() throws JAXBException {
		Parser parser = new JAXBParser();
		parser.saveObject(journal, new File("jaxb.xml"));
	}

	/**
	 * Performs shutdown.
	 * 
	 * @see Journal
	 */
	public void exit() {
		journal.cancelShedules();
	}

}
