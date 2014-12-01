package edu.learn.askabouttask.notifications;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.apache.log4j.Logger;

import edu.learn.askabouttask.console.controller.MainAction;
import edu.learn.askabouttask.console.controller.StartAction;
import edu.learn.askabouttask.entity.Task;

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
			LOGGER.trace("User left from Start Menu \n End of session");
			break;
		default:

		}

	}

	@Override
	public void notifyMainAction(MainAction action, Object arg) {
		String valueOfArg = getValue(arg);
		switch (action) {
		case TASK_LIST:
			LOGGER.trace("User watched a task list in Main Menu " + valueOfArg);
			break;
		case ADD_TASK:
			LOGGER.trace("User added task in Main Menu " + valueOfArg);
			break;
		case REMOVE_TASK:
			LOGGER.trace("User deleted a task in Main Menu " + valueOfArg);
			break;
		case SHOW_JOURNAL_INFO:
			LOGGER.trace("User watched information about the journal in Main Menu "
					+ valueOfArg);
			break;
		case SHOW_SHEDULED_TASKS:
			LOGGER.trace("User watched active tasks in Main Menu " + valueOfArg);
			break;
		case SAVE_JOURNAL:
			LOGGER.trace("User saved the journal in Main Menu " + valueOfArg);
			break;
		case EXIT:
			LOGGER.trace("User left from Main Menu " + valueOfArg);
			break;
		default:

		}
	}

	private String getValue(Object arg) {
		if (arg == null) {
			return "arg is null";
		} else if (arg instanceof Collection) {
			return "arg is Collection";
		} else if (arg instanceof Task) {
			return "arg is Task";
		} else {
			return "arg is unknown value";
		}
	}
}
