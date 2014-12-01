package edu.learn.askabouttask.notifications;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import edu.learn.askabouttask.console.controller.MainAction;
import edu.learn.askabouttask.console.controller.StartAction;
import edu.learn.askabouttask.entity.Task;

public class TimerTaskNotification implements NotifySystem {

	static final Logger LOGGER = Logger.getLogger(TimerTaskNotification.class);

	public TimerTaskNotification() {
	}

	// TODO: Сделал таймер демоном
	// теперь не нужно вручную завершать его работу
	private Timer timer = new Timer(true);

	// TODO: Пришлось задействовать коллекцию для удаления задачи из журнала
	private Map<Date, TimerTask> sheduledTimerTasks = new HashMap<>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.learn.askabouttask.notifications.NotificationSystem2#notifyStartAction
	 * (edu.learn.askabouttask.console.controller.StartAction)
	 */
	@Override
	public void notifyStartAction(StartAction action) {
		switch (action) {
		default:
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.learn.askabouttask.notifications.NotificationSystem2#notifyMainAction
	 * (edu.learn.askabouttask.console.controller.MainAction, java.lang.Object)
	 */
	@Override
	public void notifyMainAction(MainAction action, Object arg) {
		LOGGER.info("notifyMainAction:" + arg);
		Collection<Task> tasks = getChekedList(arg);
		switch (action) {
		case ADD_TASK:
			for (Task task : tasks) {
				if (task.isActive()) {
					RunJarTaskWithReflect runJar = new RunJarTaskWithReflect(
							this, task.getReminderApplication(), task.getName());
					timer.schedule(runJar, task.getMinderTime());
					sheduledTimerTasks.put(task.getMinderTime(), runJar);
				}
			}
			break;
		case REMOVE_TASK:
			for (Task task : tasks) {
				TimerTask target;
				if ((target = sheduledTimerTasks.get(task.getMinderTime())) != null) {
					target.cancel();
					sheduledTimerTasks.remove(task.getMinderTime());
				}
			}
			timer.purge();
			break;
		case EXIT:
			sheduledTimerTasks.clear();
			timer.cancel();
		default:
			break;
		}
	}

	private Collection<Task> getChekedList(Object arg) {
		Collection<Task> tasks = Collections.checkedList(new ArrayList<Task>(),
				Task.class);
		if (arg != null) {
			if (arg instanceof Collection) {
				tasks.addAll((Collection) arg);
			} else if (arg instanceof Task) {
				tasks.add((Task) arg);
			}
		}
		return tasks;
	}
}
