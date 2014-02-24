package auth;

import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.EntitySubclass;

import donnees.User;

@EntitySubclass
public class SessionKinect extends Session {
	
	private int idMachine;
	private Date dateExp; //date à partir de laquelle la session n'est plus sensée exister
	
	
	public int getIdMachine() {
		return idMachine;
	}


	public Date getDateExp() {
		return dateExp;
	}


	public SessionKinect(long id, Key<User> userKey, int idMachine, Date dateExp) {
		super(id, userKey);
		this.idMachine = idMachine;
		this.dateExp = dateExp;
	}
	
}
