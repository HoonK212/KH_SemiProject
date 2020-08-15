package web.dto;

public class Grade {

	private int	no;
	private String	id;
	private int	grade;
	private int	starCnt;
	private int	reviewCnt;
	
	@Override
	public String toString() {
		return "Grade [no=" + no + ", id=" + id + ", grade=" + grade + ", starCnt=" + starCnt + ", reviewCnt="
				+ reviewCnt + "]";
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getStarCnt() {
		return starCnt;
	}
	public void setStarCnt(int starCnt) {
		this.starCnt = starCnt;
	}
	public int getReviewCnt() {
		return reviewCnt;
	}
	public void setReviewCnt(int reviewCnt) {
		this.reviewCnt = reviewCnt;
	}
	
	
	
}
