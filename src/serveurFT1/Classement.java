package serveurFT1;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import securite.Session;
import securite.TableSessions;

import com.googlecode.objectify.ObjectifyService;

import donnees.InteractionObjectify;
import donnees.Serialiseur;
import donnees.User;

@SuppressWarnings("serial")
public class Classement extends HttpServlet {

	static {
        ObjectifyService.register(User.class);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try
		{
			req.setCharacterEncoding("UTF-8");
			resp.setContentType("text/xml; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			if(req.getParameter("n") != null) {
				int n = Integer.parseInt(req.getParameter("n"));


				if( n>0){
					List<User> users = ofy().load().type(User.class).order("-coins").limit(n).list();
					String classement = Serialiseur.serialiseUsers(users);
					out.print(classement);
				}
			}
		}catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
}
