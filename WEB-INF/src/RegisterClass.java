import java.sql.*;

public class RegisterClass {
	
	//declare variables of the same name as the webpage paramters taken in
	private String username;
	private String password;
	private String email;

	
	public RegisterClass() {

	}
	
	public String registration() {
		int userid = 0;

		Connection connection = null;
		if(username != null && password != null && email != null){
			if(username.length() < 20 && password.contains("!") && email.contains("@")){


				try {
 			connection = DriverManager.getConnection(
 					"jdbc:mysql://localhost:3306/ebay?serverTimezone=UTC","root", "root");
 	       		 String insert = "INSERT into users (username, password, email)  VALUES (?,?,?)";
 				 PreparedStatement userentry = connection.prepareStatement(insert);
 					
 			userentry.setString(1, username);
             userentry.setString(2, password);
             userentry.setString(3, email);

             int rows = userentry.executeUpdate();

             if (rows > 0) {
                 System.out.println("User successfully registered: " + username);
             } else {
                 System.out.println("No rows inserted.");
             }
            
            
 			
 			Statement select = connection.createStatement();
 			ResultSet rs = select.executeQuery("SELECT * from users");

			while(rs.next()) {
				System.out.println("Column 1 in ResultSet : "+rs.getString(1) + ", " + rs.getString(2) + ", " + rs.getString(3));
			}
 			
			rs.close();
 			userentry.close();
 			//GET USERID
 			
 			PreparedStatement useridps= connection.prepareStatement("SELECT userid FROM users WHERE username = ?");
 	        useridps.setString(1,username);
 	        
 	        ResultSet useridrs = useridps.executeQuery();
 	        
 	        
 	        while(useridrs.next()) {
 	        	
 	        	userid = useridrs.getInt("userid");
 	        }
 	        
 	        useridrs.close();
 	        useridps.close();
 			
 	        // Create user balance
 			PreparedStatement userbalance = connection.prepareStatement("Insert into userbalance(userid) Values(?)" );
 			
 			userbalance.setInt(1, userid);
 			userbalance.executeUpdate();
 			
 			userbalance.close();
 		
 			
 		} catch (SQLException e1) {
 			e1.printStackTrace();
 		}


		return "User Registered";


			
		}
	}
	
	return "Registration Failed";
			


	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

}
