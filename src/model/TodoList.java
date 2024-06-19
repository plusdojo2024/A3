package model;

import java.io.Serializable;

public class TodoList implements Serializable{
	private int listId;
	private int familyId;
	private String task;
	private String category;
	private int givePoint;
	private String listDate;
	private String memo;
	private int todoDelete;

	//引数なしコンストラクタ
	public TodoList() {

	}

	public TodoList(int listId, int familyId, String task, String category, int givePoint, String listDate, String memo,
			int todoDelete) {
		super();
		this.listId = listId;
		this.familyId = familyId;
		this.task = task;
		this.category = category;
		this.givePoint = givePoint;
		this.listDate = listDate;
		this.memo = memo;
		this.todoDelete = todoDelete;
	}

	public int getListId() {
		return listId;
	}

	public void setListId(int listId) {
		this.listId = listId;
	}

	public int getFamilyId() {
		return familyId;
	}

	public void setFamilyId(int familyId) {
		this.familyId = familyId;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getGivePoint() {
		return givePoint;
	}

	public void setGivePoint(int givePoint) {
		this.givePoint = givePoint;
	}

	public String getListDate() {
		return listDate;
	}

	public void setListDate(String listDate) {
		this.listDate = listDate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getTodoDelete() {
		return todoDelete;
	}

	public void setTodoDelete(int todoDelete) {
		this.todoDelete = todoDelete;
	}


}
