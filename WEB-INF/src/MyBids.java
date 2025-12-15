import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

public class MyBids implements SessionAware {
	
	//declare variables of the same name as the webpage paramters taken in
	private Map<String, Object> session;
	private ArrayList<Bid> myBidsList;
	
	public MyBids() {
		myBidsList = new ArrayList<Bid>();
	}
	
	public String mybids() {
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
			
			
			String query = "SELECT b.bidid, b.itemid, b.bidamount, i.itemname " +
						   "FROM bids b " +
						   "JOIN items i ON b.itemid = i.itemid " +
						   "JOIN users u ON b.userid = u.userid " +
						   "WHERE u.username = ? " +
						   "ORDER BY b.bidamount DESC";
			


			ps = connection.prepareStatement(query);
			ps.setString(1, currentUser);

			rs = ps.executeQuery();
			
			while(rs.next()) {

				Bid bid = new Bid();
				bid.setBidid(rs.getInt("bidid"));
				bid.setItemid(rs.getInt("itemid"));
				bid.setBidamount(rs.getInt("bidamount"));
				bid.setItemname(rs.getString("itemname"));

				myBidsList.add(bid);


			}
			
			rs.close();
			ps.close();
			return "Success";
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return "Failure";
	}
	
	@Override
	public void setSession(Map session) {
		this.session = session;
	}
	
	public ArrayList<Bid> getMyBidsList() {
		return myBidsList;
	}
}
