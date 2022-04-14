
public class RentVO {
	private int rent_num;
	private int han_num;
	private int mem_num;
	private String rent_start; 
	private String rent_return;
	private String rent_real_return;
	private int rent_fee;
	private String rent_status;
	private int rent_sum;
	private int star_score;

	public String getRent_status() {
		return rent_status;
	}


	public void setRent_status(String rent_status) {
		this.rent_status = rent_status;
	}


	public int getRent_num() {
		return rent_num;
	}


	public void setRent_num(int rent_num) {
		this.rent_num = rent_num;
	}

	public int getHan_num() {
		return han_num;
	}


	public void setHan_num(int han_num) {
		this.han_num = han_num;
	}


	public int getMem_num() {
		return mem_num;
	}


	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}


	public int getRent_sum() {
		return rent_sum;
	}


	public void setRent_sum(int rent_sum) {
		this.rent_sum = rent_sum;
	}


	public String getRent_start() {
		return rent_start;
	}


	public void setRent_start(String rent_start) {
		this.rent_start = rent_start;
	}


	public String getRent_return() {
		return rent_return;
	}


	public void setRent_return(String rent_return) {
		this.rent_return = rent_return;
	}


	public String getRent_real_return() {
		return rent_real_return;
	}


	public void setRent_real_return(String rent_real_return) {
		this.rent_real_return = rent_real_return;
	}


	public int getRent_fee() {
		return rent_fee;
	}


	public void setRent_fee(int rent_fee) {
		this.rent_fee = rent_fee;
	}


	public int getStar_score() {
		return star_score;
	}


	public void setStar_score(int star_score) {
		this.star_score = star_score;
	}


	public RentVO() {
		
	}
}