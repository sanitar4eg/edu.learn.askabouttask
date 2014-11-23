package edu.learn.askabouttask.entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import edu.learn.askabouttask.addition.DateFormatter;
import edu.learn.askabouttask.addition.NotificationSystem;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = { "name", "description", "minderTime", "contacts" }, name = "task")
public class Task {

	protected Task() {
	}

	public Task(String name, String description, Date minderTime,
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
	
	//TODO: Можно ли обойтись константой для облегчения задачи?
	private final String REMINDER_APPLICATION = "src/resources/Reminder.jar"; 

	public String getName() {
		return this.name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	private void setDescription(String description) {
		this.description = description;
	}

	@XmlJavaTypeAdapter(DateFormatter.class)
	public final Date getMinderTime() {						
		return minderTime;
	}

	private void setMinderTime(Date minderTime) {
		this.minderTime = minderTime;
	}

	public String getContacts() {
		return contacts;
	}

	private void setContacts(String contacts) {
		this.contacts = contacts;
	}
	
	public String getReminderApplication () {
		return REMINDER_APPLICATION;
	}

	public boolean setShedule() {
		if (reminder == null)
			if (name != null && minderTime != null) {
				if (new Date().getTime() < minderTime.getTime()) {
					reminder = new NotificationSystem();
					reminder.setShedule(this);
					return true;
				}
			}
		return false;
	}

	public void cancelShedule() {
		if (reminder != null)
			reminder.cancelShedule();
	}

}
