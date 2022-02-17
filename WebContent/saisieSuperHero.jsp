<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inscription Super Hero</title>
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
			<div class="card-header text-primary">Inscription d'un Super Hero</div>
			<div class="card-body">
				<p id="geoError">
					<font color="red"> </font>
				</p>
				<form action="saveSuperHero.do" method="post">

					<div class="form-group">
						<label class="control-label">Nom :</label> <input type="text"
							name="nom" class="form-control" placeholder="Votre nom" required />
						<c:if test="${empty param.nom}">
							<span> <font color="red"> Entrer votre Nom </font></span>
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
						<label class="control-label">Phone :</label> <input type="text"
							name="phone" class="form-control" placeholder="Phone" required />
						<c:if test="${empty param.phone}">
							<span> <font color="red"> Entrer phone </font></span>
						</c:if>
					</div>
					<div class="form-group">
						<label class="control-label">Incidents :</label> <select
							class="form-select form-select-lg mt-1" multiple
							aria-label="incidents multiple select" size="5" id="incidents"
							name="incidents" required>
							<c:forEach items="${incidents}" var="incident">
								<option value="${incident}">${incident}</option>
							</c:forEach>
						</select>
						<c:if test="${empty param.incidents}">
							<span> <font color="red"> Selectionner 1 à 3
									incidents </font></span>
						</c:if>
					</div>
					<div class="mt-2">
						<button type="submit" class="btn btn-primary">Enregistrer</button>
					</div>
				</form>

			</div>
		</div>
	</div>
	<script type="text/javascript">
	//Recupere la infos latitude et  longitude de l'utilisateur courant à partir du navigateur
		var x = document.getElementById("geoError");

		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(showPosition, showError);
		} else {
			x.innerHTML = "Geolocation is not supported by this browser.";
		}

		function showPosition(position) {
			document.getElementById("latitude").setAttribute('value',
					position.coords.latitude);
			document.getElementById("longitude").setAttribute('value',
					position.coords.longitude);

		}

		function showError(error) {
			switch (error.code) {
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

		//Multi select constraints

		$(document).ready(function() {

			var last_valid_selection = null;

			$('#incidents').change(function(event) {

				if ($(this).val().length > 3) {
					$(this).val(last_valid_selection);
				} else {
					last_valid_selection = $(this).val();
				}
			});
		});
	</script>
</body>
</html>