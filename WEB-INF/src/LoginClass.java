import java.sql.*;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;


public class LoginClass implements SessionAware {
	
	//declare variables of the same name as the webpage paramters taken in
	private String username;
	private String password;
	private Map<String, Object> session;
	

	
	public LoginClass() {

	}
	
	public String Login(){

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		if(username != null && password != null && username.length() < 20 ){
			try {
				connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ebay?serverTimezone=UTC","root", "root");
				
				ps = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
				ps.setString(1, username);
				ps.setString(2, password);
				
				rs = ps.executeQuery();
				
				if(rs.next()){
					
					session.put("currentUser", username);
					
					rs.close();
				    ps.close();
					return "Success";
				} else {
					
					rs.close();
					ps.close();
					return "Failure";
				}

				
				
			} catch (SQLException e1) {
				e1.printStackTrace();
				return "Failure";
			} 
			
		}
		
		return "Failure";





		

		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public void setSession(Map session) {
		this.session = session;
	}
	
	
	

	

}
