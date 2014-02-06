package serveurFT1;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

import donnees.Exercice;
import donnees.User;

@SuppressWarnings("serial")
public class Nouvelexercice extends HttpServlet {
	
	static {
        ObjectifyService.register(User.class);
    }
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try
		{
			this.getServletContext().getRequestDispatcher("/WEB-INF/simulationstation.jsp").forward(req, resp);
		}
		catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void doPost( HttpServletRequest req, HttpServletResponse reponse ) throws ServletException, IOException{
		req.setCharacterEncoding("UTF-8");
        // Création de l'objet
		String id = req.getParameter("id");
    	if(id != null)
    	{
    		User user = ofy().load().type(User.class).parent(KeyFactory.createKey("RepertoireUser", "RepertoireUser")).id(id).now();
    		if (user != null)
    		{
    			if(req.getParameter("date") != null){
    				Date date;
    				try {
    					date = new SimpleDateFormat("ddMMyyyy").parse(req.getParameter("date"));
    					Exercice exercice = new Exercice( date , req.getParameter("sport"), Long.parseLong( req.getParameter("duree")), 
    							Integer.parseInt( req.getParameter("repetitions")), Integer.parseInt( req.getParameter("coins")));
    					ofy().save().entity(exercice).now(); // exercice sauvegardé dans le Datastore
    					
    					Key<Exercice> cleExercice = Key.create(Exercice.class, exercice.getId());
    					user.addExercices(cleExercice);   // Relation user --> exercice
    					user.addCoins(Integer.parseInt( req.getParameter("coins")));
    					ofy().save().entity(user).now();
    				} catch (ParseException e) {
    					e.printStackTrace();
    				}
    			}

    		}
    		
    	}
		
	}
	
	
}

