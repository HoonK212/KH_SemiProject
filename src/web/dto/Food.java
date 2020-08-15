package web.dto;

public class Food {

	private int foodNo;
	private String foodName;
	@Override
	public String toString() {
		return "Food [foodNo=" + foodNo + ", foodName=" + foodName + "]";
	}
	public int getFoodNo() {
		return foodNo;
	}
	public void setFoodNo(int foodNo) {
		this.foodNo = foodNo;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	
	
}
