package edu.learn.askabouttask.addition;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Timer;
import java.util.TimerTask;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import edu.learn.askabouttask.entity.Task;

public class NotificationSystem {

	public NotificationSystem() {
	}

	private Timer timer;

	private NotificationTask nt;

	public void cancelShedule() {
		if (timer != null) {
			timer.cancel();
		}
	}

	public void setShedule(Task task) {
		nt = new NotificationTask(task);
		timer = new Timer();
		timer.schedule(nt, task.getMinderTime());
	}

	private class NotificationTask extends TimerTask {

		public NotificationTask(Task inTask) {
			this.task = inTask;
		}

		private Task task;

		public void run() {
			runWithJarFile();
			timer.cancel();
		}
	}

	private void runWithJarFile() {
		try {
			// TODO: Не уверен, коректен ли такой способ передачи параметров
			// или переделать на входной параметр в методе?
			File file = new File(nt.task.getReminderApplication());
			JarFile jf = new JarFile(file);
			Manifest manifest = jf.getManifest();
			Attributes attr = manifest.getMainAttributes();
			String mainClassName = attr.getValue("Main-Class");
			ClassLoader cl = 
					new URLClassLoader(new URL[] { file.toURI().toURL()});
			Class mainClass = cl.loadClass(mainClassName);
			Method mainMethod = mainClass.getMethod("main",
					new Class[] { String[].class });
			String[] args2 = { "-n", nt.task.getName() };
			mainMethod.invoke(mainClass, new Object[] { args2 });
			jf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void runWithExec() {
		try {
			Process proc = Runtime.getRuntime().exec(
					"java -jar " + nt.task.getReminderApplication() + " -n " 
							+ nt.task.getName());

			proc.waitFor();
			InputStream in = proc.getInputStream();
			InputStream err = proc.getErrorStream();

			// TODO: [Vyacheslav Zh.] Для копирования потоков проще использовать
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
			e.printStackTrace();
		}
	}
}
