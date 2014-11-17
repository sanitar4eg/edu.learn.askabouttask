package edu.learn.askabouttask.entity;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Класс представляющий модель Журнала
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"name", "tasks"}, name = "journal")
public class Journal {
	
	protected Journal () {}
	
	public Journal (String name) {
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
	
	@XmlElementWrapper(name = "tasks")
	@XmlElement(name = "task")
	//@XmlJavaTypeAdapter(MapAdapter.class)
	public final Map<String, Task> getTasks() {
		if (tasks == null) {
			return null;
		} else {
			return tasks;
		}
	}

	private void setTasks(Map<String, Task> tasks) {
		this.tasks = tasks;
	}

	public int getCount() {
		return tasks.size();
	}
	
	public boolean isEmpty() {
		return tasks.isEmpty();
	}
	
	public boolean deleteTask (String name) {
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
		//task.setShedule();
		// 1 create task, set parameters
		// 2 add to map
		// 3 run task.setSchedule() / setReminder
	}

}
