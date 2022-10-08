package bussiness.competicion.commands;

import java.util.Optional;

import bussiness.atleta.CompeticionDTO;
import persistencia.competicion.CompeticionGateway;
import persistencia.competicion.CompeticionRecord;
import persistencia.competicion.impl.CompleticionGatewayImpl;
import util.BusinessException;
import util.DtoAssembler;
import util.command.Command;

public class FindById implements Command<Optional<CompeticionDTO>> {
	private int idCompeticion;
	private CompeticionGateway cg = new CompleticionGatewayImpl();
	public FindById(int idCompeticion) {
		this.idCompeticion = idCompeticion;
	}


	@Override
	public Optional<CompeticionDTO> execute() throws BusinessException {
		Optional<CompeticionRecord> oc = cg.findById(idCompeticion);
		
		return DtoAssembler.toDtoCompeticion(oc);
		
	}

}
