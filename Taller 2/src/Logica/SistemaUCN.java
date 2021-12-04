package Logica;

public interface SistemaUCN {
	/**
	 * this function insert a student in the container of students
	 * @param rut
	 * @param correo
	 * @param nivel
	 * @param clave
	 * @return
	 */
	public boolean IngresarEstudiante(String rut,String correo, int nivel, String clave);
	/**
	 * 
	 * @param codigo
	 * @param nombre
	 * @param credito
	 * @param nivel
	 * @return
	 */
	public boolean IngresarAsignaturaObligatoria(String codigo, String nombre, int credito,int nivel);
	/**
	 * this function associates a subject for a obligatory subject
	 * @param codigoObligatoria
	 * @param codigo
	 * @return
	 */
	public boolean AsociarAsignatura(String codigoObligatoria,String codigo);
	/**
	 * this function insert a optional subject in the container of subjects
	 * @param codigo
	 * @param nombre
	 * @param credito
	 * @param creditoPrerequisito
	 * @return
	 */
	public boolean IngresarAsignaturaOpcional(String codigo, String nombre, int credito, int creditoPrerequisito);
	/**
	 * this function insert a teacher in the container of teachers
	 * @param rut
	 * @param correo
	 * @param clave
	 * @param salario
	 * @return
	 */
	public boolean IngresarProfesor(String rut, String correo,String clave, String salario);
	/**
	 * this function insert a section in the container of sections
	 * @param numero
	 * @param codigo
	 * @param rut
	 * @return
	 */
	public boolean IngresarParalelo(String numero,String codigo, String rut);
	/**
	 * this function associates a subject with a student
	 * @param codigo
	 * @param correo
	 * @param notaFinal
	 * @return
	 */
	public boolean AsociarAsignaturaEstudiante(String codigo, String correo, double notaFinal);
	/**
	 * this function check a user
	 * @param correo
	 * @param clave
	 * @return
	 */
	public boolean VerificarUsuario(String 	correo, String clave);
	/**
	 * this function returns the type of the user
	 * @param correo
	 * @return
	 */
	public String retornarTipo(String correo);
	/**
	 * this function returns the subjects that the student can enroll
	 * @param correo
	 * @return
	 */
	public String RetornarAsignaturasAInscribir(String correo);
	/**
	 * this function returns the sections for a student
	 * @param codigo
	 * @return
	 */
	public String RetornaParalelosAlumnos(String codigo);
	/**
	 * this function sign on a subject for a student
	 * @param correo
	 * @param codigo
	 * @param paralelo
	 * @return
	 */
	public boolean InscripcionAsignatura(String correo, String codigo, String paralelo);
	/**
	 * this function returns the subjects sign on of the student
	 * @param correo
	 * @return
	 */
	public String RetornarAsignaturasInscritas(String correo);
	/**
	 * this function deletes a subject from a student
	 * @param correo
	 * @param codigo
	 * @return
	 */
	public boolean EliminarAsignatura(String correo, String codigo);
	/**
	 * this function returns the subjects of a teacher
	 * @param correo
	 * @return
	 */
	public String RetornaAsignaturasProfesor(String correo);
	/**
	 * this function returns the students of a section
	 * @param correo
	 * @param codigo
	 * @return
	 */
	public String RetornaAlumnos(String correo, String codigo);
	/**
	 * this function allows the teacher to enter a student's final grade
	 * @param nota
	 * @param correo_alumno
	 * @param codigo
	 * @return
	 */
	public boolean NotaFinal(double nota,String correo_alumno, String codigo);
	/**
	 * this function insert the graduates in their container
	 */
	public void Egresados();
	/**
	 * this function returns the data of the students
	 * @return
	 */
	public String datosAlumnos();
	/**
	 * this function returns the data of the graduates
	 * @return
	 */
	public String datosEgresados();
}
