<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Achat</title>
</head>

<%@ include file="menu.jsp" %>
<body>
 <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
	<br/>
	<h3>Faire une demande d'achat</h3>
	<form action = "" method = "POST">
		Catégorie :
		<select class="form-control" name="categorie" id="categorie" required>
	      <option value="" selected disabled hidden=true>Choisir une catégorie</option>
			<c:forEach var="categorie" items="${ categories }">
				<option value="<c:out value="${ categorie }" />"><c:out value="${ categorie }" /></option>
			</c:forEach>
	    </select>
		<br/>
		Modèle :
		<select class="form-control" name="modele" id="modele" required>
	      <option value="" selected disabled hidden=true>Choisir un modele de voiture</option>
			<c:forEach var="modele" items="${ modeles }">
				<option value="<c:out value="${ modele }" />"><c:out value="${ modele }" /></option>
			</c:forEach>
	    </select>
		<br/>
		Couleur :
		<select class="form-control" name="couleur" id="couleur" required>
	      <option value="" selected disabled hidden=true>Choisir la couleur de la voiture souhaitée</option>
			<c:forEach var="couleur" items="${ couleurs }">
				<option value="<c:out value="${ couleur }" />"><c:out value="${ couleur }" /></option>
			</c:forEach>
	    </select>
		<br/>
	    Budget : <input type = "number" name = "prix" id="prix" class="form-control" required>
	  	<br/>
	  <input type = "submit" value = "Submit" />
	</form>
  </main>
</body>
</html>
