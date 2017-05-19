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
 * Servlet implementation class ISST_VIAJES_SolicitarReembolsoPrevio_Servlet
 */
public class ISST_VIAJES_SolicitarReembolsoPrevio_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ISST_VIAJES_SolicitarReembolsoPrevio_Servlet() {
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
		
		String correoSupervisorViaje = viaje.getProyecto().getSupervisor().getNombre();
		String nombreEmpleado = viaje.getEmpleado().getApellido1() + " " + viaje.getEmpleado().getApellido2() + " (" + viaje.getEmpleado().getNombre()+ ")";
		String nombreProyecto = viaje.getProyecto().getCodigoProyecto();
		String destinoViaje = viaje.getDestinoCiudad();
		
		if (viaje != null){
			List<JUSTIFICANTE> justificantesSinEnviar = viaje.getJustificantesSinEnviar();
			if(justificantesSinEnviar.size() != 0){
				for (JUSTIFICANTE justificante : justificantesSinEnviar) {
					justificante.setEstado(2);
					dao.update(justificante);
				}
				viaje.setEstado(22);
				dao.update(viaje);
				
				try {
					MimeMessage msg = new MimeMessage(Session.getDefaultInstance(new Properties()));
					msg.setFrom(new InternetAddress("info@gestiondeviajesgrupo07.appspotmail.com", "Sistema de gestion de Viajes"));
					msg.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(correoSupervisorViaje));
					msg.setSubject("El empleado " + nombreEmpleado + " solicita un reembolso previo");
					msg.setText("El empleado " + nombreEmpleado + " solcita un reembolso previo del viaje a " + destinoViaje + " con el proyecto " + nombreProyecto);
					Transport.send(msg);
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("no se ha podido enviar el correo");
				}
			}
		}
		response.sendRedirect("/vistaMisViajes");
	}

}
