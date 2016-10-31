<form method="post">
	<h3>${evt.title}</h3>
	<c:set var="datebeg">
		<fmt:formatDate pattern="dd/MM/yy hh:mm" value="${evt.startingDate}" />
	</c:set>
	<c:set var="dateend">
		<fmt:formatDate pattern="dd/MM/yy hh:mm" value="${evt.endDate}" />
	</c:set>

	<p>
		<b>D�but :</b> ${datebeg}
	</p>
	<p>
		<b>Fin :</b> ${dateend}
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
				class="btn btn-success">S'inscrire � l'�v�nement</button>
		</c:if>

		<c:if test='${isRegister[keyIdEvent]}'>
			</br>
			<button name="button" value="unregisterAction" type="submit"
				class="btn btn-warning">Se d�sinscrire de l'�v�nement</button>
		</c:if>
	</div>

	<div>
		<c:if test='${isCreator[keyIdEvent]}'>
			</br>
			<button name="button" value="deleteAction" type="submit"
				class="btn btn-danger">Supprimer l'�v�nement</button>
		</c:if>
	</div>
	<br />
	<div>
		<c:if test='${isCreator[keyIdEvent]}'>
			<button name="button" value="updateAction" type="submit"
				class="btn btn-primary">Modifier</button>
		</c:if>
		<a href="javascript:hide('createdEvt_${evt.id}')">Retour</a>
	</div>
</form>