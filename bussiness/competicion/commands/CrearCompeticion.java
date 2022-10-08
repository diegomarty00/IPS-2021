package bussiness.competicion.commands;

import bussiness.atleta.CompeticionDTO;
import persistencia.PersistenceFactory;
import persistencia.competicion.CompeticionGateway;
import persistencia.competicion.CompeticionRecord;
import util.BusinessException;
import util.command.Command;

public class CrearCompeticion implements Command<Void>{
	private CompeticionDTO dto ;
	public CrearCompeticion(CompeticionDTO dto) throws BusinessException {
		if (dto == null) {
			throw new BusinessException("Dto invalido");
		}
		this.dto=dto;
	}
	
	
	@Override
	public Void execute() throws BusinessException {
		CompeticionGateway gateway =  PersistenceFactory.forCompeticion();
			
		CompeticionRecord record = new CompeticionRecord();
		
		record.id_competicion= dto.id_competicion;
		record.cuota= dto.cuota;
		record.distancia= dto.distancia;
		record.plazas= dto.plazas;
		record.fecha= dto.fecha;
		record.fecha_fin_inscripcion= dto.fecha_fin_inscripcion;
		record.fecha_inicio_inscripcion= dto.fecha_inicio_inscripcion;
		record.nombre= dto.nombre;
		record.tipo= dto.tipo;
		record.dorsalesReservados = dto.dorsalesReservados;
		record.numero_tramos = dto.numero_tramos;
		gateway.add(record);
		
		
		
		
		return null;
	}

}
