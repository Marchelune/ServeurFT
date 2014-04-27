<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="donnees.User" %>
<%@ page import="donnees.Exercice" %>    
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test de l'achat et du feedback</title>
</head>
<body>
<h1>Rentrer ici les paramètres</h1>
<form action="/TestStore" method="post" >
   
  		<p>
			<label for="session">N° de session :</label>
   			<input type="text" name="session" size="20" maxlength="70" required id="session"  />
		</p>
		<p>
			<label for="item">Nom de l'article :</label>
   			<input type="text" name="item" size="20" maxlength="70" required id="item"  />
		</p>
		<p>
			<label for="q">Requête (buy/opinion):</label>
   			<input type="text" name="q" size="20" maxlength="70" required id="q"  />
		</p>
		<p>
			<label for="comment">Commentaire :</label>
   			<input type="text" name="comment" size="20" maxlength="170"  id="comment"  />
		</p>
		<p>
			<label for="note">Note /5 :</label>
   			<input type="text" name="note" size="20" maxlength="1"  id="note"  />
		</p>
  
	<p>
		<input type="submit" value="Envoyer" />
	</p>
   
</form>

</body>
</html>