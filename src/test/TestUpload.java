package test;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.Md5;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.googlecode.objectify.ObjectifyService;

import donnees.InteractionObjectify;
import donnees.User;

@SuppressWarnings("serial")
public class TestUpload extends HttpServlet { // Permet de tester l'upload d'une image en créant un compte générique Jean Dupond avec l'image uploadé en photo de profil
	
	static {
        ObjectifyService.register(User.class);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)   //renvoie la page d'inscription
			throws IOException {
		try
		{
			List<User> users = ofy().load().type(User.class).order("-coins").limit(10).list();

			req.setAttribute("users", users);
			this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateurs.jsp").forward(req, resp);
		}
		catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) {	 //permet l'inscription
        try {
        	req.setCharacterEncoding("UTF-8");
        	BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
            // Récupère une Map de tous les champs d'upload de fichiers
        	try{
        		Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
        		// Récupère la liste des fichiers uploadés dans le champ "photo" du formulaire d'inscription (il peut y en avoir plusieurs)
        		List<BlobKey> blobKeys = blobs.get("photo");
        		// Création de l'utilisateur
        		newUser(req, blobKeys);
        	}catch (IllegalStateException e){  //cas où la requete n'est pas passée par le blobstore
        		newUser(req);
        	}
            

            resp.sendRedirect("/");

        	} catch (IOException e) {
        		e.printStackTrace();
        	}
        }
	
	
	private void newUser(HttpServletRequest req, List<BlobKey> blobKeys){
		
		User user;
		if ( blobKeys != null) { //en local, la condition blobKeys != null suffit à éviter un nullPointerException
			try 
			{
				user = new User("Dupont" + (int)(10*Math.random()), "Jean" + (int)(10*Math.random()), Md5.encode("jean"), "jean" +  (int)(100*Math.random()) + "@dupond.com", blobKeys.get(0));
				// (appel du constructeur avec photo)
			}
			catch (IllegalArgumentException e)  //sur AppEngine, la condition blobKeys != null ne suffit pas
			{
				user = new User("Dupont" + (int)(10*Math.random()), "Jean" + (int)(10*Math.random()), Md5.encode("jean"), "jean" +  (int)(100*Math.random()) + "@dupond.com");
				// (appel du constructeur sans photo)
			}



			// Enregistrement de l'utilisateur dans le Datastore avec Objectify
			InteractionObjectify.saveUser(user);

		}
	}

	private void newUser(HttpServletRequest req){
		
		User user = new User("Dupont" + (int)(10*Math.random()), "Jean" + (int)(10*Math.random()), Md5.encode("jean"), "jean" +  (int)(100*Math.random()) + "@dupond.com");
		// (appel du constructeur sans photo)

		// Enregistrement de l'utilisateur dans le Datastore avec Objectify
		InteractionObjectify.saveUser(user);
	}
}