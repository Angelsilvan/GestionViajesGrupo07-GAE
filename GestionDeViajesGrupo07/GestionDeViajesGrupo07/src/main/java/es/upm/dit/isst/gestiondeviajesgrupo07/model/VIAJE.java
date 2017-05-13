/**
 * 
 */
package es.upm.dit.isst.gestiondeviajesgrupo07.model;

import java.io.Serializable;
import java.util.*;

import javax.annotation.Generated;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.*;
import com.googlecode.objectify.annotation.Index;

/**
 * @author jaime
 *
 */
@Entity
public class VIAJE implements Serializable {
	
	@Id
	private long numeroViaje;
	@Index
	private EMPLEADO empleado;
//	@Index
//	private EMPLEADO supervisor;
	@Index
	private String fechaInicio;
	@Index
	private String fechaFin;
	@Index
	private PROYECTO proyecto;
	@Index
	private int estado;
	@Index
	private String destinoCiudad;
	@Index 
	private String destinoPais;
	@Index
	private String destinoProvincia;
	@Index
	private String motivo;
	
	private ArrayList<String> justificantes = new ArrayList<String>();
	
	private String todosLosJustificantesString;
	
	public VIAJE(){
	}
	
	/**
	 * Constructor de la clase
	 */
	public VIAJE(long viaje, EMPLEADO empleado, String fechaInicio, String fechaFin, PROYECTO proyecto, int estado, String destinoCiudad, String destinoPais, String destinoProvincia, String motivo) {
		this.numeroViaje = viaje;
		this.empleado = empleado;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.proyecto = proyecto;
		this.estado = estado;
		this.destinoCiudad = destinoCiudad;
		this.destinoPais = destinoPais;
		this.destinoProvincia = destinoProvincia;
		this.motivo = motivo;
		this.todosLosJustificantesString="No hay justificantes";
	}
	
	public String getAllInfo(){
		String infonumeroViaje = "Viaje número: " + numeroViaje;
		String infoNomreEmpleado = "Nombre empleado: " + empleado.getNombre();
		String infoNumeroEmpleado = "Número empleado: " + empleado.getNumeroEmpleado();
		String infoFechaInicio = "Fecha de inicio del viaje: " + fechaInicio; 
		String infoFechaFin = "Fecha final del viaje: " + fechaFin;
		String infoProyecto = "Código de proyecto: " + proyecto.getCodigoProyecto();
		String infoEstado = "Estado: " + estado;
		String infoDestino = "Destino: " + destinoCiudad + ", " + destinoProvincia + ", " + destinoPais + ".";
		String infoViaje = infonumeroViaje + "\n" + infoNomreEmpleado + "\n" + infoNumeroEmpleado + "\n" + infoFechaInicio + "\n" + infoFechaFin + "\n" + infoProyecto + "\n" + infoEstado + "\n" + infoDestino;
		
		return infoViaje;
	}
	/**
	 * Metodo para añadir un fichero al array de ficheros
	 * @param fichero. Fichero a añadir
	 */
	public void addJustificante(String justificante){
//		if (!justificantes.contains(justificante)){
			justificantes.add(justificante);
			updateAllJustificantes();
//		}
	}
	/**
	 * Metodo que devuelve un String con todos los ficheros separados en distintas filas.
	 * @return String con todos los ficheros.
	 */
	public void updateAllJustificantes(){
		String todosLosJustificantes = "";
		int numeroJustificante = 1;
		for (String justificante : justificantes) {
			todosLosJustificantes = todosLosJustificantes + numeroJustificante + ": "+ justificante + "\n";
			numeroJustificante ++;
		}
		setTodosLosJustificantesString(todosLosJustificantes);
	}
	
	public ArrayList<String> getJustificantes() {
		return justificantes;
	}

	public void setJustificantes(ArrayList<String> justificantes) {
		this.justificantes = justificantes;
	}

	public String getTodosLosJustificantesString() {
		return todosLosJustificantesString;
	}

	public void setTodosLosJustificantesString(String todosLosJustificantesString) {
		this.todosLosJustificantesString = todosLosJustificantesString;
	}

	/**
	 * @return the numeroViaje
	 */
	public long getNumeroViaje() {
		return numeroViaje;
	}

	/**
	 * @param numeroViaje the numeroViaje to set
	 */
	public void setNumeroViaje(long numeroViaje) {
		this.numeroViaje = numeroViaje;
	}

	/**
	 * @return the empleado
	 */
	public EMPLEADO getEmpleado() {
		return empleado;
	}

	/**
	 * @param empleado the empleado to set
	 */
	public void setEmpleado(EMPLEADO empleado) {
		this.empleado = empleado;
	}

	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the codigoProyecto
	 */
	public PROYECTO getProyecto() {
		return proyecto;
	}

	/**
	 * @param codigoProyecto the codigoProyecto to set
	 */
	public void setProyecto(PROYECTO proyecto) {
		this.proyecto = proyecto;
	}

	/**
	 * @return the estado
	 */
	public int getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}

	/**
	 * @return the destinoCiudad
	 */
	public String getDestinoCiudad() {
		return destinoCiudad;
	}

	/**
	 * @param destinoCiudad the destinoCiudad to set
	 */
	public void setDestinoCiudad(String destinoCiudad) {
		this.destinoCiudad = destinoCiudad;
	}

	/**
	 * @return the destinoPais
	 */
	public String getDestinoPais() {
		return destinoPais;
	}

	/**
	 * @param destinoPais the destinoPais to set
	 */
	public void setDestinoPais(String destinoPais) {
		this.destinoPais = destinoPais;
	}

	/**
	 * @return the destinoProvincia
	 */
	public String getDestinoProvincia() {
		return destinoProvincia;
	}

	/**
	 * @param destinoProvincia the destinoProvincia to set
	 */
	public void setDestinoProvincia(String destinoProvincia) {
		this.destinoProvincia = destinoProvincia;
	}

	/**
	 * @return the motivo
	 */
	public String getMotivo() {
		return motivo;
	}

	/**
	 * @param motivo the motivo to set
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
