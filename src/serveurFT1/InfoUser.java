package serveurFT1;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import donnees.InteractionObjectify;
import donnees.Serialiseur;
import donnees.User;



@SuppressWarnings("serial")
public class InfoUser extends HttpServlet {  //fournit les informations basiques d'un utilisateur en XML

	public void doGet( HttpServletRequest request, HttpServletResponse reponse ) throws ServletException, IOException{
		reponse.setContentType("text/xml; charset=UTF-8");
		String id = request.getParameter("id");
		PrintWriter out = reponse.getWriter();
		
		
		User user = InteractionObjectify.getUserById(id);
		
		out.print(Serialiseur.serialiseUser(user));
		
		
		
	}
	
	
}
