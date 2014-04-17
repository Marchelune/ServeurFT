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

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

import donnees.Exercice;
import donnees.InteractionObjectify;
import donnees.User;

@SuppressWarnings("serial") //Service qui permet d'ajouter un exercice à l'historique d'un utilisateur. Ceci n'est pas une version définitive
							// mais elle permet de simuler l'ajout d'un exercice par la station kinect.
public class Nouvelexercice extends HttpServlet {
	
	static {
        ObjectifyService.register(User.class);
    }
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) //affiche la page de simulation d'un nouvel exercice
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
		String id = req.getParameter("id");
    	if(id != null)
    	{
    		User user = InteractionObjectify.getUserById(id);
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
    					user.addExercices(cleExercice, exercice);   // addExercice réalise toutes les opérations (ajout de SportCoins, ajout de l'exercice à l'historique etc)
    					ofy().save().entity(user).now();
    				} catch (ParseException e) {
    					e.printStackTrace();
    				}
    			}

    		}
    		
    	}
		
	}
	
	
}

