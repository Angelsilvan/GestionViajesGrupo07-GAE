package es.upm.dit.isst.gestiondeviajesgrupo07;

import java.io.IOException;
import java.util.Properties;

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
import es.upm.dit.isst.gestiondeviajesgrupo07.model.*;



/**
 * Servlet implementation class ISST_VIAJES_AceptarViaje_Servlet
 */
public class ISST_VIAJES_AceptarViaje_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ISST_VIAJES_AceptarViaje_Servlet() {
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
		String numeroViaje = request.getParameter("numeroViaje");
		VIAJESDAO dao = VIAJESDAOImpl.getInstancia();
		VIAJE viaje = dao.readViaje(numeroViaje);
		
		String correoEmpleado = viaje.getEmpleado().getNombre();
		String nombreSupervisorViaje = viaje.getProyecto().getSupervisor().getApellido1() + " " + viaje.getProyecto().getSupervisor().getApellido2() + " (" + viaje.getProyecto().getSupervisor().getNombre()+ ")";
		String nombreProyecto = viaje.getProyecto().getCodigoProyecto();
		String destinoViaje = viaje.getDestinoCiudad();
		
		if (viaje != null){
			viaje.setEstado(2);
			dao.update(viaje);
			
			try {
				MimeMessage msg = new MimeMessage(Session.getDefaultInstance(new Properties()));
				msg.setFrom(new InternetAddress("info@gestiondeviajesgrupo07.appspotmail.com", "Sistema de gestion de Viajes"));
				msg.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(correoEmpleado));
				msg.setSubject("El responsable " + nombreSupervisorViaje + " ha aprobado la realizacion de tu viaje");
				msg.setText("El responsable " + nombreSupervisorViaje + " del proyecto "+ nombreProyecto +" ha aprobado tu solicitud de viaje a " + destinoViaje + ". Ya puedes comenzar a subir justificantes a la plataforma");
				Transport.send(msg);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("no se ha podido enviar el correo");
			}
		}
		
		response.sendRedirect("/vistaResponsable");
	}

}
