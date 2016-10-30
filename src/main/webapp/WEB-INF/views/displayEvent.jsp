<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:directive.include file="/WEB-INF/imports.jsp" />

<title>My Agenda</title>
</head>
<body id="background">
	<div id="wrapper">
		<div id="calque"></div>
		<!-- Sidebar -->
		<jsp:directive.include file="/WEB-INF/sidebar.jsp" />
		<!-- /#sidebar-wrapper -->
		<div id="page-content-wrapper">
			<h2>Mes �v�nements</h2>
			<c:forEach var="evt" items="${evenementsPerso}">
				<div class="evenement" onClick="show('createdEvt_${evt.id}')">
					<h4>${evt.title}</h4>
					<p>
						<b>D�but :</b> ${evt.startingDate}
					</p>
					<p>
						<b>Fin :</b> ${evt.endDate}
					</p>
					<p>
						<b>Nombre de participants : ${evt.participants.size()}</b>
					</p>
				</div>
				<form method="post">
					<div class="modale" id="createdEvt_${evt.id}">
						<h3>${evt.title}</h3>
						<p>
							<b>D�but :</b> ${evt.startingDate}
						</p>
						<p>
							<b>Fin :</b> ${evt.endDate}
						</p>
						<p>
							<b>Adresse :</b> ${evt.location}
						</p>
						<p>${evt.description}</p>
						<p>
							<b>Participants :</b>
						</p>
						<div class="scrollable">
							<c:forEach var="participant" items="${evt.participants}">
								<p>${participant},</p>
							</c:forEach>
						</div>
						<input type="hidden" name="eventId" value="${evt.id }">
						<c:set var="keyIdEvent" value="${evt.id }" />
						<div>
							<c:if test='${!isRegister[keyIdEvent]}'>
								<br />
								<button name="button" value="registerAction" type="submit"
									class="btn btn-success">S'inscrire � l'�v�nement</button>
							</c:if>
							<c:if test='${isRegister[keyIdEvent]}'>
								<br />
								<button name="button" value="registerAction" type="submit"
									class="btn btn-warning">Se d�sinscrire de l'�v�nement</button>
							</c:if>
						</div>
						<div>
							<c:if test='${isCreator[keyIdEvent]}'>
								<br />
								<button name="button" value="deleteAction" type="submit"
									class="btn btn-danger">Supprimer l'�v�nement</button>
							</c:if>
						</div>
						<br /> <a href="javascript:hide('createdEvt_${evt.id}')"
							class="btn btn-primary">Retour</a>
					</div>
				</form>
			</c:forEach>
			<div class="evenement creerEvenement"
				onclick="redirect('creationEvenement.jspa')">
				<h4>Creer un �v�nement</h4>
				<span class="glyphicon glyphicon-plus perso-glyphicon"></span>
			</div>
			<h2>Les �v�nements auxquels je participe</h2>
			<c:forEach var="evt" items="${evenementsParticipate}">
				<div class="evenement" onClick="show('createdEvt_${evt.id}')">
					<h4>${evt.title}</h4>
					<p>
						<b>D�but :</b> ${evt.startingDate}
					</p>
					<p>
						<b>Fin :</b> ${evt.endDate}
					</p>
					<p>
						<b>Nombre de participants : ${evt.participants.size()}</b>
					</p>
				</div>
				<form method="post">
					<div class="modale" id="createdEvt_${evt.id}">
						<h3>${evt.title}</h3>
						<p>
							<b>D�but :</b> ${evt.startingDate}
						</p>
						<p>
							<b>Fin :</b> ${evt.endDate}
						</p>
						<p>
							<b>Adresse :</b> ${evt.location}
						</p>
						<p>${evt.description}</p>
						<p>
							<b>Participants :</b>
						</p>
						<div class="scrollable">
							<c:forEach var="participant" items="${evt.participants}">
								<p>${participant},</p>
							</c:forEach>
						</div>
						<br />
						<c:set var="keyIdEvent" value="${evt.id}" />
						<input type="hidden" name="eventId" value="${evt.id }">
						<div>
							<c:if test='${!isRegister[keyIdEvent]}'>
								<br />
								<button name="button" value="registerAction" type="submit"
									class="btn btn-success">S'inscrire � l'�v�nement</button>
							</c:if>
							<c:if test='${isRegister[keyIdEvent]}'>
								<br />
								<button name="button" value="registerAction" type="submit"
									class="btn btn-warning">Se d�sinscrire de l'�v�nement</button>
							</c:if>
						</div>
						<div>
							<c:if test='${isCreator[keyIdEvent]}'>
								<br />
								<button name="button" value="deleteAction" type="submit"
									class="btn btn-danger">Supprimer l'�v�nement</button>
							</c:if>
						</div>
						<br /> <a href="javascript:hide('createdEvt_${evt.id}')"
							class="btn btn-primary">Retour</a>
					</div>
				</form>
			</c:forEach>
			<h2>Les �v�nements � venir</h2>
			<c:forEach var="evt" items="${evenementsAll}">
				<div class="evenement" onClick="show('createdEvt_${evt.id}')">
					<h4>${evt.title}</h4>
					<p>
						<b>D�but :</b> ${evt.startingDate}
					</p>
					<p>
						<b>Fin :</b> ${evt.endDate}
					</p>
					<p>
						<b>Nombre de participants : ${evt.participants.size()}</b>
					</p>
				</div>
				<form method="post">
					<div class="modale" id="createdEvt_${evt.id}">
						<h3>${evt.title}</h3>
						<p>
							<b>D�but :</b> ${evt.startingDate}
						</p>
						<p>
							<b>Fin :</b> ${evt.endDate}
						</p>
						<p>
							<b>Adresse :</b> ${evt.location}
						</p>
						<p>${evt.description}</p>
						<p>
							<b>Participants :</b>
						</p>
						<div class="scrollable">
							<c:forEach var="participant" items="${evt.participants}">
								<p>${participant},</p>
							</c:forEach>
						</div>
						<input type="hidden" name="eventId" value="${evt.id }">
						<c:set var="keyIdEvent" value="${evt.id}" />
						<div>
							<c:if test='${!isRegister[keyIdEvent]}'>
								<br />
								<button name="button" value="registerAction" type="submit"
									class="btn btn-success">S'inscrire � l'�v�nement</button>
							</c:if>
							<c:if test='${isRegister[keyIdEvent]}'>
								<br />
								<button name="button" value="registerAction" type="submit"
									class="btn btn-warning">Se d�sinscrire de l'�v�nement</button>
							</c:if>
						</div>
						<div>
							<c:if test='${isCreator[keyIdEvent]}'>
								<br />
								<button name="button" value="deleteAction" type="submit"
									class="btn btn-danger">Supprimer l'�v�nement</button>
							</c:if>
						</div>
						<br /> <a href="javascript:hide('createdEvt_${evt.id}')"
							class="btn btn-primary">Retour</a>
					</div>
				</form>
			</c:forEach>
		</div>
	</div>
</body>
</html>