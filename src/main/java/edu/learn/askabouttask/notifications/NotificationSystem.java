package edu.learn.askabouttask.notifications;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.apache.log4j.Logger;

import edu.learn.askabouttask.entity.Task;

public class NotificationSystem implements Reminiscentable {
	
	private static final Logger LOGGER = 
			Logger.getLogger(NotificationSystem.class);

	public NotificationSystem(Task inTask) {
		this.task = inTask;
	}

	private Task task;

	private Timer timer;

	private RunJarTask runJar;

	public static boolean isAvailableForMonitoring(Task task) {
		if (task != null) {
			if (task.getMinderTime().getTime() > new Date().getTime()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isActive() {
		return isAvailableForMonitoring(this.task) && timer != null;
	}

	@Override
	public void setShedule() {
		runJar = new RunJarTask(task.getReminderApplication());
		timer = new Timer();
		timer.schedule(runJar, task.getMinderTime());
	}

	@Override
	public Task getTask() {
		return task;
	}

	@Override
	public void cancelShedule() {
		if (timer != null) {
			timer.cancel();
		}
	}

	private class RunJarTask extends TimerTask {

		private String path;

		public RunJarTask(String inPath) {
			this.path = inPath;
		}

		public void run() {
			runWithJarFile();
			timer.cancel();
		}

		private void runWithJarFile() {
			try {
				File file = new File(path);
				JarFile jf = new JarFile(file);
				Manifest manifest = jf.getManifest();
				Attributes attr = manifest.getMainAttributes();
				String mainClassName = attr.getValue("Main-Class");
				ClassLoader cl = new URLClassLoader(new URL[] { file.toURI()
						.toURL() });
				Class mainClass = cl.loadClass(mainClassName);
				Method mainMethod = mainClass.getMethod("main",
						new Class[] { String[].class });
				String[] args2 = { "-n", task.getName() };
				mainMethod.invoke(mainClass, new Object[] { args2 });
				jf.close();
			} catch (Exception e) {
				LOGGER.error("Error in runWithJarFile", e);
			}
		}

		private void runWithExec() {
			try {
				Process proc = Runtime.getRuntime().exec(
						"java -jar " + task.getReminderApplication() + " -n "
								+ task.getName());

				proc.waitFor();
				InputStream in = proc.getInputStream();
				InputStream err = proc.getErrorStream();

				// TODO: [Vyacheslav Zh.] Для копирования потоков проще
				// использовать
				// библиотеку - Apache commons-io
				byte bytes[] = new byte[in.available()];
				in.read(bytes, 0, bytes.length);
				System.out.println(new String(bytes));

				int readCount = 0;
				while ((readCount = in.read(bytes)) != -1) {
					String string = new String(bytes, 0, readCount);
				}

				byte bytes2[] = new byte[err.available()];
				err.read(bytes2, 0, bytes2.length);
				System.out.println(new String(bytes2));
			} catch (Exception e) {
				LOGGER.error("Error in runWithExec", e);
			}
		}
	}
}
