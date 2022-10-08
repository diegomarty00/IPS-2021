package bussiness.clubes.commands;

import bussiness.clubes.ClubService;
import bussiness.clubes.impl.ClubServiceImpl;
import persistencia.clubes.ClubGateway;
import persistencia.clubes.impl.ClubGatewayImpl;
import util.BusinessException;
import util.command.Command;

public class UpdateDeuda {
	private ClubGateway cg = new ClubGatewayImpl();
	private Double deuda;
	private String nombre;
	
	
	
	public UpdateDeuda(Double deuda, String nombre) {
		super();
		this.deuda = deuda;
		this.nombre = nombre;
	}


	public Void execute() throws BusinessException {
		cg.updateDeuda(deuda, nombre);
		return null;
	}

}
