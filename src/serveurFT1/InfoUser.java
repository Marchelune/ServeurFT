package serveurFT1;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import donnees.Serialiseur;




@SuppressWarnings("serial")
public class InfoUser extends HttpServlet {

	public void doGet( HttpServletRequest request, HttpServletResponse reponse ) throws ServletException, IOException{
		reponse.setContentType("text/xml");
		
		PrintWriter out = reponse.getWriter();
		Serialiseur serialiseur = new Serialiseur();

		out.print(serialiseur.serialise());
		
	}
	
	
}
