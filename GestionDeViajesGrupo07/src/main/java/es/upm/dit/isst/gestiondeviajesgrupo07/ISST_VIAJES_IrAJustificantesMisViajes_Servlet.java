package es.upm.dit.isst.gestiondeviajesgrupo07;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import es.upm.dit.isst.gestiondeviajesgrupo07.model.JUSTIFICANTE;
import es.upm.dit.isst.gestiondeviajesgrupo07.model.VIAJE;

/**
 * Servlet implementation class ISST_VIAJES_IrAJustificantesMisViajes_Servlet
 */
public class ISST_VIAJES_IrAJustificantesMisViajes_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ISST_VIAJES_IrAJustificantesMisViajes_Servlet() {
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
		List<JUSTIFICANTE> justificantesMiViajeSinEnviar = dao.readJustificantesSinEnviar(viaje);
		List<JUSTIFICANTE> justificantesMiViajeEnviados = dao.readJustificantesEnviados(viaje);
		List<JUSTIFICANTE> justificantesMiViajeAprobados = dao.readJustificantesAprobados(viaje);
		List<JUSTIFICANTE> justificantesMiViajePagados = dao.readJustificantesPagados(viaje);


		int importeTotal = 0;
		for (int i = 0; i < justificantesMiViajeSinEnviar.size(); i++) {
			importeTotal += justificantesMiViajeSinEnviar.get(i).getImporte();
		}
		
		request.getSession().setAttribute("numeroViaje", numeroViaje);
		request.getSession().setAttribute("estadoViaje", estadoViaje);
		request.getSession().setAttribute("importeTotal", importeTotal);
		request.getSession().setAttribute("justificantesMiViajeSinEnviar", justificantesMiViajeSinEnviar);
		request.getSession().setAttribute("justificantesMiViajeEnviados", justificantesMiViajeEnviados);
		request.getSession().setAttribute("justificantesMiViajeAprobados", justificantesMiViajeAprobados);
		request.getSession().setAttribute("justificantesMiViajePagados", justificantesMiViajePagados);

		
		RequestDispatcher view = request.getRequestDispatcher("jsp/justificantesMisViajesView.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
