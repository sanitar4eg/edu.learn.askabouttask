package edu.learn.askabouttask.entity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import edu.learn.askabouttask.addition.MapAdapter;


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
	
	// TODO: [Vyacheslav Zh.] Название подобрано не очень оптимально - такое впечатление что дочерние объекты - Task
	// будут удалены. По факту - нет.
	public void eraseTasks() {
		for (Task task : tasks.values()) {
			task.eraseReminder();
		}
	}

	public void addTask(Task task/* ... task properties .. */) {
		tasks.put(task.getName(), task);
		task.setShedule();
		// 1 create task, set parameters
		// 2 add to map
		// 3 run task.setSchedule() / setReminder
	}

}
