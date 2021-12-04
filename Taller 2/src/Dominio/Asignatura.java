package Dominio;

public class Asignatura {
	protected String codigo;
	protected String nombre;
	protected int credito;
	protected double notaFinal;
	protected Paralelo paralelo;
	public  Asignatura(String codigo, String nombre, int credito) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.credito = credito;
		notaFinal = 0;
		paralelo = null;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCredito() {
		return credito;
	}
	public void setCredito(int credito) {
		this.credito = credito;
	}
	public double getNotaFinal() {
		return notaFinal;
	}
	public void setNotaFinal(double notaFinal) {
		this.notaFinal = notaFinal;
	}
	public Paralelo getParalelo() {
		return paralelo;
	}
	public void setParalelo(Paralelo paralelo) {
		this.paralelo = paralelo;
	}
	
}
