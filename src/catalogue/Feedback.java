package catalogue;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import donnees.User;

@Entity
@Cache
public class Feedback { //encapsule un avis laissé par l'utilisateur sur un article (item)
	
	@Id Long id;
	private transient String idUser; 
    private String userFirstName; // nom de l'utilisateur à afficher
    private String comment;
    private int note;
    private String item;
    
    
    public Feedback(){};
    
    public Feedback(User user, String comment, int note, String item){
    	
    	this.note = note;
    	this.comment = comment;
    	idUser = user.getId();
    	userFirstName = user.getPrenom();
    	this.item = item;
    	
    }

	public Long getId() {
		return id;
	}

	public String getIdUser() {
		return idUser;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public String getComment() {
		return comment;
	}

	public int getNote() {
		return note;
	}

	public String getItem() {
		return item;
	}

    
    
}
