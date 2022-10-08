package bussiness.inscripciones.operaciones;

import java.util.List;

import bussiness.inscripciones.InscripcionDto;
import persistencia.PersistenceFactory;
import persistencia.inscripcion.InscripcionGateway;
import util.BusinessException;
import util.DtoAssembler;
import util.command.Command;

public class FindByDni implements Command<List<InscripcionDto>> {

	String dni;
	
	public FindByDni(String dni) {
		super();
		this.dni = dni;
	}

	@Override
	public List<InscripcionDto> execute() throws BusinessException {
		InscripcionGateway ig = PersistenceFactory.forInscripcion();
		return DtoAssembler.toDtoListInscripcion(ig.findInscripcionesByDni(dni));
	}

}
