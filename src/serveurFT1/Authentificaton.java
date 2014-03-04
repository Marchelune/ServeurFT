package serveurFT1;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.Key;

import auth.Session;
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
        	if(id != null && pass != null)
        	{
        		User user = interaction.getUserById(id);
        		if (user != null){
        			String passwordCrypte = "ZSS3q2b65m"+ pass + id;
        			if(passwordCrypte.equals(user.getPassword())){
        				Session session = new Session(user.getKey());
        				interaction.saveSession(session);
        				out.print(session.getId());
        			}else{resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);} // erreur 401
        		}else{resp.sendError(HttpServletResponse.SC_NOT_FOUND );}  // erreur 404
        	}else{resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);}   //erreur 401

        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
