package bussiness.atleta;

import java.time.LocalDate;

public class AtletaDto {

	
	public String DNI;
	public String correo;
	
	public String nombre;
	public String apellido;
	
	public String genero;
	public LocalDate fechaNacimiento;
	
	public String categoria;
	
	public LocalDate fechaDeInscripcion;
	public String estadoInscripcion;
	public int dorsal;
	public int id_club;
	
	
	public String[] toList() {
		String result[] =  {DNI,nombre,apellido,correo,genero,fechaNacimiento.toString()};
		return result;
		
	}
	
	public String toListaTexto() {
		return  DNI +"	"+nombre+" "+apellido;
	}
	
	@Override
	public String toString() {
		return  DNI +", Dorsal ->" + dorsal;
	}
	
	public String mostrarDatos() {
		return DNI + " " + nombre + " " + apellido + " " + fechaNacimiento.toString() + " " + genero + " " + correo;
	}
	
	
	
	
	
}
