package edu.learn.askabouttask.notifications;

import java.io.InputStream;
import java.util.TimerTask;

public class RunJarTaskWithExec extends TimerTask {

	/**
	 * 
	 */
	private final TimerTaskNotification runJarTask;
	private String path;
	private String message;

	public RunJarTaskWithExec(TimerTaskNotification timerTaskNotification, String inPath, String inMessage) {
		runJarTask = timerTaskNotification;
		this.path = inPath;
		this.message = inMessage;
	}

	public void run() {
		runWithExec();
	}

	private void runWithExec() {
		try {
			Process proc = Runtime.getRuntime().exec(
					"java -jar " + path + " -n "
							+ message);

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
			TimerTaskNotification.LOGGER.error("Error in runWithExec", e);
		}
	}
}