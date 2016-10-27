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
		<!-- Sidebar -->
		<jsp:directive.include file="/WEB-INF/sidebar.jsp" />
		<!-- /#sidebar-wrapper -->
		<div id="page-content-wrapper">
			<div>
				<form class="col-lg-10 formulaire">
					<legend>Création d'un événement</legend>
					${msgErreur}
					<%-- 		${pageContext.request.contextPath} --%>
					<div class="form-group row">
						<label for="titre" class="col-xs-3 col-form-label">Titre*
						</label>
						<div class="col-xs-9">
							<input class="form-control" type="text"
								placeholder="Titre de l'événement..." id="titre">
						</div>
					</div>
					<div class="form-group row">
						<label for="description" class="col-xs-3 col-form-label">Description*
						</label>
						<div class="col-xs-9">
							<textarea class="form-control" type="text" id="description"
								placeholder="Description de l'événement"></textarea>
						</div>
					</div>
					<div class="form-group row">
						<label for="lieu" class="col-xs-3 col-form-label">Lieu </label>
						<div class="col-xs-9">
							<input class="form-control" type="text" id="lieu"
								placeholder="5 rue de la loire..">
						</div>
					</div>

					<div class="well">
  <div id="datetimepicker1" class="input-append date">
    <input data-format="dd/MM/yyyy hh:mm:ss" type="text"></input>
    <span class="add-on">
      <span class="glyphicon glyphicon-calendar"></span>
    </span>
  </div>
</div>


					<div class="col-xs-9 col-xs-offset-3">
						<button type="button" class="btn btn-primary btn-lg">Créer
							l'événement</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>