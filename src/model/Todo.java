package model;

import java.io.Serializable;

public class Todo implements Serializable
{
	private int uid;
    private int listId;
    private String loop;
    private String startDate;
    private String endDate;
    private int monday;
    private int tuesday;
    private int wednesday;
    private int thursday;
    private int friday;
    private int saturday;
    private int sunday;
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
	public String getLoop() {
		return loop;
	}
	public void setLoop(String loop) {
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
}
