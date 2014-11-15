package edu.learn.askabouttask.addition;

import java.io.File;

import javax.xml.bind.JAXBException;

public interface Parser {
	
	Object getObject(Class sourceClass, File file) throws JAXBException;

	void saveObject(Object source, File file) throws JAXBException;
}
