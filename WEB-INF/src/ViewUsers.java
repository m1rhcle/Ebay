import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

public class ViewUsers implements SessionAware {

	//declare variables of the same name as the webpage paramters taken in
	private Map<String, Object> session;
	private ArrayList<User> usersList;
	
	public ViewUsers() {
		usersList = new ArrayList<User>();
	}
	
	public String viewusers() {
		String currentUser = (String) session.get("currentUser");
		
		if(currentUser == null) {
			return "error";
		}
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/ebay?serverTimezone=UTC","root", "root");
			
			ps = connection.prepareStatement("SELECT userid, username, email FROM users ORDER BY userid");
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				User user = new User();
				user.setUserid(rs.getInt("userid"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				usersList.add(user);
			}
			rs.close();
			ps.close();
			return "success";
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
			 
		
		
		return "error";
	}
	
	@Override
	public void setSession(Map session) {
		this.session = session;
	}
	
	public ArrayList<User> getUsersList() {
		return usersList;
	}
}
