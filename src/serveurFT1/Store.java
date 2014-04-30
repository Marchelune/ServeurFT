package serveurFT1;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import securite.Session;
import securite.TableSessions;
import catalogue.Catalogue;
import catalogue.Feedback;
import catalogue.Item;

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
				}else if(q.equals("getItem") && req.getParameter("id") != null){
					resp.setContentType("text/xml; charset=UTF-8");
					out.print(Serialiseur.serialiseItem(Catalogue.getItemById(req.getParameter("id"))));


				}else if(q.equals("historic") && req.getParameter("session") != null){ //renvoie l'historique des achats
					Session session = TableSessions.getSession(req.getParameter("session")); 
					if(session != null){
						resp.setContentType("text/xml; charset=UTF-8");
						User user = InteractionObjectify.getUserByKey(session.getUserKey());
						out.print(Serialiseur.serialisePurchases(Catalogue.getPurchasesByKey(user.getPurchases())));
					}else{resp.sendError(HttpServletResponse.SC_NOT_FOUND);}

				}else{resp.sendError(HttpServletResponse.SC_BAD_REQUEST);}

			}else{resp.sendError(HttpServletResponse.SC_BAD_REQUEST);}

		}catch (IOException e)
		{
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

							if(user.possede(sItem)){ // on vérifie que l'utilisateur a bien acheté l'item qu'il note.
								Feedback feedback = new Feedback(user, comment,note, sItem);
								Catalogue.saveFeedback(feedback);
								item.addFeedback(feedback);
								Catalogue.saveItem(item);
								out.print(Serialiseur.serialiseFeedback(feedback));
							}else{resp.sendError(HttpServletResponse.SC_FORBIDDEN);}

						}else {resp.sendError(HttpServletResponse.SC_BAD_REQUEST);}
						
					}else {resp.sendError(HttpServletResponse.SC_BAD_REQUEST);}
					
				}else {resp.sendError(HttpServletResponse.SC_NOT_FOUND);}

			}else {resp.sendError(HttpServletResponse.SC_FORBIDDEN);}

		}else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}

	}

}
