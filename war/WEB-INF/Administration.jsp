<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.google.appengine.api.blobstore.*" %>
<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Interface Admin FitTogether</title>
</head>
<body>
<h1>Bienvenue sur l'interface d'administration de FitTogether</h1>
		<h2>Ajouter une récompense</h2>
		
		<form action="<%= blobstoreService.createUploadUrl("/Admin") %> " method="post" enctype="multipart/form-data">
			<fieldset>
			<p>
				<label for="name">Nom de l'article :</label>
   				<input type="text" name="name" size="20" maxlength="70" required id="name"  />
			</p>
			<p>
				<label for="price">Prix de l'article :</label>
   				<input type="text" name="price" size="20" maxlength="70" required id="price" />
			</p>
			<p>
				<label for="quantity">Quantité initiale :</label>
   				<input type="text" name="quantity" size="20" maxlength="40" id="quantity" required  />
			</p>
			<p>
				<label>Choisissez une photo de profil : <input type="file" name="photo" /></label>
			</p>
			<p>
     		  <label for="category">Catégorie du produit</label><br />
     		  <select name="category" id="category">
           <option value="Voyage">Voyage</option>
           <option value="Gastronomie">Gastronomie</option>
           <option value="Décoration">Décoration</option>
           <option value="Sport">Sport</option>
           <option value="Autre">Autre</option>
       			</select>
   			</p>
   			<p>
				<label for="description">Description de l'article :</label>
   				<input type="text" name="description" size="20" required id="name"  />
			</p>
			<p>
				<input type="submit" />
			</p>
			</fieldset>
		</form>


</body>
</html>