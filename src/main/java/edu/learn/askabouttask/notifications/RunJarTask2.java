package edu.learn.askabouttask.notifications;

import java.io.InputStream;
import java.util.TimerTask;

public class RunJarTask2 extends TimerTask {

	/**
	 * 
	 */
	private final TimerTaskNotification RunJarTask2;
	private String path;
	private String message;

	public RunJarTask2(TimerTaskNotification timerTaskNotification, String inPath, String message) {
		RunJarTask2 = timerTaskNotification;
		this.path = inPath;
		this.message = message;
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