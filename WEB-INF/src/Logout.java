import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

public class Logout implements SessionAware {
	
	//declare variables of the same name as the webpage paramters taken in

	private Map<String, Object> session;
	
	public Logout() {
	}
	
	public String logoff() {
		
		if(session != null) {


			session.clear();
		}
		return "success";
	}
	
	@Override
	public void setSession(Map session) {
		this.session = session;
	}
}
