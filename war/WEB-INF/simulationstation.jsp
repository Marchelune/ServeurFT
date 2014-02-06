<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="donnees.User" %>
<%@ page import="donnees.Exercice" %>    
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Simulation de station kinect</title>
</head>
<body>
<h1>Rentrer ici les rendus de la séance</h1>
<form action="/Nouvelexercice" method="post" >
   <p>Texte à l'intérieur du formulaire</p>
   
   <p>
   <label>Id de l'utilisateur : <input type="text" name="id" required/></label>
   </p>
   <p>
   <label>Durée (en secondes) : <input type="number" name="duree" min="0" required /></label>
   </p>
   <p>
   <label>Nombre de répétitions : <input type="number" name="repetitions" min="0" required /></label>
   </p>
   <p>
   <label>SportCoins gagnés : <input type="number" name="coins" min="0" required /></label>
   </p>
   <p>
       <label for="sport">De quel sport sagissait-il ?</label><br />
       <select name="sport" id="sport">
           <option value="squat">Squat</option>
           <option value="etirement">Etirement</option>
           <option value="ciseaux">Ciseaux</option>
           <option value="step">Step</option>
           <option value="boxe">Boxe</option>
       </select>
   </p>
   <p>
   <label>Date (JJMMAAAA) : <input  type="date"  name="date" required /></label>
   </p>
  
	<p>
		<input type="submit" value="Envoyer" />
	</p>
   
</form>

</body>
</html>