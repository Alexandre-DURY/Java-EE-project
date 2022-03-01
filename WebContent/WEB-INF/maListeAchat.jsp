<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ma liste</title>
</head>
	<%@ include file="menu.jsp" %>
	
    
     <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4"> 
      <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<c:if test="${not empty sessionScope.pseudo}">
			<h2 class="h2">Bienvenue <c:out value="${ sessionScope.pseudo }"></c:out></h2>
		</c:if>
        
        <div class="btn-toolbar mb-2 mb-md-0">
          <div class="btn-group me-2">
            <button type="button" class="btn btn-sm btn-outline-secondary">Share</button>
            <button type="button" class="btn btn-sm btn-outline-secondary">Export</button>
          </div>
          <button type="button" class="btn btn-sm btn-outline-secondary dropdown-toggle">
            <span data-feather="calendar"></span>
            This week
          </button>
        </div>
      </div>
      
      <c:if test="${not empty sessionScope.pseudo }">
	      <section class="py-5 text-center container">
		    	<div class="row py-lg-5">
		      		<div class="col-lg-6 col-md-8 mx-auto">
				      	<h1 class="fw-light">Mes achats</h1>
				        <p class="lead text-muted">Cette section regroupe toutes vos voitures acheté sur ce site.</p>
				        <c:if test="${ !(not empty mesArticles) }"> 
				        <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor" class="bi bi-emoji-frown" viewBox="0 0 16 16">
  							<path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
  							<path d="M4.285 12.433a.5.5 0 0 0 .683-.183A3.498 3.498 0 0 1 8 10.5c1.295 0 2.426.703 3.032 1.75a.5.5 0 0 0 .866-.5A4.498 4.498 0 0 0 8 9.5a4.5 4.5 0 0 0-3.898 2.25.5.5 0 0 0 .183.683zM7 6.5C7 7.328 6.552 8 6 8s-1-.672-1-1.5S5.448 5 6 5s1 .672 1 1.5zm4 0c0 .828-.448 1.5-1 1.5s-1-.672-1-1.5S9.448 5 10 5s1 .672 1 1.5z"/>
						</svg>
				        <h2> Vous n'avez acheté aucune voiture sur ce site ! </h2> 
				        </c:if>
		      		</div>
		    	</div>
		 	</section>
		</c:if>
 	 
        <div class="album py-5 bg-light">
        	<div class="container">
            	<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            	<c:forEach var="article" items="${ mesArticles }">
            		<div class="col">
                      	<div class="card shadow-sm">
                        	<img class="mb-4" src="assets/photos/offres/${article.visuel }" alt="" width="300" height="200">
                        	<div class="card-body">
	                          <p class="card-text"><c:out value="${ article.modele }" /></p>
	                          <p class="card-text"><c:out value="${ article.description }" /></p>
		                      	<div class="d-flex justify-content-between align-items-center">
		                            <small class="text-muted"><c:out value="${ article.prix }" /> €</small>
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