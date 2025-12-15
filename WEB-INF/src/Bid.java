public class Bid {
	private int bidid;
	private int itemid;
	private int userid;
	private int bidamount;
	private String username;  
	private String itemname;  
	
	public Bid() {
	}
	
	public int getBidid() {
		return bidid;
	}
	
	public void setBidid(int bidid) {
		this.bidid = bidid;
	}
	
	public int getItemid() {
		return itemid;
	}
	
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	
	public int getUserid() {
		return userid;
	}
	
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public int getBidamount() {
		return bidamount;
	}
	
	public void setBidamount(int bidamount) {
		this.bidamount = bidamount;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getItemname() {
		return itemname;
	}
	
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
}
