<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>connexion</title>

 <!-- Bootstrap core CSS -->
<link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/sign-in/">
<link href="assets/css/signin.css" rel="stylesheet">

</head>
<body class="text-center">
<c:if test="${not empty sessionScope.pseudo }">
	<meta http-equiv="refresh" content="0; URL=accueil">
</c:if>
<c:if test="${empty sessionScope.pseudo }">
	<main class="form-signin">
	<h1 class="h3 mb-3 fw-normal" >Page de connexion : </h1>
	<img class="mb-4" src="assets/photos/vente-aux-encheres.png" alt="" width="90" height="90">
	    <form method = "post" action = "">
	       <label for = "login" class="visually-hidden" >Login : </label>
	       <input type = "text" name = "login" id = "login" class="form-control" placeholder="Login" required autofocus/>
	       <label for = "pass" class="visually-hidden"> Mot de passe : </label>
	       <input type = "password" name = "pass" id = "pass" class="form-control" placeholder="password" required />
	       <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
	       </form>
	       	<a href="inscription">Inscription</a>
	       	<c:if test = "${erreur == 'oui'}">
	       		<br/><div class="alert alert-danger" role="alert">Le login et/ou mot de passe est incorrect </div>
	    	</c:if>
	    	<p class="mt-5 mb-3 text-muted">&copy; 2021</p>
	</main>
</c:if>
</body>
</html>