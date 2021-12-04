package Dominio;
import Logica.ListaAsignaturas;
public class Estudiante {
	private String rut;
	private String correo;
	private int nivel;
	private String clave;
	private ListaAsignaturas aprobadas;
	private ListaAsignaturas cursadas;
	private ListaAsignaturas inscritas;
	public Estudiante(String rut,String correo, int nivel, String clave) {
		this.rut = rut;
		this.correo = correo;
		this.nivel = nivel;
		this.clave = clave;
		this.cursadas = new ListaAsignaturas(100);
		this.inscritas = new ListaAsignaturas(100);
		this.aprobadas = new ListaAsignaturas(100);
	}
	
	public ListaAsignaturas getAprobadas() {
		return aprobadas;
	}

	public void setAprobadas(ListaAsignaturas aprobadas) {
		this.aprobadas = aprobadas;
	}

	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public ListaAsignaturas getCursadas() {
		return cursadas;
	}
	public void setCursadas(ListaAsignaturas cursadas) {
		this.cursadas = cursadas;
	}
	public ListaAsignaturas getInscritas() {
		return inscritas;
	}
	public void setInscritas(ListaAsignaturas inscritas) {
		this.inscritas = inscritas;
	}
	
}
