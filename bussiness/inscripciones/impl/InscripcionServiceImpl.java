package bussiness.inscripciones.impl;

import java.io.File;
import java.util.List;

import bussiness.inscripciones.DatosPagoDTO;
import bussiness.inscripciones.InscripcionDto;
import bussiness.inscripciones.InscripcionService;
import bussiness.inscripciones.JustificantePagoDTO;
import bussiness.inscripciones.operaciones.FindByDni;
import bussiness.inscripciones.operaciones.GenerarDatosPago;
import bussiness.inscripciones.operaciones.GenerarJustificantePago;
import bussiness.inscripciones.operaciones.TramitarInscripciones;
import util.BusinessException;
import util.command.CommandExecutor;

public class InscripcionServiceImpl implements InscripcionService {

	@Override
	public JustificantePagoDTO generarJustificante(String dni, String nombreCompeticion) throws BusinessException {
		CommandExecutor c = new CommandExecutor();
		return c.execute(new GenerarJustificantePago(dni, nombreCompeticion));
	}

	@Override
	public DatosPagoDTO generarDatosPago(String dni, String nombreCompeticion) throws BusinessException {
		CommandExecutor c = new CommandExecutor();
		return c.execute(new GenerarDatosPago(dni, nombreCompeticion));
	}

	@Override
	public List<InscripcionDto> findByDni(String dni) throws BusinessException {
		CommandExecutor c = new CommandExecutor();
		return c.execute(new FindByDni(dni));
	}

	@Override
	public void tramitarInscripciones(File archivo) throws BusinessException {
		CommandExecutor c = new CommandExecutor();
		c.execute(new TramitarInscripciones(archivo));
		
	}
	

}
