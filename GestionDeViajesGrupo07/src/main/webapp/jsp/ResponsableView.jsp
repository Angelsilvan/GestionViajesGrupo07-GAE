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
<title>iTravel - Responsable</title>
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
									<input class="navButton active" type="submit" value="Viajes de mis empleados" />
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
        <c:if test="${empleado !=null}">
        	<div class="responsableView">
        		<div class="container">
        			<c:if test="${empty viajesSupervisor}">
						<h1 class="cabeceraTablaViajes">No hay viajes asociados a este perfil</h1>
					</c:if>
					<c:if test="${not empty viajesSupervisorEnCurso}">
						<h1 class="cabeceraTablaViajes">Detalles de los viajes de sus empleados en curso:</h1>
						<table class="tablaViajes">
							<tr>
								<th><p>Numero de viaje</p></th>
								<th><p>Fechas</p></th>
								<th><p>Destino</p></th>
								<th><p>Empleado</p></th>
								<th><p>Proyecto</p></th>
								<th><p>Motivo</p></th>
								<th><p>Estado</p></th>
								<th colspan="2"><p>Aprobación Viajes</p></th>
							</tr>
							<c:forEach items="${viajesSupervisorEnCurso}" var="viajei">
								<tr>
									<td><c:out value="${viajei.numeroViaje}" /></td>
									<td><c:out value="Del ${viajei.fechaInicio} al ${viajei.fechaFin}" /></td>
									<td><c:out value="${viajei.destinoCiudad}, ${viajei.destinoProvincia}, ${viajei.destinoPais}" /></td>
									<td><c:out value="${viajei.empleado.apellido1}" /> <c:out value="${viajei.empleado.apellido2}" /> (<c:out value="${viajei.empleado.nombre}" />)</td>
									<td><c:out value="${viajei.proyecto.codigoProyecto}"/></td>
									<td><c:out value="${viajei.motivo}" /></td>
									<td>
										<c:choose>
											<c:when test="${viajei.estado == 1}">Pendiente de aprobación</c:when>
											<c:when test="${viajei.estado == 2}"> Aprobado</c:when>
											<c:when test="${viajei.estado == 22}">
												<form action="/irAJustificantesMisEmpleados" method="post">
													<input id="numeroViaje" name="numeroViaje" type="hidden" value="${viajei.numeroViaje}" />
													<input class="submitToUrl" type="submit" value="Previaje" />
												</form>
											</c:when>
											<c:when test="${viajei.estado == 3}">
												<form action="/irAJustificantesMisEmpleados" method="post">
													<input id="numeroViaje" name="numeroViaje" type="hidden" value="${viajei.numeroViaje}" />
													<input class="submitToUrl" type="submit" value="Pendiente de aceptación de justificante" />
												</form>
											</c:when>
											<c:when test="${viajei.estado == 4}">
												<form action="/irAJustificantesMisEmpleados" method="post">
													<input id="numeroViaje" name="numeroViaje" type="hidden" value="${viajei.numeroViaje}" />
													<input class="submitToUrl" type="submit" value="Pendiente de reembolso" />
												</form>
											</c:when>
										</c:choose>
									</td>
									<c:if test="${viajei.estado == 1}">
										<td>
											<form action="/aceptarViaje" method="post">
												<input name="numeroViaje" type="hidden" value="${viajei.numeroViaje}" /> 
												<input class="aceptarViaje" type="submit" value="Aceptar" />
											</form>
										</td>
										<td>
											<form action="/denegarViaje" method="post">
												<input id="numeroViaje" name="numeroViaje" type="hidden" value="${viajei.numeroViaje}" /> 
												<div class="navButtonWidth">
													<input type="text" name="motivoDenegacion" 
														id="motivoDenegacion" maxLength="255" required size="40"
														placeholder="Indique el motivo de la Denegación" />
												</div>
												<input class ="navButton active" type="submit" value="Denegar" />
											</form>
<!-- 											<form action="/denegarViaje" method="post"> -->
<%-- 												<input name="numeroViaje" type="hidden" value="${viajei.numeroViaje}" /> --%>
<!-- 												<input class ="navButton active" type="submit" value="Denegar" /> -->
<!-- 											</form> -->
										</td>
									</c:if>
									<c:if test="${viajei.estado != 1}">
										<td colspan="2">
											<p>Ya ha aceptado el viaje</p>
										</td>
									</c:if>
								</tr>
							</c:forEach>
						</table>
					</c:if>
					<c:if test="${not empty viajesSupervisorFinalizados}">
						<h1 class="cabeceraTablaViajes">Detalles de los viajes de sus empleados finalizados:</h1>
						<table class="tablaViajes">
							<tr>
								<th><p>Numero de viaje</p></th>
								<th><p>Fechas</p></th>
								<th><p>Destino</p></th>
								<th><p>Supervisor</p></th>
								<th><p>Proyecto</p></th>
								<th><p>Motivo</p></th>
								<th><p>Estado</p></th>
							</tr>
							<c:forEach items="${viajesSupervisorFinalizados}" var="viajei">
								<tr>
									<td><c:out value="${viajei.numeroViaje}" /></td>
									<td><c:out
											value="Del ${viajei.fechaInicio} al ${viajei.fechaFin}" /></td>
									<td><c:out
											value="${viajei.destinoCiudad}, ${viajei.destinoProvincia}, ${viajei.destinoPais}" /></td>
									<td><c:out value="${viajei.proyecto.supervisor.apellido1}" /> <c:out value="${viajei.proyecto.supervisor.apellido2}" /> (<c:out value="${viajei.proyecto.supervisor.nombre}" />)</td>
									<td><c:out value="${viajei.proyecto.codigoProyecto}" /></td>
									<td><c:out value="${viajei.motivo}" /></td>
									<td>
										<form action="/irAJustificantesMisEmpleados" method="get">
											<input id="numeroViaje" name="numeroViaje" type="hidden" value="${viajei.numeroViaje}" />
											<input class="submitToUrl" type="submit" value="Finalizado" />
										</form>
									</td>
								</tr>
							</c:forEach>
						</table>
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