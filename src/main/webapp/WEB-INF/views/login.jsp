<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/sideMenu.css" type="text/css">
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<title>My Agenda</title>
</head>
<body>
	<div id="wrapper">
		<jsp:directive.include file="/WEB-INF/sidebar.jsp" />

		<div id="page-content-wrapper">
			<div>
				<form class="col-lg-6 formulaire" action="MyAgenda/connexion" method="post">
					<legend>Connexion</legend>
					${msgErreur}
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