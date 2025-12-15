import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

public class ViewItems implements SessionAware {
	
    //declare variables of the same name as the webpage paramters taken in
	private Map<String, Object> session;

	private ArrayList<AllItems> itemsList;
	
	public ViewItems() {
		itemsList = new ArrayList<AllItems>();


	}
	
	public String viewItems() {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/ebay?serverTimezone=UTC","root", "root");
			
			
			String items = "SELECT i.itemid, i.itemname, i.startingbid, u.username as owner, " +
						   "(SELECT MAX(bidamount) FROM bids WHERE itemid = i.itemid) as currentbid " +
						   "FROM items i " +
						   "LEFT JOIN users u ON i.userid = u.userid " +
						   "ORDER BY i.itemid DESC";
			
			ps = connection.prepareStatement(items);
			rs = ps.executeQuery();
			
			while(rs.next()) {

				AllItems item = new AllItems();
				item.setItemid(rs.getInt("itemid"));
				item.setItemname(rs.getString("itemname"));
				item.setStartingbid(rs.getInt("startingbid"));
				item.setOwner(rs.getString("owner"));
				
				int currentbid = rs.getInt("currentbid");
				if(rs.wasNull()) {
					item.setCurrentbid(rs.getInt("startingbid"));
				} else {
					item.setCurrentbid(currentbid);
				}
				
				itemsList.add(item);


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
	
	public ArrayList<AllItems> getItemsList() {
		return itemsList;
	}
}
