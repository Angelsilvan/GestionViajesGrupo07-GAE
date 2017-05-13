package es.upm.dit.isst.gestiondeviajesgrupo07;

import java.io.IOException;
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
		EMPLEADO empleado = dao.readEmpleado(nombre);
		String numeroEmpleado = request.getParameter("numeroEmpleado");
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
		response.sendRedirect("/isst_viajes");
	}

}
