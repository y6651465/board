package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.JournalEntry;

@RestController
public class JournalController {
	private static List<JournalEntry> entries = new ArrayList<JournalEntry>();
	
	static {
		try {
			entries.add(new JournalEntry("Title1", "summuery1", "12/13/2000"));
			entries.add(new JournalEntry("Title2", "summuery2", "12/13/2000"));
			entries.add(new JournalEntry("Title3", "summuery3", "12/13/2000"));
			entries.add(new JournalEntry("Title4", "summuery4", "12/13/2000"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/all")
	public List<JournalEntry> getAll() throws Exception{
		return entries;
	}
	
	@RequestMapping("/findBy/title/{title}")
	public List<JournalEntry> findBy(@PathVariable String title) throws Exception {
		return entries.stream().filter(
				entry -> entry.getTitle().toLowerCase().contains(title.toLowerCase())).collect(Collectors.toList());
				
	}
	
	@RequestMapping(value="/journal", method=RequestMethod.POST)
	public JournalEntry add(@RequestBody JournalEntry entry) {
		entries.add(entry);
		return entry;
	}
	@RequestMapping(value="/journal/{index}", method=RequestMethod.DELETE)
	public JournalEntry add(@PathVariable int index) {
		
		return entries.remove(index);
	}
	
}
