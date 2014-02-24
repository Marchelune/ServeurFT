package auth;


import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import donnees.User;

@Entity
@Cache
public abstract class Session {
	
	@Id private long id; //à renvoyer dans les requêtes
	private Key<User> userKey;
	
	
	public long getId() {
		return id;
	}
	public Key<User> getUserKey() {
		return userKey;
	}
	
	public Session(long id, Key<User> userKey) {
		this.id = id;
		this.userKey = userKey;
	}
	
	
	

}
