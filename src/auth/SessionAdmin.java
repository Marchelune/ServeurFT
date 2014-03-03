package auth;



import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.EntitySubclass;

import donnees.User;

@EntitySubclass
public class SessionAdmin extends Session {

	public SessionAdmin(Key<User> userKey) {
		super(userKey);
		// TODO Auto-generated constructor stub
	}

	
	
	
	
}
