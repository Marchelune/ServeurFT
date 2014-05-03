package serveurFT1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import securite.Session;
import securite.TableSessions;
import donnees.InteractionObjectify;
import donnees.User;

@SuppressWarnings("serial")
public class Friends extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try
		{
			String s = req.getParameter("session");
			String id = req.getParameter("id");
			String q = req.getParameter("q");

			if(s != null && id != null && q!= null){
				Session session = TableSessions.getSession(s);
				User user = InteractionObjectify.getUserByKey(session.getUserKey());
				User friend = InteractionObjectify.getUserById(id);

				if (user != null){
					if (q.equals("add")){
						if(friend != null){
							user.addFriend(friend.getId());
							InteractionObjectify.saveUser(user);
						}else{resp.sendError(HttpServletResponse.SC_NOT_FOUND);}
					}else if (q.equals("delete")){
						if(friend != null){
							friend.deleteFriend(user.getId());
							InteractionObjectify.saveUser(user);
						}else{resp.sendError(HttpServletResponse.SC_NOT_FOUND);}
					}
				}else{resp.sendError(HttpServletResponse.SC_NOT_FOUND);}
			}else{resp.sendError(HttpServletResponse.SC_BAD_REQUEST);}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}
