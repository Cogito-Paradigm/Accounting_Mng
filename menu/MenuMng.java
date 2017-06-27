package menu;

import java.util.Scanner;

import biz.BizMng;
import vo.TotalVO;

//메뉴목록들을 관리하여 실제 프로그램 화면에 보여지게 하는 클래스
public class MenuMng {
	TotalVO totalVO;
	MainMenu main = new MainMenu();
	SubMenu01 sub01 = new SubMenu01();
	SubMenu02 sub02 = new SubMenu02();
	SubMenu03 sub03 = new SubMenu03();
	SubMenu04 sub04 = new SubMenu04();
	BizMng bizMng = new BizMng();
	
	//생성자
	public MenuMng(){
	}
	public MenuMng(TotalVO totalVO){
		this.totalVO = totalVO;
	}
	
	//입력받은 숫자를 통해 서브메뉴 소환, 5(저장 및 종료)가 입력될 때까지 반복하는 메소드
	public void runMenu(){
		Scanner sc = new Scanner(System.in);
		int imsi = 0;
		
		while(imsi !=5){
			System.out.println("원하는 작업을 선택하세요.");
			System.out.println(main.toString());
			imsi = sc.nextInt();
			
			if(imsi==1){
				runSub01();
			}else if(imsi==2){
				runSub02();
			}else if(imsi==2){
				runSub02();
			}else if(imsi==3){
				runSub03();
			}else if(imsi==4){
				runSub04();
			}
		}	
		System.out.println("변경된 사항을 저장하고 종료합니다.");
	}
	
	//입력받은 숫자를 통해 작업을 수행할 메소드 소환, 4(상위 메뉴로)가 입력될 때까지 반복하는 메소드
	public void runSub01(){
		Scanner sc = new Scanner(System.in);
		int imsi = 0;
		
		while(imsi != 4){
			System.out.println("원하는 작업을 선택하세요.");
			System.out.println(sub01.toString());
			imsi = sc.nextInt();
			
			if(imsi == 1){
				bizMng.writeList(totalVO.getAssetList());
				bizMng.writeDouble(totalVO);
			}else if(imsi ==2){
				bizMng.writeList(totalVO.getLiabilityList());
				bizMng.writeDouble(totalVO);
			}else if(imsi ==3){
				bizMng.writeList(totalVO.getCapitalList());
				bizMng.writeDouble(totalVO);
			}else if(imsi ==4){
				System.out.println("상위메뉴로 돌아갑니다.");
			}else{
				System.out.println("잘못누르셨습니다.");
			}
		}
	}
	
	//입력받은 숫자를 통해 작업을 수행할 메소드 소환, 5(상위 메뉴로)가 입력될 때까지 반복하는 메소드
	public void runSub02(){
		Scanner sc = new Scanner(System.in);
		int imsi = 0;
		
		while(imsi != 5){
			System.out.println("원하는 작업을 선택하세요.");
			System.out.println(sub02.toString());
			imsi = sc.nextInt();
			if(imsi == 1){
				bizMng.dispList(totalVO.getAssetList());
			}else if(imsi == 2){
				bizMng.dispList(totalVO.getLiabilityList());
			}else if(imsi == 3){
				bizMng.dispList(totalVO.getCapitalList());
			}else if(imsi == 4){
				bizMng.dispTotalList(totalVO);
			}else if(imsi == 5){
				System.out.println("상위메뉴로 돌아갑니다.");
			}else{
				System.out.println("잘못누르셨습니다.");
			}
		}
	}
	
	//입력받은 숫자를 통해 작업을 수행할 메소드 소환, 5(상위 메뉴로)가 입력될 때까지 반복하는 메소드
	public void runSub03(){
		Scanner sc = new Scanner(System.in);
		int imsi = 0;
		
		while(imsi != 5){
			System.out.println("원하는 작업을 선택하세요.");
			System.out.println(sub03.toString());
			imsi = sc.nextInt();
			if(imsi == 1){
				bizMng.dispDateList(totalVO);
			}else if(imsi == 2){
				bizMng.dispPeriodList(totalVO, 7);
			}else if(imsi == 3){
				bizMng.dispPeriodList(totalVO, 30);
			}else if(imsi == 4){
				bizMng.dispTotalList(totalVO);
			}else if(imsi == 5){
				System.out.println("상위메뉴로 돌아갑니다.");
			}else{
				System.out.println("잘못누르셨습니다.");
			}
		}
	}
	
	//입력받은 숫자를 통해 작업을 수행할 메소드 소환, 5(상위 메뉴로)가 입력될 때까지 반복하는 메소드
	public void runSub04(){
		Scanner sc = new Scanner(System.in);
		int imsi = 0;
		
		while(imsi != 5){
			System.out.println("원하는 작업을 선택하세요.");
			System.out.println(sub04.toString());
			imsi = sc.nextInt();
			if(imsi == 1){
				bizMng.dispSum(totalVO.getAssetList());
			}else if(imsi == 2){
				bizMng.dispSum(totalVO.getLiabilityList());
			}else if(imsi == 3){
				bizMng.dispSum(totalVO.getCapitalList());
			}else if(imsi == 4){
				bizMng.dispListCheck(totalVO);
			}else if(imsi == 5){
				System.out.println("상위메뉴로 돌아갑니다.");
			}else{
				System.out.println("잘못누르셨습니다.");
			}
		}
	}
	
}