package model;

import java.io.Serializable;

public class Notes implements Serializable{
	private String title;
	private String memo;
	private int noteID;  //
	private int familyID;  //
	private String note;  //
	private String noteDate;  //
	private String imageOne;  //
	private String imageTwo;  //
	private String noteUpdate;  //



	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;

	}
	public String getNoteUpdate() {
		return noteUpdate;
	}
	public void setNoteUpdate(String noteUpdate) {
		this.noteUpdate = noteUpdate;
	}
	public int getFamilyID() {
		return familyID;
	}
	public void setFamilyID(int familyID) {
		this.familyID = familyID;
	}
	public int getNoteID() {
		return noteID;
	}
	public void setNoteID(int noteID) {
		this.noteID = noteID;
	}
	public String getImageTwo() {
		return imageTwo;
	}
	public void setImageTwo(String imageTwo) {
		this.imageTwo = imageTwo;
	}
	public String getImageOne() {
		return imageOne;
	}
	public void setImageOne(String imageOne) {
		this.imageOne = imageOne;
	}
	public String getNoteDate() {
		return noteDate;
	}
	public void setNoteDate(String noteDate) {
		this.noteDate = noteDate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}