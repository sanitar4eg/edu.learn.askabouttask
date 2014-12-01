package edu.learn.askabouttask.console.controller;

import java.io.File;
import java.util.Collection;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import edu.learn.askabouttask.addition.JAXBParser;
import edu.learn.askabouttask.addition.Parser;
import edu.learn.askabouttask.console.view.JournalView;
import edu.learn.askabouttask.entity.Journal;
import edu.learn.askabouttask.entity.Task;
import edu.learn.askabouttask.notifications.NotificationAggregator;
import edu.learn.askabouttask.notifications.NotifySystem;

/**
 * Класс обеспчивающий взаимодействие с пользователем.
 * 
 */
public class JournalController {

	private static final Logger LOGGER = Logger
			.getLogger(JournalController.class);

	private JournalView view = new JournalView(); // controller

	private TaskController taskController = new TaskController();

	/**
	 * Данный объект может быть сохранен в XML файл, а так же загружен из него.
	 * 
	 * @see Journal
	 * @serialField
	 */
	private Journal journal; // model

	private Collection<NotifySystem> notifySystems;

	public JournalController(Collection<NotifySystem> inNotifySystems) {
		this.notifySystems = inNotifySystems;
	}

	private NotificationAggregator getNSAggregator() {
		return NotificationAggregator.getAggregator(notifySystems);
	}

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
			while ((choice = ConsoleHelper
					.getInt(1, StartAction.values().length)) == null) {
				view.printWrongInput();
			}
			StartAction selectedAction = allActions[choice - 1];
			getNSAggregator().notifyStartAction(selectedAction);

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
				exit();
				return;
			default:
				view.printWrongInput();
				continue;
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
				return;
			default:
				view.printWrongInput();
				continue;
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

			getNSAggregator().notifyMainAction(MainAction.ADD_TASK,
					journal.getTasks());

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
		journal.addTask(task);
		getNSAggregator().notifyMainAction(MainAction.ADD_TASK, task);
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
			getNSAggregator().notifyMainAction(MainAction.REMOVE_TASK, target);
			view.printRemoveSuccesfull();
		} else {
			view.printRemoveFailed();
		}
	}

	private void showSheduledTasks() {
		for (Task task : journal.getTasks()) {
			if (task.isActive()) {
				taskController.showTask(task);
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
		notifySystems.clear();
	}
}
