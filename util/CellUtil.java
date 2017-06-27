package util;

import java.text.SimpleDateFormat;

import org.apache.poi.xssf.usermodel.XSSFCell;

//엑셀의 셀 관리에 기능하는 Cell관련 유틸클래스
public class CellUtil {
	//셀타입이 날짜일 경우 String으로 변환, 문자일 경우 String값을 그래도 반환하는 메소드
	public static String findValueDate(XSSFCell dateCell){
		String imsi = "날짜값이 없습니다.";
		if(dateCell.getCellType() == 0){
			SimpleDateFormat dateform = new SimpleDateFormat("yyyy-MM-dd");
			imsi = dateform.format(dateCell.getDateCellValue());
		}else if(dateCell.getCellType() == 1){
			imsi = dateCell.getStringCellValue();
		}
		return imsi;
	}
	
	//셀타입이 문자(1)일 경우 저장하여 반환하는 메소드
	public static String findValueString(XSSFCell stringCell){
		String imsi = "문자값이 없습니다.";
		if(stringCell != null){
			imsi = stringCell.getStringCellValue();
		}
		return imsi;
	}
	
	//일반 숫자 형식의 셀이 빈셀이 아닐 경우 셀값을 반환, 빈셀일 경우 0을 반환하는 메소드
	public static double findValueDouble(XSSFCell doubleCell){
		double imsi = 0;
		if(doubleCell != null){
			imsi = doubleCell.getNumericCellValue();
		}
		return imsi;
	}
}
