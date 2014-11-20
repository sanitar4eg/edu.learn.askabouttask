package edu.learn.askabouttask.console.controller;

import java.util.Date;

import edu.learn.askabouttask.console.view.TaskView;
import edu.learn.askabouttask.entity.Task;

public class TaskController {

	TaskView view = new TaskView();

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
		return new Task(name, description, minderTime, contacts);
	}

	public void showTask(Task task) {
		view.showTaskInfo(task.getName(), task.getDescription(),
				task.getMinderTime(), task.getContacts());
	}

}
