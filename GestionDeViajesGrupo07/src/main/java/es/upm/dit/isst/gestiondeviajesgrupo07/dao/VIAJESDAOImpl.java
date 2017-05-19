package es.upm.dit.isst.gestiondeviajesgrupo07.dao;

import java.util.*;

import es.upm.dit.isst.gestiondeviajesgrupo07.model.EMPLEADO;
import es.upm.dit.isst.gestiondeviajesgrupo07.model.JUSTIFICANTE;
import es.upm.dit.isst.gestiondeviajesgrupo07.model.PROYECTO;
import es.upm.dit.isst.gestiondeviajesgrupo07.model.VIAJE;

import static com.googlecode.objectify.ObjectifyService.ofy;


public class VIAJESDAOImpl implements VIAJESDAO {
	
	private static VIAJESDAOImpl instancia;
	private VIAJESDAOImpl() {
	}
	public static VIAJESDAOImpl getInstancia() {
		if (instancia == null)
			instancia= new VIAJESDAOImpl();
		return instancia;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	//******** JUSTIFICANTES ************//
	
	@Override
	public JUSTIFICANTE createJustificante(String concepto, long importe, String fichero, VIAJE viaje){
		ArrayList<JUSTIFICANTE> todosLosJustificantesViaje = new ArrayList<>();
		todosLosJustificantesViaje = viaje.getJustificantes();
		List<JUSTIFICANTE> todosLosJustificantes = ofy().load().type(JUSTIFICANTE.class).list();
		long idTotalMasAlto=0;
		for(JUSTIFICANTE justificantei: todosLosJustificantes){
			if(justificantei.getIdJustificante()>idTotalMasAlto){
				idTotalMasAlto = justificantei.getIdJustificante();
			}
		}
		long numeroIdJustificante = idTotalMasAlto+1;
		
		long idMasAlto=0;
		for(JUSTIFICANTE justificantei: todosLosJustificantesViaje){
			if(justificantei.getNumeroJustificante()>idMasAlto){
				idMasAlto = justificantei.getNumeroJustificante();
			}
		}
		long numeroJustificante = idMasAlto+1;
		JUSTIFICANTE justificante = new JUSTIFICANTE(numeroIdJustificante, numeroJustificante, concepto, importe, fichero);
		viaje.addJustificante(justificante);
		ofy().save().entity(justificante).now();
		ofy().save().entity(viaje).now();
		return justificante;
	}
	
	public List<JUSTIFICANTE> readJustificantes(VIAJE viaje){
		return viaje.getJustificantes();
	}
	
	public List<JUSTIFICANTE> readJustificantesEnviados(VIAJE viaje){
		return viaje.getJustificantesEnviados();
	}
	
	@Override
	public List<JUSTIFICANTE> readJustificantesSinEnviar(VIAJE viaje) {
		return viaje.getJustificantesSinEnviar();
	}
	
	@Override
	public List<JUSTIFICANTE> readJustificantesAprobados(VIAJE viaje) {
		return viaje.getJustificantesAprobados();
	}
	
	@Override
	public List<JUSTIFICANTE> readJustificantesPagados(VIAJE viaje) {
		return viaje.getJustificantesPagados();
	}
	
	@Override
	public JUSTIFICANTE update(JUSTIFICANTE justificante) {
		ofy().save().entity(justificante).now();
		return justificante;
	}
	
	public void deleteJustificante(JUSTIFICANTE justificante, VIAJE viaje){
		viaje.deleteJustificante(justificante);
		ofy().save().entity(viaje).now();
		ofy().delete().entity(justificante).now();
	}
	
	//******** VIAJES ************//

	@Override
	public VIAJE createViaje(EMPLEADO empleado, String fechaInicio, String fechaFin, PROYECTO proyecto,
		int estado, String destinoCiudad, String destinoPais, String destinoProvincia, String motivo) {
		VIAJE viaje= null;
		List<VIAJE> todosLosViajes =   ofy().load().type(VIAJE.class).list();
		long idMasAlto=0;
		for(VIAJE viajei: todosLosViajes){
			if(viajei.getNumeroViaje()>idMasAlto){
				idMasAlto = viajei.getNumeroViaje();
			}
		}
		long numeroViaje = idMasAlto+1;
		viaje = new VIAJE(numeroViaje, empleado, fechaInicio, fechaFin, proyecto, estado, destinoCiudad, destinoPais, destinoProvincia, motivo);
		ofy().save().entity(viaje).now();
		return viaje;
	}
	
	@Override
	public VIAJE readViaje(String numeroViaje) {
		List<VIAJE> todosLosViajes =   ofy().load().type(VIAJE.class).list();
		VIAJE viaje = null;
		for (VIAJE viajei : todosLosViajes) {
			String numeroViajeString = Long.toString(viajei.getNumeroViaje());
			if(numeroViajeString.equals(numeroViaje)){
				viaje = viajei;
			}
		}
		return viaje;
	}

	@Override
	public List<VIAJE> readAllViajes() {
		List<VIAJE> viajes =  ofy().load().type(VIAJE.class).list();
		return viajes;
	}

	@Override
	public ArrayList<VIAJE> readViajesSuperivisor(EMPLEADO supervisor) {
		
		List<VIAJE> todosLosViajes =   ofy().load().type(VIAJE.class).list();
		ArrayList<VIAJE> viajesConSupervisor = new ArrayList<>();
		for (VIAJE viaje : todosLosViajes) {
			if(viaje.getProyecto().getSupervisor().equals(supervisor)){
				viajesConSupervisor.add(viaje);
			}
		}
		return viajesConSupervisor;
	}
	
	@Override
	public ArrayList<VIAJE> readViajesSuperivisor(String supervisor) {
		
		List<VIAJE> todosLosViajes =   ofy().load().type(VIAJE.class).list();
		ArrayList<VIAJE> viajesConSupervisor = new ArrayList<>();
		for (VIAJE viaje : todosLosViajes) {
			if(viaje.getProyecto().getSupervisor().getNombre().equals(supervisor)){
				viajesConSupervisor.add(viaje);
			}
		}
		return viajesConSupervisor;
	}

	@Override
	public List<VIAJE> readViajesEstado(int estado) {
		List<VIAJE> viajes =   ofy().load().type(VIAJE.class).filter("estado", estado).list();
		return viajes;
	}

	@Override
	public List<VIAJE> readViajesEmpleado(EMPLEADO empleado) {
		List<VIAJE> todosLosViajes =   ofy().load().type(VIAJE.class).list();
		ArrayList<VIAJE> viajesConEmpleado = new ArrayList<>();
		for (VIAJE viajei : todosLosViajes) {
			if(viajei.getEmpleado().getNombre().equals(empleado.getNombre())){
				viajesConEmpleado.add(viajei);
			}
		}
		return viajesConEmpleado;
	}

	@Override
	public ArrayList<VIAJE> readViajesEmpleado(String empleado) {
		List<VIAJE> todosLosViajes = ofy().load().type(VIAJE.class).list();
		ArrayList<VIAJE> viajesConEmpleado = new ArrayList<>();
		for (VIAJE viaje : todosLosViajes) {
			if(viaje.getEmpleado().getNombre().equals(empleado)){
				viajesConEmpleado.add(viaje);
			}
		}
		return viajesConEmpleado;
	}

	@Override
	public VIAJE update(VIAJE viaje) {
		ofy().save().entity(viaje).now();
		return viaje;
	}

	@Override
	public void delete(VIAJE viaje) {
		ofy().delete().entity(viaje).now();
	}

	//******** EMPLEADOS ************//
	
	@Override
	public EMPLEADO readEmpleado(String nombreEmpleado) {
		List<EMPLEADO> todosLosEmpleados =   ofy().load().type(EMPLEADO.class).list();
		EMPLEADO empleado = null;
		for (EMPLEADO empleadoi : todosLosEmpleados) {
			if(empleadoi.getNombre().equals(nombreEmpleado)){
				empleado = empleadoi;
			}
		}
		return empleado;
	}
	
	@Override
	public EMPLEADO createEMPLEADO(long numeroEmpleado, String nombre, String apellido1, String apellido2) {
		EMPLEADO empleado= null;
		empleado = new EMPLEADO(numeroEmpleado, nombre, apellido1, apellido2);
		ofy().save().entity(empleado).now();
		return empleado;
	}

	@Override
	public long readNumeroEmpleado(String empleado) {
		List<EMPLEADO> todosLosEmpleados =   ofy().load().type(EMPLEADO.class).list();
		long numeroEmpleado = 0;
		for (EMPLEADO empleadoi : todosLosEmpleados) {
			if(empleadoi.getNombre().equals(empleado)){
				numeroEmpleado = empleadoi.getNumeroEmpleado();
			}
		}
		return numeroEmpleado;
	}
	
	@Override
	public EMPLEADO update(EMPLEADO empleado) {
		ofy().save().entity(empleado).now();
		return empleado;
	}

	@Override
	public void delete(EMPLEADO empleado) {
		ofy().delete().entity(empleado).now();

	}

	//******** PROYECTOS ************//

	@Override
	public PROYECTO readProyecto(String codigoProyecto) {
		List<PROYECTO> todosLosProyectos =   ofy().load().type(PROYECTO.class).list();
		PROYECTO proyecto = null;
		for (PROYECTO proyectoi : todosLosProyectos) {
			if(proyectoi.getCodigoProyecto().equals(codigoProyecto)){
				proyecto = proyectoi;
			}
		}
		return proyecto;
	}
	
	@Override
	public PROYECTO createPROYECTO(String codigoProyecto, ArrayList<EMPLEADO> empleadosDelProyecto, EMPLEADO supervisor,
			String sucursal, String departamento) {
		PROYECTO proyecto= null;
		proyecto = new PROYECTO(codigoProyecto, empleadosDelProyecto, supervisor, sucursal, departamento);
		ofy().save().entity(proyecto).now();
		return proyecto;
	}

	@Override
	public List<PROYECTO> readProyectosSuperivisor(EMPLEADO supervisor) {
		List<PROYECTO> proyectos = ofy().load().type(PROYECTO.class).filter("supervisor", supervisor).list();
		return proyectos;
	}

	@Override
	public List<PROYECTO> readProyectosEmpleado(EMPLEADO empleado) {
		List<PROYECTO> todosLosProyectos =   ofy().load().type(PROYECTO.class).list();
		ArrayList<PROYECTO> proyectosConEmpleado = new ArrayList<>();
		for (PROYECTO proyectoi : todosLosProyectos) {
			ArrayList<EMPLEADO> empleadosDelProyecto = proyectoi.getEmpleadosDelProyecto();
			for (EMPLEADO empleadoi : empleadosDelProyecto) {
				if(empleadoi.getNombre().equals(empleado.getNombre())){
					proyectosConEmpleado.add(proyectoi);
				}
			}
		}
		return proyectosConEmpleado;
	}

	@Override
	public PROYECTO update(PROYECTO proyecto) {
		ofy().save().entity(proyecto).now();
		return proyecto;
	}

	@Override
	public void delete(PROYECTO proyecto) {
		ofy().delete().entity(proyecto).now();
	}
	
}
