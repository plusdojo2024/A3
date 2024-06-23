package model;

import java.io.Serializable;

public class Notes implements Serializable{
	private int noteID;  //メモID
	private int familyID;  //家族ID
	private String title;//タイトル
	private String note;//メモ
	private String noteDate;  //ノート日付
	private String imageOne;  //写真１
	private String imageTwo;  //写真２
	private String noteUpdate;  //ノート変更
	private int year; //年
	private int month; //月
	private int day; //日
	private String yearMonth;//日だけを除く


	//引数なしコンストラクタ
	public Notes() {

	}


	public Notes(int noteID, int familyID, String title, String note, String noteDate, String imageOne, String imageTwo,
			String noteUpdate) {
		super();
		this.noteID = noteID;
		this.familyID = familyID;
		this.title = title;
		this.note = note;
		this.noteDate = noteDate;
		this.imageOne = imageOne;
		this.imageTwo = imageTwo;
		this.noteUpdate = noteUpdate;
	}
	//getter,setter
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;

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


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public int getMonth() {
		return month;
	}


	public void setMonth(int month) {
		this.month = month;
	}


	public int getDay() {
		return day;
	}


	public void setDay(int day) {
		this.day = day;
	}


	public String getYearMonth() {
		return yearMonth;
	}


	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}


}