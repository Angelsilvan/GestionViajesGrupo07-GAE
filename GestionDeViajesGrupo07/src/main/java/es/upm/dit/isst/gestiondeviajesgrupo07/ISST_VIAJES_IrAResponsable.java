package es.upm.dit.isst.gestiondeviajesgrupo07;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.gestiondeviajesgrupo07.dao.VIAJESDAO;
import es.upm.dit.isst.gestiondeviajesgrupo07.dao.VIAJESDAOImpl;
import es.upm.dit.isst.gestiondeviajesgrupo07.model.EMPLEADO;
import es.upm.dit.isst.gestiondeviajesgrupo07.model.VIAJE;

/**
 * Servlet implementation class ISST_VIAJES_IrAResponsable
 */
public class ISST_VIAJES_IrAResponsable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ISST_VIAJES_IrAResponsable() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userService = UserServiceFactory.getUserService();
		String url = userService.createLoginURL(request.getRequestURI());
		String urlLinktext = "Login";
		String user = "";

		if (request.getUserPrincipal() != null){
			user = request.getUserPrincipal().getName();
			url = userService.createLogoutURL(request.getRequestURI());
			urlLinktext = "Logout";
			
			
			VIAJESDAO dao = VIAJESDAOImpl.getInstancia();
			ArrayList<VIAJE> viajesSupervisor = new ArrayList<VIAJE>();
			viajesSupervisor.addAll(dao.readViajesSuperivisor(user));
			EMPLEADO empleado = dao.readEmpleado(user);
			
			ArrayList<VIAJE> viajesSupervisorEnCurso = new ArrayList<VIAJE>();
			ArrayList<VIAJE> viajesSupervisorFinalizados = new ArrayList<VIAJE>();

			for (VIAJE viajei : viajesSupervisor) {
				if(viajei.getEstado() == 5){
					viajesSupervisorFinalizados.add(viajei);
				}
				else{
					viajesSupervisorEnCurso.add(viajei);
				}
			}
			
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("url", url);
			request.getSession().setAttribute("urlLinktext", urlLinktext);
			request.getSession().setAttribute("viajesSupervisorFinalizados", viajesSupervisorFinalizados);
			request.getSession().setAttribute("viajesSupervisorEnCurso", viajesSupervisorEnCurso);
			request.getSession().setAttribute("viajesSupervisor", viajesSupervisor);

			request.getSession().setAttribute("empleado", empleado);
			
			RequestDispatcher view = request.getRequestDispatcher("jsp/ResponsableView.jsp");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
