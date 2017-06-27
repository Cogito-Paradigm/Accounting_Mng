package excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import biz.BizMng;
import util.CellUtil;
import vo.TotalVO;
import vo.ValueVO;

//엑셀(데이터베이스)에서 데이터를 읽어오고, 내보내는 클래스
public class ExcelMng {
	private XSSFWorkbook workbook;
	
	//fileName이라는 경로와 이름의 excel 파일을 받아 workbook에 저장하는 메소드 
	public void inputFile(String fileName) throws Exception{
		FileInputStream file = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		this.workbook = workbook;
	}
	
	//workbook의 내용을 fileName이라는 경로와 이름의 excel 파일로 저장하는 메소드
	public void outputFile(String fileName) throws Exception{
		FileOutputStream file = new FileOutputStream(fileName);
		workbook.write(file);
		file.close();
	}
	
	//sheet 이름을 받아 해당하는 sheet가 있으면 해당 sheet를 반환 없으면 동일한 sheet를 생성하여 반환
	public XSSFSheet findSheet(String sheetName){
		if(workbook.getSheet(sheetName) != null){
			return workbook.getSheet(sheetName);
		}else{
			XSSFSheet sheet = workbook.createSheet(sheetName);
			return sheet;
		}
	}
	
	//해당 시트의 정보를 읽어 List로 반환
	public List<ValueVO> inputSheet(String sheetName){
		List<ValueVO> list = new ArrayList<ValueVO>();
		XSSFSheet sheet = findSheet(sheetName);
		XSSFRow row;
		String cellDate = "";
		String cellAccount = "";
		double cellCha = 0;
		double cellDae = 0;
		
		int lastRowNum = sheet.getLastRowNum();
		for(int i = 1; i<=lastRowNum; i++){
			row = sheet.getRow(i);
			cellDate = CellUtil.findValueDate(row.getCell(0));
			cellAccount = CellUtil.findValueString(row.getCell(1));
			cellCha = CellUtil.findValueDouble(row.getCell(2));
			cellDae = CellUtil.findValueDouble(row.getCell(3));
			list.add(new ValueVO(sheetName, cellDate, cellAccount, cellCha, cellDae));
		}
		return list;
	}
	
	//List를 받아 List의 정보를 해당 sheet에 저장
	public void outputSheet(List<ValueVO> list, String sheetName){
		//리스트 중 첫번째 type을 이용하여 type과 일치하는 시트를 가져옴
		XSSFSheet sheet = findSheet(sheetName);
		
		int i = 0;	
		XSSFRow row = sheet.createRow(i);
		row.createCell(0).setCellValue("날짜");
		row.createCell(1).setCellValue("계정");
		row.createCell(2).setCellValue("차변");
		row.createCell(3).setCellValue("대변");
		
		if(list != null){
			for(ValueVO e : list){
				row = sheet.createRow(i+1);
				row.createCell(0).setCellValue(e.getDate());
				row.createCell(1).setCellValue(e.getAccount());
				if(e.getCha() != 0){
					row.createCell(2).setCellValue(e.getCha());
				}
				if(e.getDae() != 0){
					row.createCell(3).setCellValue(e.getDae());
				}
				i++;
			}
		}
	}

	//TotalVO를 받아 모든 List의 내용을 포함하는 새로운 sheet를 생성 후 저장
	public void outputTotalSheet(TotalVO totalVO, String sheetName){
		XSSFSheet sheet = findSheet(sheetName);
		
		BizMng bizMng = new BizMng();
		List<ValueVO> totalList = bizMng.createTotalList(totalVO);
		
		int i = 0;	
		XSSFRow row = sheet.createRow(i);
		row.createCell(0).setCellValue("날짜");
		row.createCell(1).setCellValue("자산계정");
		row.createCell(2).setCellValue("자산차변");
		row.createCell(3).setCellValue("자산대변");
		row.createCell(4).setCellValue("부채계정");
		row.createCell(5).setCellValue("부채차변");
		row.createCell(6).setCellValue("부채대변");
		row.createCell(7).setCellValue("자본계정");
		row.createCell(8).setCellValue("자본차변");
		row.createCell(9).setCellValue("자본대변");

		if(totalList != null){
			for(ValueVO e : totalList){
				row = sheet.createRow(i+1);
				if(e.getType().equals("자산")){
					row.createCell(0).setCellValue(e.getDate());
					row.createCell(1).setCellValue(e.getAccount());
					if(e.getCha() != 0){
						row.createCell(2).setCellValue(e.getCha());
					}
					if(e.getDae() != 0){
						row.createCell(3).setCellValue(e.getDae());
					}
				}else if(e.getType().equals("부채")){
					row.createCell(0).setCellValue(e.getDate());
					row.createCell(4).setCellValue(e.getAccount());
					if(e.getCha() != 0){
						row.createCell(5).setCellValue(e.getCha());
					}
					if(e.getDae() != 0){
						row.createCell(6).setCellValue(e.getDae());
					}
				}else if(e.getType().equals("자본")){
					row.createCell(0).setCellValue(e.getDate());
					row.createCell(7).setCellValue(e.getAccount());
					if(e.getCha() != 0){
						row.createCell(8).setCellValue(e.getCha());
					}
					if(e.getDae() != 0){
						row.createCell(9).setCellValue(e.getDae());
					}
				}
				i++;
			}
		}
	}
}