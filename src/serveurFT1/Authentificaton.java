package serveurFT1;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import securite.Md5;
import securite.Session;
import securite.TableSessions;
import donnees.InteractionObjectify;
import donnees.User;

@SuppressWarnings("serial")
public class Authentificaton extends HttpServlet { //ouverture de session (il y a différents type de sessions
	
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

        				if (type != null){
        					if(idMachine != null && type.equals("kinect")){   // on crée une session de type kinect qui permet l'ajout de sportcoins
        						Session session = TableSessions.newKinectSession(user, idMachine);
        						out.print(session.getId());
        					}
        					else if (idMachine == null && type.equals("kinect")) {
        						resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        					}
        				}else{  //cas par défaut : une session de simple consultation
        					Session session = TableSessions.newSession(user); 
        					out.print(session.getId());
        				}


        			}else{ resp.sendError(HttpServletResponse.SC_UNAUTHORIZED) ;}
        		}else{  resp.sendError(HttpServletResponse.SC_NOT_FOUND );}
        	}else{ resp.sendError(HttpServletResponse.SC_UNAUTHORIZED) ;}  //erreur 401

        } catch (IOException e) {
        	e.printStackTrace();
        }
	}

}
