package edu.learn.askabouttask;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class NotificationSystem {
	
	public NotificationSystem(Date date, String nameTask) {
		timer = new Timer();
		timer.schedule(new NotificationTask(), date);
		this.nameTask = nameTask;
	}

	private Timer timer;
	
	private String nameTask;
	
	class NotificationTask extends TimerTask {
		public void run() {
			System.out.println(nameTask);
			timer.cancel();
		}
	}
}
