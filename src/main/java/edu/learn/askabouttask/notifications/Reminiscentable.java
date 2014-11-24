package edu.learn.askabouttask.notifications;

import edu.learn.askabouttask.entity.Task;

public interface Reminiscentable {

	boolean isActive();

	void setShedule();

	Task getTask();

	void cancelShedule();
}
