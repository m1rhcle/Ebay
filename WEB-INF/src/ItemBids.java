import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

public class ItemBids implements SessionAware {
	
	private Map session;
	private int itemid;
	private String itemname;
	private int startingbid;
	private String owner;
	private int currentbid;
	private ArrayList<Bid> bidsList;
	
	public ItemBids() {
		bidsList = new ArrayList<Bid>();
	}
	
	public String itemDetails() {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/ebay?serverTimezone=UTC","root", "root");
			
			
			String selectitemdetails = "SELECT i.itemid, i.itemname, i.startingbid, " +
				"u.username as owner, " +
				 "(SELECT MAX(bidamount) FROM bids WHERE itemid = i.itemid) as currentbid " +
				 "FROM items i " +
				 "LEFT JOIN users u ON i.userid = u.userid " +
				 "WHERE i.itemid = ?";
			
			ps = connection.prepareStatement(selectitemdetails);
			ps.setInt(1, itemid);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				this.itemname = rs.getString("itemname");
				this.startingbid = rs.getInt("startingbid");
				this.owner = rs.getString("owner");
				
				int maxBid = rs.getInt("currentbid");
				if(rs.wasNull()) {
					this.currentbid = startingbid;
				} else {
					this.currentbid = maxBid;
				}
			}
			
			rs.close();
			ps.close();
			
		
			String selectbiddetails = "SELECT b.bidid, b.itemid, b.userid, b.bidamount, " +
				"u.username, i.itemname " +
				 "FROM bids b " +
				 "JOIN users u ON b.userid = u.userid " +
				 "JOIN items i ON b.itemid = i.itemid " +
				 "WHERE b.itemid = ? " +
				 "ORDER BY b.bidamount DESC";
			
			ps = connection.prepareStatement(selectbiddetails);
			ps.setInt(1, itemid);
			rs = ps.executeQuery();
			
			while(rs.next()) {

				Bid bid = new Bid();
				bid.setBidid(rs.getInt("bidid"));
				bid.setItemid(rs.getInt("itemid"));

				bid.setUserid(rs.getInt("userid"));
				bid.setBidamount(rs.getInt("bidamount"));
				bid.setUsername(rs.getString("username"));
				bid.setItemname(rs.getString("itemname"));
			bidsList.add(bid);
		}
		
		rs.close();
		ps.close();
		return "Success";
		
	} catch (SQLException e) {
		e.printStackTrace();
	} 
	
	return "Failure";
}	@Override
	public void setSession(Map session) {
		this.session = session;
	}
	
	public int getItemid() {
		return itemid;
	}
	
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	
	public String getItemname() {
		return itemname;
	}
	
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	
	public int getStartingbid() {
		return startingbid;
	}
	
	public void setStartingbid(int startingbid) {
		this.startingbid = startingbid;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public int getCurrentbid() {
		return currentbid;
	}
	
	public void setCurrentbid(int currentbid) {
		this.currentbid = currentbid;
	}
	
	public ArrayList<Bid> getBidsList() {
		return bidsList;
	}
}
