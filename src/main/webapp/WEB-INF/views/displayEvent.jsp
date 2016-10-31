<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <jsp:directive.include file="/WEB-INF/imports.jsp"/>

    <title>My Agenda</title>
</head>
<body id="background">
	<div id="wrapper">
		<!-- Sidebar -->
		<jsp:directive.include file="/WEB-INF/sidebar.jsp" />
		<!-- /#sidebar-wrapper -->
		<div id="page-content-wrapper">
			<h2>Mes événements</h2>
			<c:forEach var="evt" items="${evenementsPerso}">
				<div class="evenement" onClick="show('createdEvt_${evt.id}')">
					<h4>${evt.title}</h4>
					<p>
						<b>Début :</b> ${evt.startingDate}
					</p>
					<p>
						<b>Fin :</b> ${evt.endDate}
					</p>
					<p>
						<b>Nombre de participants : ${evt.participants.size()}</b>
					</p>
				</div>
					<div class="modale invisible" id="createdEvt_${evt.id}">
						<jsp:directive.include file="/WEB-INF/modale.jsp"/>
					</div>
			</c:forEach>
			<div class="evenement creerEvenement"
				onclick="redirect('creationEvenement.jspa')">
				<h4>Creer un événement</h4>
				<span class="glyphicon glyphicon-plus perso-glyphicon"></span>
			</div>
			<h2>Les événements auxquels je participe</h2>
			<c:forEach var="evt" items="${evenementsParticipate}">
				<div class="evenement" onClick="show('createdEvt_${evt.id}')">
					<h4>${evt.title}</h4>
					<p>
						<b>Début :</b> ${evt.startingDate}
					</p>
					<p>
						<b>Fin :</b> ${evt.endDate}
					</p>
					<p>
						<b>Nombre de participants : ${evt.participants.size()}</b>
					</p>
				</div>
					<div class="modale invisible" id="createdEvt_${evt.id}">
						<jsp:directive.include file="/WEB-INF/modale.jsp"/>
					</div>
			</c:forEach>
			<h2>Les événements à venir</h2>
			<c:forEach var="evt" items="${evenementsAll}">
				<c:if test="${!evt.hidden }">
					<div class="evenement" onClick="show('createdEvt_${evt.id}')">
						<h4>${evt.title}</h4>
						<p>
							<b>Début :</b> ${evt.startingDate}
						</p>
						<p>
							<b>Fin :</b> ${evt.endDate}
						</p>
						<p>
							<b>Nombre de participants : ${evt.participants.size()}</b>
						</p>
					</div>
						<div class="modale invisible" id="createdEvt_${evt.id}">
							<jsp:directive.include file="/WEB-INF/modale.jsp"/>
						</div>
				</c:if>
			</c:forEach>
			<c:if test= "${not empty evenement }">
				<div class="modale" id="eventDisplayed">
					<jsp:directive.include file="/WEB-INF/modaleURL.jsp"/>
				</div>
				<div id="calque"></div>
			</c:if>
			<c:if test= "${empty evenement }">
				<div id="calque" class="invisible"></div>
			</c:if>
		</div>
	</div>
</body>
</html>