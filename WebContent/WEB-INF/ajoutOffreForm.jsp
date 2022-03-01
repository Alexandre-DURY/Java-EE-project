<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Ajout Offre</title>
</head>
<c:if test="${empty Ajouts }">
	<%@ include file="menu.jsp" %>
	<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
		<form method=POST enctype="multipart/form-data">
			<br/>
			<label for="avatar">Photo du vehicule:</label>
			<br/>
			<input type="file" id="visuel" name="visuel" accept="image/*" multiple onchange="readFilesAndDisplayPreview(this.files);">
			<br/>
			<br/>
			<label>Modele :</label>
			<br/>
			<input type="text" class="form-control" id="modele" name="modele">
			<br/>
			<label>Categorie:</label>
			<br/>
			<input type="text" class="form-control" id="categorie" name="categorie">
			<br/>
			<label>Etat general :</label>
			<br/>
			<div class="form-check form-check-inline">
			  <input class="form-check-input" type="radio" name="etat" id="etat" value="Neuf">
			  <label class="form-check-label" for="inlineRadio2">Neuf</label>
			</div>
			<div class="form-check form-check-inline">
			  <input class="form-check-input" type="radio" name="etat" id="etat" value="Tres Bon">
			  <label class="form-check-label" for="inlineRadio2">Tres Bon</label>
			</div>
			<div class="form-check form-check-inline">
			  <input class="form-check-input" type="radio" name="etat" id="etat" value="Bon">
			  <label class="form-check-label" for="inlineRadio2">Bon</label>
			</div>
			<div class="form-check form-check-inline">
			  <input class="form-check-input" type="radio" name="etat" id="etat" value="Moyen">
			  <label class="form-check-label" for="inlineRadio2">Moyen</label>
			</div>
			<div class="form-check form-check-inline">
			  <input class="form-check-input" type="radio" name="etat" id="etat" value="Mauvais">
			  <label class="form-check-label" for="inlineRadio2">Mauvais</label>
			</div>
			<div class="form-check form-check-inline">
			 <input class="form-check-input" type="radio" name="etat" id="etat" value="Tres Mauvais">
			 <label class="form-check-label" for="inlineRadio1">Tres Mauvais</label>
			</div>
			<br/>
			<br/>
			<label>Kilometrage :</label>
			<br/>
			<div class="input-group">
			  <input type="number" name="km" id="km">
			  <div class="input-group-append">
			    <span class="input-group-text">km</span>
			  </div>
			</div>
			<br/>
			<label>Controle technique :</label>
			<br/>
			<div class="form-check form-check-inline">
			  <input class="form-check-input" type="radio" name="ct" id="ct" value="1">
			  <label class="form-check-label" for="inlineRadio1">Oui</label>
			</div>
			<div class="form-check form-check-inline">
			  <input class="form-check-input" type="radio" name="ct" id="ct" value="0">
			  <label class="form-check-label" for="inlineRadio2">Non</label>
			</div>
			<br/>
			<br/>
			<label>Couleur:</label>
			<br/>
			<input type="text" id="couleur" name="couleur">
			<br/>
			<br/>
			<label>Annee :</label>
			<br/>
			<input type="number" id="annee" name="annee" min="1900" max="2021">
			<br/>
			<br/>
			<label>Description :</label>
			<input type="text" class="form-control" id="description" name="description">
		 	<br/>
		 	<label>Prix :</label>
			<br/>
			<input type="number" id="prix" name="prix" min="0" max="100000000">
			<br/>
			<br/>
		 	<input type = "submit" value = "Soumettre l'offre" />
		</form>
	</main>
</c:if>
<c:if test="${not empty Ajouts }">
<meta http-equiv="refresh" content="0; URL=offre?id=<c:out value="${id}"></c:out>" >
</c:if>
</html>