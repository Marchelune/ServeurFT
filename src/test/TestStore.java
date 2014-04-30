package test;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import securite.Session;
import securite.TableSessions;
import catalogue.Catalogue;
import catalogue.Feedback;
import catalogue.Item;


import donnees.InteractionObjectify;
import donnees.Serialiseur;
import donnees.User;

@SuppressWarnings("serial")
public class TestStore extends HttpServlet { //service du catalogue : permet de consulter les articles disponibles (GET) et de faire des achats (TODO POST)

	

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		try {
			this.getServletContext().getRequestDispatcher("/WEB-INF/TestAchat.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		}
		
	}


	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String s = req.getParameter("session"); //identifiant de la session (UUID)
		String sItem = req.getParameter("item");
		String querie = req.getParameter("q");
		PrintWriter out = resp.getWriter();

		//Authentification
		if(s != null && querie != null){
			Session session = TableSessions.getSession(s);
			if (session != null){
				User user = InteractionObjectify.getUserByKey(session.getUserKey());
				Item item = Catalogue.getItemById(sItem);
				if(item != null){
					if(querie.equals("buy")){
						
						if(user.getCoins() >= item.getPrice()){
							if(item.getQuantity() > 0){
								user.acheter(item);
								InteractionObjectify.saveUser(user);
								resp.setContentType("text/xml; charset=UTF-8");
								out.print(Serialiseur.serialisePurchase(Catalogue.getPurchaseByKey(user.getPurchases().get(user.getPurchases().size() -1))));
							}else {resp.sendError(500, "Sold out");}
						}else {resp.sendError(HttpServletResponse.SC_PAYMENT_REQUIRED);}

					}else if (querie.equals("opinion")){
						String snote = req.getParameter("note");
						String comment = req.getParameter("comment");
						if(snote != null && comment != null){
							int note = Integer.parseInt(snote) % 5;
							
							Feedback feedback = new Feedback(user, comment,note, sItem);
							Catalogue.saveFeedback(feedback);
							item.addFeedback(feedback);
							Catalogue.saveItem(item);
							out.print(Serialiseur.serialiseFeedback(feedback));
							
						}else {resp.sendError(HttpServletResponse.SC_BAD_REQUEST);}
						
					}else {resp.sendError(HttpServletResponse.SC_BAD_REQUEST);}
					
				}else {resp.sendError(HttpServletResponse.SC_NOT_FOUND);}

			}else {resp.sendError(HttpServletResponse.SC_FORBIDDEN);}

		}else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}

	}

}
