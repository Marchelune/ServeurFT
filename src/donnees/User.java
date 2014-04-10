package donnees;

import java.util.ArrayList;
import java.util.Date;

import auth.Md5;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.images.*;
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
	private ArrayList<String> friends = new ArrayList<String>();
	private transient String password;
	private transient ArrayList<Key<Exercice>> exercices = new ArrayList<Key<Exercice>>();   // clé objectify
	private transient BlobKey clePhoto;
	private String urlPhoto;
	
	
	public User(){};
	
	public User(String nom, String prenom , String pass, String id) {
		this.nom = nom;
		this.prenom = prenom;
		this.coins = 0;
		this.parent = Key.create(User.class, "registre");     // permet à toutes les entités de type User d'avoir un même parent "artificiel" registre, ce qui optimise le temps de chargement
		this.id = id;
		this.inscription = new Date();
		password = Md5.encode("ZSS3q2b65m"+ pass + id);
	}
	
	public User(String nom, String prenom , String pass, String id, BlobKey key) {
		this(nom, prenom, pass, id);
		clePhoto = key;
		ImagesService imagesService = ImagesServiceFactory.getImagesService();
		ServingUrlOptions options = ServingUrlOptions.Builder.withBlobKey(key);
		urlPhoto = imagesService.getServingUrl(options);
	}
	
	
	public BlobKey getClePhoto() {
		return clePhoto;
	}

	public String getUrlPhoto() {
		return urlPhoto;
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
		if (this.getExercicesKeys().size() == 0){
			return null;
		}
		Key<Exercice> exerciceKey = exercices.get(this.getExercicesKeys().size() -1 - k );
		Exercice exercice = InteractionObjectify.getExerciceByKey(exerciceKey);
		
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
