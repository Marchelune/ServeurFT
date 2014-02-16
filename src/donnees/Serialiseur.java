package donnees;

import java.util.ArrayList;

import com.googlecode.objectify.ObjectifyService;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Serialiseur {
	static {
        ObjectifyService.register(User.class);
    }
	

	public Serialiseur(){
		
	}
	
	public String serialiseExercice(ArrayList<Exercice> e) {
		
		XStream xstream = new XStream(new DomDriver("UTF-8"));
		xstream.alias("Exercice", Exercice.class);
		String exercicesXml = xstream.toXML(e);

		return exercicesXml;
		
	}
	
	public String serialiseExercice(Exercice e) {
	
		XStream xstream = new XStream(new DomDriver("UTF-8"));
		xstream.alias("Exercice", Exercice.class);
		String exerciceXml = xstream.toXML(e);

		return exerciceXml;
		
	}
	
	
	public String serialiseUser(User user) {
		
	
		XStream xstream = new XStream(new DomDriver("UTF-8"));
		xstream.alias("User", User.class);
		String userxml = xstream.toXML(user);

		return userxml;
		
	}
	
	
}
