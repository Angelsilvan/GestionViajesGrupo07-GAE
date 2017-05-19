package es.upm.dit.isst.gestiondeviajesgrupo07;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

import es.upm.dit.isst.gestiondeviajesgrupo07.dao.*;
import es.upm.dit.isst.gestiondeviajesgrupo07.model.*;

/**
 * Servlet implementation class ISST_VIAJES_Update_Servlet
 */
public class ISST_VIAJES_Upload_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ISST_VIAJES_Upload_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VIAJESDAO dao = VIAJESDAOImpl.getInstancia();
		String numeroViaje = request.getParameter("numeroViaje");
		String concepto = request.getParameter("concepto");
		String importe = request.getParameter("importe");
		long importeLong = Long.parseLong(importe);
		VIAJE viaje = dao.readViaje(numeroViaje);
		Map<String, List<BlobKey>> blobs = BlobstoreServiceFactory.getBlobstoreService().getUploads(request);
		List<BlobKey> blobKeys = blobs.get("file");
		if (blobKeys == null || blobKeys.isEmpty() || blobKeys.get(0) == null) {
			response.sendError(1200);
		}
		JUSTIFICANTE justificante = dao.createJustificante(concepto, importeLong, blobKeys.get(0).getKeyString(), viaje);
		viaje.addJustificante(justificante);
		dao.update(viaje);
		
		request.getSession().setAttribute("numeroViaje", numeroViaje);
		
		response.sendRedirect("/irANuevoJustificante");
	}

}
