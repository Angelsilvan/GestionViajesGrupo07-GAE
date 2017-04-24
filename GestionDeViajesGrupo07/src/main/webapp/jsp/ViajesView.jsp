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
			<c:if test="${empty viajesEmpleado and empty viajesSupervisor}">
				<h3>No hay viajes asociado a este perfil</h3>
			</c:if>
			<c:if test="${not empty viajesEmpleado}">
				<p>
					<u>Detalles de sus viajes:</u>
				</p>
				<table>
					<tr>
						<th><p>Numero de viaje</p></th>
						<th><p>Fechas</p></th>
						<th><p>Destino</p></th>
						<th><p>Supervisor</p></th>
						<th><p>Proyecto</p></th>
						<th><p>Motivo</p></th>
						<th><p>Justificantes</p></th>
						<th><p>Estado</p></th>
						<th><p>Subir un justificante</p></th>
					</tr>
					<c:forEach items="${viajesEmpleado}" var="viajei">
						<tr>
							<td><c:out value="${viajei.numeroViaje}" /></td>
							<td><c:out
									value="Del ${viajei.fechaInicio} al ${viajei.fechaFin}" /></td>
							<td><c:out
									value="${viajei.destinoCiudad}, ${viajei.destinoProvincia}, ${viajei.destinoPais}" /></td>
							<td><c:out value="${viajei.proyecto.supervisor.nombre}" /></td>
							<td><c:out value="${viajei.proyecto.codigoProyecto}" /></td>
							<td><c:out value="${viajei.motivo}" /></td>
							<td><c:out value="${viajei.todosLosJustificantesString}"/></td>
							<td><c:out value="${viajei.estado}" /></td>
								<c:if test="${viajei.estado == 2}">
									<td>
										<form action="<%=blobstoreService.createUploadUrl("/upload")%>"
											method="post" enctype="multipart/form-data">
											<input id="numeroViaje" name="numeroViaje" type="hidden" value="${viajei.numeroViaje}" /> 
											<input type="file" name="file" /> 
											<input type="submit" value="Upload document" />
										</form>
									</td>
									<td>
										<form action="/finalizarViaje" method="post">
											<input id="numeroViaje" name="numeroViaje" type="hidden" value="${viajei.numeroViaje}" />
											<input type="submit" value="Finalizar Viaje" />
										</form>
									</td>
								</c:if> 
								<c:if test="${viajei.estado == 1}">
									<td>
										<p>Aun no puede subir justificantes</p>
										<p>Deben aprobarte el viaje primero</p>
									</td>
								</c:if>
								<c:if test="${viajei.estado == 3}">
									<td>
										<p>Ya no puede subir más justificantes</p>
										<p>Los justificantes están pendientes de aprobación</p>
									</td>
								</c:if>
								<c:if test="${viajei.estado == 4}">
									<td>
										<p>Ya no puede subir más justificantes</p>
										<p>Los justificantes están pendientes de reembolso</p>
									</td>
								</c:if>
								<%-- <c:if
									test="${viajei.estado == 3}">
									<form action="/aceptarDefensaTFG" method="post">
										<input id="autor" name="autor" type="hidden"
											value="${viajei.autor}" /> <input type="submit"
											value="Aceptar Defensa TFG" />
									</form>
								</c:if> --%>
						</tr>
					</c:forEach>
				</table>
			</c:if>
			<c:if test="${not empty viajesSupervisor}">
				<p>
					<u>Detalles de los viajes de sus empleados:</u>
				</p>
				<table>
					<tr>
						<th><p>Numero de viaje</p></th>
						<th><p>Fechas</p></th>
						<th><p>Destino</p></th>
						<th><p>Empleado</p></th>
						<th><p>Proyecto</p></th>
						<th><p>Motivos</p></th>
						<th><p>Justificantes</p></th>
						<th><p>Estado</p></th>
						<th><p>Aceptar Viajes</p></th>
						<th><p>Aprobar Justificantes</p></th>
					</tr>
					<c:forEach items="${viajesSupervisor}" var="viajei">
						<tr>
							<td><c:out value="${viajei.numeroViaje}" /></td>
							<td><c:out value="Del ${viajei.fechaInicio} al ${viajei.fechaFin}" /></td>
							<td><c:out value="${viajei.destinoCiudad}, ${viajei.destinoProvincia}, ${viajei.destinoPais}" /></td>
							<td><c:out value="${viajei.empleado.nombre}" /></td>
							<td><c:out value="${viajei.proyecto.codigoProyecto}"/></td>
							<td><c:out value="${viajei.motivo}" /></td>
							<td><c:out value="${viajei.todosLosJustificantesString}"/></td>
							<td><c:out value="${viajei.estado}" /></td>
							<c:if test="${viajei.estado == 1}">
								<td>
									<form action="/aceptarViaje" method="post">
										<input id="numeroViaje" name="numeroViaje" type="hidden"
											value="${viajei.numeroViaje}" /> <input type="submit"
											value="Aceptar Viaje" />
									</form>
								</td>
							</c:if>
							<c:if test="${viajei.estado != 1}">
								<td><p>Ya ha aceptado el viaje</p></td>
							</c:if>
							 <c:if test="${viajei.estado == 2}">
									<td>
										<p>Aún no puede aprobar justificantes</p>
										<p>No hay justificantes pendientes de aprobación</p>
									</td>
								</c:if>
								<c:if test="${viajei.estado == 3}">
									<td>
										<form action="/aprobarJustificantes" method="post">
										<input id="numeroViaje" name="numeroViaje" type="hidden"
											value="${viajei.numeroViaje}" /> <input type="submit"
											value="Aprobar Justificantes" />
									</form>
									</td>
								</c:if>
								<c:if test="${viajei.estado == 4}">
									<td>
										<p>Ya ha aceptado los justificantes</p>
										<p>Los justificantes están pendientes de reembolso</p>
									</td>
								</c:if>
							<%-- <c:if
									test="${viajei.estado == 3}">
									<form action="/aceptarDefensaTFG" method="post">
										<input id="autor" name="autor" type="hidden"
											value="${viajei.autor}" /> <input type="submit"
											value="Aceptar Defensa TFG" />
									</form>
								</c:if> --%>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</c:if>
	</c:if>
	<p>
		Puedes acceder a este link para hacer
		<c:out value="${urlLinktext}" />
		: <a href="<c:url value="${url}"/>"><c:out value="${urlLinktext}" /></a>
	</p>
</body>
</html>