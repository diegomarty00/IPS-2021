package bussiness.clubes.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import bussiness.atleta.AtletaDto;
import bussiness.clubes.ClubDto;
import bussiness.clubes.ClubService;
import bussiness.clubes.commands.FindAllByNombre;
import bussiness.clubes.commands.FindByNombre;
import bussiness.clubes.commands.LeerFichero;
import bussiness.clubes.commands.UpdateDeuda;
import util.BusinessException;
import util.command.CommandExecutor;

public class ClubServiceImpl implements ClubService{

	private CommandExecutor ex = new CommandExecutor();
	@Override
	public List<String> findClubesByNombre() throws BusinessException {
		return ex.execute(new FindAllByNombre());
	}
	
	@Override
	public List<AtletaDto> leerFichero(File archivo) throws BusinessException {
		return ex.execute(new LeerFichero(archivo));
	}

	@Override
	public void updateDeuda(Double deuda, String nombre) throws BusinessException {
		new UpdateDeuda(deuda, nombre).execute();
		
	}

	@Override
	public Optional<ClubDto> findByNombre(String nombre) {
		return new FindByNombre(nombre).execute();
	}

}
