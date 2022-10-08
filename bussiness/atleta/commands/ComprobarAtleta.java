package bussiness.atleta.commands;

import java.util.Optional;

import bussiness.atleta.AtletaDto;
import persistencia.PersistenceFactory;
import persistencia.atleta.AtletaGateway;
import util.BusinessException;
import util.DtoAssembler;
import util.command.Command;

public class ComprobarAtleta implements Command<Optional<AtletaDto>>  {

	private String text;
	private boolean is_dni;
	
	private AtletaGateway gateway = PersistenceFactory.forAtleta();
	public ComprobarAtleta(String text, boolean is_dni) {
		super();
		this.text = text;
		this.is_dni = is_dni;
	}




	@Override
	public Optional<AtletaDto> execute() throws BusinessException {
	
		
		
		if(is_dni) {
			return DtoAssembler.toAtleta(gateway.getByDni(text));
		}else {
			return DtoAssembler.toAtleta(gateway.getByEmail(text));
		}
	}

}
