package bussiness.atleta.impl;

import java.util.List;
import java.util.Optional;

import bussiness.atleta.AtletaDto;
import bussiness.atleta.AtletaService;
import bussiness.atleta.InscripcionDTO;
import bussiness.atleta.commands.ComprobarAtleta;
import bussiness.atleta.commands.GetInscripcionesDeAtleta;
import bussiness.atleta.operaciones.PagarConTarjeta;
import bussiness.atleta.operaciones.PagarConTransferencia;
import util.BusinessException;
import util.command.CommandExecutor;

public class AtletaServiceImpl implements AtletaService {

	@Override
	public void pagarConTarjeta(String dni, String nombreCompeticion) throws BusinessException {
		CommandExecutor c = new CommandExecutor();
		c.execute(new PagarConTarjeta(dni, nombreCompeticion));
		

	}

	@Override
	public void pagarConTransferencia(String dni, String nombreCompeticion) throws BusinessException {
		CommandExecutor c = new CommandExecutor();
		c.execute(new PagarConTransferencia(dni, nombreCompeticion));
		
	}

	@Override
	public Optional<AtletaDto> comprobarAtleta(String text, boolean is_dni) throws BusinessException {
		CommandExecutor c = new CommandExecutor();
		return c.execute(new ComprobarAtleta(text, is_dni));
	}

	@Override
	public List<InscripcionDTO> getInscripcionesDeAtleta(String dni) throws BusinessException {
		CommandExecutor c = new CommandExecutor();
		return c.execute(new GetInscripcionesDeAtleta(dni));
	}
	
	
	
}
