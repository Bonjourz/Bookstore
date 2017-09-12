package model;

public class Statics {
	private int Id;
	private String month;
	private int buyerId;
	private double price;
	private String type;
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	public int getBuyerId() {
		return buyerId;
	}
	
	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
