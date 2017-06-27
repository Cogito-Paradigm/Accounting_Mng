package biz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import util.DateUtil;
import util.StringUtil;
import vo.TotalVO;
import vo.ValueVO;

//데이터를 실제로 처리하고 관리하는 biz와 관련된 클래스
public class BizMng {
	//받은 List를 date를 기준으로 오름차순으로 정렬 후 반환하는 메소드
	public List<ValueVO> sortList(List<ValueVO> list){
		if(!list.isEmpty()){
			Collections.sort(list, new Comparator<ValueVO>(){
				public int compare(ValueVO e1, ValueVO e2){
					String[] imsi = e1.getDate().split("-");
					String[] imsi2 = e2.getDate().split("-");
					
					int year01 = Integer.parseInt(imsi[0]);
					int month01 = Integer.parseInt(imsi[1]);
					int date01 = Integer.parseInt(imsi[2]);
					
					int year02 = Integer.parseInt(imsi2[0]);
					int month02 = Integer.parseInt(imsi2[1]);
					int date02 = Integer.parseInt(imsi2[2]);
					
					if(year01>year02){
						return 1;
					}else if(year01==year02&&month01>month02){
						return 1;
					}else if(year01==year02&&month01==month02&&date01>date02){
						return 1;
					}else if(year01<year02){
						return -1;
					}else if(year01==year02&&month01<month02){
						return -1;
					}else if(year01==year02&&month01==month02&&date01<date02){
						return -1;
					}
					return 0;	
				}
			});
		}
		return list;
	}
	
	//TotalVO 내의 각 List를 종합하는 통합 totalList를 생성하여 반환하는 메소드
	public List<ValueVO> createTotalList(TotalVO totalVO){
		List<ValueVO> totalList = new ArrayList<ValueVO>();
		totalList.addAll(totalVO.getAssetList());
		totalList.addAll(totalVO.getLiabilityList());
		totalList.addAll(totalVO.getCapitalList());

		sortList(totalList);

		return totalList;
	}

	//사용자가 입력한 값을 리스트에 저장하는 메소드(자산일 경우 +를 차변 -를 대변에 저장, 부채나 자본일 경우 -를 차변 +를 대변에 저장)
	public void writeList(List<ValueVO> list){
		System.out.println("발생한 회계를 날짜/분류/계정명/금액(+,-) 순서로 입력하세요.");
		System.out.println("ex)2017-03-01/자산/현금/+3000");
		Scanner sc = new Scanner(System.in);
		String imsi = sc.nextLine();
		
		if(StringUtil.splitString(imsi, 1).equals("자산")){
			String date = StringUtil.splitString(imsi, 0);
			String type = StringUtil.splitString(imsi, 1);
			String account = StringUtil.splitString(imsi, 2);
			double cha = 0;
			double dae = 0;
			
			double imsiNum = Double.parseDouble(StringUtil.splitString(imsi, 3));
			if(imsiNum>=0){
				cha = imsiNum;
			}else{
				dae = -1*(imsiNum);
			}
			list.add(new ValueVO(type, date, account, cha, dae));
		}else{
			String date = StringUtil.splitString(imsi, 0);
			String type = StringUtil.splitString(imsi, 1);
			String account = StringUtil.splitString(imsi, 2);
			double cha = 0;
			double dae = 0;
			
			double imsiNum = Double.parseDouble(StringUtil.splitString(imsi, 3));
			if(imsiNum < 0){
				cha = -1*(imsiNum);
			}else{
				dae = imsiNum;
			}
			list.add(new ValueVO(type, date, account, cha, dae));
		}
		sortList(list);
	}
	
	//복식부기를 위한 메소드
	public void writeDouble(TotalVO totalVO){
		while(true){
			Scanner sc = new Scanner(System.in);
			System.out.println("복식부기할 메뉴를 선택하세요.");
			System.out.println("1.자산입력  2.부채입력  3.자본입력");
			int imsi = sc.nextInt();
			
			if(imsi == 1){
				writeList(totalVO.getAssetList());
				break;
			}else if(imsi == 2){
				writeList(totalVO.getLiabilityList());
				break;
			}else if(imsi == 3){
				writeList(totalVO.getCapitalList());
				break;
			}else{
				System.out.println("잘못입력하셨습니다.");
			}
		}
	}
	
	//파라미터로 받은 List의 모든내용을 순서대로 출력하는 메소드
	public void dispList(List<ValueVO> list){
		for(ValueVO e : list){
			System.out.println(e.toString());
		}
	}
	
	//TotalVO 내의 모든 List의 내용을 출력하는 메소드
	public void dispTotalList(TotalVO totalVO){
		List totalList = createTotalList(totalVO);
		dispList(totalList);
	}
	
	//List 중 입력받은 특정 날짜와 일치하는 날짜의 내용을 출력하는 메소드
	public void dispDateList(TotalVO totalVO){
		System.out.println("원하는 날짜를 입력하세요.");
		Scanner sc = new Scanner(System.in);
		String imsiDate = sc.nextLine();
		
		List<ValueVO> totalList = createTotalList(totalVO);
		for(ValueVO e : totalList){
			if(e.getDate().equals(imsiDate)){
				System.out.println(e.toString());
			}
		}
	}
	
	//List 중 현재날짜를 기준으로 num만큼의 기간의 내용을 출력하는 메소드
	public void dispPeriodList(TotalVO totalVO, int num){
		List<ValueVO> totalList = createTotalList(totalVO);
		
		for(int i = num; i>=0; i--){
			String imsiDate = DateUtil.dayBefore(i);
			for(ValueVO e : totalList){
				if(e.getDate().equals(imsiDate)){
					System.out.println(e.toString());
				}
			}
		}
	}

	//리스트의 차변과 대변의 합을 반환하는 메소드
	public double sumChaDae(List<ValueVO> list){
		double sum = 0;
		
		if(list.size() != 0){
			if(list.get(0).getType().equals("자산")){
				double sumCha = 0;
				double sumDae = 0;
				for(ValueVO e : list){
					sumCha = sumCha + e.getCha();
					sumDae = sumDae + e.getDae();
				}
				sum = sumCha - sumDae;
			}else{
				double sumCha = 0;
				double sumDae = 0;
				for(ValueVO e : list){
					sumCha = sumCha + e.getCha();
					sumDae = sumDae + e.getDae();
				}
				sum = sumDae - sumCha;
			}
		}
		return sum;
	}
	
	//받은 리스트의 차변과 대변의 합계를 출력하는 메소드
	public void dispSum(List<ValueVO> list){
		double imsi = sumChaDae(list);
		if(list.size() != 0){
			System.out.println(list.get(0).getType()+"의 총합은 "+imsi+" 입니다.");
		}
	}
	
	//리스트들을 종합하여 회계처리가 올바른지 판단하여 출력하는 메소드
	public void dispListCheck(TotalVO totalVO){
		double assetSum = sumChaDae(totalVO.getAssetList());
		double liabilitySum = sumChaDae(totalVO.getLiabilityList());
		double capitalSum = sumChaDae(totalVO.getCapitalList());
		
		System.out.println("자산의 합계 : "+assetSum);
		System.out.println("부채의 합계 : "+liabilitySum);
		System.out.println("자본의 합계 : "+capitalSum);	
		System.out.println("자산 = 부채 + 자본");
		System.out.println(assetSum+" = "+liabilitySum+" + "+capitalSum);
		if(assetSum == liabilitySum + capitalSum){
			System.out.println("올바른 회계 처리입니다.");
		}else{
			System.out.println("잘못된 회계 처리입니다.");
		}
	}
	
}
