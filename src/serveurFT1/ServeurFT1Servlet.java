package serveurFT1;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.ObjectifyService;

import donnees.User;
import static com.googlecode.objectify.ObjectifyService.ofy;

@SuppressWarnings("serial")
public class ServeurFT1Servlet extends HttpServlet {
	static {
        ObjectifyService.register(User.class);
    }
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try
		{
			List<User> users = ofy().load().type(User.class).order("-coins").limit(5).list();

			req.setAttribute("users", users);
			this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateurs.jsp").forward(req, resp);
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
            // Création de l'objet
        	if(req.getParameter("nom") != null && req.getParameter("prenom") != null && Integer.parseInt( req.getParameter("coins")) >= 0)
        	{
            User user = new User(req.getParameter("nom"), req.getParameter("prenom"), Integer.parseInt( req.getParameter("coins")));
            // Enregistrement de l'objet dans le Datastore avec Objectify
            ofy().save().entity(user).now();
        	}

            resp.sendRedirect("/");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}
