package edu.learn.askabouttask.interaction;

import java.io.File;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import edu.learn.askabouttask.addition.DateFormatter;
import edu.learn.askabouttask.entity.Journal;

/**
 * Класс предназначенный для хранения текущего журнала и его обработки
 */
public class JournalInterface implements Parcerable {
	
	/**
	 * Данный объект может быть сохранен в XML файл, а так же загружен из него
	 * @see Journal
	 * @serialField 
	 */
	private Journal current;

	/**
	 * Метод создает новый журнал и помещает в поле current
	 */
	public void createJournal() {
		System.out.println("Введите имя планировщика");
		String name = ConsoleInterface.getString();
		current = new Journal(name);
		current.viewInfo();
	}
	
	/**
	 * Метод добавляет новую задачу в журнал
	 * @see Journal
	 */
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
	
	/**
	 * Метод удаляет задачу из журнала 
	 * @see Journal
	 */
	public void deleteTask() {
		System.out.println("Введите название удаляемой задачи");
		String name = ConsoleInterface.getString();
		if (current.deleteTask(name)) {
			System.out.println("Успешное удаление");
		} else {
			System.out.println("Задача не найдена");
		}		
	}
	
	/**
	 * @see Journal
	 */
	public void viewTasks() {
		current.viewTasks();
	}
	
	/**
	 * @see Journal
	 */
	public void viewInfo () {
		current.viewInfo();
	}
	
	/**
	 * Сохраняет объект в файл
	 */
	public void save() {
		saveObject(new File ("jaxb.xml"));
	}
	
	/**
	 * Открывает объект из файла
	 */
	public void openJournal() {
		try {
		current = (Journal) getObject(new File("jaxb.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @see Journal
	 */
	public void exit() {
		current.eraseTasks();
	}
	
	/**
	 * Метод осуществляющий загрузку объекта типа {@link Journal} из файла
	 * @param file XML файл из которого будет загружен объект
	 * @return Возвращает объект, загруженный из XML файла
	 */
	@Override
	public Object getObject(File file) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(Journal.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		unmarshaller.setAdapter(new DateFormatter());
		Object object = unmarshaller.unmarshal(file);
		return object;
	}

	/**
	 * Метод, сохраняющий объект типа {@link Journal} в файл
	 * @param file XML файл в который сохранится объект
	 */
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
