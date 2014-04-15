package donnees;

import java.util.ArrayList;
import java.util.List;

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
	
	public static String serialiseUsers(List<User> users) { //permet de sérialiser une liste d'utilisateurs dans les classements
		
		ArrayList<User> ArrayUser = new ArrayList<User>(); //users est potentiellement généré par la couche Objectify, et donne, une fois sérialisé, des informations lié à google inutiles pour l'application, d'où la réécriture d'un arraylist
		for (User user : users){
			ArrayUser.add(user);
		}
		XStream xstream = new XStream(new DomDriver("UTF-8"));
		xstream.alias("User", User.class);
		String usersxml = xstream.toXML(ArrayUser);

		return usersxml;
		
	}
	
	
}
