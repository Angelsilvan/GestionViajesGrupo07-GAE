package es.upm.dit.isst.gestiondeviajesgrupo07;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;

import es.upm.dit.isst.gestiondeviajesgrupo07.dao.VIAJESDAO;
import es.upm.dit.isst.gestiondeviajesgrupo07.dao.VIAJESDAOImpl;
import es.upm.dit.isst.gestiondeviajesgrupo07.model.*;

public class ISST_VIAJES_Servlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		ObjectifyService.register(EMPLEADO.class);
		ObjectifyService.register(PROYECTO.class);
		ObjectifyService.register(VIAJE.class);
		ObjectifyService.register(JUSTIFICANTE.class);
	}
	public ISST_VIAJES_Servlet() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		
		UserService userService = UserServiceFactory.getUserService();
		String url = userService.createLoginURL(request.getRequestURI());
		String urlLinktext = "Login";
		String user = "";

		if (request.getUserPrincipal() != null){
			user = request.getUserPrincipal().getName();
			url = userService.createLogoutURL(request.getRequestURI());
			urlLinktext = "Logout";
			
			VIAJESDAO dao = VIAJESDAOImpl.getInstancia();
//			Prueba empleados y proyectos.
			EMPLEADO jaime = dao.createEMPLEADO(1, "jpalosp@gmail.com", "Jaime", "Palos Pereira");
			EMPLEADO granja = dao.createEMPLEADO(2, "a.granja.garcia@gmail.com", "Alejandro", "Granja Garcia");
			EMPLEADO angel = dao.createEMPLEADO(3, "angelsilvan94@gmail.com", "Ángel", "Silvan Camiña");
			EMPLEADO alicia = dao.createEMPLEADO(4, "aliciaortiz95@gmail.com", "Alicia", "Ortiz Serrano");

			ArrayList<EMPLEADO> empleados = new ArrayList<>();
			empleados.add(granja);
			empleados.add(angel);
			empleados.add(jaime);
			empleados.add(alicia);
			PROYECTO proyecto1 = dao.createPROYECTO("AREA51", empleados, granja, "Madrid", "ISST");
			PROYECTO proyecto2 = dao.createPROYECTO("AREA52", empleados, angel, "Madrid", "ISST");
			PROYECTO proyecto3 = dao.createPROYECTO("AREA53", empleados, jaime, "Madrid", "ISST");
			PROYECTO proyecto4 = dao.createPROYECTO("AREA54", empleados, alicia, "Madrid", "ISST");
			
			ArrayList<VIAJE> viajesEmpleado =  new ArrayList<VIAJE>();
			ArrayList<VIAJE> viajesSupervisor = new ArrayList<VIAJE>();
			viajesEmpleado.addAll(dao.readViajesEmpleado(user));
			viajesSupervisor.addAll(dao.readViajesSuperivisor(user));
			EMPLEADO empleado = dao.readEmpleado(user);
			
			/* //Fechas
			String input = "Jun 18 2017";
	        SimpleDateFormat parser = new SimpleDateFormat("MMM dd yyyy");
	        Date fechaInicio = parser.parse(input);
	        String input2 = "Jun 28 2017";
	        SimpleDateFormat parser2 = new SimpleDateFormat("MMM dd yyyy");
	        Date fechaFin = parser2.parse(input2);
	        Date hola = new Date(2017,6, 8);
			 */
			
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("url", url);
			request.getSession().setAttribute("urlLinktext", urlLinktext);
			request.getSession().setAttribute("viajesEmpleado", viajesEmpleado);
			request.getSession().setAttribute("viajesSupervisor", viajesSupervisor);
			request.getSession().setAttribute("empleado", empleado);
			
			RequestDispatcher view = request.getRequestDispatcher("jsp/Index.jsp");
			view.forward(request, response);
		}
		response.getWriter().println(
			"<!DOCTYPE html>"+
				"<html>" + 
					"<head>"+
					"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" +
					"<title>iTravel - Login</title>" +
					"<link href=\"../css/main.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\">"+
					"</head>"+
					"<body>"+
						"<div class=\"index\">"+
					      	"<div class=\"container\">"+
					      		"<div class=\"login\">"+
					      			"<h1>Plataforma de Gestion de Viajes</h1>"+
					      			"<h2>Inicie Sesion</h2>" +
					      			"<a class=\"aceptarViaje\" href=\"" + url + "\">" + urlLinktext + "</a>"+
				      			"</div>" +
			      			" </div>" +
		      			" </div>" +
		      			"<div class=\"footer\">" +
		      				"<div class=\"container\">" +
		      					"<p> Grupo 7 </p>" +
		      					"<p class=\"a-center\"><a href=\"https://www.upm.es\"> Universidad Politecnica De Madrid </a></p>" +
		      					"<p class=\"p-right\" id=\"fecha\">ISST 2017</p>" +
		      				"</div>" +
					    "</div>" +
				    "</body>"+
				"</html>"
		);
//		response.getWriter().println("<p>Pulsa <a href=\"" + url + "\">" + urlLinktext + "</a> para entrar.</p>");
	}
}
