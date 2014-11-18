package edu.learn.askabouttask.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * Класс представляющий модель Журнала
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = { "name", "tasksAsArray" }, name = "journal")
@XmlSeeAlso(Task.class)
public class Journal {

	protected Journal() {
	}

	public Journal(String name) {
		this.setName(name);
		setTasks(new HashMap<String, Task>());
	}

	private String name;

	private Map<String, Task> tasks;

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	@XmlTransient
	public final Collection<Task> getTasks() {
		if (tasks == null) {
			return null;
		} else {
			return Collections.unmodifiableCollection(tasks.values());
		}
	}

	private void setTasks(Map<String, Task> tasks) {
		this.tasks = tasks;
	}

	// for JAXB only
	@XmlElementWrapper(name = "tasks")
	@XmlElement(name = "task", type = ArrayList.class)
	private Collection<Task> getTasksAsArray() {
		return tasks.values();
	}

	// for JAXB only
	private void setTasksAsArray(Collection<Task> tasks) {
		if (this.tasks == null) {
			this.tasks = new HashMap<String, Task>();
		} else {
			this.tasks.clear();
		}
		for (Task t : tasks) {
			this.tasks.put(t.getName(), t);
		}
	}

	public int getCount() {
		return tasks.size();
	}

	public boolean isEmpty() {
		return tasks.isEmpty();
	}

	public boolean deleteTask(String name) {
		if (tasks.containsKey(name)) {
			tasks.remove(name);
			return true;
		} else {
			return false;
		}
	}

	public void cancelShedules() {
		for (Task task : tasks.values()) {
			task.cancelShedule();
		}
	}

	public void addTask(Task task/* ... task properties .. */) {
		tasks.put(task.getName(), task);
		// task.setShedule();
		// 1 create task, set parameters
		// 2 add to map
		// 3 run task.setSchedule() / setReminder
	}

}
