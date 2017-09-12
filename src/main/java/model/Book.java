package model;

public class Book {

	private int id;
	private String title;
	private String author;
	private double price;
	private String publisher;
	private int stock;
	private String date;
	private String type;

	public Book() {
	}

	public Book(String title, String author, double price, String publisher, int stock,
			String date, String type) {
		this.title = title;
		this.author = author;
		this.price = price;
		this.publisher = publisher;
		this.stock = stock;
		this.date = date;
		this.type = type;
	}

	public int getId() {return id;}

	public void setId(int id) {this.id = id;}

	public String getTitle() {return title;}

	public void setTitle(String title) {this.title = title;}

	public String getAuthor() {return author;}

	public void setAuthor(String author) {this.author = author;}

	public double getPrice() {return price;}

	public void setPrice(double price) {this.price = price;}

	public String getPublisher() {return publisher;}

	public void setPublisher(String publisher) {this.publisher = publisher;}

	public int getStock() {return stock;}

	public void setStock(int stock) {this.stock = stock;}
	
	public void setDate(String date) {this.date = date;}
	
	public String getDate() {return date;}

	public String getType() {return type;}

	public void setType(String type) {this.type = type;}
	
}
