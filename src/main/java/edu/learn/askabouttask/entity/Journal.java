package edu.learn.askabouttask.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * Класс представляющий модель Журнала.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = { "name", "tasksAsArray" }, name = "journal")
@XmlSeeAlso(Task.class)
public class Journal {

	protected Journal() {
	}

	public Journal(String inName) {
		setName(inName);
		setTasks(new HashMap<String, Task>());
	}

	private String name;

	private Map<String, Task> tasks;

	public final String getName() {
		return name;
	}

	private void setName(String inName) {
		this.name = inName;
	}

	public final int getCount() {
		return tasks.size();
	}

	public final boolean isEmpty() {
		return tasks.isEmpty();
	}

	private void setTasks(Map<String, Task> inTasks) {
		this.tasks = inTasks;
	}

	@XmlTransient
	public final Collection<Task> getTasks() {
		if (tasks == null) {
			return null;
		} else {
			return Collections.unmodifiableCollection(tasks.values());
		}
	}

	// for JAXB only
	@XmlElementWrapper(name = "tasks")
	@XmlElement(name = "task")
	// , type = ArrayList.class
	private Collection<Task> getTasksAsArray() {
		if (tasks != null) {
			return tasks.values();
		} else {
			return null;
		}
	}

	// for JAXB only
	private void setTasksAsArray(Collection<Task> inTasks) {
		if (this.tasks == null) {
			this.tasks = new HashMap<String, Task>();
		} else {
			this.tasks.clear();
		}
		for (Task t : inTasks) {
			this.tasks.put(t.getName(), t);
		}
	}

	// TODO: Решил проблему с установкой shedule после анмаршалинга,
	// Не уверен что правильно
	private void afterUnmarshal(final Unmarshaller umrsh, final Object parent) {
		if (this.tasks != null) {
			for (Task task : this.tasks.values()) {
				task.setShedule();
			}
		}
	}

	public final boolean deleteTask(String inName) {
		if (tasks.containsKey(inName)) {
			tasks.remove(inName);
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

	public void addTask(Task task) {
		task.setShedule();
		tasks.put(task.getName(), task);
	}

}
