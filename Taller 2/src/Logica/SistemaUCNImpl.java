package Logica;
import Dominio.*;
import ucn.*;
public class SistemaUCNImpl implements SistemaUCN {
	private ListaEstudiantes listaEstudiantes;
	private ListaProfesores listaProfesores;
	private ListaParalelos listaParalelos;
	private ListaAsignaturas listaAsignaturas;
	private ListaEstudiantes Egresados;
	public SistemaUCNImpl() {
		listaEstudiantes = new ListaEstudiantes(100);
		listaProfesores = new ListaProfesores(100);
		listaParalelos = new ListaParalelos(100);
		listaAsignaturas = new ListaAsignaturas(100);
		Egresados = new ListaEstudiantes(100);
	}
	@Override
	public boolean IngresarEstudiante(String rut, String correo, int nivel, String clave) {
		// TODO Auto-generated method stub
		Estudiante estudiante = new Estudiante(rut,correo,nivel,clave);
		boolean ingreso = listaEstudiantes.insertarEstudiante(estudiante);
		return ingreso;
	}

	@Override
	public boolean IngresarAsignaturaObligatoria(String codigo, String nombre, int credito, int nivel) {
		// TODO Auto-generated method stub
		Obligatoria obligatoria = new Obligatoria(codigo,nombre,credito,nivel);
		boolean ingreso = listaAsignaturas.insertarAsignatura(obligatoria);
		return ingreso;
	}
	public boolean AsociarAsignatura(String codigoObligatoria,String codigo) {
		try {
		Obligatoria obligatoria = (Obligatoria) listaAsignaturas.buscarAsignatura(codigoObligatoria);
		Asignatura asignatura = listaAsignaturas.buscarAsignatura(codigo);
		obligatoria.getPrerequisito().insertarAsignatura(asignatura);
		}catch(NullPointerException e) {
			StdOut.println("Error al asociar Asignatura prerequisito");
		}
		return true;
	}
	@Override
	public boolean IngresarAsignaturaOpcional(String codigo, String nombre, int credito, int creditoPrerequisito) {
		// TODO Auto-generated method stub
		Opcional opcional = new Opcional(codigo,nombre,credito,creditoPrerequisito);
		boolean ingreso = listaAsignaturas.insertarAsignatura(opcional);
		return ingreso;
	}

	@Override
	public boolean IngresarProfesor(String rut, String correo, String clave, String salario) {
		// TODO Auto-generated method stub
		Profesor profesor = new Profesor(rut,correo,clave,salario);
		boolean ingreso = listaProfesores.insertarProfesor(profesor);
		return ingreso;
	}

	@Override
	public boolean IngresarParalelo(String numero, String codigo, String rut) {
		// TODO Auto-generated method stub
		boolean ingreso = false;
		try {
			Asignatura asignatura = listaAsignaturas.buscarAsignatura(codigo);
			Profesor profesor = listaProfesores.buscarProfesorRut(rut);
			Paralelo paralelo = new Paralelo(numero);
			paralelo.setAsignatura(asignatura);
			paralelo.setProfesor(profesor);
			ingreso = listaParalelos.insertarParalelo(paralelo);
			Asignatura asignaturaNueva = new Asignatura(codigo, asignatura.getNombre(),asignatura.getCredito());
			asignaturaNueva.setParalelo(paralelo);
			ingreso = profesor.getLpa().insertarAsignatura(asignaturaNueva);
			if(asignatura instanceof Obligatoria) {
				Obligatoria obligatoria = (Obligatoria) asignatura;
				obligatoria.getParalelos().insertarParalelo(paralelo);
			}
			else {
				Opcional opcional = (Opcional) asignatura;
				opcional.setParalelo(paralelo);
			}
		}catch(NullPointerException e) {
			StdOut.println("Error al ingresar Paralelo");
		}
		return ingreso;
	}

