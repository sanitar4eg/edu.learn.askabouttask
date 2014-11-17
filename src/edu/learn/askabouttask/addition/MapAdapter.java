package edu.learn.askabouttask.addition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import edu.learn.askabouttask.entity.Task;

public class MapAdapter extends XmlAdapter<MapAdapter.AdaptedMap, Map<String, Task>> {
	
	public static class AdaptedMap {
		
		public List<Entry> list = new ArrayList<>();
		
	}
	
	public static class Entry {
		
		@XmlAttribute(name = "name")
		public String key;

		@XmlElement(name = "task")
		public Task value;
	}

    public Map<String, Task> unmarshal(AdaptedMap adaptedMap) throws Exception {
        Map<String, Task> tasks = new HashMap<String, Task>();
        for (Entry entry : adaptedMap.list)
            tasks.put(entry.key, entry.value);
        return tasks;
    }

    public AdaptedMap marshal(Map<String, Task> tasks) throws Exception {
        AdaptedMap adaptedMap = new AdaptedMap();
        for (Map.Entry<String, Task> mapEntry : tasks.entrySet()) {
        	Entry entry = new Entry();
        	entry.key = mapEntry.getKey();
        	entry.value = mapEntry.getValue();
        	adaptedMap.list.add(entry);
        }
        return adaptedMap;
    }
	
}