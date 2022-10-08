package iu.organizador.actions;

import java.util.List;

import bussiness.atleta.AtletaDto;
import bussiness.atleta.commands.SacarInscripcionesDeCompeticion;

public class VerAtletasDeCompeticionAction {

	
	private int competicion;
	
	public VerAtletasDeCompeticionAction(int i) {
		setCompeticion(i);
	}
	
	
	public List<AtletaDto> execute() {
		
		if(competicion != 0) {
		
			
		return new SacarInscripcionesDeCompeticion(competicion).execute();
	
				
		}
		else {
			throw new RuntimeException();
		}
	}
	
	private void setCompeticion(int i) {
		
		competicion=i;
	}
	
}
