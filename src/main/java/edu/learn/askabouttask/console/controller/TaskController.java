package edu.learn.askabouttask.console.controller;

import java.util.Date;

import edu.learn.askabouttask.console.view.TaskView;
import edu.learn.askabouttask.entity.Task;

public class TaskController {

	private TaskView view = new TaskView();

	public Task createTask() {
		view.printRequestForTaskName();
		String name = null;
		while ((name = ConsoleHelper.getString()) == null) {
			view.printWrongInput();
		}
		view.printRequestForTaskDescription();
		String description = null;
		while ((description = ConsoleHelper.getString()) == null) {
			view.printWrongInput();
		}
		view.printRequestForTaskTime();
		Date minderTime = null;
		while ((minderTime = ConsoleHelper.getDate()) == null) {
			view.printWrongInput();
		}
		view.printRequestForTaskContacts();
		String contacts = null;
		while ((contacts = ConsoleHelper.getString()) == null) {
			view.printWrongInput();
		}
		view.printRequestForReminderApp();
		String reminderApp = null;
		while ((reminderApp = ConsoleHelper.getString()) == null) {
			view.printWrongInput();
		}
		if (reminderApp.compareTo("0") == 0) {
			return new Task(name, description, minderTime, contacts);
		} else {
			return new Task(name, description, minderTime, contacts, reminderApp);
		}
	}

	public void showTask(Task task) {
		view.showTaskInfo(task.getName(), task.getDescription(),
				task.getMinderTime(), task.getContacts());
	}

}
