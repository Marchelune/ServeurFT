package serveurFT1;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import donnees.S�rialiseur;

@SuppressWarnings("serial")
public class InfoUser extends HttpServlet {

	public void doGet( HttpServletRequest request, HttpServletResponse reponse ) throws ServletException, IOException{
		reponse.setContentType("text/xml");
		//long id = Integer.parseInt(request.getParameter( "id" ));
		
		PrintWriter out = reponse.getWriter();
		
		S�rialiseur s�rialiseur = new S�rialiseur();
		
		out.print(s�rialiseur.s�rialiser());
	}
	
	
}
