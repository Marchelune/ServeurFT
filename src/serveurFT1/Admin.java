package serveurFT1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import catalogue.Catalogue;
import catalogue.Item;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.Text;



public class Admin extends HttpServlet { //ouverture de session (il y a différents type de sessions)
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		try {
			this.getServletContext().getRequestDispatcher("/WEB-INF/Administration.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		}
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)  {	
		
		try {
			req.setCharacterEncoding("UTF-8");
			BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
			// Récupère une Map de tous les champs d'upload de fichiers
			try{
				Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
				// Récupère la liste des fichiers uploadés dans le champ "photo" du formulaire d'inscription (il peut y en avoir plusieurs)
				List<BlobKey> blobKeys = blobs.get("photo");
				// Création de l'utilisateur
				newItem(req, blobKeys, resp);
			}catch (IllegalStateException e){  //cas où la requete n'est pas passée par le blobstore
				e.printStackTrace();
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void newItem(HttpServletRequest req, List<BlobKey> blobKeys, HttpServletResponse resp) throws IOException {
		String name = req.getParameter("name");
		String description =  req.getParameter("description");
		String price = req.getParameter("price");
		String quantity = req.getParameter("quantity");
		String category = req.getParameter("category");
		
		if(name != null && description != null && price !=null && quantity != null){
			if(Catalogue.getItemById(name) == null ){
				Item item = new Item(name, description, blobKeys.get(0), Integer.parseInt(price), Integer.parseInt(quantity),category);
				Catalogue.saveItem(item);
			}else{resp.sendError(HttpServletResponse.SC_CONFLICT);}
		}else{resp.sendError(HttpServletResponse.SC_BAD_REQUEST);}
		
	}

}

