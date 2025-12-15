import java.sql.*;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

public class AddItem implements SessionAware {
	
    //declare variables of the same name as the webpage paramters taken in
	private Map<String, Object> session;
	private String itemname;
	private int startingbid;
	
	public AddItem() {
	}
	
	public String addItem() {
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
			
			
			
			
			ps = connection.prepareStatement(
				"INSERT INTO items (itemname, userid, startingbid) VALUES (?, ?, ?)");
			ps.setString(1, itemname);
			ps.setInt(2, userid);
			ps.setInt(3, startingbid);
			
			int rows = ps.executeUpdate();
			ps.close();
			connection.close();
			
			if(rows > 0) {
				return "Success";
			}
			



		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return "Failure";
	}
	



	@Override
	public void setSession(Map session) {
		this.session = session;
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
}
