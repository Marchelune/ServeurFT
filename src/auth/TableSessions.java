package auth;


import static com.googlecode.objectify.ObjectifyService.ofy;

import com.googlecode.objectify.ObjectifyService;

import donnees.User;


public class TableSessions {

	static {
		ObjectifyService.register(Session.class);
//        ObjectifyService.register(SessionAdmin.class);
//        ObjectifyService.register(SessionAndroid.class);
//        ObjectifyService.register(SessionKinect.class);
	}
	
	
	public static void saveSession(Session session)
	{
		ofy().save().entity(session).now();
	}
	
	public static void deleteSession(Session session)
	{
		ofy().delete().entity(session).now();
	}
	
	public static Session newSession(User user){
		Session session = new Session(user.getKey());
		ofy().save().entity(session).now();
		return session;
	}
	public static Session getSession(String id) {
		return ofy().load().type(Session.class).id(id).now();
	}
	
	
}
