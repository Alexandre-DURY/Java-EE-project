<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Détails</title>
</head>
<%@ include file="menu.jsp" %>
<body>
 <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4"> 
 </br></br>
 <div class="row align-items-center">
     <div class="col-lg-4">
        <svg class="bd-placeholder-img rounded-circle" width="140" height="140" src="<c:out value="${ vendeur.photo }" />" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 140x140" preserveAspectRatio="xMidYMid slice" focusable="false">
         <title>Placeholder</title><rect width="100%" height="100%" fill="#777"/><text x="50%" y="50%" fill="#777" dy=".3em">140x140</text>
         <image xlink:href="<c:out value="${ vendeur.photo }"/>" height="140" width="140" />
        </svg>
	    <h2><c:out value="${ vendeur.nom }" />  <c:out value="${ vendeur.prenom }" /></h2>
        <p><c:out value="${ vendeur.description }" /></p>
        
     </div><!-- /.col-lg-4 -->
  </div>
  </br> </br>
  <h3>Liste des offres du vendeur :</h3>
  <c:if test="${!(not empty offres) }">
  </br> </br>
  <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor" class="bi bi-emoji-frown" viewBox="0 0 16 16">
  		<path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
  		<path d="M4.285 12.433a.5.5 0 0 0 .683-.183A3.498 3.498 0 0 1 8 10.5c1.295 0 2.426.703 3.032 1.75a.5.5 0 0 0 .866-.5A4.498 4.498 0 0 0 8 9.5a4.5 4.5 0 0 0-3.898 2.25.5.5 0 0 0 .183.683zM7 6.5C7 7.328 6.552 8 6 8s-1-.672-1-1.5S5.448 5 6 5s1 .672 1 1.5zm4 0c0 .828-.448 1.5-1 1.5s-1-.672-1-1.5S9.448 5 10 5s1 .672 1 1.5z"/>
  </svg>
  <h2>Le vendeur n'a pas d'offre ! </h2> 
  </c:if>
  <div class="album py-5 bg-light">
        	<div class="container">
            	<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            	<c:forEach var="voiture" items="${ offres }">
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
		                            <small class="text-muted">${voiture.prix }  &euro;</small>
	                          	</div>
                        	</div>
                      	</div>
                    </div>
            	</c:forEach>
              	</div>
            </div>
   </div>
 </main>

</body>
</html>