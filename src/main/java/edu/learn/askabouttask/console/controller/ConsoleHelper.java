package edu.learn.askabouttask.console.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ConsoleHelper {

	private ConsoleHelper() {
	}

	public final static String DATE_FORMAT = "yyyy.MM.dd'at'HH:mm";

	private static Scanner scanner = new Scanner(System.in);

	public static SimpleDateFormat formater = new SimpleDateFormat(DATE_FORMAT);

	public static String getString() {
		String result = null;
		if (scanner.hasNext()) {
			result = scanner.next();
		}
		return result;
	}

	public static Integer getInt() {
		Integer result = null;
		if (scanner.hasNextInt()) {
			result = Integer.valueOf(scanner.nextInt());
		} else {
			scanner.next();
		}
		return result;
	}

	public static Integer getInt(int min, int max) {
		Integer result;
		if ((result = getInt()) != null)
			if (result < min || result > max)
				result = null;
		return result;
	}

	public static Date getDate() {
		Date date = null;
		if (scanner.hasNext()) {
			try {
				date = formater.parse(scanner.next());
			} catch (Exception e) {
				date = null;
			}
		}
		return date;
	}
}
