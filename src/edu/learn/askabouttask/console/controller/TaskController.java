package edu.learn.askabouttask.console.controller;

import java.util.Date;

import edu.learn.askabouttask.console.view.TaskView;
import edu.learn.askabouttask.entity.Task;

public class TaskController {

	TaskView view = new TaskView();

	public Task createTask() {
		view.enterTaskName();
		String name = ConsoleHelper.getString();
		view.enterTaskDescription();
		String description = ConsoleHelper.getString();
		view.enterTaskTime();
		Date minderTime = ConsoleHelper.getDate();
		view.enterTaskContacts();
		String contacts = ConsoleHelper.getString();
		return new Task(name, description, minderTime, contacts);
	}

	public void showTask(Task task) {
		// view лучше реализовать так что бы оставалась возможность
		// менять формат вывода, в том числе для даты (не передавать ее
		// отформатированной)
		view.showTask(task.getName(), task.getDescription(),
				ConsoleHelper.formater.format(task.getMinderTime()),
				task.getContacts());
	}

}
