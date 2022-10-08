package bussiness.competicion.impl;

import java.util.Optional;

import bussiness.atleta.CompeticionDTO;
import bussiness.competicion.CompeticionService;
import bussiness.competicion.TramoDto;
import bussiness.competicion.commands.CrearCompeticion;
import bussiness.competicion.commands.CrearTramo;
import bussiness.competicion.commands.FindById;
import util.BusinessException;
import util.command.CommandExecutor;

public class CompeticionServiceImpl implements CompeticionService {

	@Override
	public void addCompeticion(CompeticionDTO dto) throws BusinessException {
		CommandExecutor c = new CommandExecutor();
		c.execute(new CrearCompeticion(dto));

	}

	@Override
	public void addTramo(TramoDto dto) throws BusinessException {
		CommandExecutor c = new CommandExecutor();
		c.execute(new CrearTramo(dto));
		
	}

	@Override
	public Optional<CompeticionDTO> findById(int idCompeticion) throws BusinessException {
		// TODO Auto-generated method stub
		CommandExecutor c = new CommandExecutor();
		return c.execute(new FindById(idCompeticion));
	}

}
