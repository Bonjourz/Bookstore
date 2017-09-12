package model;

public class Cart {
	private int id;
	private int buyerid;
	private int num;
	private int bookid;
	
	public Cart() {}
	
	public Cart(int buyerid, int bookid, int num) {
		this.bookid = bookid;
		this.buyerid = buyerid;
		this.num = num;
	}
	
	public int getBookid() {return bookid;}

	public void setBookid(int bookid) {this.bookid = bookid;}
	
	public int getBuyerid() {return buyerid;}

	public void setBuyerid(int buyerid) {this.buyerid = buyerid;}
	
	public void setId(int id) {this.id = id;}
	
	public int getId() {return id;}
	
	public void setNum(int num) {this.num = num;}
	
	public int getNum() {return num;}

	private Book book;
	
	public Book getBook() {return book;}

	public void setBook(Book book) {this.book = book;}


}
