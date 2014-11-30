package edu.learn.askabouttask;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import org.apache.log4j.Logger;

import edu.learn.askabouttask.console.controller.JournalController;
import edu.learn.askabouttask.notifications.NotifySystem;

public class Main {

	public static final Logger LOGGER = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		try {
			InputStream s = ClassLoader
					.getSystemResourceAsStream("application_settings.properties");
			Properties p = new Properties();
			p.load(s);
			s.close();

			Collection<NotifySystem> os = new ArrayList<NotifySystem>();
			String observerNames = p.getProperty("register_observers", "");
			for (String className : observerNames.split(",")) {
				NotifySystem o = (NotifySystem) Class.forName(className)
						.newInstance();
				os.add(o);
			}

			JournalController controller = new JournalController(os);
			controller.start();

		} catch (InstantiationException e) {
			LOGGER.error("Error in Main", e);
		} catch (IllegalAccessException e) {
			LOGGER.error("Error in Main", e);
		} catch (ClassNotFoundException e) {
			LOGGER.error("Error in Main", e);
		} catch (IOException e) {
			LOGGER.error("Error in Main", e);
		}
	}
}
