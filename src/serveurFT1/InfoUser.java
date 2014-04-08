package serveurFT1;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





import auth.Session;
import auth.TableSessions;
import donnees.InteractionObjectify;
import donnees.Serialiseur;
import donnees.User;



@SuppressWarnings("serial")
public class InfoUser extends HttpServlet {  //fournit les informations basiques d'un utilisateur en XML

	public void doGet( HttpServletRequest request, HttpServletResponse reponse ) throws ServletException, IOException{
		reponse.setContentType("text/xml; charset=UTF-8");
		String id = request.getParameter("id");
		String s= request.getParameter("session");
		PrintWriter out = reponse.getWriter();
		User user;
		
		try{
		if (id == null && s != null)  //soit en fonction de la session
		{
			Session session = TableSessions.getSession(s);
			user  = InteractionObjectify.getUserByKey(session.getUserKey());
			out.print(Serialiseur.serialiseUser(user));
		}
		else if (s == null && id != null) //soit en fonction de l'id fournit de l'utilisateur
		{
			user = InteractionObjectify.getUserById(id);
			out.print(Serialiseur.serialiseUser(user));
		}
		else {
			reponse.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		}catch (Exception e){
			e.printStackTrace();
			reponse.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		
		
		
	}
	
	
}
