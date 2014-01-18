<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="donnees.User" %>


<!DOCTYPE html>

<html>
	<head>
		<title>FitTogether</title>
		<meta charset="utf-8" />
	</head>

	<body>
		<h1>Inscrivez-vous sur l'application FitTogether !</h1>
		<form action="/post" method="post">
			<p>
				<label>Votre nom : <input type="text" name="nom" /></label>
			</p>
			<p>
				<label>Votre prénom : <input type="text" name="prenom" /></label>
			</p>
			<p>
				<label>Votre nombre initial de SportCoins : <input type=number name="coins" /></label>
			</p>
			<p>
				<input type="submit" />
			</p>
		</form>
	
		<h1>Ils sont aussi inscrits sur FitTogether :</h1>
		<%
			List<User> users = (List<User>) request.getAttribute("users");
			for (User user : users) {
		%>
		<p>
			<strong><%= user.getPrenom() %> <%= user.getNom() %></strong> est aussi inscrit, avec 
			<%= user.getCoins() %> SportCoins. <br />
		</p>
		<%
			}
		%>
	</body>
</html>