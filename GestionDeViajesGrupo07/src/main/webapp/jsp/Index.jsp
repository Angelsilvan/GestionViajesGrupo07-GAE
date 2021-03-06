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
		<title>iTravel</title>
 		<link href="../css/main.css" rel="stylesheet" type="text/css" media="all">
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
								<form action="/isst_viajes" method="get">
									<input class="navButton active" type="submit" value="Inicio" />
								</form>
							</div>
							<div class="navButtonWidth">
								<form action="/vistaFormulario" method="post">
									<input class="navButton" type="submit" value="Solicitud Viaje" />
								</form>
							</div>
							<div class="navButtonWidth">
								<form action="/vistaMisViajes" method="post">
									<input class="navButton" type="submit" value="Mis Viajes" />
								</form>
							</div>
							<c:if test="${not empty viajesSupervisor}">	
								<div class="navButtonWidth">
									<form action="/vistaResponsable" method="post">
										<input class="navButton" type="submit" value="Viajes de mis empleados" />
									</form>
								</div>
							</c:if>
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
				<div class="inicioEmpleado">
	     			<div class="clearfix">
						<h1>Bienvenido <c:out value="${empleado.apellido1}"/> <c:out value="${empleado.apellido2}"/></h1>
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