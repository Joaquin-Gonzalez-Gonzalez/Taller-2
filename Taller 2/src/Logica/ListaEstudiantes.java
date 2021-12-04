package Logica;

import Dominio.Estudiante;

public class ListaEstudiantes {
	private Estudiante [] le;
	private int maximo;
	private int cantEstudiantes;
	public ListaEstudiantes(int maximo) {
		le = new Estudiante[maximo];
		cantEstudiantes = 0;
    	this.maximo = maximo;
    }
    public int getCantEstudiantes(){
    	return cantEstudiantes;
    }
    public boolean insertarEstudiante(Estudiante estudiante) {
    	if(cantEstudiantes<maximo) {
    		le[cantEstudiantes] = estudiante;
    		cantEstudiantes++;
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    public Estudiante getEstudiante(int i) {
    	if(i >= 0 && i < cantEstudiantes) {
    		return le[i];
    	}
    	else {
    		return null;
    	}
	}
    public Estudiante buscarEstudiante(String correo) {
    	for(int i = 0;i< this.cantEstudiantes;i++) {
    		if(le[i].getCorreo().equals(correo)) {
    			return le[i];
    		}
    	}
    	return null;
	}
    public void Eliminar(String correo) {
    	int i=0;	
    	for(i=0;i<this.cantEstudiantes;i++) {
    		if(correo.equals(le[i].getCorreo())) {
    			break;
    		}
    	}
    	for(int k = i ; k <cantEstudiantes-1; k++) {
    		le[k] = le[k+1];
    	}
    	this.cantEstudiantes--;
    }
}
