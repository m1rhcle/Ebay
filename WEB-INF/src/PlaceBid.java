import java.sql.*;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

public class PlaceBid implements SessionAware {
	
    //declare variables of the same name as the webpage paramters taken in
	private Map<String, Object> session;
	private int itemid;
	private int bidamount;
	
	public PlaceBid() {
	}
	
	public String placeBid() {
		String currentUser = (String) session.get("currentUser");
		
		if(currentUser == null) {
			return "Failure";
		}
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/ebay?serverTimezone=UTC","root", "root");
			
			
			ps = connection.prepareStatement("SELECT userid FROM users WHERE username = ?");
			ps.setString(1, currentUser);
			rs = ps.executeQuery();
			


			int userid = 0;
			if(rs.next()) {
				userid = rs.getInt("userid");
			}

			rs.close();
			ps.close();
			
			if(userid == 0) {
				return "Failure";
			}
			
		 
		ps = connection.prepareStatement(
			"SELECT i.startingbid, (SELECT MAX(bidamount) FROM bids WHERE itemid = i.itemid) as maxbid " +
			"FROM items i " +
			"WHERE i.itemid = ?");


		ps.setInt(1, itemid);
		rs = ps.executeQuery();
		
		int minBid = 0;


		if(rs.next()) {
			int startingbid = rs.getInt("startingbid");
			int maxbid = rs.getInt("maxbid");


			if(rs.wasNull()) {


				minBid = startingbid;
			}
             else {

				minBid = Math.max(startingbid, maxbid);
			}
		} else {

			return "error"; 

		}
		rs.close();
		ps.close();
		
		if(bidamount <= minBid) {
			return "error"; 
		}			

			ps = connection.prepareStatement(
				"INSERT INTO bids (itemid, userid, bidamount) VALUES (?, ?, ?)");
			ps.setInt(1, itemid);
			ps.setInt(2, userid);
			ps.setInt(3, bidamount);


			
			int rows = ps.executeUpdate();
			ps.close();

			connection.close();
			
			if(rows > 0) {
				return "success";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return "error";
	}
	
	@Override
	public void setSession(Map session) {
		this.session = session;
	}
	
	public int getItemid() {
		return itemid;
	}
	
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	
	public int getBidamount() {
		return bidamount;
	}
	
	public void setBidamount(int bidamount) {
		this.bidamount = bidamount;
	}
}
