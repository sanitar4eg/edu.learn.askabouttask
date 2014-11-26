package edu.learn.askabouttask.console.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import edu.learn.askabouttask.notifications.NotificationSystem;


public class JournalControllerTest {

	private static final Logger LOGGER = Logger.getLogger(JournalControllerTest.class);

	private static JournalController controller;
	  
	static {

	}
	
	@Test
	public void testSomething() {
		Logger LOGGER = Logger.getLogger(JournalControllerTest.class);
		System.out.println("Say Hello!");
		try {
		InputStream s = ClassLoader.getSystemResourceAsStream("application_settings.properties");
		Properties p = new Properties();
		p.load(s);
		s.close();
		
		Collection<NotificationSystem> os = new ArrayList<NotificationSystem>();
		String observerNames = p.getProperty("register_observers", "");
		for (String className : observerNames.split(",")) {
			NotificationSystem o = (NotificationSystem) Class.forName(className).newInstance();
			LOGGER.info(o);
			os.add(o);
		}
		LOGGER.info(os);
		controller = new JournalController(os);
		ByteArrayInputStream in = new ByteArrayInputStream(("1\n" +
				"JournalName\n" +
/*				"TaskName\n" +
				"Description\n" +
				ConsoleHelper.FORMATER.format(new Date()) + "\n" +
				"contacts\n" +*/
				"1\n" +
				"7\n").getBytes());
		System.setIn(in);
		LOGGER.info(in.toString());
		controller.start();
		
		//System.setIn(System.in);
		} catch (Exception e) {
			LOGGER.info("erroe", e);
		}
	}
}
