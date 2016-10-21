<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:directive.include file="/WEB-INF/imports.jsp" />
<title>My Agenda</title>
</head>
<body>
	<div id="wrapper">
		<jsp:directive.include file="/WEB-INF/sidebar.jsp" />

		<div id="page-content-wrapper">
			<div>
				<form class="col-lg-6 formulaire">
					<legend>Inscription</legend>
					${msgErreur}
					<%-- 		${pageContext.request.contextPath} --%>
					<div class="form-group row">
						<label for="nom" class="col-xs-3 col-form-label">Nom* </label>
						<div class="col-xs-9">
							<input class="form-control" type="text"
								placeholder="Votre nom..." id="nom">
						</div>
					</div>
					<div class="form-group row">
						<label for="prenom" class="col-xs-3 col-form-label">Prénom*
							</label>
						<div class="col-xs-9">
							<input class="form-control" type="text" id="prenom"
								placeholder="Votre prénom...">
						</div>
					</div>
					<div class="form-group row">
						<label for="societe" class="col-xs-3 col-form-label">Société
							</label>
						<div class="col-xs-9">
							<input class="form-control" type="text" id="societe"
								placeholder="Nom de société...">
						</div>
					</div>
					<div class="form-group row">
						<label for="mail" class="col-xs-3 col-form-label">Adresse
							mail* </label>
						<div class="col-xs-9">
							<input class="form-control" type="text" id="mail"
								placeholder="Votre adresse mail...">
						</div>
					</div>
					<div class="form-group row">
						<label for="mdp" class="col-xs-3 col-form-label">Mot de
							passe* </label>
						<div class="col-xs-9">
							<input class="form-control" type="password" id="mdp">
						</div>
					</div>
					<div class="form-group row">
						<label for="verifmdp" class="col-xs-3 col-form-label">Confirmer
							mot de passe* </label>
						<div class="col-xs-9">
							<input class="form-control" type="password" id="verifmdp">
						</div>
					</div>
					<div class="form-group row">
						<div class="col-xs-9 col-xs-offset-3">
							<button type="button" class="btn btn-primary btn-lg">S'inscrire</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>