package ssg.inc.sav;

import java.io.Serializable;

public class LVSample3Item implements Serializable {

	private static final long serialVersionUID = -1586987653093943671L;
	private static long index = 0;
	private long Id = 0;
	private String title;
	private String summary;
	private String uniqueId;

	public LVSample3Item(String title, String summary, String uniqueId) {
		Id = index++;
		this.title = title;
		this.summary = summary;
		this.uniqueId = uniqueId;
	}

	public long getId() {
		return Id;
	}

	public void setId(long Id) {
		this.Id = Id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public String getUniqueId () {
		return uniqueId;
	}
	
	public void setUniqueId (String uniqueId)
	{
		this.uniqueId = uniqueId;
	}
}
