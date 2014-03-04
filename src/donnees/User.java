package donnees;

import java.util.ArrayList;




import java.util.Date;

import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;



@Entity
@Cache
public class User implements UserInterface {
	
	@Parent private transient Key parent; 
	@Id private String id;
	private String nom;
	private String prenom;
	private Date inscription;
	@Index private int coins;
	private ArrayList<String> friends;
	private transient String password;
	private transient ArrayList<Key<Exercice>> exercices = new ArrayList<Key<Exercice>>();   // clé objectify
	
	
	public User(){};
	
	public User(String nom, String prenom , String pass) {
		this.nom = nom;
		this.prenom = prenom;
		this.coins = 0;
		this.parent = Key.create(User.class, "registre");     // KeyFactory.createKey("RepertoireUser", "RepertoireUser");
		id = prenom+nom+ (int) (Math.random()*100);
		this.inscription = new Date();
		password = "ZSS3q2b65m"+ pass + id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Key<User> getKey(){
		
		return Key.create(parent, User.class, id); 
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
		InteractionObjectify interaction = new InteractionObjectify();
		Key<Exercice> exerciceKey = exercices.get(this.getExercicesKeys().size() -1 - k );
		Exercice exercice = interaction.getExerciceByKey(exerciceKey);
		
		return exercice;
	}
	
	public ArrayList<Exercice> getAllExercices() { //  renvoie tous les exos  
		ArrayList<Exercice> tousexercices = new ArrayList<Exercice>(); 
		InteractionObjectify interaction = new InteractionObjectify();
		if (exercices.size() == 0){return null;};
		for(int i = 0 ; i <= this.exercices.size() -1; i++)
		{
			Key<Exercice> exerciceKey = exercices.get(i);
			tousexercices.add(interaction.getExerciceByKey(exerciceKey));
		}
		
		return tousexercices;
	}
	
	public ArrayList<Exercice> getExerciceFromKToEnd(int k) { //  renvoie de k à la fin
		ArrayList<Exercice> exercicesNonSynchro = new ArrayList<Exercice>();
		InteractionObjectify interaction = new InteractionObjectify();
		if (exercices.size() == 0){return null;};
		for(int i = k  ; i <= this.exercices.size() -1; i++)
		{
			Key<Exercice> exerciceKey = exercices.get(i);
			exercicesNonSynchro.add(interaction.getExerciceByKey(exerciceKey));
		}
		return exercicesNonSynchro;
	}
	
	
	public void addExercices(Key<Exercice> e) {
		exercices.add(e);
	}

	
	public Date getInscription() {
		return inscription;
	}

	
	

	

}
