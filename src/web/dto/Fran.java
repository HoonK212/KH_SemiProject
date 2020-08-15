package web.dto;

public class Fran {

	private int franNo;
	private String franName;
	private int	foodNo;
	@Override
	public String toString() {
		return "Fran [franNo=" + franNo + ", franName=" + franName + ", foodNo=" + foodNo + "]";
	}
	public int getFranNo() {
		return franNo;
	}
	public void setFranNo(int franNo) {
		this.franNo = franNo;
	}
	public String getFranName() {
		return franName;
	}
	public void setFranName(String franName) {
		this.franName = franName;
	}
	public int getFoodNo() {
		return foodNo;
	}
	public void setFoodNo(int foodNo) {
		this.foodNo = foodNo;
	}

	
	
	
}
