package edu.learn.askabouttask.addition;

import org.testng.Assert;
import org.testng.annotations.Test;


public class DateFormatterTest {
	@Test
	public void testUnmarshal() {
		DateFormatter df = new DateFormatter();
		Assert.assertNotNull(df);
	}
}
