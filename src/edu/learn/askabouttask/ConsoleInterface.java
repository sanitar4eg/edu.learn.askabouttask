package edu.learn.askabouttask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ConsoleInterface {
	
	public final static String DATE_FORMAT = "yyyy.MM.dd'at'HH:mm";
	
	private static Scanner scanner = new Scanner(System.in);
	
	private final static String WRONG_INPUT = "Неверные данные, попробуйте еще раз";
	
	private static SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
	
	public static String getString() {
		while (true) {
			if (scanner.hasNext()) 
				return scanner.next();
			System.out.println(WRONG_INPUT);	
		}
	}
	
	public static int getInt() {
		while (true) {
			if (scanner.hasNextInt()) {
				int result = scanner.nextInt();
				return result;
			}
			System.out.println(WRONG_INPUT);	
		}
	}

	public static int getInt(int min, int max) {
		while (true) {
			if (scanner.hasNextInt()) {
				int result = scanner.nextInt();
				if (result >= min && result <= max)
					return result;
			}
			System.out.println(WRONG_INPUT);	
		}
	}
	
	public static Date getDate() {
		System.out.println("Формат для ввода даты: " + DATE_FORMAT);
		Date date;
		while (true) {
			if (scanner.hasNext()) {
				try {
					date = format.parse(scanner.next());
				if (date != null)
					return date;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println(WRONG_INPUT);
		}
	}
}
