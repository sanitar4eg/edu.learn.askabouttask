package edu.learn.askabouttask;

import java.util.Date;
import java.util.TimerTask;

public class Task {
	
	protected Task () {}
	
	public Task (String name, String description, Date minderTime,
			String contacts) {
		this.name = name;
		this.description = description;
		this.minderTime = minderTime;
		this.contacts = contacts;
	}
	
	private String name;
	
	private String description;
	
	private Date minderTime;
	
	private String contacts;
	
	public String getName () {
		return this.name;
	}
	
	public void viewTask () {
		System.out.println(name);
		System.out.println("Описание: " + description);
		System.out.println("Время: " + minderTime);
		System.out.println("Контакты: " + contacts);
	}

}