	@Override
	public boolean AsociarAsignaturaEstudiante(String codigo, String correo, double notaFinal) {
		// TODO Auto-generated method stub
		boolean asociar = false;
		try {
			Asignatura asignatura = listaAsignaturas.buscarAsignatura(codigo);
			Estudiante estudiante = listaEstudiantes.buscarEstudiante(correo);
			if(notaFinal < 3.5) {
				asociar = estudiante.getCursadas().insertarAsignatura(asignatura);
				asignatura.setNotaFinal(notaFinal);
			}
			else {
				asociar = estudiante.getAprobadas().insertarAsignatura(asignatura);
				asignatura.setNotaFinal(notaFinal);
			}
		}catch(NullPointerException e) {
			StdOut.println("Error al asociar Asignatura");
		}
		return asociar;
	}

	@Override
	public boolean VerificarUsuario(String correo, String clave) {
		// TODO Auto-generated method stub
		int i;
		int j;
		for(i = 0; i < listaEstudiantes.getCantEstudiantes();i++) {
			if(listaEstudiantes.getEstudiante(i).getCorreo().equals(correo) && listaEstudiantes.getEstudiante(i).getClave().equals(clave)) {
				break;
			}
		}
		if(i < listaEstudiantes.getCantEstudiantes()) {
			return true;
		}
		else {
			for(j = 0;j < listaProfesores.getCantProfesores();j++) {
				if(listaProfesores.getProfesor(j).getCorreo().equals(correo) && listaProfesores.getProfesor(j).getClave().equals(clave)) {
					break;
				}
			}
			if(i < listaProfesores.getCantProfesores()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String retornarTipo(String correo) {
		// TODO Auto-generated method stub
		int i;
		int j;
		for(i = 0; i < listaEstudiantes.getCantEstudiantes();i++) {
			if(listaEstudiantes.getEstudiante(i).getCorreo().equals(correo)) {
				break;
			}
		}
		if(i < listaEstudiantes.getCantEstudiantes()) {
			return "Estudiante";
		}
		else {
			for(j = 0;j < listaProfesores.getCantProfesores();j++) {
				if(listaProfesores.getProfesor(j).getCorreo().equals(correo)) {
					break;
				}
			}
			if(j < listaProfesores.getCantProfesores()) {
				return "Profesor";
			}
		}
		return "Ninguno";
	}

	@Override
	public String RetornarAsignaturasAInscribir(String correo) {
		// TODO Auto-generated method stub
		String despliegue = "";
		Estudiante estudiante = listaEstudiantes.buscarEstudiante(correo);
		for(int i = 0;i< estudiante.getCursadas().getCantAsignaturas();i++) {
			despliegue += "Codigo: " + estudiante.getCursadas().getAsignatura(i).getCodigo() + " Nombre: " + estudiante.getCursadas().getAsignatura(i).getNombre() + "\n";
		}
		for(int i = 0;i < listaAsignaturas.getCantAsignaturas(); i++) {
			Asignatura asignatura = listaAsignaturas.getAsignatura(i);
			if(asignatura instanceof Obligatoria) {
				Obligatoria obligatoria = (Obligatoria) asignatura;
				int cantidadRequisitos = obligatoria.getPrerequisito().getCantAsignaturas();
				for(int j = 0;j < cantidadRequisitos;j++) {
					for(int k = 0; k < estudiante.getAprobadas().getCantAsignaturas();k++) {
						if(obligatoria.getPrerequisito().getAsignatura(j) == estudiante.getAprobadas().getAsignatura(k)) {
							cantidadRequisitos--;
						}
					}
				}
				if(cantidadRequisitos == 0) {
					despliegue += "Codigo: " + obligatoria.getCodigo() + " Nombre: " + obligatoria.getNombre() + "\n";
				}
			}
			else {
				Opcional opcional = (Opcional) asignatura;
				int cantCreditos = 0;
				for(int j = 0; j < estudiante.getAprobadas().getCantAsignaturas();j++ ) {
					cantCreditos += estudiante.getAprobadas().getAsignatura(j).getCredito();
				}
				if(opcional.getCreditoPrerequisito() <= cantCreditos) {
					despliegue += "Codigo: " + opcional.getCodigo() + " Nombre: " + opcional.getNombre() + "\n";
				}
			} 
		}
		return despliegue;
	}

	@Override
	public String RetornaParalelosAlumnos(String codigo) {
		// TODO Auto-generated method stub
		String despliegue = "Paralelos: " + "\n";
		try {
			Asignatura asignatura = listaAsignaturas.buscarAsignatura(codigo); 
			if(asignatura instanceof Obligatoria) {
				Obligatoria obligatoria = (Obligatoria) asignatura;
				for(int i=0;i<obligatoria.getParalelos().getCantParalelos();i++) {
					despliegue += obligatoria.getParalelos().getParalelo(i).getNumero();
				}
			}
			else {
				Opcional opcional = (Opcional) asignatura;
				despliegue += opcional.getParaleloUnico().getNumero();
			}
		}catch(NullPointerException e) {
			StdOut.println("Error al desplegar paralelos");
		}
		return despliegue;
	}

	@Override
	public boolean InscripcionAsignatura(String correo, String codigo, String paralelo) {
		// TODO Auto-generated method stub
		boolean inscripcion = false;
		try {
			Estudiante estudiante = listaEstudiantes.buscarEstudiante(correo);
			Asignatura asignatura = listaAsignaturas.buscarAsignatura(codigo);
			if(asignatura instanceof Obligatoria) {
				Obligatoria obligatoria = (Obligatoria) asignatura;
				ListaParalelos paralelos = obligatoria.getParalelos();
				Paralelo paralelo1 = paralelos.buscarParalelo(paralelo);
				Asignatura asignaturaNueva = new Asignatura(codigo,obligatoria.getNombre(),obligatoria.getCredito());
				asignaturaNueva.setParalelo(paralelo1);
				inscripcion = estudiante.getInscritas().insertarAsignatura(asignaturaNueva);
				paralelo1.getLpe().insertarEstudiante(estudiante);
			}
			else {
				Opcional opcional = (Opcional) asignatura;
				Paralelo paralelo1 = opcional.getParaleloUnico();
				if(paralelo1.getNumero().equals(paralelo)) {
					opcional.setParalelo(paralelo1);
					Asignatura asignaturaNueva = new Asignatura(codigo,opcional.getNombre(),opcional.getCredito());
					inscripcion = estudiante.getInscritas().insertarAsignatura(asignaturaNueva);
				}
				paralelo1.getLpe().insertarEstudiante(estudiante);
			}
			
		}catch(NullPointerException e) {
			StdOut.println("Error de verificación");
		}
		return inscripcion;
	}

	@Override
	public String RetornarAsignaturasInscritas(String correo) {
		// TODO Auto-generated method stub
		String despliegue = "";
		try {
		Estudiante estudiante = listaEstudiantes.buscarEstudiante(correo);
		if(estudiante.getInscritas().getCantAsignaturas() == 0) {
			despliegue = "No cuenta con asignaturas inscritas";
		}
		else {
			for(int i = 0;i<estudiante.getInscritas().getCantAsignaturas();i++) {
				despliegue += "Codigo: " + estudiante.getInscritas().getAsignatura(i).getCodigo() + " Asignatura: " + estudiante.getInscritas().getAsignatura(i).getNombre()+ "\n";
			}
		}
		}catch(NullPointerException e){
			StdOut.println("Error al inscribir asignatura");
		}
		return despliegue;
	}
	public boolean EliminarAsignatura(String correo, String codigo) {
		// TODO Auto-generated method stub
		try {
			Estudiante estudiante = listaEstudiantes.buscarEstudiante(correo);
			estudiante.getInscritas().Eliminar(codigo);
		}catch(NullPointerException e){
			StdOut.println("Error al eliminar asignatura");
		}
		return true;
	}

	@Override
	public String RetornaAsignaturasProfesor(String correo) {
		// TODO Auto-generated method stub
		String despliegue = "";
		Profesor profesor = listaProfesores.buscarProfesor(correo);
		ListaAsignaturas la = profesor.getLpa();
		for(int i = 0;i< la.getCantAsignaturas() ;i++) {
			despliegue += "Paralelo :" + la.getAsignatura(i).getParalelo().getNumero() + " Asignatura: " + la.getAsignatura(i).getNombre() + "Codigo: "+ la.getAsignatura(i).getCodigo() + "\n"; 
		}
		return despliegue;
	}
	@Override
	public String RetornaAlumnos(String correo, String codigo) {
		// TODO Auto-generated method stub
		String despliegue = "";
		try {
		Profesor profesor = listaProfesores.buscarProfesor(correo);
		Asignatura asignatura = profesor.getLpa().buscarAsignatura(codigo);
		ListaEstudiantes le = asignatura.getParalelo().getLpe();
		for(int i = 0;i < le.getCantEstudiantes();i++) {
			despliegue += "Correo: " + le.getEstudiante(i).getCorreo() + "Rut: "+ le.getEstudiante(i).getRut() + "\n";
		}
		}catch(NullPointerException e) {
			StdOut.println("Error al buscar la asignatura");
		}
		return despliegue;
	}

	@Override
	public boolean NotaFinal(double nota, String correo, String codigo) {
		// TODO Auto-generated method stub
		try {
			Estudiante estudiante = listaEstudiantes.buscarEstudiante(correo);
			Asignatura asignatura = estudiante.getInscritas().buscarAsignatura(codigo);
			asignatura.setNotaFinal(nota);
			if(nota < 3.95) {
				estudiante.getCursadas().insertarAsignatura(asignatura);
			}
			else {
				estudiante.getAprobadas().insertarAsignatura(asignatura);
			}
			estudiante.getInscritas().Eliminar(codigo);
		}catch(NullPointerException e) {
			StdOut.println("Error al ingresar nota final");
		}
		return false;
	}

	@Override
	public void Egresados() {
		// TODO Auto-generated method stub
		int contAsignaturas = listaAsignaturas.getCantAsignaturas();
		for(int i = 0;i < listaEstudiantes.getCantEstudiantes();i++) {
			if(contAsignaturas == listaEstudiantes.getEstudiante(i).getAprobadas().getCantAsignaturas()) {
				Egresados.insertarEstudiante(listaEstudiantes.getEstudiante(i));
				listaEstudiantes.Eliminar(listaEstudiantes.getEstudiante(i).getCorreo());
			}
		}
	}

	@Override
	public String datosAlumnos() {
		// TODO Auto-generated method stub
		String despliegue = "";
		for(int i = 0;i<listaEstudiantes.getCantEstudiantes();i++) {
			Estudiante estudiante = listaEstudiantes.getEstudiante(i);
			despliegue += estudiante.getRut() + "," + estudiante.getCorreo() + "," + estudiante.getNivel() + "," +estudiante.getClave() + "\n";
			int cant = estudiante.getAprobadas().getCantAsignaturas() + estudiante.getCursadas().getCantAsignaturas();
			despliegue += cant + "\n";
			for(int j = 0; j < estudiante.getAprobadas().getCantAsignaturas();j++) {
				Asignatura asignatura = estudiante.getAprobadas().getAsignatura(j);
				despliegue += asignatura.getCodigo() + "," + asignatura.getNotaFinal()+ "\n";
			}
			for(int j = 0; j < estudiante.getCursadas().getCantAsignaturas();j++) {
				Asignatura asignatura = estudiante.getCursadas().getAsignatura(j);
				despliegue += asignatura.getCodigo() + "," + asignatura.getNotaFinal()+ "\n";
			}
			despliegue += estudiante.getInscritas().getCantAsignaturas()+ "\n";
			for(int j = 0; j< estudiante.getInscritas().getCantAsignaturas();j++) {
				Asignatura asignatura = estudiante.getInscritas().getAsignatura(j);
				despliegue += asignatura.getCodigo() + "," + asignatura.getParalelo().getNumero()+ "\n";
			}
		}
		return despliegue;
	}

	@Override
	public String datosEgresados() {
		// TODO Auto-generated method stub
		String despliegue = "";
		for(int i = 0; i< Egresados.getCantEstudiantes();i++) {
			despliegue += Egresados.getEstudiante(i).getRut() + "\n";
		}
		return despliegue;
	}
	
}
