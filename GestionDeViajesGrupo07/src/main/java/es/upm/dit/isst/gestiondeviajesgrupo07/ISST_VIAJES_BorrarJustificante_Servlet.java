package es.upm.dit.isst.gestiondeviajesgrupo07;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.gestiondeviajesgrupo07.dao.VIAJESDAO;
import es.upm.dit.isst.gestiondeviajesgrupo07.dao.VIAJESDAOImpl;
import es.upm.dit.isst.gestiondeviajesgrupo07.model.JUSTIFICANTE;
import es.upm.dit.isst.gestiondeviajesgrupo07.model.VIAJE;

/**
 * Servlet implementation class ISST_VIAJES_BorrarJustificante_Servlet
 */
public class ISST_VIAJES_BorrarJustificante_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ISST_VIAJES_BorrarJustificante_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = "";
		if (request.getUserPrincipal() != null){
			user = request.getUserPrincipal().getName();
		}
		String numeroViaje = request.getParameter("numeroViaje");
		String numeroJustificante = request.getParameter("numeroJustificante");

		VIAJESDAO dao = VIAJESDAOImpl.getInstancia();
		VIAJE viaje = dao.readViaje(numeroViaje);
		if (viaje != null){
			long numeroJustificanteLong = Long.parseLong(numeroJustificante);
			JUSTIFICANTE justificante = viaje.getJustificante(numeroJustificanteLong);
			dao.deleteJustificante(justificante, viaje);
		}
		request.getSession().setAttribute("numeroViaje", numeroViaje);
		
		RequestDispatcher view = request.getRequestDispatcher("/irAJustificantesMisViajes");
		view.forward(request, response);
//		response.sendRedirect("/irANuevoJustificante");
//		response.sendRedirect("/irAJustificantesMisViajes");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
