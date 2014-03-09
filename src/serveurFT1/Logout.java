package serveurFT1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.Session;
import auth.TableSessions;


@SuppressWarnings("serial")
public class Logout extends HttpServlet {  //Permet de mettre fin à une session

	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {	
        try {
        	req.setCharacterEncoding("UTF-8");
        	PrintWriter out = resp.getWriter();
        	String s = req.getParameter("session");
        	
        	Session session = TableSessions.getSession(s); 
        	if (session != null)
        	{
        		TableSessions.deleteSession(session);
        	}else {out.print("404");}
        	
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
