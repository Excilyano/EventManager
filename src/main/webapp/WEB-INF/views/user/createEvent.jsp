<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
				<form class="col-lg-10 formulaire" method="POST">
					<c:if test="${isUpdate}">
						<legend>Gestion d'un événement</legend>
					</c:if>
					<c:if test="${!isUpdate}">
						<legend>Création d'un événement</legend>
					</c:if>

					<c:if test="${not empty msgError}">
						<div class="alert alert-danger">${msgError}</div>
					</c:if>
					<%-- 		${pageContext.request.contextPath} --%>

					<div class="form-group row"></div>
					<div class="form-group row">
						<label for="title" class="col-xs-3 col-form-label">Titre*
						</label>
						<div class="col-xs-9">
							<input class="form-control" required type="text"
								placeholder="Titre de l'événement..." id="title" name="title"
								value="${event.title }">
						</div>
					</div>
					<div class="form-group row">
						<label for="description" class="col-xs-3 col-form-label">Description*
						</label>
						<div class="col-xs-9">
							<textarea class="form-control" rows="4" type="text"
								id="description" name="description"
								placeholder="Description de l'événement">${event.description }</textarea>
						</div>
					</div>
					<div class="form-group row">
						<label for="location" class="col-xs-3 col-form-label">Lieu
						</label>
						<div class="col-xs-9">
							<input required class="form-control" type="text" id="location"
								name="location" placeholder="5 rue de la loire.."
								value="${event.location }">
						</div>
					</div>
					<div class="form-group row">
						<label for="datetimepickerfirst" class="col-xs-3 col-form-label">Date
							et heure début *</label>
						<div class="col-xs-4">
							<input required type='text' class="form-control"
								id='datetimepickerfirst' name="datetimepickerfirst"
								value="${dateBegin}" />
						</div>
					</div>

					<div class="form-group row">
						<label for="datetimepickerend" class="col-xs-3 col-form-label">Date
							et heure fin *</label>
						<div class="col-xs-4">
							<input required type='text' class="form-control"
								id='datetimepickerend' name="datetimepickerend"
								value="${dateEnd}" />
						</div>
					</div>
					<div class="form-group row">
						<label for="visibility" class="col-xs-3 col-form-label">Rendre
							visible l'événement </label>
						<div class="col-xs-5">
							<input id="visibility" name="visibility"
								<c:if test="${!event.hidden}"> checked </c:if> type="checkbox"
								data-toggle="toggle" data-on="Visible" data-off="Invisible"
								data-onstyle="success" data-offstyle="danger">
						</div>
					</div>
					<c:if test="${isUpdate}">
						<div class="col-xs-9 col-xs-offset-3">
							<button type="submit" class="btn btn-primary btn-lg">Modifier
								l'evenement</button>
						</div>
					</c:if>
					<c:if test="${!isUpdate}">
						<div class="col-xs-9 col-xs-offset-3">
							<button type="submit" class="btn btn-primary btn-lg">Créer
								l'événement</button>
						</div>
					</c:if>
				</form>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(function() {
			$('#datetimepickerfirst').datetimepicker({
				locale : 'fr',
				minDate : moment(),
				format : "DD/MM/YYYY HH:mm",
				sideBySide : true
			});
			$('#datetimepickerend').datetimepicker({
				locale : 'fr',
				minDate : moment(),
				format : "DD/MM/YYYY HH:mm",
				useCurrent : false,
				sideBySide : true
			//Important! See issue #1075
			});
			$("#datetimepickerfirst").on("dp.change", function(e) {
				$('#datetimepickerend').data("DateTimePicker").minDate(e.date);
			});
			$("#datetimepickerend").on(
					"dp.change",
					function(e) {
						$('#datetimepickerfirst').data("DateTimePicker")
								.maxDate(e.date);
					});
			$("#datetimepickerfirst").on('dp.show', function() {
				if ($(this).val()) {
					$('td.hour.disabled').removeClass('disabled');
					$('td.minute.disabled').removeClass('disabled');
				}
			});

			$("#datetimepickerfirst").on('dp.change', function() {
				$('td.hour.disabled').removeClass('disabled');
				$('td.minute.disabled').removeClass('disabled');
			});
			$("#datetimepickerend").on('dp.show', function() {
				if ($(this).val()) {
					$('td.hour.disabled').removeClass('disabled');
					$('td.minute.disabled').removeClass('disabled');
				}
			});

			$("#datetimepickerend").on('dp.change', function() {
				$('td.hour.disabled').removeClass('disabled');
				$('td.minute.disabled').removeClass('disabled');
			});
		});
	</script>
</body>
</html>