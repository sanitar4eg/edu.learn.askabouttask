package edu.learn.askabouttask.notifications;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.TimerTask;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.apache.log4j.Logger;

public class RunJarTaskWithReflect extends TimerTask {

	private static final Logger LOGGER = Logger
			.getLogger(RunJarTaskWithReflect.class);

	/**
	 * 
	 */
	private String path;
	private String message;

	public RunJarTaskWithReflect(String inPath, String inMessage) {
		this.path = inPath;
		this.message = inMessage;
	}

	public void run() {
		runWithJarFile();
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
			String[] args2 = { "-n", message };
			mainMethod.invoke(mainClass, new Object[] { args2 });
			jf.close();
		} catch (Exception e) {
			LOGGER.error("Error in runWithJarFile", e);
		}
	}
}