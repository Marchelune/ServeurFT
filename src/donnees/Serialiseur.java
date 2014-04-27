package donnees;

import java.util.ArrayList;
import java.util.List;

import catalogue.Feedback;
import catalogue.Item;
import catalogue.Purchase;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Serialiseur {


	public static String serialiseItems(List<Item> i) {

		ArrayList<Item> ArrayItem = new ArrayList<Item>(); //i est potentiellement généré par la couche Objectify, et donne, une fois sérialisé, des informations lié à google inutiles pour l'application, d'où la réécriture d'un arraylist
		for (Item item : i){
			ArrayItem.add(item);
		}
		XStream xstream = new XStream(new DomDriver("UTF-8"));
		xstream.alias("Item", Item.class);
		String itemsXml = xstream.toXML(ArrayItem);

		return itemsXml;

	}

	public static String serialisePurchases(ArrayList<Purchase> p) {

		XStream xstream = new XStream(new DomDriver("UTF-8"));
		xstream.alias("Purchase", Purchase.class);
		String purchasesXml = xstream.toXML(p);

		return purchasesXml;

	}

	public static String serialisePurchase(Purchase p) {

		XStream xstream = new XStream(new DomDriver("UTF-8"));
		xstream.alias("Purchase", Purchase.class);
		String purchaseXml = xstream.toXML(p);

		return purchaseXml;

	}

	public static String serialiseFeedback(Feedback f) {

		XStream xstream = new XStream(new DomDriver("UTF-8"));
		xstream.alias("Feedback", Feedback.class);
		String feedbackXml = xstream.toXML(f);

		return feedbackXml;

	}

	public static String serialiseItem(Item i) {

		XStream xstream = new XStream(new DomDriver("UTF-8"));
		xstream.alias("Item", Item.class);
		String itemXml = xstream.toXML(i);

		return itemXml;

	}

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
