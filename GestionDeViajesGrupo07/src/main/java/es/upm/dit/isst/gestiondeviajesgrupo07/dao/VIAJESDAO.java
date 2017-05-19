package es.upm.dit.isst.gestiondeviajesgrupo07.dao;

import java.util.*;

import es.upm.dit.isst.gestiondeviajesgrupo07.model.EMPLEADO;
import es.upm.dit.isst.gestiondeviajesgrupo07.model.JUSTIFICANTE;
import es.upm.dit.isst.gestiondeviajesgrupo07.model.PROYECTO;
import es.upm.dit.isst.gestiondeviajesgrupo07.model.VIAJE;


public interface VIAJESDAO {
	
	//******** JUSTIFICANTES ************//

	/**
	 * Método para crear un nuevo objeto de la clase JUSTIFICANTE
	 */
	public JUSTIFICANTE createJustificante(String concepto, long importe, String fichero, VIAJE viaje);
	
	/**
	 * Metodo para obtener todos los objetos justificante pasandole el numero de viaje
	 * @param numeroViaje
	 * @return
	 */
	public List<JUSTIFICANTE> readJustificantes(VIAJE viaje);
	
	/**
	 * Método para borrar un justificante
	 * @param numeroJustificante. Justificante que se desea borrar.
	 */
	public void deleteJustificante(JUSTIFICANTE justificante, VIAJE viaje);
	
	
	
	//******** VIAJES ************//

	/**
	 * Método para crear un nuevo objeto de la clase VIAJE
	 */
	public VIAJE createViaje(EMPLEADO empleado, String fechaInicio, String fechaFin, PROYECTO proyecto, int estado, String destinoCiudad, String destinoPais, String destinoProvincia, String motivo);

	/**
	 * Metodo para obtener un objeto viaje pasandole el numero de viaje
	 * @param numeroViaje
	 * @return
	 */
	public VIAJE readViaje(String numeroViaje);	
	
	/**
	 * Método para leer todos los VIAJES
	 */	
	public List<VIAJE> readAllViajes();

	/**
	 * Método para leer todos los VIAJES de un supervisor
	 * @param supervisor. superviror del viaje
	 * @return todos los viajes de dicho supervisor
	 */
	public ArrayList<VIAJE> readViajesSuperivisor(EMPLEADO supervisor);
	
	/**
	 * Método para leer todos los VIAJES de un supervisor pasando su nombre.
	 * @param supervisor. nombre del superviror del viaje
	 * @return todos los viajes de dicho supervisor
	 */
	public ArrayList<VIAJE> readViajesSuperivisor(String supervisor);

	/**
	 * Método para leer todos los VIAJES que están en un estado
	 * @param estado. Estado en el que están todos los VIAJES que queremos leer.
	 * @return todos los VIAJES que están en dicho estado
	 */
	public List<VIAJE> readViajesEstado(int estado);

	/**
	 * Método para leer los VIAJEs de un empleado pasado como parámetro.
	 * @param empleado. Empleado que viaja
	 * @return VIAJE de dicho empleado
	 */
	public List<VIAJE> readViajesEmpleado (EMPLEADO empleado);

	/**
	 * Método para leer los VIAJEs de un empleado pasando como parámetro el nombre.
	 * @param empleado. Nombre del empleado que viaja
	 * @return VIAJE de dicho empleado
	 */
	public ArrayList<VIAJE> readViajesEmpleado(String empleado);
	/**
	 * Método que tomará como parámetro un objeto VIAJE 
	 * que ya exista pero al que se le ha hecho algún cambio 
	 * y lo sincronizará con la base de datos
	 * @param VIAJE. VIAJE a actualizar
	 * @return VIAJE actualizado
	 */
	public VIAJE update(VIAJE viaje);

	/**
	 * Método para borrar un viaje
	 * @param viaje. Viaje que se desea borrar.
	 */
	public void delete(VIAJE viaje);


	//******** EMPLEADO ************//
	/**
	 * Metodo que devuelve un objeto de la clase empleado pasando un nombre de empleado.
	 * @param nombreEmpleado. Nombre del empleado que se desea obtener
	 * @return objeto de la clase empleado de dicho empleado.
	 */
	EMPLEADO readEmpleado(String nombreEmpleado);

	/**
	 * Método para crear un nuevo objeto de la clase EMPLEADO
	 */
	public EMPLEADO createEMPLEADO( long numeroEmpleado, String nombre, String apellido1, String apellido2);

//	/**
//	 * Método para leer todos los EMPLEADOS
//	 */	
//	public List<EMPLEADO> readAllEmpleados();

	/**
	 * Método que tomará como parámetro un objeto EMPLEADO 
	 * que ya exista pero al que se le ha hecho algún cambio 
	 * y lo sincronizará con la base de datos
	 * @param EMPLEADO. EMPLEADO a actualizar
	 * @return EMPLEADO actualizado
	 */
	public EMPLEADO update(EMPLEADO empleado);

	/**
	 * Metodo que devuelve el numero de empleado de un empleado pasando su nombre.
	 * @param empleado. nombre del empleado
	 * @return numero de empleado del empleado.
	 */
	public long readNumeroEmpleado(String empleado);
	
	/**
	 * Método para borrar un empleado
	 * @param empleado. empleado que se desea borrar.
	 */
	public void delete(EMPLEADO empleado);

	//******** PROYECTO ************//

	/**
	 * Metodo para obtener un objeto de la clase PROYECTO pasando un codigo de proyecto.
	 * @param codigoProyecto. codigo de proyecto del proyecto que queremos obtener
	 * @return proyecto de la clase PROYECTO con dicho codigo de proyecto
	 */
	public PROYECTO readProyecto(String codigoProyecto);

	/**
	 * Método para crear un nuevo objeto de la clase PROYECTO
	 */
	public PROYECTO createPROYECTO(String codigoProyecto, ArrayList<EMPLEADO> empleadosDelProyecto, EMPLEADO supervisor,
			String sucursal, String departamento);

//	/**
//	 * Método para leer todos los PROYECTOS
//	 */	
//	public List<PROYECTO> readAllProyectos();

	/**
	 * Método para leer todos los PROYECTOS de un supervisor
	 * @param supervisor. superviror del proyecto
	 * @return todos los proyectos de dicho supervisor
	 */
	public List<PROYECTO> readProyectosSuperivisor(EMPLEADO supervisor);

	/**
	 * Método para leer el PROYECTO de un empleado pasado como parámetro.
	 * @param empleado. Empleado que está en un proyecto
	 * @return PROYECTOs de dicho empleado
	 */
	public List<PROYECTO> readProyectosEmpleado(EMPLEADO empleado);

	/**
	 * Método que tomará como parámetro un objeto PROYECTO 
	 * que ya exista pero al que se le ha hecho algún cambio 
	 * y lo sincronizará con la base de datos
	 * @param PROYECTO. PROYECTO a actualizar
	 * @return PROYECTO actualizado
	 */
	public PROYECTO update (PROYECTO proyecto);

	/**
	 * Método para borrar un proyecto
	 * @param proyecto. Proyecto que se desea borrar.
	 */
	public void delete (PROYECTO proyecto);

	public List<JUSTIFICANTE> readJustificantesEnviados(VIAJE viaje);

	public List<JUSTIFICANTE> readJustificantesSinEnviar(VIAJE viaje);

	public JUSTIFICANTE update(JUSTIFICANTE justificante);

	public List<JUSTIFICANTE> readJustificantesAprobados(VIAJE viaje);

	public List<JUSTIFICANTE> readJustificantesPagados(VIAJE viaje);



}

