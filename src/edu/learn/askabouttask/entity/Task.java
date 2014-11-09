package edu.learn.askabouttask.entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import edu.learn.askabouttask.addition.DateFormatter;
import edu.learn.askabouttask.addition.NotificationSystem;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"name", "description", "minderTime", "contacts"}, name = "task")
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
	
	private NotificationSystem reminder;
	
	public String getName () {
		return this.name;
	}

	private void setName(String name) {
		this.name = name;
		setReminder();
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
		setReminder();
	}

	private String getContacts() {
		return contacts;
	}

	private void setContacts(String contacts) {
		this.contacts = contacts;
	}
	
	private void setReminder() {
		if (reminder == null)
			if (name != null && minderTime != null) {
				reminder = new NotificationSystem(minderTime, name);
			}
	}
	
	public void viewTask () {
		System.out.println(getName());
		System.out.println("Описание: " + getDescription());
		System.out.println("Время: " + getMinderTime());
		System.out.println("Контакты: " + getContacts());
	}
	
	public void eraseReminder() {
		reminder.cancelReminder();
	}

}
