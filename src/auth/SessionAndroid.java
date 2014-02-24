package auth;

import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.EntitySubclass;

import donnees.User;

@EntitySubclass
public class SessionAndroid extends Session {
	
	private int lastUpdatedExercice;
	private Date dateExp; //date à partir de laquelle la session n'est plus sensée exister
	
	
	public SessionAndroid(long id, Key<User> userKey, int lastUpdate, Date dateExp) {
		super(id, userKey);
		this.lastUpdatedExercice = lastUpdate;
		this.dateExp = dateExp;
	}

	public int getLastUpdatedExercice() {
		return lastUpdatedExercice;
	}


	public void setLastUpdatedExercice(int lastUpdatedExercice) {
		this.lastUpdatedExercice = lastUpdatedExercice;
	}


	public Date getDateExp() {
		return dateExp;
	}
	
	
}
