<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:directive.include file="/WEB-INF/imports.jsp"/>
    <title>My Agenda</title>
</head>
<body>
<div id="wrapper">
    <jsp:directive.include file="/WEB-INF/sidebar.jsp"/>

    <div id="page-content-wrapper">
        <div>
            <form class="col-lg-6 formulaire" action="inscription.jspa" method="post">
                <legend>Inscription</legend>
                ${msgErreur}
                <%-- 		${pageContext.request.contextPath} --%>
                <div class="form-group row">
                    <label for="name" class="col-xs-3 col-form-label">Nom* </label>
                    <div class="col-xs-9">
                        <input class="form-control" type="text" name="lastname"
                               placeholder="Votre nom..." id="name" value="${param.get("lastname")}">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="firstname" class="col-xs-3 col-form-label">Prénom*
                    </label>
                    <div class="col-xs-9">
                        <input class="form-control" type="text" id="firstname" name="firstname"
                               placeholder="Votre prénom..." value="${param.get("firstname")}">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="company" class="col-xs-3 col-form-label">Société
                    </label>
                    <div class="col-xs-9">
                        <input class="form-control" type="text" id="company" name="company"
                               placeholder="Nom de société..." value="${param.get("company")}">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="mail" class="col-xs-3 col-form-label">Adresse
                        mail* </label>
                    <div class="col-xs-9">
                        <input class="form-control" type="email" id="mail" name="mail"
                               placeholder="Votre adresse mail..." value="${param.get("mail")}">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="password" class="col-xs-3 col-form-label">Mot de
                        passe* </label>
                    <div class="col-xs-9">
                        <input class="form-control" type="password" id="password" name="password">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="checkpwd" class="col-xs-3 col-form-label">Confirmer
                        mot de passe* </label>
                    <div class="col-xs-9">
                        <input class="form-control" type="password" id="checkpwd" name="checkpwd">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-xs-9 col-xs-offset-3">
                        <button type="submit" class="btn btn-primary btn-lg">S'inscrire</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>