package auth;


import static com.googlecode.objectify.ObjectifyService.ofy;

import com.googlecode.objectify.ObjectifyService;


public class TableSessions {

	static {
		ObjectifyService.register(Session.class);
//        ObjectifyService.register(SessionAdmin.class);
//        ObjectifyService.register(SessionAndroid.class);
//        ObjectifyService.register(SessionKinect.class);
	}
	
	public TableSessions(){}
	
	public void saveSession(Session session)
	{
		ofy().save().entity(session).now();
	}
	
	public Session getSession(long id) {
		return ofy().load().type(Session.class).id(id).now();
	}
	
	
}
