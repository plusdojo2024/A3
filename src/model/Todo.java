package model;

import java.io.Serializable;

public class Todo implements Serializable
{
	private int todoId;
	private String todoDate;
	private int uid;
    private int listId;
    private int loop;
    private String startDate;
    private String endDate;
    private int monday;
    private int tuesday;
    private int wednesday;
    private int thursday;
    private int friday;
    private int saturday;
    private int sunday;
    private String task;
    private String name;
    private String color;

    public Todo(int todoId, int listId, String todoDate, int uid, int loop, String startDate, String endDate,
    	int monday, int tuesday, int wednesday, int thursday, int friday,int saturday, int sunday)
    {
    	this.todoId = todoId;
    	this.listId = listId;
    	this.todoDate = todoDate;
    	this.loop = loop;
    	this.startDate = startDate;
    	this.endDate = endDate;
    	this.monday = monday;
    	this.tuesday = tuesday;
    	this.wednesday = wednesday;
    	this.thursday = thursday;
    	this.friday = friday;
    	this.saturday = saturday;
    	this.sunday = sunday;
    }

	public Todo()
	{
		this.monday = 0;
    	this.tuesday = 0;
    	this.wednesday = 0;
    	this.thursday = 0;
    	this.friday = 0;
    	this.saturday = 0;
    	this.sunday = 0;
	}

	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getListId() {
		return listId;
	}
	public void setListId(int listId) {
		this.listId = listId;
	}
	public int getLoop() {
		return loop;
	}
	public void setLoop(int loop) {
		this.loop = loop;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getMonday() {
		return monday;
	}
	public void setMonday(int monday) {
		this.monday = monday;
	}
	public int getTuesday() {
		return tuesday;
	}
	public void setTuesday(int tuesday) {
		this.tuesday = tuesday;
	}
	public int getWednesday() {
		return wednesday;
	}
	public void setWednesday(int wednesday) {
		this.wednesday = wednesday;
	}
	public int getThursday() {
		return thursday;
	}
	public void setThursday(int thursday) {
		this.thursday = thursday;
	}
	public int getFriday() {
		return friday;
	}
	public void setFriday(int friday) {
		this.friday = friday;
	}
	public int getSaturday() {
		return saturday;
	}
	public void setSaturday(int saturday) {
		this.saturday = saturday;
	}
	public int getSunday() {
		return sunday;
	}
	public void setSunday(int sunday) {
		this.sunday = sunday;
	}
	public int getTodoId() {
		return todoId;
	}
	public void setTodoId(int todoId) {
		this.todoId = todoId;
	}
	public String getTodoDate() {
		return todoDate;
	}
	public void setTodoDate(String todoDate) {
		this.todoDate = todoDate;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
