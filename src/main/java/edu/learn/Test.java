package edu.learn;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class Test {

	public static void main(String[] args) throws IOException {
		InputStream s = ClassLoader.getSystemResourceAsStream("log4j.properties");
		
		Properties p = new Properties();
		p.load(s);
		
		
		
		System.out.println(p.getProperty("log4j.appender.file.layout.ConversionPattern"));
//		System.out.println(p);
		s.close();
	}

}
