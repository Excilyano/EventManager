<jsp:directive.include file="/WEB-INF/imports.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Sidebar -->
<div id="sidebar-wrapper">
	<ul class="sidebar-nav">
		<li class="sidebar-brand"><a href="#"> My Agenda </a></li>
		<li><a href="consultation.jspa">Consulter les événements</a></li>
	</ul>
    <c:if test="${sessionUser != null}">
        <p class="logout">
            <a href="logout.jspa" class="deconnexion"><span
                    class="glyphicon glyphicon-log-out"></span> Déconnexion</a>
        </p>
    </c:if>
    <c:if test="${sessionUser == null}">
        <p class="logout">
            <a href="connexion.jspa" class="connexion"><span
                    class="glyphicon glyphicon-log-in"></span> Connexion</a>
        </p>
    </c:if>

	<p class="decouvrir">
		<a href="#" class="btn btn-primary"><span
			class="glyphicon glyphicon-expand"></span> Découvrir My Agenda</a>
	</p>
</div>
<!-- /#sidebar-wrapper -->