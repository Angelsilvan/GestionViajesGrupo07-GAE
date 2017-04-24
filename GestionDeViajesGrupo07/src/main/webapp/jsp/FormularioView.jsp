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
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
</head>
<body>
	<h3>Sistema de gestión de viajes</h3>
	<c:if test="${not empty user}">
		<c:if test="${empleado == null}">
			<p>Usuario no registrado</p>
			<p>
				<u>Introduzca los datos en el formulario para registrarse:</u>
			</p>
			<form action="/nuevoEmpleado" method="post" acceptcharset="utf-8">
				<p>
					Numero de empleado: <input type="text" name="numeroEmpleado"
						id="numeroEmpleado" maxLength="255" size="20"
						placeholder="Numero de empleado" />
				</p>
				<p>
					Nombre: <input type="text" name="nombre" id="nombre"
						maxLength="255" size="20" required placeholder="Nombre"
						value="<c:out value="${user}"/>" readonly />
				</p>
				<p>
					Primer Apellido: <input type="text" name="apellido1" id="apellido1"
						maxLength="255" required size="20" placeholder="Primer apellido" />
				</p>
				<p>
					Segundo Apellido: <input type="text" name="apellido2"
						id="apellido2" maxLength="255" required size="20"
						placeholder="Segundo apellido" />
				</p>
				<input type="submit" value="Send" />
			</form>
		</c:if>
		<c:if test="${empleado != null}">
			<form action="/vistaFormulario" method="post">
				<input type="submit" value="Solicitud Viaje" />
			</form>
			<form action="/vistaMisViajes" method="post">
				<input type="submit" value="Mis Viajes" />
			</form>
			<p>
				<u>Introduzca los datos en el formulario para solicitar un
					viaje:</u>
			</p>
			<form action="/nuevoViaje" method="post" acceptcharset="utf-8">
				<p>
					Nombre: <input type="text" name="nombre" id="nombre"
						maxLength="255" size="20" placeholder="Nombre"
						value="<c:out value="${user}"/>" readonly />
				</p>
				<p>
					Numero de empleado: <input type="text" name="numeroEmpleado"
						id="numeroEmpleado" maxLength="255" size="20" required
						placeholder="Numero de empleado"
						value="<c:out value="${empleado.numeroEmpleado}"/>" readonly />
				</p>
				<p>
					Fecha de inicio: <input type="text" name="fechaInicio"
						id="fechaInicio" maxLength="255" required size="20"
						placeholder="Fecha de inicio del viaje" />
				</p>
				<p>
					Fecha de finalizacion: <input type="text" name="fechaFin"
						id="fechaFin" maxLength="255" required size="20"
						placeholder="Fecha de finalizacion del viaje" />
				</p>
				<p>
					Proyecto: <input type="text" name="proyecto" id="proyecto"
						maxLength="255" required size="20"
						placeholder="Código de proyecto" />
				</p>
				<p>
					Ciudad de destino: <input type="text" name="destinoCiudad"
						id="destinoCiudad" maxLength="255" required size="20"
						placeholder="Ciudad destino" />
				</p>
				<p>
					Provincia de destino: <input type="text" name="destinoProvincia"
						id="destinoProvincia" maxLength="255" required size="20"
						placeholder="Provincia de destino" />
				</p>
				<p>
					Pais de destino: <input type="text" name="destinoPais"
						id="destinoPais" maxLength="255" required size="20"
						placeholder="Pais de destino" />
				</p>
				<p>
					Motivo: <input type="text" name="motivo" id="motivo"
						maxLength="255" required size="20" placeholder="Motivo del viaje" />
				</p>
				<input type="submit" value="Send" />
			</form>
		</c:if>
	</c:if>
	<p>
		Puedes acceder a este link para hacer
		<c:out value="${urlLinktext}" />
		: <a href="<c:url value="${url}"/>"><c:out value="${urlLinktext}" /></a>
	</p>
</body>
</html>