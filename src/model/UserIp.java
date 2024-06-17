package model;

import java.io.Serializable;
import java.time.LocalDateTime;
public class UserIp implements Serializable{
	private String ipAddress;
	private int count;
	private LocalDateTime date;

	public UserIp() {
		this.date = LocalDateTime.now();

	}

	//受け取った時間を24時間前にして返す
	public LocalDateTime yesterday(LocalDateTime cal) {
		return cal.minusHours(24);
	}

	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public void countPlus() {
		this.count++;
	}
}
