package catalogue;

import java.util.ArrayList;

import javax.persistence.Transient;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

import donnees.User;

@Entity
@Cache
public class Item { //une récompense du catalogue
	
	@Id private String name;
	@Index private String category;
	private String description;
	private String urlPhoto;
	@Index private int price;
	private int quantity;
	@Index private float note;
	private ArrayList<Key<Feedback>> feedbacks = new ArrayList<Key<Feedback>>();
	@Parent private transient Key parent; //"astuce" pour avoir toutes les entités Item sur un même serveur physique --> accès plus rapide
	private transient BlobKey keyPhoto;
	@Index int hit; //nombre d'achats déjà effectués

	public Item(){}

	public Item(String name, String description, BlobKey keyPhoto, int price, int quantity, String category) {
		this.parent = Key.create(Item.class, "catalogue");
		this.name = name;
		this.description = description;
		this.keyPhoto = keyPhoto;
		this.price = price;
		this.quantity = quantity;
		this.hit =0;
		this.category = category;
		this.note= (float) 2.5;
		
		ImagesService imagesService = ImagesServiceFactory.getImagesService();
		ServingUrlOptions options = ServingUrlOptions.Builder.withBlobKey(keyPhoto);
		this.urlPhoto = imagesService.getServingUrl(options);
	};
	
	public void incrementHit(){
		hit++;
	}
	
	public void decrementQuantity(){
		quantity--;
	}
	
	public void incrementQuantity(){
		quantity++;
	}
	
	public void addQuantity(int x){
		quantity += x;
	}
	
	public void addFeedback(Feedback feedback){
		
		note = (note + feedback.getNote())/2;
		Key<Feedback> keyFeedback = Key.create(Feedback.class, feedback.getId());
		feedbacks.add(keyFeedback);
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public String getCategory() {
		return category;
	}

	public String getDescription() {
		return description;
	}

	public String getUrlPhoto() {
		return urlPhoto;
	}

	public int getQuantity() {
		return quantity;
	}

	public float getNote() {
		return note;
	}

	public ArrayList<Key<Feedback>> getFeedbacks() {
		return feedbacks;
	}

	public Key getParent() {
		return parent;
	}

	public BlobKey getKeyPhoto() {
		return keyPhoto;
	}

	public int getHit() {
		return hit;
	}
	
	
	
	
}
