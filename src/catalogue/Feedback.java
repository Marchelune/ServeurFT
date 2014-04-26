package catalogue;

import com.google.appengine.api.datastore.Text;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
@Cache
public class Feedback { //encapsule un avis laissé par l'utilisateur sur un article (item)
	@Id Long id;
	private String idUser; 
    private String userFirstName; // nom de l'utilisateur à afficher
    private Text comment;
    private int note;
    
    
    Feedback(){};

}
