<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page
	import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService"%>
<%
	BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>iTravel - Nuevo Justificante</title>
<link rel="stylesheet" type="text/css" href="css/main.css" />
</head>
<body>
	<c:if test="${not empty user}">
		<div class="navegador">
      		<div class="container">
        		<div class="nav nav-pills">
					<c:if test="${empleado == null}">
						<div class="navButtonWidth">
							<a class="navButton" href="<c:url value="${url}"/>"><c:out value="${urlLinktext}" /></a>
						</div>
					</c:if>
					<c:if test="${empleado != null}">
						<div class="navButtonWidth">
							<form action="/vistaMisViajes" method="post">
								<input class="navButton" type="submit" value="Atrás" />
							</form>
						</div>
						<div class="navButtonWidth">
							<form action="/irAJustificantesMisViajes" method="get">
								<input id="numeroViaje" name="numeroViaje" type="hidden" value="${numeroViaje}" />
								<input class="navButton" type="submit" value="Mis Justificantes" />
							</form>
						</div>
						<div class="navButtonWidth">
							<form action="/irANuevoJustificante" method="post">
								<input class="navButton active" type="submit" value="Nuevo Justificante" />
							</form>
						</div>
						<div class="navButtonWidth">
							<a class="navButton" href="<c:url value="${url}"/>"><c:out value="${urlLinktext}" /></a>
						</div>
					</c:if>
        		</div>
        	</div>
        </div>
        <c:if test="${empleado == null}">
       		<div class="usuarioNoRegistrado">
	        	<div class="clearfix">
					<h1>Usuario no registrado</h1>
					<h3>No hay ningún empleado con este correo electrónico</h3>
				</div>
			</div>
		</c:if>
        <c:if test="${empleado != null}">
			<div class="justificantesMisViajes">
				<div class="container">
					<c:if test="${estadoViaje == 2 or estadoViaje == 22}">
						<h1 class="cabeceraTablaViajes">Subir un nuevo justificante</h1>
						<form action="<%=blobstoreService.createUploadUrl("/upload")%>" method="post" enctype="multipart/form-data">
							<input id="numeroViaje" name="numeroViaje" type="hidden" value="${numeroViaje}" />
							<p>
								Concepto: <input type="text" name="concepto"
									id="concepto" maxLength="255" required size="50"
									placeholder="Concepto del justificante" />
							</p>
							<p>
								Importe: <input type="text" name="importe"
									id="importe" maxLength="255" required size="50"
									placeholder="Importe en euros" />
							</p>
							<p>
								Fichero:  <input type="file" name="file" />
							</p>
							<input class="aceptarViaje" type="submit" value="Subir justificante" />
						</form>		
					</c:if>
				</div>
	 		</div>
		</c:if>
		<div class="footer">
	      <div class="container"> 
	        <p> Grupo 7 </p>
	        <p class="a-center"><a href="https://www.upm.es">Universidad Politécnica De Madrid</a></p>
	        <p class="p-right" id="fecha">ISST 2017</p>
	      </div>
	    </div>
	</c:if>
</body>
</html>