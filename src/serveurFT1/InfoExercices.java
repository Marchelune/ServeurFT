package serveurFT1;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;







import com.googlecode.objectify.Key;

import auth.Session;
import auth.TableSessions;
import donnees.Exercice;
import donnees.InteractionObjectify;
import donnees.Serialiseur;
import donnees.User;




@SuppressWarnings("serial")
public class InfoExercices extends HttpServlet {

	public void doGet( HttpServletRequest request, HttpServletResponse reponse ) throws ServletException, IOException{
		reponse.setContentType("text/xml; charset=UTF-8");
		String s = request.getParameter("session"); //identifiant de la session (UUID)
		String querie = request.getParameter("q");
		PrintWriter out = reponse.getWriter();
		
		//Authentification
		Session session = TableSessions.getSession(s);
		if (session != null){
			Key<User> userKey = session.getUserKey();
			User user = InteractionObjectify.getUserByKey(userKey);

			ArrayList<Exercice> exercices = new ArrayList<Exercice>();

			if ( querie.equals("last")){   //renvoie le dernier exercice réalisé par l'utilisateur
				Exercice exercice = user.getExercice(0); 
				out.print(Serialiseur.serialiseExercice(exercice));
			}
			if ( querie.equals("synchro")){ //renvoie tous les exercices qui n'ont pas déjà été téléchargés depuis la dernière synchro/le dernier reset sur cette session
				exercices = user.getExerciceFromKToEnd(session.getLastUpdatedExercice()+1);
				out.print(Serialiseur.serialiseExercice(exercices));
				session.setLastUpdatedExercice(user.getExercicesKeys().size()-1);
				InteractionObjectify.saveUser(user);
				TableSessions.saveSession(session);
			}
			if ( querie.equals("reset")){ //renvoie tous les exercices déjà effectués
				exercices = user.getAllExercices();
				out.print(Serialiseur.serialiseExercice(exercices));
				session.setLastUpdatedExercice(user.getExercicesKeys().size()-1);
				InteractionObjectify.saveUser(user);
				TableSessions.saveSession(session);
			}
		}

		
		
	}
	
	
}
