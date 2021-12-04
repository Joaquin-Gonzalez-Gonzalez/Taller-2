package Dominio;

public class Opcional extends Asignatura {
	private int creditoPrerequisito;
	private Paralelo paraleloUnico;
	public Opcional(String codigo, String nombre, int credito,int creditoPrerequisito) {
		super(codigo, nombre, credito);
		this.creditoPrerequisito = creditoPrerequisito;
		this.paraleloUnico = null;
	}
	
	public Paralelo getParaleloUnico() {
		return paraleloUnico;
	}

	public void setParaleloUnico(Paralelo paraleloUnico) {
		this.paraleloUnico = paraleloUnico;
	}

	public int getCreditoPrerequisito() {
		return creditoPrerequisito;
	}
	public void setCreditoPrerequisito(int creditoPrerequisito) {
		this.creditoPrerequisito = creditoPrerequisito;
	}
	
	
}
