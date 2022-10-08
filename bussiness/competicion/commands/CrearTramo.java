package bussiness.competicion.commands;

import bussiness.competicion.TramoDto;
import persistencia.PersistenceFactory;
import persistencia.competicion.CompeticionGateway;
import persistencia.competicion.TramoRecord;
import util.BusinessException;
import util.command.Command;

public class CrearTramo implements Command<Void>{
	
	private TramoDto dto;
	
	
	public CrearTramo(TramoDto dto) {
		super();
		this.dto = dto;
	}



	@Override
	public Void execute() throws BusinessException {
		CompeticionGateway gateway =  PersistenceFactory.forCompeticion();
		
		TramoRecord record = new TramoRecord();
		
		record.idCompeticion = dto.idCompeticion;
		record.numeroTramo = dto.numeroTramo;
		record.descripcion = dto.descripcion;
		gateway.addTramo(record);
		
		return null;
	}

}
