package securite;


import java.util.UUID;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import donnees.User;

@Entity
@Cache
public class Session {
	
	@Id private String id; //à renvoyer dans les requêtes 
	private Key<User> userKey;
	private int lastUpdatedExercice;
	
	public Session(){} //Constructeur vide nécessaire à la gestion de la base de données du Datastore
	
	
	public int getLastUpdatedExercice() {
		return lastUpdatedExercice;
	}
	public void setLastUpdatedExercice(int lastUpdatedExercice) {
		this.lastUpdatedExercice = lastUpdatedExercice;
	}
	
	public String getId() {
		return id;
	}
	public Key<User> getUserKey() {
		return userKey;
	}
	
	public Session( Key<User> userKey) {
		this.userKey = userKey;
		lastUpdatedExercice = -1;
		id = UUID.randomUUID().toString();
	}
	
	
	

}
