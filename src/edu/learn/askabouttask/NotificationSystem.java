package edu.learn.askabouttask;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class NotificationSystem {
	
	public NotificationSystem(Date date, String nameTask) {
		timer = new Timer();
		timer.schedule(nt, date);
		this.nameTask = nameTask;
	}

	private Timer timer;
	
	private String nameTask;
	
	private NotificationTask nt = new NotificationTask();
	
	class NotificationTask extends TimerTask {
		public void run() {
			System.out.println(nameTask);
			timer.cancel();
		}
	}
}
