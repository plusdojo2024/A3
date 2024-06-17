package model;

import java.io.Serializable;

public class Family implements Serializable{
	private int familyId;
	private String mail;
	private String familyPass;
	private String familyName;
	private String familySalt;
	private String familyDate;


	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getFamilySalt() {
		return familySalt;
	}
	public void setFamilySalt(String familySalt) {
		this.familySalt = familySalt;
	}
	public String getFamilyDate() {
		return familyDate;
	}
	public void setFamilyDate(String familyDate) {
		this.familyDate = familyDate;
	}
	public int getFamilyId() {
		return familyId;
	}
	public void setFamilyId(int familyId) {
		this.familyId = familyId;
	}
	public String getFamilyPass() {
		return familyPass;
	}
	public void setFamilyPass(String familyPass) {
		this.familyPass = familyPass;
	}
}
