package es.upm.dit.isst.gestiondeviajesgrupo07.model;

import java.io.Serializable;

import javax.swing.Spring;

import com.googlecode.objectify.annotation.*;

@Entity
public class JUSTIFICANTE implements Serializable {
	
	@Id
	private long idJustificante;
	@Index
	private long numeroJustificante;
	@Index
	private String concepto;
	@Index
	private long importe;
	@Index
	private String fichero;
	@Index
	private long estado;


	public long getIdJustificante() {
		return idJustificante;
	}

	public void setIdJustificante(long idJustificante) {
		this.idJustificante = idJustificante;
	}

	public JUSTIFICANTE(){
	}
	
	/**
	 * Constructor de la clase
	 */
	public JUSTIFICANTE(long idJustificante, long numeroJustificante, String concepto, long importe, String fichero) {
		this.idJustificante = idJustificante;
		this.numeroJustificante = numeroJustificante;
		this.concepto = concepto;
		this.importe = importe;
		this.fichero = fichero;
		this.estado = 1;
	}

	public long getEstado() {
		return estado;
	}

	public void setEstado(long estado) {
		this.estado = estado;
	}

	public long getNumeroJustificante() {
		return numeroJustificante;
	}

	public void setNumeroJustificante(long numeroJustificante) {
		this.numeroJustificante = numeroJustificante;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public long getImporte() {
		return importe;
	}

	public void setImporte(long importe) {
		this.importe = importe;
	}

	public String getFichero() {
		return fichero;
	}

	public void setFichero(String fichero) {
		this.fichero = fichero;
	}
}