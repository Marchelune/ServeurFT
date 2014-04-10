package serveurFT1;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.Md5;
import auth.Session;
import auth.TableSessions;
import donnees.InteractionObjectify;
import donnees.User;

@SuppressWarnings("serial")
public class Authentificaton extends HttpServlet { //ouverture de session. TODO : ajouter un paramètre pour créer différents type de session
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try
		{
			this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(req, resp);
		}
		catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) {	
        try {
        	req.setCharacterEncoding("UTF-8");
        	PrintWriter out = resp.getWriter();
        	String id = req.getParameter("id");
        	String type = req.getParameter("type");
        	String idMachine = req.getParameter("m");
        	String pass = req.getParameter("password");
        	if(id != "" && pass != "")
        	{
        		User user = InteractionObjectify.getUserById(id);
        		if (user != null){
        			String passwordCrypte = Md5.encode("ZSS3q2b65m"+ pass + id);
        			if(passwordCrypte.equals(user.getPassword())){

        				if(idMachine != null && type.equals("kinect")){
        					Session session = TableSessions.newKinectSession(user, idMachine);
        					out.print(session.getId());
        				}
        				else if (idMachine == null && type.equals("kinect")) {
        					resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        				}else{
        					Session session = TableSessions.newSession(user); 
        					out.print(session.getId());
        				}


        			}else{out.print("401");} // resp.sendError(HttpServletResponse.SC_UNAUTHORIZED)
        		}else{out.print("404");}  // resp.sendError(HttpServletResponse.SC_NOT_FOUND );
        	}else{out.print("401");}   //erreur 401

        } catch (IOException e) {
        	e.printStackTrace();
        }
	}

}
