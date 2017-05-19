package es.upm.dit.isst.gestiondeviajesgrupo07;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class ISST_VIAJES_irAJustificantesMisEmpleados
 */
public class ISST_VIAJES_irAJustificantesMisEmpleados extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ISST_VIAJES_irAJustificantesMisEmpleados() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String numeroViaje = request.getParameter("numeroViaje");
		VIAJESDAO dao = VIAJESDAOImpl.getInstancia();
		VIAJE viaje = dao.readViaje(numeroViaje);
		int estadoViaje = viaje.getEstado();
		List<JUSTIFICANTE> justificantesMiViajeAprobados = dao.readJustificantesAprobados(viaje);
		List<JUSTIFICANTE> justificantesMiViajeEnviados = dao.readJustificantesEnviados(viaje);
		List<JUSTIFICANTE> justificantesMiViajePagados = dao.readJustificantesPagados(viaje);

		
		request.getSession().setAttribute("numeroViaje", numeroViaje);
		request.getSession().setAttribute("estadoViaje", estadoViaje);
		request.getSession().setAttribute("justificantesMiViajeAprobados", justificantesMiViajeAprobados);
		request.getSession().setAttribute("justificantesMiViajeEnviados", justificantesMiViajeEnviados);
		request.getSession().setAttribute("justificantesMiViajePagados", justificantesMiViajePagados);
		
		RequestDispatcher view = request.getRequestDispatcher("jsp/justificantesMisEmpleadosView.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
