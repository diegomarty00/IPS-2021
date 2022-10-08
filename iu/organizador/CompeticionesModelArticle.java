package iu.organizador;

import java.time.LocalDate;

import bussiness.atleta.CompeticionDTO;

public class CompeticionesModelArticle {

	
	public int id;
	public String nombre;
	public LocalDate fecha;
	public String tipo;
	public int distancia;
	public double cuota;
	public int numero_paticipantes;
	
	public CompeticionesModelArticle(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	public CompeticionesModelArticle(CompeticionDTO dto) {
		super();
		setWithCompetitionDTO(dto);
	}
	@Override
	public String toString() {
		return "ID-" + id + " --> Competición: " + nombre + " de " + tipo + " del " + fecha + " (" + cuota + "€ - "+distancia+ "metros - " + numero_paticipantes + " atletas)";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getDistancia() {
		return distancia;
	}
	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}
	public double getCuota() {
		return cuota;
	}
	public void setCuota(double cuota) {
		this.cuota = cuota;
	}
	
	public int getNumero_paticipantes() {
		return numero_paticipantes;
	}
	public void setNumero_paticipantes(int numero_paticipantes) {
		this.numero_paticipantes = numero_paticipantes;
	}
	public void setWithCompetitionDTO(CompeticionDTO dto) {
		setId(dto.id_competicion);
		setNombre(dto.nombre);
		setTipo(dto.tipo);
		setFecha(dto.fecha);
		setDistancia(dto.distancia);
		setCuota(dto.cuota);
		numero_paticipantes=dto.plazas;
	}
	
	
	
}
