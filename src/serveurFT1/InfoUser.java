package serveurFT1;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import auth.TableSessions;
import donnees.InteractionObjectify;
import donnees.Serialiseur;
import donnees.User;
import com.googlecode.objectify.Key;



@SuppressWarnings("serial")
public class InfoUser extends HttpServlet {

	public void doGet( HttpServletRequest request, HttpServletResponse reponse ) throws ServletException, IOException{
		reponse.setContentType("text/xml; charset=UTF-8");
		String s = request.getParameter("s");
		PrintWriter out = reponse.getWriter();
		Serialiseur serialiseur = new Serialiseur();
		
		TableSessions table = new TableSessions();
		
		Key <User> userKey = table.getSession(Long.parseLong(s)).getUserKey();
		
		InteractionObjectify interaction = new InteractionObjectify();
		User user = interaction.getUserByKey(userKey);
		
		out.print(serialiseur.serialiseUser(user));
		
		
		
	}
	
	
}
