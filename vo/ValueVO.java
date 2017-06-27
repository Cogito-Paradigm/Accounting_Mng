package vo;

public class ValueVO {
	private String date;
	private String type;
	private String account;
	private double cha;
	private double dae;
	
	public ValueVO(){
	}
	public ValueVO(String type, String date, String account, double cha, double dae){
		this.date = date;
		this.type = type;
		this.account = account;
		this.cha = cha;
		this.dae = dae;
	}
	
	public String toString(){
		return "날짜 : "+date+"  분류 : "+type+"  계정 : "+account+"  차변 : "+cha+"  대변 : "+dae;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public double getCha() {
		return cha;
	}

	public void setCha(double cha) {
		this.cha = cha;
	}

	public double getDae() {
		return dae;
	}

	public void setDae(double dae) {
		this.dae = dae;
	}
	
}
