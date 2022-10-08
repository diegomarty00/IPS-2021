package persistencia;

import persistencia.atleta.AtletaGateway;
import persistencia.atleta.impl.AtletaGatewayImpl;
import persistencia.clasificaciones.ClasificacionesGateway;
import persistencia.clasificaciones.impl.ClasificacionesGatewayImpl;
import persistencia.competicion.CompeticionGateway;
import persistencia.competicion.impl.CompleticionGatewayImpl;
import persistencia.inscripcion.InscripcionGateway;
import persistencia.inscripcion.impl.InscripcionGatewayImpl;

public class PersistenceFactory {

	public static InscripcionGateway forInscripcion() {
		return new InscripcionGatewayImpl();
	}
	public static AtletaGateway forAtleta() {
		return new AtletaGatewayImpl();
	}
	public static ClasificacionesGateway forClasificacion() {
		return new ClasificacionesGatewayImpl();
	}
	public static CompeticionGateway forCompeticion() {
		return new CompleticionGatewayImpl();
	}
}

