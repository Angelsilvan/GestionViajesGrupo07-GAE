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
<title>iTravel - Mis Justificantes</title>
<link rel="stylesheet" type="text/css" href="css/main.css" />
</head>
<body>
	<c:if test="${not empty user}">
		<div class="navegador">
			<div class="container">
				<div class="nav nav-pills">
					<c:if test="${empleado == null}">
						<div class="navButtonWidth">
							<a class="navButton" href="<c:url value="${url}"/>"><c:out
									value="${urlLinktext}" /></a>
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
								<input id="numeroViaje" name="numeroViaje" type="hidden"
									value="${numeroViaje}" /> <input class="navButton active"
									type="submit" value="Mis Justificantes" />
							</form>
						</div>
						<c:if test="${estadoViaje == 2 or estadoViaje == 22}">
							<div class="navButtonWidth">
								<form action="/irANuevoJustificante" method="post">
									<input class="navButton" type="submit"
										value="Nuevo Justificante" />
								</form>
							</div>
						</c:if>
						<div class="navButtonWidth">
							<a class="navButton" href="<c:url value="${url}"/>"><c:out
									value="${urlLinktext}" /></a>
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
					<h1 class="cabeceraTablaViajes">
						Detalles de los justificantes del viaje
						<c:out value="${numeroViaje}" />
						:
					</h1>
					<c:if
						test="${empty justificantesMiViajeSinEnviar and empty justificantesMiViajeEnviados and empty justificantesMiViajeAprobados and empty justificantesMiViajePagados}">
						<h3 class="cabeceraTablaViajes">No hay justificantes
							asociados a este viaje</h3>
					</c:if>
					<c:if test="${not empty justificantesMiViajeSinEnviar}">
						<table class="tablaViajes">
							<tr>
								<th><p>Numero de justificante</p></th>
								<th><p>Concepto</p></th>
								<th><p>Importe</p></th>
								<th><p>Imagen</p></th>
								<th><p>Acción</p></th>
							</tr>
							<c:forEach items="${justificantesMiViajeSinEnviar}"
								var="justificantei">
								<tr>
									<td><c:out value="${justificantei.numeroJustificante}" /></td>
									<td><c:out value="${justificantei.concepto}" /></td>
									<td><c:out value="${justificantei.importe}" /> €</td>
									<td>
										<form action="/abrirImagen" method="get">
											<input name="numeroViaje" type="hidden" value="${numeroViaje}" />
											<input name="numeroJustificante" type="hidden" value="${justificantei.numeroJustificante}" />
											<input class="submitToUrl" type="submit" value="Imagen" />
										</form>
									</td>
									<td>
										<form action="/borrarJustificante" method="post">
											<input name="numeroJustificante" type="hidden"
												value="${justificantei.numeroJustificante}" /> <input
												name="numeroViaje" type="hidden" value="${numeroViaje}" />
											<input class="navButton active" type="submit" value="Borrar" />
										</form>
									</td>
								</tr>
							</c:forEach>
						</table>
						<div class="navButtonWidth">
							<c:if test="${importeTotal >= 450 and estadoViaje == 2}">
								<form action="/solicitarReembolsoPrevio" method="post">
									<input id="numeroViaje" name="numeroViaje" type="hidden"
										value="${numeroViaje}" /> <input class="aceptarViaje"
										type="submit" value="Solicitar reembolso previo" />
								</form>
							</c:if>
						</div>
						<div class="navButtonWidth">
							<c:if test="${importeTotal < 450 and estadoViaje == 2}">
								<form action="" method="post">
									<input id="numeroViaje" name="numeroViaje" type="hidden"
										value="${numeroViaje}" /> <input class="aceptarViajeDeshabilitado"
										type="submit" value="Solicitar reembolso previo" />
								</form>
							</c:if>
						</div>
					</c:if>
					<c:if test="${estadoViaje == 2 or estadoViaje == 22}">
						<c:if test="${empty justificantesMiViajeSinEnviar}">
							<div class="navButtonWidth">
								<form action="/finalizarViaje" method="post">
									<input id="numeroViaje" name="numeroViaje" type="hidden"
										value="${numeroViaje}" /> <input class="aceptarViaje"
										type="submit" value="Finalizar Viaje" />
								</form>
							</div>
						</c:if>
						<c:if test="${not empty justificantesMiViajeSinEnviar}">
							<div class="navButtonWidth">
								<form action="/finalizarViaje" method="post">
									<input id="numeroViaje" name="numeroViaje" type="hidden" value="${numeroViaje}" /> 
									<input class="aceptarViaje"
										type="submit" value="Solicitar reembolso total" />
								</form>
							</div>
						</c:if>
					</c:if>
					<c:if test="${not empty justificantesMiViajeEnviados or not empty justificantesMiViajeAprobados or not empty justificantesMiViajePagados}">
						<table class="tablaViajes">
							<tr>
								<th><p>Numero de justificante</p></th>
								<th><p>Concepto</p></th>
								<th><p>Importe</p></th>
								<th><p>Imagen</p></th>
								<th><p>Estado</p></th>
							</tr>
							<c:forEach items="${justificantesMiViajeEnviados}" var="justificantei">
								<tr>
									<td><c:out value="${justificantei.numeroJustificante}" /></td>
									<td><c:out value="${justificantei.concepto}" /></td>
									<td><c:out value="${justificantei.importe}" /> €</td>
									<td>
										<form action="/abrirImagen" method="get">
											<input name="numeroViaje" type="hidden" value="${numeroViaje}" />
											<input name="numeroJustificante" type="hidden" value="${justificantei.numeroJustificante}" />
											<input class="submitToUrl" type="submit" value="Imagen" />
										</form>
									</td>
									<td><p>Pendiente de aprobación</p></td>
								</tr>
							</c:forEach>
							<c:forEach items="${justificantesMiViajeAprobados}" var="justificantei">
								<tr>
									<td><c:out value="${justificantei.numeroJustificante}" /></td>
									<td><c:out value="${justificantei.concepto}" /></td>
									<td><c:out value="${justificantei.importe}" /> €</td>
									<td>
										<form action="/abrirImagen" method="get">
											<input name="numeroViaje" type="hidden" value="${numeroViaje}" />
											<input name="numeroJustificante" type="hidden" value="${justificantei.numeroJustificante}" />
											<input class="submitToUrl" type="submit" value="Imagen" />
										</form>
									</td>
									<td><p>Aprobado</p></td>
								</tr>
							</c:forEach>
							<c:forEach items="${justificantesMiViajePagados}" var="justificantei">
								<tr>
									<td><c:out value="${justificantei.numeroJustificante}" /></td>
									<td><c:out value="${justificantei.concepto}" /></td>
									<td><c:out value="${justificantei.importe}" /> €</td>
									<td>
										<form action="/abrirImagen" method="get">
											<input name="numeroViaje" type="hidden" value="${numeroViaje}" />
											<input name="numeroJustificante" type="hidden" value="${justificantei.numeroJustificante}" />
											<input class="submitToUrl" type="submit" value="Imagen" />
										</form>
									</td>
									<td><p>Pagado</p></td>
								</tr>
							</c:forEach>
						</table>
					</c:if>
				</div>
			</div>
		</c:if>
		<div class="footer">
			<div class="container">
				<p>Grupo 7</p>
				<p class="a-center">
					<a href="https://www.upm.es">Universidad Politécnica De Madrid</a>
				</p>
				<p class="p-right" id="fecha">ISST 2017</p>
			</div>
		</div>
	</c:if>
</body>
</html>