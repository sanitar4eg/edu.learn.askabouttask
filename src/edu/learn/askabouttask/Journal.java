package edu.learn.askabouttask;

import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"name", "tasks", "count"}, name = "journal")
@XmlRootElement
public class Journal {
	
	protected Journal () {}
	
	public Journal (String name) {
		this.setName(name);
		setTasks(new ArrayList<Task>());
		setCount(0);
	}
	
	private String name;
	
	private ArrayList<Task> tasks;
	
	private int count;

	private String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	private ArrayList<Task> getTasks() {
		return tasks;
	}

	private void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

	private int getCount() {
		return count;
	}

	private void setCount(int count) {
		this.count = count;
	}
	
	public void addTask (String name, String description, Date minderTime,
			String contacts) {
		getTasks().add(new Task(name, description, minderTime, contacts));
		setCount(getCount() + 1);
	}
	
	public boolean deleteTask (String name) {
		if (isEmpty()){
			return false;
		} else {
			int target = -1;
			for (Task task : getTasks()) {
				if (task.getName().equals(name)) 
					target = getTasks().indexOf(task);
			}
			if (target > -1) {
				getTasks().remove(target);
				setCount(getCount() - 1);
				return true;
			}
		}
		return false;
	}
	
	public void viewTasks () {
		if (isEmpty()) {
			System.out.println("Журнал пуст");
		} else {
			for (Task task : getTasks()) {
				System.out.println("Задача №" + (getTasks().indexOf(task)+1));
				task.viewTask();
			}
		}
	}
	
	public boolean isEmpty () {
		return (getCount() == 0) ? true : false;
	}
	
	public void viewInfo() {
		System.out.println("Планировщик " + getName() + 
				" содержит следующее количество задач: " + getCount());
	}

}
