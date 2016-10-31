<form method="post">
	<h3>${evt.title}</h3>
	<p>
		<b>Début :</b> ${evt.startingDate}
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
			</br>
			<button name="button" value="registerAction" type="submit"
				class="btn btn-success">S'inscrire à l'événement</button>
		</c:if>

		<c:if test='${isRegister[keyIdEvent]}'>
			</br>
			<button name="button" value="unregisterAction" type="submit"
				class="btn btn-warning">Se désinscrire de l'événement</button>
		</c:if>
	</div>

	<div>
		<c:if test='${isCreator[keyIdEvent]}'>
			</br>
			<button name="button" value="deleteAction" type="submit"
				class="btn btn-danger">Supprimer l'événement</button>
		</c:if>
	</div>
	<br />
	<div>
		<a href="javascript:hide('createdEvt_${evt.id}')"
			class="btn btn-primary">Retour</a>
		<c:if test='${isCreator[keyIdEvent]}'>
			<button name="button" value="updateAction" type="submit"
				class="btn btn-primary">Modifier</button>
		</c:if>
	</div>
</form>