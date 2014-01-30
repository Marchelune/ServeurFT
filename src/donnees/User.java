package donnees;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.objectify.annotation.*;

@Entity
@Cache
public class User implements UserInterface {
	
	@Parent Key parent;
	@Id String id;
	private String nom;
	private String prenom;
	@Index private int coins;
	
	public User(){};
	
	public User(String nom, String prenom, int coins ) {
		this.nom = nom;
		this.prenom = prenom;
		this.coins = coins;
		this.parent = KeyFactory.createKey("RepertoireUser", "RepertoireUser");
		id = prenom+nom;
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
	

	

}
