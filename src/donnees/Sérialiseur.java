package donnees;

import java.io.PrintWriter;

import com.googlecode.objectify.Key;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import static com.googlecode.objectify.ObjectifyService.ofy;



public class Sérialiseur {
	
	
	
	public String sérialiser(){
		// User user = ofy().load().type(User.class).id(5629499534213120).now();
		User user = new User( "Dupond","Jean", 30);
		XStream xstream = new XStream(new StaxDriver());
		xstream.alias("User", User.class);
		String userxml = xstream.toXML(user);
		
		return userxml;
	}
	
}
