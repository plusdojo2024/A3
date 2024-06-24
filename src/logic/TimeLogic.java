package logic;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import model.Todo;

public class TimeLogic {

	//フォーマットを日本語の年月日と時刻にして現在時刻を返す
	public String nowJp() {

		LocalDateTime now = LocalDateTime.now();//現在時刻取得
		//日本語フォーマットを指定
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH時mm分ss秒");
		String date = now.format(f);

		return date;
	}

	//フォーマットを日本語の年月日に直して時刻を切り落として返す
	public String nowJpDay() {

		LocalDateTime now = LocalDateTime.now();//現在時刻取得
		//日本語フォーマットを指定
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
		String date = now.format(f);

		return date;
	}

	//フォーマットを日本語の年月に直して時刻を切り落として返す
		public int nowJpYearMonth() {

			LocalDateTime now = LocalDateTime.now();//現在時刻取得
			//日本語フォーマットを指定
			DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy年MM月");
			int date = Integer.parseInt(now.format(f));

			return date;
		}

	//カレンダーで使用されているフォーマットで現在時刻を返す
	public String nowCalendar() {

		LocalDateTime now = LocalDateTime.now();//現在時刻取得
		//フォーマットを指定
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");//2020-06-06
		String date = now.format(f);

		return date;
	}

	//一般的なフォーマットで時刻を切り捨ててStringで返す
	public String nowNomalDay() {
		LocalDateTime now = LocalDateTime.now();//現在時刻取得
		//フォーマットを指定
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy/MM/dd");//2024/06/20
		String date = now.format(f);

		return date;
	}

	//startからendまでの指定した曜日の日付をlistに格納していく(week配列内の全曜日)
	//曜日は1を初期値として日曜日～月曜日の順番　(1～7)
	public List<Todo> createTodo(String startDate, String endDate, int[] week,int uid,int listId) {
		List<Todo> todoList = new ArrayList<Todo>();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");//フォーマット指定
		LocalDate start = LocalDate.parse(startDate,dtf);//Stringの日付を指定したフォーマットでLocalDateに変換
		LocalDate end = LocalDate.parse(endDate,dtf);//Stringの日付を指定したフォーマットでLocalDateに変換

		//フォーマットを指定
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");//例：2020-06-06
		for (int i = 0; i < week.length; i++) {
			if (week[i] == 1) {
				LocalDate temp = start.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
				for(;temp.isBefore(end) || temp.equals(end); temp=temp.plusDays(7)) {

					Todo todo = new Todo();
					todo.setSunday(1);
					todo.setListId(listId);
					todo.setUid(uid);
					todo.setTodoDate(temp.format(f));
					todoList.add(todo);
				}
			}else if(week[i] == 2) {
				LocalDate temp = start.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
				for(;temp.isBefore(end) || temp.equals(end); temp=temp.plusDays(7)) {

					Todo todo = new Todo();
					todo.setSunday(1);
					todo.setListId(listId);
					todo.setUid(uid);
					todo.setTodoDate(temp.format(f));
					todoList.add(todo);
				}
			}else if(week[i] == 3) {
				LocalDate temp = start.with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY));
				for(;temp.isBefore(end) || temp.equals(end); temp=temp.plusDays(7)) {
					Todo todo = new Todo();
					todo.setSunday(1);
					todo.setListId(listId);
					todo.setUid(uid);
					todo.setTodoDate(temp.format(f));
					todoList.add(todo);
				}
			}else if(week[i] == 4) {
				LocalDate temp = start.with(TemporalAdjusters.nextOrSame(DayOfWeek.WEDNESDAY));
				for(;temp.isBefore(end) || temp.equals(end); temp=temp.plusDays(7)) {
					Todo todo = new Todo();
					todo.setSunday(1);
					todo.setListId(listId);
					todo.setUid(uid);
					todo.setTodoDate(temp.format(f));
					todoList.add(todo);
				}
			}else if(week[i] == 5) {
				LocalDate temp = start.with(TemporalAdjusters.nextOrSame(DayOfWeek.THURSDAY));
				for(;temp.isBefore(end) || temp.equals(end); temp=temp.plusDays(7)) {
					Todo todo = new Todo();
					todo.setSunday(1);
					todo.setListId(listId);
					todo.setUid(uid);
					todo.setTodoDate(temp.format(f));
					todoList.add(todo);
				}
			}else if(week[i] == 6) {
				LocalDate temp = start.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
				for(;temp.isBefore(end) || temp.equals(end); temp=temp.plusDays(7)) {
					Todo todo = new Todo();
					todo.setSunday(1);
					todo.setListId(listId);
					todo.setUid(uid);
					todo.setTodoDate(temp.format(f));
					todoList.add(todo);
				}
			}else if(week[i] == 7) {
				LocalDate temp = start.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
				for(;temp.isBefore(end) || temp.equals(end); temp=temp.plusDays(7)) {
					Todo todo = new Todo();
					todo.setSunday(1);
					todo.setListId(listId);
					todo.setUid(uid);
					todo.setTodoDate(temp.format(f));
					todoList.add(todo);
				}
			}
		}

		return todoList;
	}

	//Dateを年月日のint型配列に分割して返す
	public int[] splitDate(String date) {
		int[] dateList = new int[3];

		if(date.split("[年月日]").length == 3) {//yyyy年MM月dd日

			for(int i=0;i<dateList.length;i++) {
				dateList[i] = Integer.parseInt(date.split("[年月日]")[i]);
			}
		}else if(date.split("-").length == 3) {//yyyy-MM-dd
			for(int i=0;i<dateList.length;i++) {
				dateList[i] = Integer.parseInt(date.split("-")[i]);
			}
		}else if(date.split("/").length == 3) {//yyyy/MM/dd
			for(int i=0;i<dateList.length;i++) {
				dateList[i] = Integer.parseInt(date.split("/")[i]);
			}
		}

		return dateList;
	}

	public String[] yearMonth(String date) {
		String[] dateList = new String[2];
		if(date.split("月").length==2) {
			dateList=date.split("月");
		}
		return dateList;
	}
}
