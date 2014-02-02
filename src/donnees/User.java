package donnees;

import java.util.ArrayList;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.objectify.annotation.*;


@Entity
@Cache
public class User implements UserInterface {
	
	@Parent private transient Key parent;
	@Id private String id;
	private String nom;
	private String prenom;
	@Index private int coins;
	private ArrayList<String> friends;
	private ArrayList<Exercice> exercices;
	public User(){};
	
	public User(String nom, String prenom, int coins ) {
		this.nom = nom;
		this.prenom = prenom;
		this.coins = coins;
		this.parent = KeyFactory.createKey("RepertoireUser", "RepertoireUser");
		id = prenom+nom+ (int) (Math.random()*100);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
		
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getCoins() {
		return coins;
	}
	public void setCoins(int coins) {
		this.coins = coins;
	}
	@Override
	public void addCoins(int coins) {
		this.coins += coins;
		
	}
	
	public void addFriend(String id)
	{
		friends.add(id);
	}

	public void deleteFriend(String id) {
		friends.remove(id);
	}

	
	public ArrayList<Exercice> getExercices() {
		return exercices;
	}

	
	public void addExercices(Exercice e) {
		exercices.add(e);
	}
	

	

}
