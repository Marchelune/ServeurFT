package donnees;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;

import securite.Session;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

public class InteractionObjectify {
	
	static {
        ObjectifyService.register(User.class);
        ObjectifyService.register(Exercice.class);
        ObjectifyService.register(Session.class);
    }

	public static void deleteUser(User user)
	{
		ofy().delete().entity(user).now();
	
	}
	
	
	
	public static void deleteExercicesByKeys(ArrayList<Key<Exercice>> exercices)
	{
		for(Key<Exercice> kExercice : exercices){
			ofy().delete().key(kExercice).now();
		}
	
	}
	
	public static User getUserById(String id)
	{
		User user = ofy().load().type(User.class).parent(Key.create(User.class, "registre")).id(id).now();
		return user;
	}
	
	public static User getUserByKey(Key<User> userKey) 
	{
		return ofy().load().key(userKey).now();
	}
	
	public static Exercice getExerciceByKey(Key<Exercice> exerciceKey)
	{
		Exercice exercice = ofy().load().key(exerciceKey).now();
		return exercice;
	}
	
	public static void saveUser(User user)
	{
		ofy().save().entity(user).now();
	}
	
	
	
	
	
	public static void saveExercice(Exercice exercice)
	{
		ofy().save().entity(exercice).now();
	}
}
