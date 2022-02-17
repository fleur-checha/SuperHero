<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=ISO-8859-1">
<title>Accueil</title>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<%@include file="header.jsp"%>
	<p></p>
	<div class="container">
		<div class="card">
			<div class="card-header">Inscription super hero</div>
			<div class="card-body text-center">
			 <h3>Etes-vous un super hero ?</h3>
			<a href="saisieSuperHero.do" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Inscrivez-vous</a>
			</div>

		</div>
	</div>
	
	<div class="container">
		<div class="card">
			<div class="card-header">Déclaration d'incendie</div>
			<div class="card-body text-center">
			<h3>Avez vous une déclaration ?</h3>
	        <a href="saisieDeclaration.do" class="btn  btn-info btn-lg active" role="button" aria-pressed="true"> Soumettre</a>
			</div>

		</div>
	</div>
</body>

</html>