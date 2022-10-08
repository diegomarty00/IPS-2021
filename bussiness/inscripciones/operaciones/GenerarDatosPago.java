package bussiness.inscripciones.operaciones;

import bussiness.inscripciones.DatosPagoDTO;
import persistencia.PersistenceFactory;
import persistencia.competicion.CompeticionGateway;
import persistencia.competicion.CompeticionRecord;
import persistencia.inscripcion.InscripcionGateway;
import util.BusinessException;
import util.DtoAssembler;
import util.command.Command;

public class GenerarDatosPago implements Command<DatosPagoDTO> {
	
	public String dni;
	public String nombreCompeticion;

	public GenerarDatosPago(String dni, String nombreCompeticion) {
		this.dni = dni;
		this.nombreCompeticion = nombreCompeticion;
	}

	@Override
	public DatosPagoDTO execute() throws BusinessException {
		InscripcionGateway ig = PersistenceFactory.forInscripcion();
		CompeticionGateway cg = PersistenceFactory.forCompeticion();
		CompeticionRecord cr = cg.findByNombre(nombreCompeticion).get();
		int idCompeticion = cr.id_competicion;
		DatosPagoDTO dto = DtoAssembler.toDtoPago(ig.generarDatosPago(dni, idCompeticion)).orElseThrow(()-> new BusinessException("No se ha podido generar el justificante"));
		return dto;
	}
	
	
	
}
