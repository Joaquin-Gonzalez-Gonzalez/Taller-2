package Dominio;
import Logica.ListaAsignaturas;
import Logica.ListaParalelos;
public class Obligatoria extends Asignatura{
	private int nivel; 
	private ListaAsignaturas prerequisito;
	private ListaParalelos paralelos;
	public Obligatoria(String codigo,String nombre,int credito,int nivel) {
		super(codigo,nombre,credito);
		this.prerequisito = new ListaAsignaturas(100);
		this.paralelos = new ListaParalelos(100);
		this.nivel = nivel;
	}
	public ListaParalelos getParalelos() {
		return paralelos;
	}

	public void setParalelos(ListaParalelos paralelos) {
		this.paralelos = paralelos;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public ListaAsignaturas getPrerequisito() {
		return prerequisito;
	}
	public void setPrerequisito(ListaAsignaturas prerequisito) {
		this.prerequisito = prerequisito;
	}
	
}
