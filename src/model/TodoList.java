package model;

import java.io.Serializable;

public class TodoList implements Serializable{
	private int list_id;
	private int family_id;
	private String task;
	private String category;
	private int give_point;
	private String list_date;
	private String memo;
	private int todo_delete;

	public TodoList(int list_id, int family_id, String task, String category, int give_point, String list_date,
			String memo, int todo_delete) {
		super();
		this.list_id = list_id;
		this.family_id = family_id;
		this.task = task;
		this.category = category;
		this.give_point = give_point;
		this.list_date = list_date;
		this.memo = memo;
		this.todo_delete = todo_delete;
	}

	public int getList_id() {
		return list_id;
	}

	public void setList_id(int list_id) {
		this.list_id = list_id;
	}

	public int getFamily_id() {
		return family_id;
	}

	public void setFamily_id(int family_id) {
		this.family_id = family_id;
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

	public int getGive_point() {
		return give_point;
	}

	public void setGive_point(int give_point) {
		this.give_point = give_point;
	}

	public String getList_date() {
		return list_date;
	}

	public void setList_date(String list_date) {
		this.list_date = list_date;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getTodo_delete() {
		return todo_delete;
	}

	public void setTodo_delete(int todo_delete) {
		this.todo_delete = todo_delete;
	}

}
