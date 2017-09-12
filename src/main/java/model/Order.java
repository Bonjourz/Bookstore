package model;

import java.util.HashSet;
import java.util.Set;
import java.util.Date;

public class Order {
	private int orderid;
	private String date;
	private String address;
	private String status;
	private int buyerid;
	private double price;
	
	public Order(){}
	
	public Order(int buyerid, String date, String address,
			String status, double price) {
		this.buyerid = buyerid;
		this.date = date;
		this.address = address;
		this.status = status;
		this.price = price;
	}
	
	public int getOrderid() {return orderid;}

	public void setOrderid(int orderid) {this.orderid = orderid;}
	
	public int getBuyerid() {return buyerid;}

	public void setBuyerid(int buyerid) {this.buyerid = buyerid;}
	
	public String getDate() {return date;}
	
	public void setDate(String date) {this.date = date;}

	public String getStatus() {return status;}

	public void setStatus(String status) {this.status = status;}

	public String getAddress() {return address;}
	
	public void setAddress(String address) {this.address = address;}
	
	public double getPrice() {return price;}
	
	public void setPrice(double price) { this.price = price;}
	
	private Buyer buyer ;

	public Buyer getBuyer() {return buyer;}

	public void setBuyer(Buyer buyer) {this.buyer = buyer;}


}

