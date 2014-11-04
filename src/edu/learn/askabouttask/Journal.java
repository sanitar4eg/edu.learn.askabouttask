package edu.learn.askabouttask;

import java.util.ArrayList;
import java.util.Date;

public class Journal {
	
	protected Journal () {}
	
	public Journal (String name) {
		this.name = name;
		tasks = new ArrayList<>();
		count = 0;
	}
	
	private String name;
	
	private ArrayList<Task> tasks;
	
	private int count;
	
	public void addTask (String name, String description, Date minderTime,
			String contacts) {
		tasks.add(new Task(name, description, minderTime, contacts));
		count++;
	}
	
	public boolean deleteTask (String name) {
		if (isEmpty()){
			return false;
		} else {
			int target = -1;
			for (Task task : tasks) {
				if (task.getName().equals(name)) 
					target = tasks.indexOf(task);
			}
			if (target > -1) {
				tasks.remove(target);
				count--;
				return true;
			}
		}
		return false;
	}
	
	public void viewTasks () {
		if (isEmpty()) {
			System.out.println("Журнал пуст");
		} else {
			for (Task task : tasks) {
				System.out.println("Задача №" + (tasks.indexOf(task)+1));
				task.viewTask();
			}
		}
	}
	
	public boolean isEmpty () {
		return (count == 0) ? true : false;
	}
	
	public void viewInfo() {
		System.out.println("Планировщик " + name + 
				" содержит следующее количество задач: " + count);
	}

}
