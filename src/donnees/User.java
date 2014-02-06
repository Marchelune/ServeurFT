package donnees;

import java.util.ArrayList;




import static com.googlecode.objectify.ObjectifyService.ofy;
import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;



@Entity
@Cache
public class User implements UserInterface {
	
	@Parent private transient com.google.appengine.api.datastore.Key parent;   // clé du Datastore
	@Id private String id;
	private String nom;
	private String prenom;
	@Index private int coins;
	private ArrayList<String> friends;
	private ArrayList<Key<Exercice>> exercices = new ArrayList<Key<Exercice>>();   // clé objectify
	
	static {
        ObjectifyService.register(Exercice.class);
    }
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

	
	public ArrayList<Key<Exercice>> getExercicesKeys() {
		return exercices;
	}

	public Exercice getExercice(int k) { //  renvoie l'objet kème dernier exercice effectué : si k=0 dernier exercice, k=1 avant dernier etc
		Key<Exercice> exerciceKey = exercices.get(this.getExercicesKeys().size() -1 - k );
		Exercice exercice = ofy().load().key(exerciceKey).now();
		
		return exercice;
	}
	
	public void addExercices(Key<Exercice> e) {
		exercices.add(e);
	}
	

	

}
