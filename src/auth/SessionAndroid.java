package auth;

import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.EntitySubclass;

import donnees.User;

@EntitySubclass
public class SessionAndroid extends Session {
	
	
	private Date dateExp; //date à partir de laquelle la session n'est plus sensée exister
	
	
	public SessionAndroid( Key<User> userKey, Date dateExp) {
		super( userKey);
		this.dateExp = dateExp;
	}


	public Date getDateExp() {
		return dateExp;
	}
	
	
}
