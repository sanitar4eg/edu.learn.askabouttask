package edu.learn.askabouttask.addition;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import edu.learn.askabouttask.entity.Journal;

public class JAXBParser implements Parser {

	/**
	 * Метод осуществляющий загрузку объекта типа {@link Journal} из файла
	 * @param file XML файл из которого будет загружен объект
	 * @return Возвращает объект, загруженный из XML файла
	 */
	@Override
	public Object getObject(Class sourceClass, File file) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(sourceClass);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		unmarshaller.setAdapter(new DateFormatter());
		Object object = unmarshaller.unmarshal(file);
		return object;
	}

	/**
	 * Метод, сохраняющий объект типа {@link Journal} в файл
	 * @param file XML файл в который сохранится объект
	 */
	@Override
	public void saveObject(Object source, File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(source.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(source, file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}