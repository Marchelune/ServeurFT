package donnees;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

public class InteractionObjectify {
	
	static {
        ObjectifyService.register(User.class);
        ObjectifyService.register(Exercice.class);
    }

	public InteractionObjectify(){};
	
	public User getUserById(String id)
	{
		User user = ofy().load().type(User.class).parent(KeyFactory.createKey("RepertoireUser", "RepertoireUser")).id(id).now();
		return user;
	}
	
	public Exercice getExerciceByKey(Key<Exercice> exerciceKey)
	{
		Exercice exercice = ofy().load().key(exerciceKey).now();
		return exercice;
	}
	
	public void saveUser(User user)
	{
		ofy().save().entity(user).now();
	}
	
	public void saveExercice(Exercice exercice)
	{
		ofy().save().entity(exercice).now();
	}
}
