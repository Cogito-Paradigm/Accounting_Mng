package util;

//문자열을 관리하는 문자열 유틸클래스
public class StringUtil {
	//입력받은 String을 "/"기준으로 잘라 num번째를 반환하는 메소드
	public static String splitString(String imsi, int num){
		String[] a = imsi.split("/");
		return a[num];
	}
}
