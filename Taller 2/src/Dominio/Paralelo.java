package Dominio;
import Logica.ListaEstudiantes;
public class Paralelo {
	private String numero;
	private Asignatura asignatura;
	private Profesor profesor;
	private ListaEstudiantes lpe;
	public Paralelo(String numero) {
		this.numero = numero;
		this.asignatura = null;
		this.profesor = null;
		this.lpe = new ListaEstudiantes(100); 
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Asignatura getAsignatura() {
		return asignatura;
	}
	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}
	public Profesor getProfesor() {
		return profesor;
	}
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	public ListaEstudiantes getLpe() {
		return lpe;
	}
	public void setLpe(ListaEstudiantes lpe) {
		this.lpe = lpe;
	}
	
}
