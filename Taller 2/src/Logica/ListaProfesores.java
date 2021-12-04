package Logica;

import Dominio.Profesor;

public class ListaProfesores {
	private Profesor [] lp;
	private int maximo;
	private int cantProfesores;
	public ListaProfesores(int maximo) {
		lp = new Profesor[maximo];
		cantProfesores = 0;
    	this.maximo = maximo;
    }
    public int getCantProfesores(){
    	return cantProfesores;
    }
    public boolean insertarProfesor(Profesor profesor) {
    	if(cantProfesores<maximo) {
    		lp[cantProfesores] = profesor;
    		cantProfesores++;
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    public Profesor getProfesor(int i) {
    	if(i >= 0 && i < cantProfesores) {
    		return lp[i];
    	}
    	else {
    		return null;
    	}
	}
    public Profesor buscarProfesor(String correo) {
    	for(int i = 0;i< this.cantProfesores;i++) {
    		if(lp[i].getCorreo().equals(correo)) {
    			return lp[i];
    		}
    	}
    	return null;
	}
    public Profesor buscarProfesorRut(String rut) {
    	for(int i = 0;i< this.cantProfesores;i++) {
    		if(lp[i].getRut().equals(rut)) {
    			return lp[i];
    		}
    	}
    	return null;
	}
}
