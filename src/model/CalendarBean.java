package model;

import java.io.Serializable;

//標準ライブラリのCalendarと被らないように命名規則外し
public class CalendarBean implements Serializable{
	private String name;
	private String task;
	private String date;
	private String color;
	private int uid;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
