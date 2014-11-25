package edu.learn.askabouttask.notifications;

import edu.learn.askabouttask.console.controller.MainAction;
import edu.learn.askabouttask.console.controller.StartAction;

public interface NotificationSystem {

	public abstract void notifyStartAction(StartAction action);

	public abstract void notifyMainAction(MainAction action, Object arg);

}