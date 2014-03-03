package auth;


import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import donnees.User;

@Entity
@Cache
public class Session {
	
	@Id private Long id; //à renvoyer dans les requêtes TODO long ou Long ?
	private Key<User> userKey;
	private int lastUpdatedExercice;
	
	public Session(){} 
	
	public int getLastUpdatedExercice() {
		return lastUpdatedExercice;
	}
	public void setLastUpdatedExercice(int lastUpdatedExercice) {
		this.lastUpdatedExercice = lastUpdatedExercice;
	}
	
	public long getId() {
		return id;
	}
	public Key<User> getUserKey() {
		return userKey;
	}
	
	public Session( Key<User> userKey) {
		this.userKey = userKey;
		lastUpdatedExercice = -1;
	}
	
	
	

}
