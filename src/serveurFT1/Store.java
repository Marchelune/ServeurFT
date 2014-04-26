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
import catalogue.Catalogue;

import com.googlecode.objectify.ObjectifyService;

import donnees.InteractionObjectify;
import donnees.Serialiseur;
import donnees.User;

@SuppressWarnings("serial")
public class Store extends HttpServlet { //service du catalogue : permet de consulter les articles disponibles (GET) et de faire des achats (TODO POST)

	static {
        ObjectifyService.register(User.class);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try
		{
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			PrintWriter out = resp.getWriter();
			String q = req.getParameter("q");
			String group = req.getParameter("group");
			String category = req.getParameter("category");
			
			if(q!=null){
				if(q.equals("getItems")){
					resp.setContentType("text/xml; charset=UTF-8");
					if(group != null && group.equals("category") && category != null){
						out.print(Serialiseur.serialiseItems(Catalogue.getItemByCategory(20, category)));
					}
					else if(group != null && group.equals("top")){
						out.print(Serialiseur.serialiseItems(Catalogue.getItemByPopularite(10)));
					}
					else{
						out.print(Serialiseur.serialiseItems(Catalogue.getItemByNote(20)));
					}
				}
				else if(q.equals("getGroups")){
					out.print(Catalogue.getCatecories().toString());
				}
				
			}else{resp.sendError(HttpServletResponse.SC_BAD_REQUEST);}
			
		}catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
}
