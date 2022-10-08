package bussiness.inscripciones.operaciones;

import java.util.List;

import bussiness.inscripciones.InscripcionDto;
import persistencia.PersistenceFactory;
import persistencia.inscripcion.InscripcionGateway;
import persistencia.inscripcion.InscripcionRecord;
import util.BusinessException;
import util.DtoAssembler;
import util.command.Command;

public class FindInscripcion  implements Command<List<InscripcionDto>> {

	


	
	InscripcionGateway gateway = PersistenceFactory.forInscripcion();
	private int id_competicion;

	
	
	

	public FindInscripcion(int id_competicion) {
		super();
		this.id_competicion = id_competicion;

	}



	@Override
	public List<InscripcionDto> execute() throws BusinessException {
		List<InscripcionRecord> recorsd= gateway.findByCompeticion(id_competicion);
		
		
		return DtoAssembler.toDtoListInscripcion(recorsd);
	}
}
