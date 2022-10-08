package bussiness.atleta.commands;

import java.util.ArrayList;
import java.util.List;

import bussiness.atleta.AtletaDto;
import persistencia.PersistenceFactory;
import persistencia.atleta.AtletaGateway;
import persistencia.atleta.AtletaRecord;
import persistencia.competicion.CompeticionGateway;
import util.BusinessException;
import util.DtoAssembler;
import util.command.Command;

public class AsignarDorsalesCompeticion implements Command<List<AtletaDto>>{

	private int competicion_id;


	public AsignarDorsalesCompeticion(int competicion_id) {
		super();
		this.competicion_id = competicion_id;
	}
	
	
	@Override
	public List<AtletaDto> execute() throws BusinessException {
		AtletaGateway altGateway = PersistenceFactory.forAtleta();
		List<AtletaRecord> atletas = altGateway.findByCompeticionId(competicion_id);
		List<AtletaDto> result = new ArrayList<AtletaDto>();
		CompeticionGateway compeGateway = PersistenceFactory.forCompeticion();
		int reservados = compeGateway.findById(competicion_id).get().reservados;
		int dorsal = reservados+1;
		AtletaDto dto = new AtletaDto();
		
		for (AtletaRecord atletaRecord : atletas) {
			altGateway.setDorsal(dorsal,atletaRecord.id); 
			dorsal++;
			dto= DtoAssembler.toAtletaDto(atletaRecord);
			dto.dorsal = dorsal;
		}
		
		
		
		
		
		return null;
	}

}
