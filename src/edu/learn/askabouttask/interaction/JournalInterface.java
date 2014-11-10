package edu.learn.askabouttask.interaction;

import java.io.File;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import edu.learn.askabouttask.addition.DateFormatter;
import edu.learn.askabouttask.entity.Journal;

public class JournalInterface implements Parcerable {
	
	private Journal current;

	public void createJournal() {
		System.out.println("Введите имя планировщика");
		String name = ConsoleInterface.getString();
		current = new Journal(name);
		current.viewInfo();
	}
	
	public void addTask() {
		System.out.println("Введите название задачи");
		String name = ConsoleInterface.getString();
		System.out.println("Введите описание");
		String description = ConsoleInterface.getString();
		System.out.println("Введите время для напоминания");
		Date minderTime = ConsoleInterface.getDate();
		System.out.println("Введите контакты");
		String contacts = ConsoleInterface.getString();
		current.addTask(name, description, minderTime, contacts);
	}
	
	public void deleteTask() {
		System.out.println("Введите название удаляемой задачи");
		String name = ConsoleInterface.getString();
		if (current.deleteTask(name)) {
			System.out.println("Успешное удаление");
		} else {
			System.out.println("Задача не найдена");
		}		
	}
	
	public void viewTasks() {
		current.viewTasks();
	}
	
	
	public void viewInfo () {
		current.viewInfo();
	}
	
	public void save() {
		saveObject(new File ("jaxb.xml"));
	}
	
	public void openJournal() {
		try {
		current = (Journal) getObject(new File("jaxb.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void exit() {
		current.eraseTasks();
	}
	
	@Override
	public Object getObject(File file) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(Journal.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		unmarshaller.setAdapter(new DateFormatter());
		Object object = unmarshaller.unmarshal(file);
		return object;
	}

	@Override
	public void saveObject(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(Journal.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(current, file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
