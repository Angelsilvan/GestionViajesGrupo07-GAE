package es.upm.dit.isst.gestiondeviajesgrupo07.model;
import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class EMPLEADO implements Serializable {
	
	@Id
	private long numeroEmpleado;
	@Index
	private String nombre;
	@Index
	private String apellido1;
	@Index
	private String apellido2;
	
	public EMPLEADO(){
	}
	
	public EMPLEADO( long numeroEmpleado, String nombre, String apellido1, String apellido2) {
		this.numeroEmpleado = numeroEmpleado;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
	}
	
	/**
	 * @return the numeroEmpleado
	 */
	public long getNumeroEmpleado() {
		return numeroEmpleado;
	}

	/**
	 * @param numeroEmpleado the numeroEmpleado to set
	 */
	public void setNumeroEmpleado(long numeroEmpleado) {
		this.numeroEmpleado = numeroEmpleado;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellido1
	 */
	public String getApellido1() {
		return apellido1;
	}

	/**
	 * @param apellido1 the apellido1 to set
	 */
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	/**
	 * @return the apellido2
	 */
	public String getApellido2() {
		return apellido2;
	}

	/**
	 * @param apellido2 the apellido2 to set
	 */
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
