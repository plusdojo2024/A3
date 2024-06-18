package logic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

	//カレンダーで使用されているフォーマットで現在時刻を返す
	public String nowCalendar() {

		LocalDateTime now = LocalDateTime.now();//現在時刻取得
		//フォーマットを指定
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = now.format(f);

		return date;
	}

	//一般的なフォーマットで時刻を切り捨ててStringで返す
	public String nowNomalDay() {
		LocalDateTime now = LocalDateTime.now();//現在時刻取得
		//フォーマットを指定
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String date = now.format(f);

		return date;
	}
}
