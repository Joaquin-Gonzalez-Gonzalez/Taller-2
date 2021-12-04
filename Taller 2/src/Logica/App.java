package Logica;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import ucn.*;
public class App {
/**
 * this is the main function of the program
 * @param args
 * @throws IOException
 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		SistemaUCN sistema = new SistemaUCNImpl();
		LeerAsignaturas(sistema);
		LeerProfesores(sistema);
		LeerParalelos(sistema);
		LeerEstudiantes(sistema);
		InicioSesion(sistema);
		SalidaEstudiantes(sistema);
	}
	/**
	 * this is the login of the program
	 * @param sistema
	 * @throws IOException
	 */
	public static void InicioSesion(SistemaUCN sistema) throws IOException {
		boolean inicio = false;
		StdOut.print("Ingrese correo: ");
		String correo = StdIn.readString();
		StdOut.print("Ingrese clave: ");
		String clave = StdIn.readString();
		inicio = sistema.VerificarUsuario(correo, clave);
		if(correo.equals("Admin") && clave.equals("GHI_789")){
			inicio = true;
		}
		while(inicio) {
			StdOut.print("Ingrese correo nuevamente, para salir digite (Salir): ");
			correo = StdIn.readString();
			if(correo.equals("Salir")) {
				break;
			}
			
			StdOut.print("Ingrese clave: ");
			clave = StdIn.readString();
			if(correo.equals("Admin") && clave.equals("GHI_789")){
				break;
			}
			inicio = sistema.VerificarUsuario(correo, clave);
			
		}
		if(correo.equals("Salir")) {
			
		}
		else {
			try {
				SimpleDateFormat  sdformat = new SimpleDateFormat("yyyy-MM-dd");
				StdOut.print("Ingrese fecha: ");
				String fecha = StdIn.readString();
				Date date = sdformat.parse(fecha);
				String tipo = sistema.retornarTipo(correo);
				Date dateInicioSemestre = sdformat.parse("2021-03-08");
				Date dateMitadSemestre = sdformat.parse("2021-05-02");
				Date dateFinalSemestre = sdformat.parse("2021-07-11");
				Date dateCierreSemestre = sdformat.parse("2021-07-25");
				ComparadorFechas(tipo,date,dateInicioSemestre,dateMitadSemestre,dateFinalSemestre,dateCierreSemestre,correo,sistema);
			}catch(ParseException e) {
				StdOut.println("Fecha invalida");
				
			}	
		}	
	}
	/** 
	 * this function compare the dates
	 * @param tipo
	 * @param date
	 * @param dateInicioSemestre
	 * @param dateMitadSemestre
	 * @param dateFinalSemestre
	 * @param dateCierreSemestre
	 * @param correo
	 * @param sistema
	 * @throws IOException
	 */
	public static void ComparadorFechas(String tipo,Date date, Date dateInicioSemestre, Date dateMitadSemestre, Date dateFinalSemestre, Date dateCierreSemestre, String correo,SistemaUCN sistema) throws IOException {
		if(date.compareTo(dateInicioSemestre) >= 0 && date.compareTo(dateMitadSemestre) <= 0) {
			InicioSemestre(tipo,correo,sistema);
		}
		if(date.compareTo(dateMitadSemestre) > 0 && date.compareTo(dateFinalSemestre) <= 0) {
			MitadSemestre(tipo,correo,sistema);
		}
		if(date.compareTo(dateFinalSemestre) > 0 && date.compareTo(dateCierreSemestre) <= 0) {
			FinalSemestre(tipo,correo,sistema);
		}
		if(date.compareTo(dateCierreSemestre) == 0) {
			CierreSemestre(tipo,sistema);
		}
		if(date.compareTo(dateCierreSemestre) > 0 && date.compareTo(dateInicioSemestre) < 0) {
			StdOut.println("Disfrute sus vacaciones");
		}
	}
	/**
	 * this function is the beginning of semester
	 * @param tipo
	 * @param correo
	 * @param sistema
	 */
	public static void InicioSemestre(String tipo,String correo,SistemaUCN sistema) {
		if(tipo.equals("Estudiante")) {
			String respuesta = "";
			do {
				StdOut.println("1.- Inscripcion Asignaturas");
				StdOut.println("2.- Eliminar Asignatura");
				StdOut.println("3.- Salir");
				StdOut.print("Respuesta: ");
				respuesta = StdIn.readString();
				if(respuesta.equals("1")) {
					InscribirAsignatura(correo,sistema);
				}
				if(respuesta.equals("2")) {
					EliminarAsignatura(correo,sistema);
				}
			}while(!respuesta.equals("3"));
		}
		if(tipo.equals("Profesor")) {
			String respuesta = "";
			do {
				StdOut.println("1.- Chequeo Alumnos");
				StdOut.println("2.- Salir");
				StdOut.print("Respuesta: ");
				respuesta = StdIn.readString();
				if(respuesta.equals("1")) {
					ChequeoAlumnos(correo,sistema);
				}
			}while(!respuesta.equals("2"));
		}
		if(tipo.equals("Ninguno")) {
			StdOut.println("No hay acciones Disponibles");
		}
	}
	/**
	 * this function sing on a subject for a student
	 * @param correo
	 * @param sistema
	 */
	public static void InscribirAsignatura(String correo,SistemaUCN sistema) {
		StdOut.println(sistema.RetornarAsignaturasAInscribir(correo));
		StdOut.print("Ingrese codigo asignatura: ");
		String codigo = StdIn.readString();
		StdOut.println(sistema.RetornaParalelosAlumnos(codigo));
	}
	/**
	 * this function deletes a subject for a student
	 * @param correo
	 * @param sistema
	 */
	public static void EliminarAsignatura(String correo, SistemaUCN sistema) {
		StdOut.println(sistema.RetornarAsignaturasInscritas(correo));
		StdOut.print("Ingrese codigo asignatura: ");
		String codigo = StdIn.readString();
		sistema.EliminarAsignatura(correo, codigo);
	}
	/**
	 * this function prints all the students of a teacher
	 * @param correo
	 * @param sistema
	 */
	public static void ChequeoAlumnos(String correo, SistemaUCN sistema) {
		StdOut.println(sistema.RetornaAsignaturasProfesor(correo));
		StdOut.print("Ingrese codigo asignatura: ");
		String codigo = StdIn.readString();
		StdOut.println(sistema.RetornaAlumnos(correo, codigo));
		
	}
	/**
	 * this function is for the mid semester
	 * @param tipo
	 * @param correo
	 * @param sistema
	 */
	public static void MitadSemestre(String tipo,String correo,SistemaUCN sistema) {
		if(tipo.equals("Estudiante")) {
			String respuesta = "";
			do {
				StdOut.println("1.- Eliminar Asignatura");
				StdOut.println("2.- Salir");
				StdOut.print("Respuesta: ");
				respuesta = StdIn.readString();
				if(respuesta.equals("1")) {
					EliminarAsignatura(correo,sistema);
				}
			}while(!respuesta.equals("2"));
		}
		else {
			StdOut.println("No hay acciones Disponibles");
		}
	}
	/**
	 * this function is for the final semester
	 * @param tipo
	 * @param correo
	 * @param sistema
	 */
	public static void FinalSemestre(String tipo,String correo,SistemaUCN sistema) {
		if(tipo.equals("Profesor")) {
			String respuesta = "";
			do {
				StdOut.println("1.- Ingreso Nota Final");
				StdOut.println("2.- Salir");
				StdOut.print("Respuesta: ");
				respuesta = StdIn.readString();
				if(respuesta.equals("1")) {
					NotaFinal(correo,sistema);
				}
			}while(!respuesta.equals("2"));
		}
		else {
			StdOut.println("No hay acciones Disponibles");
		}
	}
	/**
	 * this function allows the teacher to enter a student's final grade
	 * @param correo
	 * @param sistema
	 */
	public static void NotaFinal(String correo,SistemaUCN sistema) {
		StdOut.println(sistema.RetornaAsignaturasProfesor(correo));
		StdOut.print("Ingrese codigo: ");
		String codigo = StdIn.readString();
		StdOut.println(sistema.RetornaAlumnos(correo, codigo));
		StdOut.print("Ingrese alumno: ");
		String alumno = StdIn.readString();
		StdOut.print("Ingrese nota: ");
		double nota = StdIn.readDouble();
		sistema.NotaFinal(nota, alumno, codigo);
	}
	/**
	 * this function is for the close of the semester
	 * @param tipo
	 * @param sistema
	 * @throws IOException
	 */
	public static void CierreSemestre(String tipo,SistemaUCN sistema) throws IOException {
		if(tipo.equals("Ninguno")) {
			sistema.Egresados();
			SalidaEgresados(sistema);
		}
		else {
			StdOut.println("No hay acciones Disponibles");
		}
	}
	/**
	 * this function reads estudiante.txt
	 * @param sistema
	 * @throws FileNotFoundException
	 */
	public static void LeerEstudiantes(SistemaUCN sistema) throws FileNotFoundException {
		Scanner s = new Scanner(new File("estudiantes.txt"));
        while(s.hasNextLine()) {
            String linea = s.nextLine();
            String [] partes = linea.split(",");
            String rut = partes[0];
            String correo = partes[1];            
            int nivel = Integer.parseInt(partes[2]);
            String clave = partes[3];
            try {
                boolean ingreso = sistema.IngresarEstudiante(rut,correo, nivel, clave);
                if(ingreso) {
                    linea = s.nextLine();
                    int cantAsignaturasCursadas = Integer.parseInt(linea);
                    for(int i=0;i<cantAsignaturasCursadas;i++) {
                        linea= s.nextLine();
                        String [] partes3 = linea.split(",");
                        String codigo = partes3[0];
                        double notaFinal = Double.parseDouble(partes3[1]);
                        try {
                            boolean ingresoCursada = sistema.AsociarAsignaturaEstudiante(codigo, correo, notaFinal);
                            if(!ingresoCursada) {
                                StdOut.println("No se pudo ingresar la asignatura cursada");
                            }
                        }catch (Exception e) {
                            StdOut.println(e.getMessage());
                        }
                    }
                    linea = s.nextLine();
                    int cantAsigInscritas = Integer.parseInt(linea);
                    for(int j=0;j<cantAsigInscritas;j++) {
                        linea = s.nextLine();
                        String [] partes5 = linea.split(",");
                        String codigoAsignatura =partes5[0];
                        String numeroParalelo = partes5[1];
                        try {
                            boolean ingresoInscrita = sistema.InscripcionAsignatura(correo, codigoAsignatura, numeroParalelo);
                            if(!ingresoInscrita) {
                                StdOut.println("No se pudo ingresar la asignatura Inscrita");
                            }
                        }catch (Exception e) {
                            StdOut.println(e.getMessage());
                        }
                    }
                }
            }catch (Exception ex) {
            	StdOut.println("\t"+ex.getMessage());
            }
        }
        s.close();
	}
	/**
	 * this function read asignaturas.txt
	 * @param sistema
	 * @throws IOException
	 */
	public static void LeerAsignaturas(SistemaUCN sistema) throws IOException {
		ArchivoEntrada arch = new ArchivoEntrada("asignaturas.txt");
		boolean ingreso = true;
		while(!arch.isEndFile() && ingreso) {
			Registro reg = arch.getRegistro();
			String codigo = reg.getString();
			String nombre = reg.getString();
			int credito = reg.getInt();
			String tipo = reg.getString();
			if(tipo.equals("obligatoria")) {
				int nivel = reg.getInt();
				ingreso = sistema.IngresarAsignaturaObligatoria(codigo, nombre, credito, nivel);
				int cant = reg.getInt();
				for(int i = 0;i < cant;i++) {
					String asignatura = reg.getString();
					ingreso = sistema.AsociarAsignatura(codigo, asignatura);
				}
			}
			else {
				int cant = reg.getInt();
				ingreso = sistema.IngresarAsignaturaOpcional(codigo, nombre, credito, cant);
			}
	    }
		arch.close();
	}
	/**
	 * this function read profesores.txt
	 * @param sistema
	 * @throws IOException
	 */
	public static void LeerProfesores(SistemaUCN sistema) throws IOException {
		ArchivoEntrada arch = new ArchivoEntrada("profesores.txt");
		boolean ingreso = true;
		while(!arch.isEndFile() && ingreso) {
			Registro reg = arch.getRegistro();
			String rut = reg.getString();
			String correo = reg.getString();
			String clave = reg.getString();
			String salario = reg.getString();
			ingreso = sistema.IngresarProfesor(rut, correo, clave, salario);
		}
	}
	/**
	 * this function reads paralelos.txt
	 * @param sistema
	 * @throws IOException
	 */
	public static void LeerParalelos(SistemaUCN sistema) throws IOException {
		ArchivoEntrada arch = new ArchivoEntrada("paralelos.txt");
		boolean ingreso = true;
		while(!arch.isEndFile() && ingreso) {
			Registro reg = arch.getRegistro();
			String numero = reg.getString();
			String codigo = reg.getString();
			String rut = reg.getString();
			ingreso = sistema.IngresarParalelo(numero, codigo, rut);
		}
	}
	/**
	 * this function create a txt of the students
	 * @param sistema
	 * @throws IOException
	 */
	public static void SalidaEstudiantes(SistemaUCN sistema) throws IOException {
		ArchivoSalida arch1 = new ArchivoSalida("estudiantes.txt");
		Registro rg = new Registro(1);
		rg.agregarCampo(sistema.datosAlumnos());
		arch1.writeRegistro(rg);
		arch1.close();
	}
	/**
	 * this function create a txt of the graduates
	 * @param sistema
	 * @throws IOException
	 */
	public static void SalidaEgresados(SistemaUCN sistema) throws IOException {
		ArchivoSalida arch1 = new ArchivoSalida("egresados.txt");
		Registro rg = new Registro(1);
		rg.agregarCampo(sistema.datosEgresados());
		arch1.writeRegistro(rg);
		arch1.close();
	}
	

}
