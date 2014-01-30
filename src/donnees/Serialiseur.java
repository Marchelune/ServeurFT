package donnees;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class Serialiseur {

	public Serialiseur(){
		
	}
	
	public String serialise() {
		User user = new User( "Dupond","Jean", 30);
		XStream xstream = new XStream(new StaxDriver());
		xstream.alias("User", User.class);
		String userxml = xstream.toXML(user);

		return userxml;
		
	}
	
	
}
