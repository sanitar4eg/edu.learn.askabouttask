package edu.learn.askabouttask.addition;

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

public class NotificationSystem {
	
	public NotificationSystem() {}

	private Timer timer;
	
	private String nameTask;
	
	private NotificationTask nt = new NotificationTask();
	
	public void cancelShedule() {
		if (timer != null) 
			timer.cancel();
	}
	
	public void setShedule(Date date, String nameTask) {
		this.nameTask = nameTask;
		timer = new Timer();
		timer.schedule(nt, date);
	}
	
	private class NotificationTask extends TimerTask {
		public void run() {
			//System.out.println(nameTask);
			runWithJarFile();
			timer.cancel();
		}
	}
	
	private void runWithJarFile () {
		try {
			File file = new File("src/resources/Reminder.jar");
			JarFile jf= new JarFile(file);
			Manifest manifest = jf.getManifest();
			Attributes attr = manifest.getMainAttributes();
			String mainClassName = attr.getValue("Main-Class");
			// TODO: [Vyacheslav Zh.] проще использовать file.toURL()
		    //URL url = new URL("file", null, file.getAbsolutePath());  
		    ClassLoader cl = new URLClassLoader(new URL[] {file.toURL()});  //TODO Deprecated method
		    Class mainClass = cl.loadClass(mainClassName);  
		    Method mainMethod = mainClass.getMethod("main", new Class[] {String[].class});  
		    String[] args2 = {"-n", this.nameTask};
		    mainMethod.invoke(mainClass, new Object[] {args2});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void runWithExec() {
		try {
		Process proc = Runtime.getRuntime().exec("java -jar src/resources/Reminder.jar"+ " -n "+ nameTask);
		
	    proc.waitFor();
		InputStream in = proc.getInputStream();
	    InputStream err = proc.getErrorStream();
	
	    // TODO: [Vyacheslav Zh.] Для копирования потоков проще использовать библиотеку - Apache commons-io
	    byte b[]=new byte[in.available()];
	    in.read(b,0,b.length);
	    System.out.println(new String(b));
	
	    int readCount = 0;
	    while ((readCount = in.read(b)) != -1) {
	    	String s = new String(b, 0 , readCount);
	    }
	    
	    byte c[]=new byte[err.available()];
	    err.read(c,0,c.length);
	    System.out.println(new String(c));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
