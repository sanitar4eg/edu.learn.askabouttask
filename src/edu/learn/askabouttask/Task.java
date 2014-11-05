package edu.learn.askabouttask;

import java.util.Date;
import java.util.TimerTask;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlType(propOrder = {"name", "description", "mindertime", "contacts"}, name = "task")
public class Task {
	
	protected Task () {}
	
	public Task (String name, String description, Date minderTime,
			String contacts) {
		this.setName(name);
		this.setDescription(description);
		this.setMinderTime(minderTime);
		this.setContacts(contacts);
	}
	
	private String name;
	
	private String description;
	
	private Date minderTime;
	
	private String contacts;
	
	public String getName () {
		return this.name;
	}

	private void setName(String name) {
		this.name = name;
	}

	private String getDescription() {
		return description;
	}

	private void setDescription(String description) {
		this.description = description;
	}

	@XmlJavaTypeAdapter(DateFormatter.class)
	private Date getMinderTime() {
		return minderTime;
	}

	private void setMinderTime(Date minderTime) {
		this.minderTime = minderTime;
	}

	private String getContacts() {
		return contacts;
	}

	private void setContacts(String contacts) {
		this.contacts = contacts;
	}
	
	public void viewTask () {
		System.out.println(getName());
		System.out.println("Описание: " + getDescription());
		System.out.println("Время: " + getMinderTime());
		System.out.println("Контакты: " + getContacts());
	}

}
