package model;

import java.io.Serializable;

public class Rewards implements Serializable{

	private int rewardId;  //識別番号
	private String reward;  //ご褒美名
	private int reqPoint;  //必要ポイント
	private String rewardDate;  //日付
	private int uid;  //個人ID
	private int request;  //リクエスト中
	private String name;

	//空のコンストラクタ
	public Rewards() {

	}

	//コンストラクタ

	public Rewards(int rewardId, String reward, int reqPoint, String rewardDate, int uid, int request,String name) {
		this.rewardId = rewardId;
		this.reward = reward;
		this.reqPoint = reqPoint;
		this.rewardDate = rewardDate;
		this.uid = uid;
		this.request = request;
		this.name=name;
	}


	//getter,setter

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRewardId() {
		return rewardId;
	}

	public void setRewardId(int rewardId) {
		this.rewardId = rewardId;
	}

	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	public int getReqPoint() {
		return reqPoint;
	}

	public void setReqPoint(int reqPoint) {
		this.reqPoint = reqPoint;
	}

	public String getRewardDate() {
		return rewardDate;
	}

	public void setRewardDate(String rewardDate) {
		this.rewardDate = rewardDate;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getRequest() {
		return request;
	}

	public void setRequest(int request) {
		this.request = request;
	}

























}
