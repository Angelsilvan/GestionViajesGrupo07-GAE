package es.upm.dit.isst.gestiondeviajesgrupo07.model;

import java.io.Serializable;
import java.util.*;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class PROYECTO implements Serializable {
	@Id
	private String codigoProyecto;
	@Index
	private ArrayList<EMPLEADO> empleadosDelProyecto = new ArrayList<EMPLEADO>();
	@Index
	private EMPLEADO supervisor;
	private String sucursal;
	private String departamento;

	public PROYECTO(){
	}
	
	/**
	 * Constructor de la clase
	 * @param codigoProyecto
	 * @param empleadosDelProyecto
	 * @param supervisor
	 * @param sucursal
	 * @param departamento
	 */
	public PROYECTO(String codigoProyecto, ArrayList<EMPLEADO> empleadosDelProyecto, EMPLEADO supervisor,
			String sucursal, String departamento) {
		
		this.codigoProyecto = codigoProyecto;
		this.empleadosDelProyecto = empleadosDelProyecto;
		this.supervisor = supervisor;
		this.sucursal = sucursal;
		this.departamento = departamento;
	}
	
	
	/**
	 * @return the codigoProyecto
	 */
	public String getCodigoProyecto() {
		return codigoProyecto;
	}

	/**
	 * @param codigoProyecto the codigoProyecto to set
	 */
	public void setCodigoProyecto(String codigoProyecto) {
		this.codigoProyecto = codigoProyecto;
	}

	/**
	 * @return the empleadosDelProyecto
	 */
	public ArrayList<EMPLEADO> getEmpleadosDelProyecto() {
		return empleadosDelProyecto;
	}

	/**
	 * @param empleadosDelProyecto the empleadosDelProyecto to set
	 */
	public void addEmpleadosDelProyecto(EMPLEADO empleado) {
		empleadosDelProyecto.add(empleado);
	}
	/**
	 * @param empleadosDelProyecto the empleadosDelProyecto to set
	 */
	public void removeEmpleadosDelProyecto(EMPLEADO empleado) {
		empleadosDelProyecto.remove(empleado);
	}

	/**
	 * @return the supervisor
	 */
	public EMPLEADO getSupervisor() {
		return supervisor;
	}

	/**
	 * @param supervisor the supervisor to set
	 */
	public void setSupervisor(EMPLEADO supervisor) {
		this.supervisor = supervisor;
	}

	/**
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * @return the departamento
	 */
	public String getDepartamento() {
		return departamento;
	}

	/**
	 * @param departamento the departamento to set
	 */
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
