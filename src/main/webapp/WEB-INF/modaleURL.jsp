<form method="post">
<h3>${evenement.title}</h3>
<p>
	<b>Début :</b> ${evenement.startingDate}
</p>
<p>
	<b>Fin :</b> ${evenement.endDate}
</p>
<p>
	<b>Adresse :</b> ${evenement.location}
</p>
<p>${evenement.description}</p>
<p>
	<b>Participants :</b>
</p>
<div class="scrollable">
	<c:forEach var="participant" items="${evenement.participants}">
		<p>${participant},</p>
	</c:forEach>
</div>
<input type="hidden" name="eventId" value="${evenement.id }">
<c:set var="keyIdEvent" value="${evenement.id}" />
<div>
	<c:if test='${!isRegister[keyIdEvent]}'>
		<br />
		<button name="button" value="registerAction" type="submit"
			class="btn btn-success">S'inscrire à l'événement</button>
	</c:if>
	<c:if test='${isRegister[keyIdEvent]}'>
		<br />
		<button name="button" value="unregisterAction" type="submit"
			class="btn btn-warning">Se désinscrire de l'événement</button>
	</c:if>
</div>
<div>
	<c:if test='${isCreator[keyIdEvent]}'>
		<br />
		<button name="button" value="deleteAction" type="submit"
			class="btn btn-danger">Supprimer l'événement</button>
	</c:if>
</div>
<br /> <a href="javascript:hide('eventDisplayed')"
	class="btn btn-primary">Retour</a>
</form>