package edu.learn.askabouttask.addition;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import edu.learn.askabouttask.console.controller.ConsoleHelper;


public class DateFormatter extends XmlAdapter<String, Date> {
	
	private static SimpleDateFormat formatter = new SimpleDateFormat(ConsoleHelper.DATE_FORMAT);
	
	public Date unmarshal(String date) throws Exception {
		return formatter.parse(date);
	}
	
	public String marshal(Date date) throws Exception {
		return formatter.format(date);
	}
}
