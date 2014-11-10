package edu.learn.askabouttask.entity;
import java.util.Date;
import java.util.LinkedList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Класс представляющий Журнал
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"name", "tasks"}, name = "journal")
public class Journal {
	
	protected Journal () {}
	
	public Journal (String name) {
		this.setName(name);
		setTasks(new LinkedList<Task>());
	}
	
	private String name;
	
	private LinkedList<Task> tasks;

	private String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}
	
	@XmlElementWrapper(name = "tasks")
	@XmlElement(name = "task")
	private LinkedList<Task> getTasks() {
		return tasks;
	}

	private void setTasks(LinkedList<Task> tasks) {
		this.tasks = tasks;
	}

	private int getCount() {
		return tasks.size();
	}
	
	public void addTask (String name, String description, Date minderTime,
			String contacts) {
		getTasks().add(new Task(name, description, minderTime, contacts));
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
	
	public void eraseTasks() {
		for (Task task : tasks) {
			task.eraseReminder();
		}
	}

}
