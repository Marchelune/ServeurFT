<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Connexion à FitTogether</title>
 <link rel="stylesheet" type="text/css" href="CSS/style.css" />
</head>

<body>

<h1>Connectez-vous à l'application FitTogether !</h1>

	<form action="/Auth" method="post" class="login">
    <p><label>Identifiant (email) : <input type="email" name="id" /></label></p>

    <p><label>Mot de passe : <input type="password" name="password" id="password" value="password" /></label></p>

    <p class="login-submit">
      <button type="submit" class="login-button">Login</button>
    </p>
    
  </form>

</body>

</html>