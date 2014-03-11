package donnees;

import java.util.ArrayList;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Serialiseur {
	
	
	
	public static String serialiseExercice(ArrayList<Exercice> e) {
		
		XStream xstream = new XStream(new DomDriver("UTF-8"));
		xstream.alias("Exercice", Exercice.class);
		String exercicesXml = xstream.toXML(e);

		return exercicesXml;
		
	}
	
	public static String serialiseExercice(Exercice e) {
	
		XStream xstream = new XStream(new DomDriver("UTF-8"));
		xstream.alias("Exercice", Exercice.class);
		String exerciceXml = xstream.toXML(e);

		return exerciceXml;
		
	}
	
	
	public static String serialiseUser(User user) {
		
	
		XStream xstream = new XStream(new DomDriver("UTF-8"));
		xstream.alias("User", User.class);
		String userxml = xstream.toXML(user);

		return userxml;
		
	}
	
	
}
