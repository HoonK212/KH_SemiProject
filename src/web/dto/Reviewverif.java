package web.dto;

public class Reviewverif {
 public int getUserno() {
		return userno;
	}
	public void setUserno(int userno) {
		this.userno = userno;
	}
	public int getReviewno() {
		return reviewno;
	}
	@Override
	public String toString() {
		return "Reviewverif [userno=" + userno + ", reviewno=" + reviewno + ", good_bad=" + good_bad + "]";
	}
	public void setReviewno(int reviewno) {
		this.reviewno = reviewno;
	}
	public int getGood_bad() {
		return good_bad;
	}
	public void setGood_bad(int good_bad) {
		this.good_bad = good_bad;
	}
private int userno;
 private int reviewno;
 private int good_bad;
 
}
