package edu.learn.askabouttask.interaction;

import java.io.File;

import javax.xml.bind.JAXBException;

public interface Parcerable {
	
	Object getObject(File file) throws JAXBException;
	
	void saveObject(File file) throws JAXBException;
}
