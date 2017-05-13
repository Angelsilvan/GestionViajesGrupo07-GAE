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
<title>iTravel - Formulario</title>
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
								<input class="navButton" type="submit" value="Inicio" />
							</form>
						</div>
						<div class="navButtonWidth">
							<form action="/vistaFormulario" method="post">
								<input class="navButton active" type="submit" value="Solicitud Viaje" />
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
       		<div class="container">
				<h1>Usuario no registrado</h1>
				<h3>No hay ningún empleado con este correo electrónico</h3>
			</div>
		</c:if>
		<c:if test="${empleado !=null}">
			<div class="formularioView">
				<div class="container">
					<h1 class="cabeceraTablaViajes">
						Introduzca los datos en el formulario para solicitar un viaje:
					</h1>
					<form action="/nuevoViaje" method="post" acceptcharset="utf-8">
						<fieldset id="formViaje">
							<p>
								Nombre: <c:out value="${empleado.apellido1}"/> <c:out value="${empleado.apellido2}"/> (<c:out value="${empleado.nombre}"/>)
							</p>
							<p>
								Numero de empleado: <c:out value="${empleado.numeroEmpleado}"/>
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
						</fieldset>
					</form>
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