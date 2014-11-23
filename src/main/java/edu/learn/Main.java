package edu.learn;

import edu.learn.askabouttask.console.controller.JournalController;

public class Main {
	protected Main() { }
	public static void main(String[] args) {
		JournalController controller = new JournalController();
		controller.start();
	}
}
