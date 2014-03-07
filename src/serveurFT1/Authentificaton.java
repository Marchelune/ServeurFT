package serveurFT1;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.Key;

import auth.Md5;
import auth.Session;
import auth.TableSessions;
import donnees.InteractionObjectify;
import donnees.User;

@SuppressWarnings("serial")
public class Authentificaton extends HttpServlet {
	
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
        	String pass = req.getParameter("password");
        	InteractionObjectify interaction = new InteractionObjectify();
        	if(id != "" && pass != "")
        	{
        		User user = interaction.getUserById(id);
        		if (user != null){
        			String passwordCrypte = Md5.encode("ZSS3q2b65m"+ pass + id);
        			if(passwordCrypte.equals(user.getPassword())){
        				Session session = new Session(user.getKey());
        				TableSessions.saveSession(session);
        				out.print(session.getId());
        			}else{out.print("401");} // sendError(HttpServletResponse.SC_UNAUTHORIZED)
        		}else{out.print("404");}  // resp.sendError(HttpServletResponse.SC_NOT_FOUND );
        	}else{out.print("401");}   //erreur 401

        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
