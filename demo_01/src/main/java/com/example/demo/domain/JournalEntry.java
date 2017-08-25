package com.example.demo.domain;

import java.text.SimpleDateFormat;
import java.util.Date;


public class JournalEntry {
	private String title;
	private Date created;
	private String summary;
	
	private final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	
	
	
	
	public JournalEntry() {
		
	}

	public JournalEntry(String title, String summary, String created) throws Exception {
		this.title = title;
		this.created = format.parse(created);
		this.summary = summary;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(String created) throws Exception {
		this.created = format.parse(created);
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	
	
	
}
