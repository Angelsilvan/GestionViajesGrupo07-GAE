package es.upm.dit.isst.gestiondeviajesgrupo07;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.gestiondeviajesgrupo07.dao.VIAJESDAO;
import es.upm.dit.isst.gestiondeviajesgrupo07.dao.VIAJESDAOImpl;
import es.upm.dit.isst.gestiondeviajesgrupo07.model.EMPLEADO;


/**
 * Servlet implementation class ISST_VIAJES_NuevoEmpleado_Servlet
 */
public class ISST_VIAJES_NuevoEmpleado_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ISST_VIAJES_NuevoEmpleado_Servlet() {
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
		String nombre = request.getParameter("nombre");
		String apellido1 = request.getParameter("apellido1");
		String apellido2 = request.getParameter("apellido2");
		String numeroEmpleado = request.getParameter("numeroEmpleado");
		long numeroEmpleadoLong = Long.parseLong(numeroEmpleado);
		
		VIAJESDAO dao = VIAJESDAOImpl.getInstancia();
		EMPLEADO empleado = dao.createEMPLEADO(numeroEmpleadoLong, nombre, apellido1, apellido2);
		response.sendRedirect("/isst_viajes");
	}

}
