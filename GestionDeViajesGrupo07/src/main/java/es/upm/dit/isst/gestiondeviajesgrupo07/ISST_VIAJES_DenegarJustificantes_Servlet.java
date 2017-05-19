package es.upm.dit.isst.gestiondeviajesgrupo07;

import java.io.IOException;
import java.util.List;
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
import es.upm.dit.isst.gestiondeviajesgrupo07.model.JUSTIFICANTE;
import es.upm.dit.isst.gestiondeviajesgrupo07.model.VIAJE;

/**
 * Servlet implementation class ISST_VIAJES_DenegarJustificantes_Servlet
 */
public class ISST_VIAJES_DenegarJustificantes_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ISST_VIAJES_DenegarJustificantes_Servlet() {
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
		String motivoDenegacion = request.getParameter("motivoDenegacion");
		VIAJESDAO dao = VIAJESDAOImpl.getInstancia();
		VIAJE viaje = dao.readViaje(numeroViaje);
		
		String correoEmpleado = viaje.getEmpleado().getNombre();
		String nombreSupervisorViaje = viaje.getProyecto().getSupervisor().getApellido1() + " " + viaje.getProyecto().getSupervisor().getApellido2() + " (" + viaje.getProyecto().getSupervisor().getNombre()+ ")";
		String nombreProyecto = viaje.getProyecto().getCodigoProyecto();
		String destinoViaje = viaje.getDestinoCiudad();
		
		if (viaje != null){
			List<JUSTIFICANTE> justificantesEnviados = viaje.getJustificantesEnviados();
			for (JUSTIFICANTE justificante : justificantesEnviados) {
				justificante.setEstado(1);
				dao.update(justificante);
			}
			if(viaje.getEstado() == 3){
				viaje.setEstado(22);
			}
			else if(viaje.getEstado() == 22){
				viaje.setEstado(2);
			}
			dao.update(viaje);
			
			try {
				MimeMessage msg = new MimeMessage(Session.getDefaultInstance(new Properties()));
				msg.setFrom(new InternetAddress("info@gestiondeviajesgrupo07.appspotmail.com", "Sistema de gestion de Viajes"));
				msg.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(correoEmpleado));
				msg.setSubject("El responsable " + nombreSupervisorViaje + " ha denegado su reembolso solicitado");
				msg.setText("El responsable " + nombreSupervisorViaje + " del proyecto " + nombreProyecto + " ha denegado su solicitud de reembolso de los justificantes del viaje a " + destinoViaje + " por el motivo: " + motivoDenegacion);
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
