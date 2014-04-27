package catalogue;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;


import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;



public class Catalogue { //gère la persistence et l'accès aux items du catalogue
	
	static {
        ObjectifyService.register(Item.class);
        ObjectifyService.register(Feedback.class);
        ObjectifyService.register(Purchase.class);
    }
	
	public static List<Item> getItemByNote(int limite){
		List<Item> items = ofy().load().type(Item.class).order("-note").limit(limite).list();
		
		return items;
	}
	
	public static List<Item> getItemByPrixDecroissant(int limite){
		List<Item> items = ofy().load().type(Item.class).order("-price").limit(limite).list();
		
		return items;
	}
	
	public static List<Item> getItemByPrixCroissant(int limite){
		List<Item> items = ofy().load().type(Item.class).order("price").limit(limite).list();
		
		return items;
	}
	
	public static List<Item> getItemByPopularite(int limite){
		List<Item> items = ofy().load().type(Item.class).order("-hit").limit(limite).list();
		return items;
	}
	
	public static List<Item> getItemByCategory(int limite, String category){
		List<Item> items = ofy().load().type(Item.class).filter("category", category).limit(limite).list();
		return items;
	}
	
	public static void saveItem(Item item)
	{
		ofy().save().entity(item).now();
	}
	
	public static void saveFeedback(Feedback feedback)
	{
		ofy().save().entity(feedback).now();
	}
	
	public static Item getItemById(String id)
	{
		Item item = ofy().load().type(Item.class).parent(Key.create(Item.class, "catalogue")).id(id).now();
		return item;
	}
	
	public static Purchase getPurchaseByKey(Key<Purchase> kp){
		
		return ofy().load().key(kp).now();
		
	}
	
	public static ArrayList<Purchase> getPurchasesByKey(ArrayList<Key<Purchase>> kp){
		
		ArrayList<Purchase> purchases = new ArrayList<Purchase>();
		
		for (Key<Purchase> keyPurchase : kp){
			purchases.add(getPurchaseByKey(keyPurchase));
		}
		
		return purchases;
		
	}
	
	public static void savePurchase(Purchase purchase)
	{
		ofy().save().entity(purchase).now();
	}
	
	public static ArrayList<String> getCatecories(){ //défini les différentes catégories possibles (c'est un peu "artisanal" mais cela aurait une bonne forme dans une version évoluée du projet)
		
		ArrayList<String> categories = new ArrayList<String>();
		categories.add("Voyage");
		categories.add("Gastronomie");
		categories.add("Décoration");
		categories.add("Sport");
		categories.add("Autre");
		
		return categories;
	}
	
	
}
