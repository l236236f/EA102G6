package shopping;

public class Sprod implements java.io.Serializable{
	
	public	Sprod() {
		name = "";
		venNo = "";
		prodNo = "";
		price = 0;
		quantity = 0;
	}
	
	private String name;
	private String venNo;
	private int price;
	private int quantity;
	private String prodNo;
	
	public String getName() {
		return name;
	}
	public String getProdNo() {
		return prodNo;
	}
	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVenNo() {
		return venNo;
	}
	public void setVenNo(String venNo) {
		this.venNo = venNo;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
		
}
