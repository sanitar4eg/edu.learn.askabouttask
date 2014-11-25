package edu.learn;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import edu.learn.askabouttask.console.controller.JournalController;
import edu.learn.askabouttask.notifications.NotificationSystem;

public class Main {
	
	public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		InputStream s = ClassLoader.getSystemResourceAsStream("log4j.properties");
		Properties p = new Properties();
		p.load(s);
		s.close();

		Collection<NotificationSystem> os = new ArrayList<NotificationSystem>();
		String observerNames = p.getProperty("register_observers", "");
		for (String className : observerNames.split(",")) {
			NotificationSystem o = (NotificationSystem) Class.forName(className).newInstance();
			os.add(o);
		}
		
		
		JournalController controller = new JournalController(os);
		controller.start();
	}
}
