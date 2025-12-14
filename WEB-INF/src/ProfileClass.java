import java.sql.*;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

public class ProfileClass implements SessionAware {
	
	//declare variables of the same name as the webpage paramters taken in
	
	private Map<String, Object> session;
	private String username;
	private String email;
	private int userid;
	
	public ProfileClass() {
	}
	
	public String viewProfile() {
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
			
			ps = connection.prepareStatement("SELECT userid, username, email FROM users WHERE username = ?");
			ps.setString(1, currentUser);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				this.userid = rs.getInt("userid");
				this.username = rs.getString("username");
				this.email = rs.getString("email");
				return "success";
			}
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return "error";
	}
	
	@Override
	public void setSession(Map session) {
		this.session = session;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public int getUserid() {
		return userid;
	}
}
