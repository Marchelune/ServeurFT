package serveurFT1;


import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import securite.GenerateurCleControle;
import securite.Session;
import securite.SessionKinect;
import securite.TableSessions;

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
		String s = req.getParameter("session");
		String sport = req.getParameter("sport");
		String repetitions = req.getParameter("repetitions");
		String duree = req.getParameter("duree");
		String coins = req.getParameter("coins");
		String controle = req.getParameter("c");
		
		if(s != null && sport != null && repetitions != null && duree != null && coins != null && controle != null)
		{
			User user = null;
			if(TableSessions.getSession(s) != null){
				SessionKinect session = (SessionKinect) TableSessions.getSession(s);
				user = InteractionObjectify.getUserByKey(session.getUserKey() ); 
				if (user != null)
				{
					String c = GenerateurCleControle.getCleControleur(session, Integer.parseInt(coins), Integer.parseInt(repetitions), Long.parseLong(duree));
					if(controle.equals(c)){
					Exercice exercice = new Exercice( new Date() , req.getParameter("sport"), Long.parseLong( req.getParameter("duree")), 
							Integer.parseInt( req.getParameter("repetitions")), Integer.parseInt( req.getParameter("coins")));
					
					InteractionObjectify.saveExercice(exercice); // exercice sauvegardé dans le Datastore
					session.incrementNmbExercices(); // on incrémente le nombre d'exercices réalisés sur cette session

					Key<Exercice> cleExercice = Key.create(Exercice.class, exercice.getId());
					user.addExercices(cleExercice, exercice);   // addExercice réalise toutes les opérations (ajout de SportCoins, ajout de l'exercice à l'historique etc)
					InteractionObjectify.saveUser(user);
					}else{reponse.sendError(HttpServletResponse.SC_UNAUTHORIZED );}
				}
			}

		}else{reponse.sendError(HttpServletResponse.SC_BAD_REQUEST );}

	}
	
	
}

