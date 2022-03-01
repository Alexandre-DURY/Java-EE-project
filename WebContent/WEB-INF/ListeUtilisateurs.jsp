<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste-Util</title>
<link href="assets/css/userList.css" rel="stylesheet">
</head>

<%@ include file="menu.jsp" %>
<body>
	<main class="col-md-12 ms-sm-auto col-lg-10 px-md-4">
			    <div class="row">
			      <div class="col-md-12">
			        <div class="user-dashboard-info-box table-responsive mb-0 bg-white p-4 shadow-sm">
			          <table class="table manage-candidates-top mb-0">
			            <thead>
			              <tr>
							  <th class="text-center">pseudo</th>
						      <th class="text-center">nombre articles</th>
						      <th class="text-center">date creation</th>
						      <th class="text-center">description</th>
			              </tr>
			            </thead>
			            <tbody>
			            	<c:forEach var="utilisateur" items="${ utilisateurs }">
						  		<tr class="candidates-list">
			                <td class="title">
			                  <div class="thumb">
			                  	<a href="detail?id=<c:out value="${utilisateur.id }"/>">
			                    	<img src="<c:out value="${utilisateur.photo }"/>" >
			                    </a>
			                  </div>
			                  <div class="candidate-list-details">
			                    <div class="candidate-list-info">
			                      <div class="candidate-list-title">
			                        <h5 class="mb-0"><c:out value="${utilisateur.pseudo }"/></h5>
			                      </div>
			                      <div class="candidate-list-option">
			                        <ul class="list-unstyled">
			                          <li><i class="fas fa-filter pr-1"></i><c:out value="${utilisateur.nom }"/></li>
			                          <li><i class="fas fa-map-marker-alt pr-1"></i><c:out value="${utilisateur.prenom }"/></li>
			                        </ul>
			                      </div>
			                    </div>
			                  </div>
			                </td>
			                <td class="candidate-list-favourite-time text-center">
			                  <a class="candidate-list-favourite order-2 text-danger" href="#"><i class="fas fa-heart"></i></a>
			                  <span class="candidate-list-time order-1"><c:out value="${utilisateur.nb_articles }"/></span>
			                </td>
			                <td class="candidate-list-favourite-time text-center">
			                  <a class="candidate-list-favourite order-2 text-danger" href="#"><i class="fas fa-heart"></i></a>
			                  <span class="candidate-list-time order-1"><c:out value="${utilisateur.date_creation }"/></span>
			                </td>
			                <td class="candidate-list-favourite-time text-center">
			                  <a class="candidate-list-favourite order-2 text-danger" href="#"><i class="fas fa-heart"></i></a>
			                  <span class="candidate-list-time order-1"><c:out value="${utilisateur.description }"/></span>
			                </td>
			              </tr>
						  	</c:forEach>
			              </tbody>
			          </table>
			        </div>
			      </div>
			    </div>
			  
		
  </main>
</body>
</html>
