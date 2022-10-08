package bussiness.clasificacion;

import java.sql.Time;
import java.time.LocalDate;

public class ClasificacionDTO {

	public int id_competicion;
	public String dni_atleta;
	public Time tiempoInicial;
	public Time tiempoFinal;
	public String estado;
	
	public String tiempos_tramos;
	public int id_club;
	public String nombre_club;
	public int distancia;
	public String resultadoTiempo;
	public String posicion;
	
	public String sexo;
	public String nombre_atleta;
	public String apellidos_atleta;
	
	public String nombre_competicion;
	public String tipo;
	public LocalDate fecha_competicion;
	
}