package edu.learn.askabouttask.addition;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import edu.learn.askabouttask.interaction.ConsoleInterface;


public class DateFormatter extends XmlAdapter<String, Date> {
	
	SimpleDateFormat formatter = new SimpleDateFormat(ConsoleInterface.DATE_FORMAT);
	
	public Date unmarshal(String date) throws Exception {
		return formatter.parse(date);
	}
	
	public String marshal(Date date) throws Exception {
		return formatter.format(date);
	}
}
