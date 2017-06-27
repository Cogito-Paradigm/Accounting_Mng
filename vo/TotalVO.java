package vo;

import java.util.ArrayList;
import java.util.List;

public class TotalVO {
	List<ValueVO> assetList = new ArrayList<ValueVO>();
	List<ValueVO> liabilityList = new ArrayList<ValueVO>();
	List<ValueVO> capitalList = new ArrayList<ValueVO>();
	
	
	public List<ValueVO> getAssetList() {
		return assetList;
	}
	public void setAssetList(List<ValueVO> assetList) {
		this.assetList = assetList;
	}
	public List<ValueVO> getLiabilityList() {
		return liabilityList;
	}
	public void setLiabilityList(List<ValueVO> liabilityList) {
		this.liabilityList = liabilityList;
	}
	public List<ValueVO> getCapitalList() {
		return capitalList;
	}
	public void setCapitalList(List<ValueVO> capitalList) {
		this.capitalList = capitalList;
	}
}
