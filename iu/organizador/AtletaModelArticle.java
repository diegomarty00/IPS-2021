package iu.organizador;

import java.time.LocalDate;

import bussiness.atleta.AtletaDto;

public class AtletaModelArticle {

	
	public String DNI;
	
	
	public String nombre;
	public String apellido;
	
	public String genero;
	public LocalDate fechaNacimiento;
	
	public String categoria;
	
	public LocalDate fechaDeInscripcion;
	public String estadoInscripcion;
	
	
	@Override
	public String toString() {
		return "DNI=" + DNI + ", nombre=" + nombre + ", apellido=" + apellido + ", genero=" + genero
				+ ", fechaNacimiento=" + fechaNacimiento + ", categoria=" + categoria + ", fechaDeInscripcion="
				+ fechaDeInscripcion + ", estadoInscripcion=" + estadoInscripcion;
	}


	public AtletaModelArticle(AtletaDto dto) {
		setWithDto(dto);
	}
	
	
	public void setWithDto(AtletaDto dto) {
		
		DNI = dto.DNI;
		nombre = dto.nombre;
		apellido = dto.apellido;
		genero = dto.genero;
		fechaNacimiento = dto.fechaNacimiento;
		categoria = dto.categoria;
		fechaDeInscripcion = dto.fechaDeInscripcion;
		estadoInscripcion = dto.estadoInscripcion;
		
		
		
	}
	
	
	
	
	public AtletaModelArticle(String dNI, String nombre, String apellido, String genero, LocalDate fechaNacimiento,
			String categoria, LocalDate fechaDeInscripcion, String estadoInscripcion) {
		super();
			DNI = dNI;
		this.nombre = nombre;
		this.apellido = apellido;
		this.genero = genero;
		this.fechaNacimiento = fechaNacimiento;
		this.categoria = categoria;
		this.fechaDeInscripcion = fechaDeInscripcion;
		this.estadoInscripcion = estadoInscripcion;
	}
	
	
	
	
	
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public LocalDate getFechaDeInscripcion() {
		return fechaDeInscripcion;
	}
	public void setFechaDeInscripcion(LocalDate fechaDeInscripcion) {
		this.fechaDeInscripcion = fechaDeInscripcion;
	}
	public String getEstadoInscripcion() {
		return estadoInscripcion;
	}
	public void setEstadoInscripcion(String estadoInscripcion) {
		this.estadoInscripcion = estadoInscripcion;
	}
	
	
	public String[] getColumnNames() {

	String result[] = {"DNI","Nombre","Genero","Nacimiento","Categoria","Fecha Inscripcion","Estado Inscripcion"};

	
		return result;
	}
	
	private String getNacimientoString() {
		if(fechaNacimiento!= null)
			return fechaNacimiento.toString();
		else
			return "NAN";
	}
	private String getFechaDeInscripcionString() {
		if(fechaDeInscripcion!= null)
			return fechaDeInscripcion.toString();
		else
			return "NAN";
	}

	public String[] toList() {
		String result[] = {DNI,nombre,apellido,genero,getNacimientoString(),categoria,getFechaDeInscripcionString()
				,estadoInscripcion};
		
		return result;
	}
}
