package edu.learn.askabouttask.notifications;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;

import org.apache.log4j.Logger;

import edu.learn.askabouttask.console.controller.MainAction;
import edu.learn.askabouttask.console.controller.StartAction;
import edu.learn.askabouttask.entity.Task;

public class TimerTaskNotification implements NotificationSystem {
	
	static final Logger LOGGER = 
			Logger.getLogger(TimerTaskNotification.class);

	public TimerTaskNotification() {
		// конструктор по умолчанию для использования Class.forName(...).newInstance
	}

	private Timer timer = new Timer();
		
//	public static boolean isAvailableForMonitoring(Task task) {
//		if (task != null) {
//			// TODO move to mode class Task, method - isActive
//			if (task.getMinderTime().getTime() > new Date().getTime()) {
//				return true;
//			}
//		}
//		return false;
//	}

	/* (non-Javadoc)
	 * @see edu.learn.askabouttask.notifications.NotificationSystem2#notifyStartAction(edu.learn.askabouttask.console.controller.StartAction)
	 */
	@Override
	public void notifyStartAction(StartAction action) {
		if (StartAction.EXIT.equals(action)) {
			timer.cancel();
		}
	}

	/* (non-Javadoc)
	 * @see edu.learn.askabouttask.notifications.NotificationSystem2#notifyMainAction(edu.learn.askabouttask.console.controller.MainAction, java.lang.Object)
	 */
	@Override
	public void notifyMainAction(MainAction action, Object arg) {
		LOGGER.info("notifyMainAction:" + arg);

		if (MainAction.ADD_TASK.equals(action)) {
			Collection<Task> tasks = Collections.checkedList(new ArrayList<Task>(), Task.class);
			if (arg instanceof Collection) {
				tasks.addAll((Collection) arg);
			} else if (arg instanceof Task) {
				tasks.add((Task) arg);
			}
			for (Task task : tasks) {
				// TODO ?
				if (task.isActive()) {
					RunJarTask runJar = new RunJarTask(this, task.getReminderApplication(), task.getName());
					timer.schedule(runJar, task.getMinderTime());
				}
			}
		}
	}
}
