<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Accueil</title>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css"
	integrity="sha512-xodZBNTC5n17Xt2atTPuE1HxjVMSvLVW9ocqUKLsCC5CXdbqCmblAshOMAS6/keqq/sMZMZ19scR4PsZChSR7A=="
	crossorigin="" />
<!-- Make sure you put this AFTER Leaflet's CSS -->
<script src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js"
	integrity="sha512-XQoYMqMTK8LvdxXYG3nZ448hOEQiglfqkJs1NOQV44cWnUrBc8PkAOcXy20w0vlaXaVUearIOBhiXZ5V3ynxwA=="
	crossorigin=""></script>
<style>
#map {
	height: 500px;
}
</style>

</head>
<body>
	<%@include file="header.jsp"%>
	<p></p>
	<div class="container">
		<div class="card">
			<div class="card-header">Super heros</div>
			<div class="card-body text-center">
				<c:forEach items="${superHeros}" var="superHero">
					<h2>${superHero.nom}</h2>
					<p>${superHero.phone}</p>
				</c:forEach>

			</div>

		</div>

		<div class="card mt-3">
			<div class="card-header">Map</div>
			<div class="card-body text-center">
				<div id="map"></div>
			</div>

		</div>
	</div>
	<script type="text/javascript">
	
	//Recuperer les infos des super heros
	 var heros = [];
	 var latitude  = "<c:out value='${latitude}'/>";
	 var longitude = "<c:out value='${longitude}'/>";
	 <c:forEach items="${superHeros}" var="superHero">
	    heros.push({ nom:"${superHero.nom}" , latitude:"${superHero.latitude}" , longitude:"${superHero.longitude}", phone:"${superHero.phone}" }); 
	 </c:forEach>
	 //Creation de l'object map pour placer les marqueurs afin de visualiser la localisation des super heros
  var map = L.map('map').setView([46.3630104, 2.9846608], 6);
      L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
	    attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
	}).addTo(map);

    //la position de l'utilisateurqui déclare un incendie
	var mkr = L.marker([latitude, longitude]).addTo(map);	  
	  var desc = '<b>Votre position</b>';
	  mkr.bindPopup(desc).openPopup();
	  
	  //Placer le marker à une position suite à un clique
	  //Pas très important
	  map.on('click', placerMarqueur);

	  function placerMarqueur(e) {
		  mkr.setLatLng(e.latlng);
	  };
	  
	  
	  
   //Placer les markers des supers heros pour visualiser leurs localisations 
  for(var i = 0; i < heros.length; i++){
	var marker = L.marker([heros[i].latitude, heros[i].longitude]).addTo(map);
	  
	  var description = '<b> Nom: '+ heros[i].nom +' Phone: '+ heros[i].phone+'</b>';
	  
	  marker.bindPopup(description).openPopup();
	 
  }

  
  </script>
</body>

</html>