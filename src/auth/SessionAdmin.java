package auth;



import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.EntitySubclass;

import donnees.User;

@EntitySubclass
public class SessionAdmin extends Session {

	public SessionAdmin(long id, Key<User> userKey) {
		super(id, userKey);

	}
	
	
	
}
