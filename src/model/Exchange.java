package model;

import java.io.Serializable;

public class Exchange implements Serializable
{
	private int exchangeId;//識別番号
	private String reward;//ご褒美名
	private int uid;//対象者番号
	private String exchangeDate;//交換日付

	// 引数がないコンストラクタ
	public Exchange()
	{

	}
	// 引数があるコンストラクタ
	public Exchange(int exchangeId, String reward, int uid, String exchangeDate)
	{
		this.exchangeId = exchangeId;
		this.reward = reward;
		this.uid = uid;
		this.exchangeDate = exchangeDate;
	}

	public int getExchangeId()
	{
		return exchangeId;
	}

	public void setExchangeId(int exchangeId)
	{
		this.exchangeId = exchangeId;
	}

	public String getReward()
	{
		return reward;
	}

	public void setReward(String reward)
	{
		this.reward = reward;
	}

	public int getUid()
	{
		return uid;
	}

	public void setUid(int uid)
	{
		this.uid = uid;
	}

	public String getExchangeDate()
	{
		return exchangeDate;
	}

	public void setExchangeDate(String exchangeDate)
	{
		this.exchangeDate = exchangeDate;
	}











}
