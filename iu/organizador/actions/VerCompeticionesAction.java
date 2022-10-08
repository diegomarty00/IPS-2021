package iu.organizador.actions;

import java.util.List;

import bussiness.atleta.CompeticionDTO;
import bussiness.competicion.commands.VerCompeticionesPascu;

public class VerCompeticionesAction {
	
	public List<CompeticionDTO> execute(){
		
	
		
		new VerCompeticionesPascu();
		return VerCompeticionesPascu.execute();
	}
}
