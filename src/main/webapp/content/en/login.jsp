<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/sideMenu.css">
<script src="js/bootstrap.min.js"></script>
<title>My Agenda</title>
</head>
<body>
	<div id="wrapper">

		<!-- Sidebar -->
		<div id="sidebar-wrapper">
			<ul class="sidebar-nav">
				<li class="sidebar-brand"><a href="#"> My Agenda </a></li>
				<li><a href="#">Événements à venir</a></li>
				<li><a href="#">Mes événements</a></li>
				<li><a href="#">Autre chose ?</a></li>
			</ul>
			<p class="logout">
				<a href="#" class="deconnexion"><span
					class="glyphicon glyphicon-off"></span> Déconnexion</a>
			</p>
			<p class="decouvrir">
				<a href="#" class="btn btn-primary"><span
					class="glyphicon glyphicon-expand"></span> Découvrir My Agenda</a>
			</p>
		</div>
		<!-- /#sidebar-wrapper -->

		<div id="page-content-wrapper">
			<div>
				<form class="col-lg-6 formulaire">
					<legend>Connexion</legend>
					${msgErreur}
					<%-- 		${pageContext.request.contextPath} --%>
					<label for="mail">Adresse mail : </label> <input id="mail"
						type="text" class="form-control"><br /> <label for="mdp">Mot
						de passe : </label> <input id="mdp" type="password" class="form-control">
					<br /> <a>Mot de passe oublié ?</a><br /> <a>Créer mon compte</a> <span
						class="right-aligned-button">
						<button class="btn btn-primary">Se connecter</button>
					</span>
				</form>
			</div>
		</div>
	</div>
</body>
</html>