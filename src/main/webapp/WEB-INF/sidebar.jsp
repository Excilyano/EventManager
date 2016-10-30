<jsp:directive.include file="/WEB-INF/imports.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Sidebar -->
<div id="sidebar-wrapper">
	<ul class="sidebar-nav">
		<li class="sidebar-brand"><a href="#"> My Agenda </a></li>
		<li><a href="consultation.jspa">Consulter les �v�nements</a></li>
		<li><a href="#">Mes �v�nements</a></li>
		<li><a href="#">Autre chose ?</a></li>
	</ul>
    <c:if test="${sessionUser != null}">
        <p class="logout">
            <a href="logout.jspa" class="deconnexion"><span
                    class="glyphicon glyphicon-off"></span> D�connexion</a>
        </p>
    </c:if>


	<p class="decouvrir">
		<a href="#" class="btn btn-primary"><span
			class="glyphicon glyphicon-expand"></span> D�couvrir My Agenda</a>
	</p>
</div>
<!-- /#sidebar-wrapper -->