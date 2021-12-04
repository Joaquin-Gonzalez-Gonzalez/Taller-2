package Logica;
import Dominio.Asignatura;
public class ListaAsignaturas {
	private Asignatura [] la;
	private int maximo;
	private int cantAsignaturas;
	public ListaAsignaturas(int maximo) {
		la = new Asignatura[maximo];
		cantAsignaturas = 0;
    	this.maximo = maximo;
    }
    public int getCantAsignaturas(){
    	return cantAsignaturas;
    }
    public void setAsignaturas(int cantAsignaturas) {
    	this.cantAsignaturas = cantAsignaturas;
    }
    public boolean insertarAsignatura(Asignatura asignatura) {
    	if(cantAsignaturas<maximo) {
    		la[cantAsignaturas] = asignatura;
    		cantAsignaturas++;
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    public Asignatura getAsignatura(int i) {
    	if(i >= 0 && i < cantAsignaturas) {
    		return la[i];
    	}
    	else {
    		return null;
    	}
	}
    public Asignatura buscarAsignatura(String codigo) {
    	for(int i = 0;i< this.cantAsignaturas;i++) {
    		if(la[i].getCodigo().equals(codigo)) {
    			return la[i];
    		}
    	}
    	return null;
	}
    public void Eliminar(String codigo) {
    	int i=0;	
    	for(i=0;i<this.cantAsignaturas;i++) {
    		if(codigo.equals(la[i].getNombre())) {
    			break;
    		}
    	}
    	for(int k = i ; k <cantAsignaturas-1; k++) {
    		la[k] = la[k+1];
    	}
    	this.cantAsignaturas--;
    }
}
