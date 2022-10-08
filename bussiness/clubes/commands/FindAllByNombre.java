package bussiness.clubes.commands;

import java.util.List;

import persistencia.clubes.ClubGateway;
import persistencia.clubes.impl.ClubGatewayImpl;
import util.BusinessException;
import util.command.Command;

public class FindAllByNombre implements Command<List<String>> {

	ClubGateway cg = new ClubGatewayImpl();
	@Override
	public List<String> execute() throws BusinessException {
		return cg.findAllByNombre();
	}

}
