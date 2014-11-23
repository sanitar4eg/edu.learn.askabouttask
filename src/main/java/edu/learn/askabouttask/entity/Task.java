package edu.learn.askabouttask.entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import edu.learn.askabouttask.addition.DateFormatter;
import edu.learn.askabouttask.addition.NotificationSystem;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = { "name", "description", 
		"minderTime", "contacts" }, name = "task")
public class Task {

	protected Task() {
	}

	public Task(String inName, String inDescription, Date inMinderTime,
			String inContacts) {
		setName(inName);
		setDescription(inDescription);
		setMinderTime(inMinderTime);
		setContacts(inContacts);
	}

	private String name;

	private String description;

	private Date minderTime;

	private String contacts;

	private NotificationSystem reminder;
	
	//TODO: Можно ли обойтись константой для облегчения задачи?
	private static final String REMINDER_APPLICATION = 
			"src/resources/Reminder.jar"; 

	public final String getName() {
		return this.name;
	}

	private void setName(String inName) {
		this.name = inName;
	}

	public final String getDescription() {
		return description;
	}

	private void setDescription(String inDescription) {
		this.description = inDescription;
	}

	@XmlJavaTypeAdapter(DateFormatter.class)
	public final Date getMinderTime() {						
		return minderTime;
	}

	private void setMinderTime(Date inMinderTime) {
		this.minderTime = inMinderTime;
	}

	public final String getContacts() {
		return contacts;
	}

	private void setContacts(String inContacts) {
		this.contacts = inContacts;
	}
	
	public final String getReminderApplication() {
		return REMINDER_APPLICATION;
	}

	public final boolean setShedule() {
		if (reminder == null) {
			if (name != null && minderTime != null) {
				if (new Date().getTime() < minderTime.getTime()) {
					reminder = new NotificationSystem();
					reminder.setShedule(this);
					return true;
				}
			}
		}
		return false;
	}

	public void cancelShedule() {
		if (reminder != null) {
			reminder.cancelShedule();
		}
	}

}
