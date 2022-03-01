<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>
</head>
	<%@ include file="menu.jsp" %>
	
    
     <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4"> 
      <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<c:if test="${not empty sessionScope.pseudo}">
			<h2 class="h2">Bienvenue <c:out value="${ sessionScope.pseudo }"></c:out></h2>
		</c:if>
        
        <!--Pas encore fonctionnel, en cours de programmation -->
        <div class="btn-toolbar mb-2 mb-md-0">
          <div class="btn-group me-2">
            <button type="button" class="btn btn-sm btn-outline-secondary">Partager</button>
            <button type="button" class="btn btn-sm btn-outline-secondary">Exporter</button>
          </div>
        </div>
      </div>
      
      <c:if test="${not empty sessionScope.pseudo }">
	      <section class="py-5 text-center container">
		    	<div class="row py-lg-5">
		      		<div class="col-lg-6 col-md-8 mx-auto">
				      	<h1 class="fw-light">Mes transactions</h1>
				        <p class="lead text-muted">Cette section regroupe toutes vos transactions.</p>
				        <p>
				          <a href="maListeAchat" class="btn btn-primary my-2">Ma liste d'achats</a>
				          <a href="mesVentes" class="btn btn-secondary my-2">Mes annonces</a>
				        </p>
		      		</div>
		    	</div>
		 	</section>
		</c:if>
 	 
        <div class="album py-5 bg-light">
        <h2>Liste des annonces du site </h2>
        	<div class="container">
            	<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            	<c:forEach var="voiture" items="${ voitures }">
            		<div class="col">
                      	<div class="card shadow-sm">
                        	<img class="mb-4" src="assets/photos/offres/${voiture.visuel }" alt="" width="auto" height="auto">
                        	<div class="card-body">
	                          <p class="card-text"><c:out value="${ voiture.modele }" /></p>
	                          <p class="card-text"><c:out value="${ voiture.description }" /></p>
		                      	<div class="d-flex justify-content-between align-items-center">
		                            <div class="btn-group">
		                            <button type="button" class="btn btn-sm btn-outline-secondary" onclick="window.location.href='offre?id=<c:out value="${ voiture.id }" />';">Voir</button>
		                            <button type="button" class="btn btn-sm btn-outline-secondary" disabled>Modifier</button>
		                            </div>
		                            <small class="text-muted">${ voiture.prix } &euro;</small>
	                          	</div>
                        	</div>
                      	</div>
                    </div>
            	</c:forEach>
              	</div>
            </div>
        </div>

      <canvas class="my-4 w-100" id="myChart" width="900" height="380"></canvas>
   
   </main>
  
</body>
</html>