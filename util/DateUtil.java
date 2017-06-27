package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.SimpleTimeZone;

//날짜를 관리하는 날짜 유틸클래스
public class DateUtil {
	//숫자를 입력받아 현재날짜로부터 숫자만큼 거슬러 간 과거의 날짜를 반환
	public static String dayBefore(int num){
		Calendar cal = Calendar.getInstance(new SimpleTimeZone(0x1ee6280, "KST"));
		cal.add(Calendar.DATE, -1*num);
		java.util.Date dayBefore = cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		return formatter.format(dayBefore);
	}
}
