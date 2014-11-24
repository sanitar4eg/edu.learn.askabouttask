package edu.learn.askabouttask.console.controller;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import edu.learn.askabouttask.addition.JAXBParser;
import edu.learn.askabouttask.addition.Parser;
import edu.learn.askabouttask.console.view.JournalView;
import edu.learn.askabouttask.entity.Journal;
import edu.learn.askabouttask.entity.Task;
import edu.learn.askabouttask.notifications.NotificationSystem;
import edu.learn.askabouttask.notifications.Reminiscentable;

/**
 * Класс обеспчивающий взаимодействие с пользователем.
 * 
 */
public class JournalController {
	
	private static final Logger LOGGER = 
			Logger.getLogger(JournalController.class);

	private JournalView view = new JournalView(); // controller

	private TaskController taskController = new TaskController();

	private Collection<Reminiscentable> reminders = new LinkedList<>();

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
		while (true) {
			view.printStartMenu();
	
			StartAction[] allActions = StartAction.values();
			Integer choice = null;
			while ((choice = ConsoleHelper.getInt(
					1, StartAction.values().length)) == null) {
				view.printWrongInput();
			}
			StartAction selectedAction = allActions[choice - 1];
	
			switch (selectedAction) {
			case CREATE_JOURNAL:
				createJournal();
				choiceOfAction();
				break;
			case OPEN_JOURNAL:
				if (openJournal()) {
					choiceOfAction();
				} else {
					view.printErrorOpenJournal();
				}
				break;
			case EXIT:
				return;
			default:
				view.printWrongInput();
			}
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
			while ((choice = ConsoleHelper
					.getInt(1, MainAction.values().length)) == null) {
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
			case SHOW_SHEDULED_TASKS:
				showSheduledTasks();
				break;
			case SAVE_JOURNAL:
				save();
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
	 * 
	 * @return true if successful open
	 */
	public boolean openJournal() {
		Parser parser = new JAXBParser();
		try {
			journal = (Journal) parser.getObject(Journal.class, new File(
					"jaxb.xml"));
			for (Task task : journal.getTasks()) {
				if (NotificationSystem.isAvailableForMonitoring(task)) {
					Reminiscentable reminder = new NotificationSystem(task);
					reminder.setShedule();
					reminders.add(reminder);
				}
			}
			view.printJournalInfo(journal.getName(), journal.getCount());
			return true;
		} catch (Exception e) {
			LOGGER.error("Dont opened Journal", e);
			return false;
		}
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
		Task task = taskController.createTask();
		if (NotificationSystem.isAvailableForMonitoring(task)) {
			Reminiscentable reminder = new NotificationSystem(task);
			reminder.setShedule();
			reminders.add(reminder);
		}
		journal.addTask(task);
	}

	/**
	 * Метод удаляет задачу из журнала.
	 * 
	 * @see Journal
	 */
	public void deleteTask() {
		view.printRequestForTaskName();
		String name = null;
		Task target;
		while ((name = ConsoleHelper.getString()) == null) {
			view.printWrongInput();
		}
		if ((target = journal.deleteTask(name)) != null) {
			reminders.remove(target);
			view.printRemoveSuccesfull();
		} else {
			view.printRemoveFailed();
		}
	}

	private void showSheduledTasks() {
		if (reminders.isEmpty()) {
			view.printNoShedulledTasks();
		} else {
			for (Iterator<Reminiscentable> i = reminders.iterator();
					i.hasNext();) {
				Task task = i.next().getTask();
				if (NotificationSystem.isAvailableForMonitoring(task)) {
					taskController.showTask(task);
				} else {
					i.remove();
				}
			}
		}
	}

	/**
	 * Сохраняет объект в файл.
	 * 
	 * @throws JAXBException
	 *             JAXBException
	 * @return true if successful open
	 */
	public boolean save() {
		try {
			Parser parser = new JAXBParser();
			parser.saveObject(journal, new File("jaxb.xml"));
			return true;
		} catch (JAXBException e) {
			LOGGER.error("Dont saved journal", e);
			return false;
		}
	}

	/**
	 * Performs shutdown.
	 * 
	 * @see Journal
	 */
	public void exit() {
		for (Reminiscentable reminder : reminders) {
			reminder.cancelShedule();
		}
	}

}
