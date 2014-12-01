package edu.learn.askabouttask.notifications;

import java.io.InputStream;
import java.util.TimerTask;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

public class RunJarTaskWithExec extends TimerTask {

	private static final Logger LOGGER = Logger
			.getLogger(RunJarTaskWithExec.class);

	/**
	 * 
	 */
	private String path;
	private String message;

	public RunJarTaskWithExec(String inPath, String inMessage) {
		this.path = inPath;
		this.message = inMessage;
	}

	public void run() {
		runWithExec();
	}

	private void runWithExec() {
		try {
			Process proc = Runtime.getRuntime().exec(
					"java -jar " + path + " -n " + message);
			proc.waitFor();
			InputStream in = proc.getInputStream();
			InputStream err = proc.getErrorStream();
			System.out.println(IOUtils.toString(in));
			System.out.println(IOUtils.toString(err));
		} catch (Exception e) {
			LOGGER.error("Error in runWithExec", e);
		}
	}
}