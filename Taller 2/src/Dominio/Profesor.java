package Dominio;
import Logica.ListaAsignaturas;
public class Profesor {
	private String rut;
	private String correo;
	private String clave;
	private String salario;
	private ListaAsignaturas lpa;
	public Profesor(String rut, String correo, String clave, String salario) {
		this.rut = rut;
		this.correo = correo;
		this.clave = clave;
		this.salario = salario;
		this.lpa = new ListaAsignaturas(4);
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
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getSalario() {
		return salario;
	}
	public void setSalario(String salario) {
		this.salario = salario;
	}
	public ListaAsignaturas getLpa() {
		return lpa;
	}
	public void setLpa(ListaAsignaturas lpa) {
		this.lpa = lpa;
	}
	
}
