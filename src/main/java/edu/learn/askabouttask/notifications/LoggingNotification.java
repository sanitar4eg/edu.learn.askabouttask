package edu.learn.askabouttask.notifications;

import org.apache.log4j.Logger;

import edu.learn.askabouttask.console.controller.MainAction;
import edu.learn.askabouttask.console.controller.StartAction;

public class LoggingNotification implements NotifySystem {

	private static final Logger LOGGER = Logger.getLogger("UserActions");

	@Override
	public void notifyStartAction(StartAction action) {
		switch (action) {
		case CREATE_JOURNAL:
			LOGGER.trace("User created the journal in Start Menu");
			break;
		case OPEN_JOURNAL:
			LOGGER.trace("User opened an existing journal in Start Menu");
			break;
		case EXIT:
			LOGGER.trace("User left from Start Menu");
			break;
		default:

		}

	}

	@Override
	public void notifyMainAction(MainAction action, Object arg) {
		switch (action) {
		case TASK_LIST:
			LOGGER.trace("User watched a task list in Main Menu");
			break;
		case ADD_TASK:
			LOGGER.trace("User added task in Main Menu");
			break;
		case REMOVE_TASK:
			LOGGER.trace("User deleted a task in Main Menu");
			break;
		case SHOW_JOURNAL_INFO:
			LOGGER.trace("User watched information about the journal in Main Menu");
			break;
		case SHOW_SHEDULED_TASKS:
			LOGGER.info("User watched active tasks in Main Menu");
			break;
		case SAVE_JOURNAL:
			LOGGER.trace("User saved the journal in Main Menu");
			break;
		case EXIT:
			LOGGER.trace("User left from Main Menu");
			break;
		default:

		}
	}
}
