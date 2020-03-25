package entity;

public class Shop {
	//条码
	private String shopID;
	//名称
	private String shopName;
	//进价
	private double costPrice;
	//售价
	private double sellPrice;
	//库存量
	private int num;
	//分类
	private int category;
	//厂家
	private String marker;
	//架号
	private String shelfID;
	
	public String getShelfID() {
		return shelfID;
	}
	public void setShelfID(String shelfID) {
		this.shelfID = shelfID;
	}
	public String getShopID() {
		return shopID;
	}
	public void setShopID(String shopID) {
		this.shopID = shopID;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}
	public double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getMarker() {
		return marker;
	}
	public void setMarker(String marker) {
		this.marker = marker;
	}
	
	
	
}
