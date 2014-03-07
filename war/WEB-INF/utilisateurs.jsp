<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="donnees.User" %>
<%@ page import="com.google.appengine.api.blobstore.*" %>

<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>


<!DOCTYPE html>

<html>
	<head>
		<title>FitTogether</title>
		<meta charset="utf-8" />
	</head>

	<body bgcolor="#00CC00">
		<h1>Inscrivez-vous sur l'application FitTogether !</h1>
		
		
		<form action="<%= blobstoreService.createUploadUrl("/upload") %>" method="post" enctype="multipart/form-data">
			<p>
				<label>Votre nom : <input type="text" name="nom" required /></label>
			</p>
			<p>
				<label>Votre prénom : <input type="text" name="prenom" required /></label>
			</p>
			<p>
				<label>Votre email : <input type="email" name="id" required /></label>
			</p>
			<p>
				<label>Choisissez une photo de profil : <input type="file" name="photo" /></label>
			</p>
			<p>
				<label>Votre mot de passe : <input type="password" name="password" required /></label>
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
			<% if(user.getUrlPhoto() != null ) {%>
			<img src="<%=user.getUrlPhoto()%>" alt="Image utilisateur" class="img-polaroid" style="max-width: 100px;" />
			<% }%>
			<strong><%= user.getPrenom() %> <%= user.getNom() %></strong> est aussi inscrit, avec 
			<%= user.getCoins() %> SportCoins. Id = <%= user.getId() %> <br />
			<% if(user.getExercicesKeys().size() != 0) {%>
			Le dernier sport de <%= user.getPrenom() %> était : <%= user.getExercice(0).getType() %> <br /> 
			<% } %>
		</p>
		<%
			}
		%>
	</body>
</html>


