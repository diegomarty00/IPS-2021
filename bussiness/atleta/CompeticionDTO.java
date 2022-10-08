package bussiness.atleta;

import java.time.LocalDate;

public class CompeticionDTO {
	
	public int id_competicion;
	public String nombre;
	public LocalDate fecha;
	public String tipo;
	public int distancia;
	public double cuota;
	public int numero_tramos;
	public LocalDate fecha_inicio_inscripcion;
	public LocalDate fecha_fin_inscripcion;
	public int dorsalesReservados;
	public int  plazas;
	
	@Override
	public String toString() {
		return nombre;
	}
	
	
	
	
	
}
