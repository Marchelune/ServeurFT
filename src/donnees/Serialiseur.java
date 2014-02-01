package donnees;

import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class Serialiseur {
	static {
        ObjectifyService.register(User.class);
    }
	

	public Serialiseur(){
		
	}
	
	public String serialise(String id) {
		//Key<User> cleUser = Key.create(User.class, id);
		
		User user = ofy().load().type(User.class).parent(KeyFactory.createKey("RepertoireUser", "RepertoireUser")).id(id).now();
	
		XStream xstream = new XStream(new StaxDriver());
		xstream.alias("User", User.class);
		String userxml = xstream.toXML(user);

		return userxml;
		
	}
	
	
}
