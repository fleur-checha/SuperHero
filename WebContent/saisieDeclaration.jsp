<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Déclaration incendie</title>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
</head>
<body>
	<%@include file="header.jsp"%>
	<p></p>
	<div class="container">
		<div class="card">
			<div class="card-header">Déclaration d'incendie</div>
			<div class="card-body">
				<p id="geoError"> <font color="red"> </font></p>
				<form action="saveDeclaration.do" method="post">
					<div class="form-group">
						<label class="control-label">Ville :</label> <input type="text"
							name="ville" class="form-control" placeholder="Votre ville"
							required />
						<c:if test="${empty param.ville}">
							<span> <font color="red"> Entrer ville </font></span>
						</c:if>
					</div>
					<div class="form-group">
						<label class="control-label">Latitude :</label> <input type="text"
							name="latitude" id="latitude" class="form-control"
							placeholder="Latitude" required />
						<c:if test="${empty param.latitude}">
							<span> <font color="red"> Entrer latitude </font></span>
						</c:if>
					</div>
					<div class="form-group">
						<label class="control-label">Longitude :</label> <input
							type="text" name="longitude" id="longitude" class="form-control"
							placeholder="Longitude" required />
						<c:if test="${empty param.longitude}">
							<span> <font color="red"> Entrer longitude </font></span>
						</c:if>
					</div>
					<div class="form-group">
						<label class="control-label">Type d'incendie :</label> <select
							class="form-select form-select-lg mt-1"
							aria-label="incident select" name="typeIncident" required>
							<c:forEach items="${incidents}" var="incident">
								<option value="${incident}">${incident}</option>
							</c:forEach>
						</select>
						<c:if test="${empty param.typeIncident}">
							<span> <font color="red"> Selectionner un type
									d'incendie </font></span>
						</c:if>
					</div>
					<div class="mt-2">
						<button type="submit" class="btn btn-primary">Soumettre</button>
					</div>
				</form>

			</div>
		</div>
	</div>

	<script type="text/javascript">
	
	//Obtenir la localisation de l'utilisateur courant via le navigateur 
// Cette variable sert à afficher les erreurs dans le p avec id 'geoError'
var x = document.getElementById("geoError");
  if (navigator.geolocation) {
	    navigator.geolocation.getCurrentPosition(showPosition, showError);
  } else {
    x.innerHTML = "Geolocation is not supported by this browser.";
  }

//Auto-complete de latitude et de longitude dans les champs correspondants
function showPosition(position) {
	document.getElementById("latitude").setAttribute('value', position.coords.latitude);
	document.getElementById("longitude").setAttribute('value',position.coords.longitude);
}

//Gestion des erreurs 
function showError(error) {
	  switch(error.code) {
	    case error.PERMISSION_DENIED:
	      x.innerHTML = "User denied the request for Geolocation."
	      break;
	    case error.POSITION_UNAVAILABLE:
	      x.innerHTML = "Location information is unavailable."
	      break;
	    case error.TIMEOUT:
	      x.innerHTML = "The request to get user location timed out."
	      break;
	    case error.UNKNOWN_ERROR:
	      x.innerHTML = "An unknown error occurred."
	      break;
	  }
	}




</script>
</body>
</html>