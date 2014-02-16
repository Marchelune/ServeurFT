package serveurFT1;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import donnees.Exercice;
import donnees.InteractionObjectify;
import donnees.Serialiseur;
import donnees.User;




@SuppressWarnings("serial")
public class InfoExercices extends HttpServlet {

	public void doGet( HttpServletRequest request, HttpServletResponse reponse ) throws ServletException, IOException{
		reponse.setContentType("text/xml; charset=UTF-8");
		String id = request.getParameter("id");
		String querie = request.getParameter("q");
		PrintWriter out = reponse.getWriter();
		Serialiseur serialiseur = new Serialiseur();
		
		InteractionObjectify interaction = new InteractionObjectify();
		User user = interaction.getUserById(id);
		
		ArrayList<Exercice> exercices = new ArrayList<Exercice>();

		if ( querie.equals("synchro")){
			exercices = user.getLastUnsynchronizedExercice();
			out.print(serialiseur.serialiseExercice(exercices));
			interaction.saveUser(user);
		}
		if ( querie.equals("reset")){
			exercices = user.getAllExercices();
			out.print(serialiseur.serialiseExercice(exercices));
			interaction.saveUser(user);
		}
		
		
		
		
	}
	
	
}
