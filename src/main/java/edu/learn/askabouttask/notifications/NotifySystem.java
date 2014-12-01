package edu.learn.askabouttask.notifications;

import edu.learn.askabouttask.console.controller.MainAction;
import edu.learn.askabouttask.console.controller.StartAction;

public interface NotifySystem {

	void notifyStartAction(StartAction action);

	void notifyMainAction(MainAction action, Object arg);

}