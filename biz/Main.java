package biz;

import excel.ExcelMng;
import menu.MenuMng;
import vo.TotalVO;

//메인클래스
public class Main {
	public static void main(String[] args) throws Exception{
		String fileName = "D://test.xlsx";
		String sheet00 = "자산";
		String sheet01 = "부채";
		String sheet02 = "자본";
		String sheet03 = "통합";
		
		ExcelMng excelMng = new ExcelMng();
		BizMng bizMng = new BizMng();
		TotalVO totalVO = new TotalVO();
		
		excelMng.inputFile(fileName);
		totalVO.setAssetList(excelMng.inputSheet(sheet00));
		totalVO.setLiabilityList(excelMng.inputSheet(sheet01));
		totalVO.setCapitalList(excelMng.inputSheet(sheet02));
		
		MenuMng menuMng = new MenuMng(totalVO);
		menuMng.runMenu();
		
		excelMng.outputSheet(totalVO.getAssetList(), sheet00);
		excelMng.outputSheet(totalVO.getLiabilityList(), sheet01);
		excelMng.outputSheet(totalVO.getCapitalList(), sheet02);
		excelMng.outputTotalSheet(totalVO, sheet03);
		excelMng.outputFile(fileName);
	}
}
