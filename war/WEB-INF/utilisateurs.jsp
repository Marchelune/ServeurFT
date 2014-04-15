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
		<meta charset="UTF-8" />
		<link rel="stylesheet" type="text/css" href="css/style.css">
	</head>

	<body >
		<h1>Inscrivez-vous sur l'application FitTogether !</h1>
		
		
		<form action="<%= blobstoreService.createUploadUrl("/") %> " method="post" enctype="multipart/form-data">
			<fieldset>
			<p>
				<label for="prenom">Votre prénom :</label>
   				<input type="text" name="prenom" size="20" maxlength="70" required id="prenom"  />
			</p>
			<p>
				<label for="nom">Votre nom :</label>
   				<input type="text" name="nom" size="20" maxlength="70" required id="nom" />
			</p>
			<p>
				<label for="email">Votre email :</label>
   				<input type="email" name="id" size="20" maxlength="40" id="email" required  />
			</p>
			<p>
				<label>Choisissez une photo de profil : <input type="file" name="photo" /></label>
			</p>
			<p>
				<label for="password">Votre mot de passe  :</label>
   				<input type="password" name="password" required size="20"   id="password"/>
			</p>
			<p>
				<input type="submit" />
			</p>
			</fieldset>
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


