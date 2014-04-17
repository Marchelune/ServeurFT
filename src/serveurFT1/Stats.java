package serveurFT1;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;








import securite.Session;
import securite.TableSessions;

import com.googlecode.objectify.Key;

import donnees.Exercice;
import donnees.InteractionObjectify;
import donnees.Serialiseur;
import donnees.User;


@SuppressWarnings("serial")
public class Stats extends HttpServlet {

	public void doGet( HttpServletRequest request, HttpServletResponse reponse ) throws ServletException, IOException{
		String s = request.getParameter("session"); //identifiant de la session (UUID)
		String type = request.getParameter("type");
		PrintWriter out = reponse.getWriter();

		//Authentification
		if (s != null && type != null ){
			Session session = TableSessions.getSession(s);
			if (session != null){
				Key<User> userKey = session.getUserKey();
				User user = InteractionObjectify.getUserByKey(userKey);

				ArrayList<Exercice> exercices = new ArrayList<Exercice>();

				if ( type.equals("ciseaux")){   //renvoie le total de secondes passées sur le sport "ciseaux"
					out.print(Long.toString(user.getTotalCiseaux()));
				}
				if ( type.equals("boxe")){   //renvoie le total de secondes passées sur le sport "boxe"
					out.print(Long.toString(user.getTotalBoxe()));
				}
				if ( type.equals("squat")){   
					out.print(Long.toString(user.getTotalSquat()));
				}
				if ( type.equals("pompes")){  
					out.print(Long.toString(user.getTotalPompes()));
				}
			}


		}
	}


}
