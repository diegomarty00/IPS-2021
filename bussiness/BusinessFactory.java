package bussiness;

import bussiness.atleta.AtletaService;
import bussiness.atleta.impl.AtletaServiceImpl;
import bussiness.competicion.CompeticionService;
import bussiness.competicion.impl.CompeticionServiceImpl;
import bussiness.inscripciones.InscripcionService;
import bussiness.inscripciones.impl.InscripcionServiceImpl;

public class BusinessFactory {



	public static AtletaService forAtletaService() {
		return new AtletaServiceImpl();
	}
	
	public static InscripcionService forInscripcionService() {
		return new InscripcionServiceImpl();
	}

	public static CompeticionService forCompeticionService() {
		return new CompeticionServiceImpl();
	}

}

