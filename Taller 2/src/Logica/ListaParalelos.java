package Logica;

import Dominio.Paralelo;

public class ListaParalelos {
	private Paralelo [] lpa;
	private int maximo;
	private int cantParalelos;
	public ListaParalelos(int maximo) {
		lpa = new Paralelo[maximo];
		cantParalelos = 0;
    	this.maximo = maximo;
    }
    public int getCantParalelos(){
    	return cantParalelos;
    }
    public boolean insertarParalelo(Paralelo paralelo) {
    	if(cantParalelos<maximo) {
    		lpa[cantParalelos] = paralelo;
    		cantParalelos++;
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    public Paralelo getParalelo(int i) {
    	if(i >= 0 && i < cantParalelos) {
    		return lpa[i];
    	}
    	else {
    		return null;
    	}
	}
    public Paralelo buscarParalelo(String numero) {
    	for(int i = 0;i< this.cantParalelos;i++) {
    		if(lpa[i].getNumero().equals(numero)) {
    			return lpa[i];
    		}
    	}
    	return null;
	}
}
