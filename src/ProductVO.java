import java.awt.Image;
import java.awt.image.BufferedImage;

public class ProductVO {
	private int han_num;
	private int han_sum;
	private int han_rend;
	private int han_left;
	private int han_price;
	private int rent_num;
	private String han_name;
	private String han_size;
	private String han_gender;
	private String han_age;
	private Image han_image;
	private String han_image_path; 
	
	private int rent_fee; 

	private String rent_start; 
	private String rent_return;
	private String rent_status;

	
	public int getRent_num() {
		return rent_num;
	}

	public void setRent_num(int rent_num) {
		this.rent_num = rent_num;
	}

	public String getRent_status() {
		return rent_status;
	}

	public void setRent_status(String rent_status) {
		this.rent_status = rent_status;
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

	public ProductVO() {
		
	}
	
	public int getHan_num() {
		return han_num;
	}
	public void setHan_num(int han_num) {
		this.han_num = han_num;
	}
	public int getHan_sum() {
		return han_sum;
	}
	public void setHan_sum(int han_sum) {
		this.han_sum = han_sum;
	}
	public int getHan_rend() {
		return han_rend;
	}
	public void setHan_rend(int han_rend) {
		this.han_rend = han_rend;
	}
	public int getHan_left() {
		return han_left;
	}
	public void setHan_left(int han_left) {
		this.han_left = han_left;
	}
	public String getHan_name() {
		return han_name;
	}
	public void setHan_name(String han_name) {
		this.han_name = han_name;
	}
	public String getHan_size() {
		return han_size;
	}
	public void setHan_size(String han_size) {
		this.han_size = han_size;
	}
	public String getHan_gender() {
		return han_gender;
	}
	public void setHan_gender(String han_gender) {
		this.han_gender = han_gender;
	}
	public String getHan_age() {
		return han_age;
	}
	public void setHan_age(String han_age) {
		this.han_age = han_age;
	}
	public int getHan_price() {
		return han_price;
	}
	public void setHan_price(int han_price) {
		this.han_price = han_price;
	}

	public Image getHan_image() {
		return han_image;
	}

	public void setHan_image(Image han_image) {
		this.han_image = han_image;
	}
	
	public String getHan_image_path() {
		return han_image_path;
	}

	public void setHan_image_path(String han_image_path) {
		this.han_image_path = han_image_path;
	}
	
	public int getRent_fee() {
		return rent_fee;
	}
	
	public void setRent_fee(int rent_fee) {
		this.rent_fee = rent_fee;
	}


}
