package bussiness.clubes.commands;

import java.util.Optional;

import bussiness.clubes.ClubDto;
import persistencia.clubes.ClubGateway;
import persistencia.clubes.ClubRecord;
import persistencia.clubes.impl.ClubGatewayImpl;

public class FindByNombre {
	
	private String nombre;
	private ClubGateway cg = new ClubGatewayImpl();
	public FindByNombre(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	public Optional<ClubDto> execute() {
		Optional<ClubRecord> oc = cg.findByNombre(nombre);
		
		if (oc.isPresent()) {
			ClubDto dto = new ClubDto();
			dto.nombreClub = oc.get().nombreClub;
			dto.totalPagar = oc.get().totalPagar;
			dto.id_club = oc.get().id_club;
			return Optional.of(dto);
		}
		else {
			return Optional.empty();
		}
	}
	

}
