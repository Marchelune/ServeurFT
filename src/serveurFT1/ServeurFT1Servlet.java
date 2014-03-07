package serveurFT1;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.*;
import com.googlecode.objectify.ObjectifyService;

import donnees.InteractionObjectify;
import donnees.User;
import static com.googlecode.objectify.ObjectifyService.ofy;

@SuppressWarnings("serial")  //accueil du serveur pour l'inscription
public class ServeurFT1Servlet extends HttpServlet {
	
	static {
        ObjectifyService.register(User.class);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)   //renvoie la page d'inscription
			throws IOException {
		try
		{
			List<User> users = ofy().load().type(User.class).order("-coins").limit(5).list();

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
            Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
            // Récupère la liste des fichiers uploadés dans le champ "photo" du formulaire d'inscription (il peut y en avoir plusieurs)
            List<BlobKey> blobKeys = blobs.get("photo");
            // Récupère la clé identifiant du fichier uploadé dans le Blobstore 
        	
        	// Création de l'utilisateur
            
            if(req.getParameter("nom") != "" && req.getParameter("prenom") != "" && req.getParameter("password") != "" && req.getParameter("id") != "")
            {
            	User user;
            	if (blobKeys.get(0) != null) {
            		user = new User(req.getParameter("nom"), req.getParameter("prenom"), req.getParameter("password"), req.getParameter("id"), blobKeys.get(0));
            	}
            	else {
            		user = new User(req.getParameter("nom"), req.getParameter("prenom"), req.getParameter("password"), req.getParameter("id"));
            	}
            	// Enregistrement de l'objet dans le Datastore avec Objectify
            	InteractionObjectify interaction = new InteractionObjectify();
            	interaction.saveUser(user);
            }

            resp.sendRedirect("/");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}
