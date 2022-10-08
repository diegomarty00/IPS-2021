package bussiness.atleta.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import bussiness.atleta.AtletaDto;
import persistencia.PersistenceFactory;
import persistencia.clasificaciones.ClasificacionesGateway;
import persistencia.clasificaciones.ClasificacionesRecord;
import persistencia.competicion.CompeticionGateway;
import persistencia.competicion.CompeticionRecord;
import persistencia.inscripcion.InscripcionGateway;
import persistencia.inscripcion.InscripcionRecord;
import util.BusinessException;
import util.command.Command;

public class AsignarDorsales implements Command<List<AtletaDto>> {

	private int id_competicion;

	private InscripcionGateway gateway = PersistenceFactory.forInscripcion();
	
	private CompeticionGateway clas = PersistenceFactory.forCompeticion();
	public AsignarDorsales(int id_competicion) {
		super();
		this.id_competicion = id_competicion;
	}

	@Override
	public List<AtletaDto> execute() throws BusinessException {
		Optional<CompeticionRecord> clasificacion = clas.findById(id_competicion);
		List<AtletaDto> dtos = new ArrayList<>();
		if (!clasificacion.isEmpty()) {
			int dorsales = clasificacion.get().dorsales_resrvados;
			List<InscripcionRecord> insc = gateway.findByCompeticion(id_competicion);

			if (insc != null && !insc.isEmpty()) {
				AtletaDto dto;
				int i = dorsales;
				for (InscripcionRecord inscripcionRecord : insc) {
					dto = new AtletaDto();
					gateway.asignarDorsal(inscripcionRecord.dni_Atleta, id_competicion, i);
					dto.DNI = inscripcionRecord.dni_Atleta;
					dto.dorsal = i;

					dtos.add(dto);

					i++;
				}
			}

		}
		return dtos;
	}

}
