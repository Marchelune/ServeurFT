package serveurFT1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import donnees.InteractionObjectify;
import donnees.User;
import auth.Session;
import auth.TableSessions;

@SuppressWarnings("serial")
public class Friends extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try
		{
			String s = req.getParameter("session");
			String id = req.getParameter("id");
			String q = req.getParameter("q");
			Session session = TableSessions.getSession(s);
			User user = InteractionObjectify.getUserByKey(session.getUserKey());
			User friend = InteractionObjectify.getUserById(id);

			if (user != null){
				if (q.equals("add")){
					if(friend != null){
						user.addFriend(id);
						InteractionObjectify.saveUser(user);
					}else{resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);}
				}else if (q.equals("delete")){
					if(friend != null){
						user.deleteFriend(id);
						InteractionObjectify.saveUser(user);
					}
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
