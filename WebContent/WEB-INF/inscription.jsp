<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>inscription</title>

 <!-- Bootstrap core CSS -->
<link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/sign-in/">
<link href="assets/css/signin.css" rel="stylesheet">

</head>
<body class="text-center">
<c:if test="${erreur  == 'non'}">
	<meta http-equiv="refresh" content="0; URL=login">
</c:if>
<c:if test="${empty sessionScope.pseudo }">
	<main class="form-signin">
	<h1 class="h3 mb-3 fw-normal" >Page d'inscription : </h1>
	<img class="mb-4" src="assets/photos/vente-aux-encheres.png" alt="" width="90" height="90">
	    <form method = "post" action = "">
	       <label for = "user" class="visually-hidden" >Nom d'utilisateur : </label>
	       <input type = "text" name = "user" id = "user" class="form-control" placeholder="Nom d'utilisateur" required autofocus/>
	       <label for = "email" class="visually-hidden" >Adresse mail : </label>
	       <input type = "text" name = "email" id = "email" class = "form-control" placeholder = "Adresse mail" required autofocus/>
	       <label for = "pass" class="visually-hidden"> Mot de passe : </label>
	       <input type = "password" name = "pass" id = "pass" class="form-control" placeholder="mot de passe" required autofocus/>
	       <button class="w-100 btn btn-lg btn-primary" type="submit">S'inscrire</button>
	       </form>
	       	<a href="login">Se connecter</a>
	       	<c:if test = "${erreur == 'oui'}">
	       		<br/><div class="alert alert-danger" role="alert">Le login et/ou mot de passe est incorrect </div>
	    	</c:if>
	    	<p class="mt-5 mb-3 text-muted">&copy; 2021</p>
	</main>
</c:if>
</body>
</html>