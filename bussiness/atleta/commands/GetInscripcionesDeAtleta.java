package bussiness.atleta.commands;

import java.util.LinkedList;
import java.util.List;

import bussiness.atleta.InscripcionDTO;
import persistencia.PersistenceFactory;
import persistencia.inscripcion.InscripcionGateway;
import persistencia.inscripcion.InscripcionRecord;
import util.BusinessException;
import util.command.Command;

public class GetInscripcionesDeAtleta implements Command<List<InscripcionDTO>> {

	private String id;
	
	
	public GetInscripcionesDeAtleta(String id) {
		super();
		this.id = id;
	}


	@Override
	public List<InscripcionDTO> execute() throws BusinessException {
	//	CompeticionGateway compeGateway = PersistenceFactory.forCompeticion();
		InscripcionGateway insGateway = PersistenceFactory.forInscripcion();
		List<InscripcionDTO> result = new LinkedList<InscripcionDTO>();
		List<InscripcionRecord> records = insGateway.findAllByDni(id);
		for (InscripcionRecord inscripcionRecord : records) {
			InscripcionDTO dto = new InscripcionDTO();
			dto.categoria= inscripcionRecord.categoria;
			dto.dni_Atleta= inscripcionRecord.dni_Atleta;
			dto.estado= inscripcionRecord.estado;
			dto.id_competicion= inscripcionRecord.id_competicion;
			dto.nombre_competion= inscripcionRecord.nombre_competicion;
			dto.fecha_Inscripcion = inscripcionRecord.fecha_Inscripcion;
			
			result.add(dto);
			
		}
		
		
		
		return result;
	}

}
