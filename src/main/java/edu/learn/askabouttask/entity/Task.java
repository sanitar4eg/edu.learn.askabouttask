package edu.learn.askabouttask.entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import edu.learn.askabouttask.addition.DateFormatter;

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
}
