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
			List<User> users = ofy().load().type(User.class).order("-coins").limit(30).list();

			req.setAttribute("users", users);
			try {
				this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateurs.jsp").forward(req, resp);
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}
		catch (IOException e) {
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
        		newUser(req, blobKeys, resp);
        	}catch (IllegalStateException e){  //cas où la requete n'est pas passée par le blobstore
        		newUser(req, resp);
        	}
            

            resp.sendRedirect("/");

        	} catch (IOException e) {
        		e.printStackTrace();
        	}
        }

		
	private void newUser(HttpServletRequest req, List<BlobKey> blobKeys, HttpServletResponse resp){
		String id = req.getParameter("id");
		if(req.getParameter("nom") != "" && req.getParameter("prenom") != "" && req.getParameter("password") != "" && id != "")
		{
			if(InteractionObjectify.getUserById(id) == null){
				User user;
				if ( blobKeys != null) { //en local, la condition blobKeys != null suffit à éviter un nullPointerException
					try 
					{
						user = new User(req.getParameter("nom"), req.getParameter("prenom"), req.getParameter("password"), req.getParameter("id"), blobKeys.get(0));
						// (appel du constructeur avec photo)
					}
					catch (IllegalArgumentException e)  //sur AppEngine, la condition blobKeys != null ne suffit pas
					{
						user = new User(req.getParameter("nom"), req.getParameter("prenom"), req.getParameter("password"), req.getParameter("id"));
						// (appel du constructeur sans photo)
					}

				}
				else {
					user = new User(req.getParameter("nom"), req.getParameter("prenom"), req.getParameter("password"), req.getParameter("id"));
					// (appel du constructeur sans photo)
				}
				// Enregistrement de l'utilisateur dans le Datastore avec Objectify
				InteractionObjectify.saveUser(user);
			}else{
				resp.setStatus(resp.SC_CONFLICT);
			}
		}
	}

	private void newUser(HttpServletRequest req, HttpServletResponse resp){
		String id = req.getParameter("id");
		
		if(req.getParameter("nom") != "" && req.getParameter("prenom") != "" && req.getParameter("password") != "" && id != "")
		{
			if(InteractionObjectify.getUserById(id) == null){
				User user = new User(req.getParameter("nom"), req.getParameter("prenom"), req.getParameter("password"), req.getParameter("id"));
					// (appel du constructeur sans photo)
				
				// Enregistrement de l'utilisateur dans le Datastore avec Objectify
				InteractionObjectify.saveUser(user);
			}else{
				resp.setStatus(resp.SC_CONFLICT);
			}
		}
	}

	}
