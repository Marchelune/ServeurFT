package catalogue;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Text;
import com.googlecode.objectify.annotation.Entity;

@Entity
public class Article {
	
	private String name;
	private Text description;
	private BlobKey keyPhoto;
	private String urlPhoto;

}
