package es.upm.dit.isst.gestiondeviajesgrupo07;

import java.io.IOException;
import java.util.*;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.gestiondeviajesgrupo07.dao.VIAJESDAO;
import es.upm.dit.isst.gestiondeviajesgrupo07.dao.VIAJESDAOImpl;
import es.upm.dit.isst.gestiondeviajesgrupo07.model.EMPLEADO;
import es.upm.dit.isst.gestiondeviajesgrupo07.model.PROYECTO;
import es.upm.dit.isst.gestiondeviajesgrupo07.model.VIAJE;

/**
 * Servlet implementation class ISST_VIAJES_NuevoViaje_Servlet
 */
public class ISST_VIAJES_NuevoViaje_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ISST_VIAJES_NuevoViaje_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		VIAJESDAO dao = VIAJESDAOImpl.getInstancia();
		
		String nombre = request.getParameter("nombre");
		System.out.println(nombre);
		EMPLEADO empleado = dao.readEmpleado(nombre);
//		String numeroEmpleado = request.getParameter("numeroEmpleado");
		String fechaInicio = request.getParameter("fechaInicio");
		String fechaFin = request.getParameter("fechaFin");
		String proyectoString = request.getParameter("proyecto");
		PROYECTO proyecto = dao.readProyecto(proyectoString);
		String destinoCiudad = request.getParameter("destinoCiudad");
		String destinoProvincia = request.getParameter("destinoProvincia");
		String destinoPais = request.getParameter("destinoPais");
		String motivo = request.getParameter("motivo");
		int uno = 1;
		VIAJE viaje = dao.createViaje(empleado, fechaInicio, fechaFin, proyecto, uno, destinoCiudad, destinoPais, destinoProvincia, motivo);
		
		String correoSupervisorViaje = viaje.getProyecto().getSupervisor().getNombre();
		String nombreEmpleado = viaje.getEmpleado().getApellido1() + " " + viaje.getEmpleado().getApellido2() + " (" + viaje.getEmpleado().getNombre()+ ")";
		String nombreProyecto = viaje.getProyecto().getCodigoProyecto();
		String destinoViaje = viaje.getDestinoCiudad();
				
//		System.out.println(correoSupervisorViaje);
//		System.out.println(nombreEmpleado);
//		System.out.println(nombreProyecto);
		
		try {
			MimeMessage msg = new MimeMessage(Session.getDefaultInstance(new Properties()));
			msg.setFrom(new InternetAddress("info@gestiondeviajesgrupo07.appspotmail.com", "Sistema de gestion de Viajes"));
			msg.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(correoSupervisorViaje));
			msg.setSubject("El empleado " + nombreEmpleado + " solicita un viaje");
			msg.setText("El empleado " + nombreEmpleado + " solcita un nuevo viaje a "+ destinoViaje +" con el proyecto " + nombreProyecto);
			Transport.send(msg);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("no se ha podido enviar el correo");
		}
		
		response.sendRedirect("/isst_viajes");
	}

}
