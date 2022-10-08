package bussiness.atleta.operaciones;

import java.util.Optional;

import persistencia.PersistenceFactory;
import persistencia.atleta.AtletaGateway;
import persistencia.atleta.AtletaRecord;
import persistencia.competicion.CompeticionGateway;
import persistencia.competicion.CompeticionRecord;
import persistencia.inscripcion.InscripcionGateway;
import persistencia.inscripcion.InscripcionRecord;
import util.BusinessException;
import util.command.Command;

/**
 * 
 * @author Enol
 * Clase con las operaciones para realizar el pago con tarjeta de crédito. El atleta pasará a estado inscrito
 */
public class PagarConTarjeta implements Command<Void> {
	
	public String dni;
	public String nombreCompeticion;
	public PagarConTarjeta(String dni, String nombreCompeticion) {
		this.dni = dni;
		this.nombreCompeticion = nombreCompeticion;
	}
	
	@Override
	public Void execute() throws BusinessException {
		
		InscripcionGateway ig = PersistenceFactory.forInscripcion();
		AtletaGateway ag = PersistenceFactory.forAtleta();
		CompeticionGateway cg = PersistenceFactory.forCompeticion();
		Optional<AtletaRecord> atleta = ag.getByDni(dni);
		Optional<CompeticionRecord> competicion = cg.findByNombre(nombreCompeticion);
		if (atleta.isEmpty()) {
			throw new BusinessException("No existe un atleta con ese DNI");
		}
		
		if (competicion.isEmpty()) {
			throw new BusinessException("No existe una competicion con ese nombre");
		}
		
		int idCompeticion = competicion.get().id_competicion; 
		
		Optional<InscripcionRecord> inscripcion = ig.findByDniYCompeticion(idCompeticion, dni);
		
		if (inscripcion.isEmpty()) {
			throw new BusinessException("No existe una inscripción asociada a ese atleta y competición");
		}
		
		InscripcionRecord ir = inscripcion.get();
		
		if(ir.estado.toUpperCase().equals("INSCRITO") || ir.estado.toUpperCase().equals("PENDIENTE DE PAGO")) {
			throw new BusinessException("El atleta ya ha pagado su inscripción o está pendiente de pago");
		}
		else if (ir.estado.toUpperCase().equals("ANULADA")) {
			throw new BusinessException("No se puede pagar una inscripción anulada");
		}
		else if(ir.estado.equals("NO PAGADO")) {
			ig.pagarInscripcionTarjeta(dni, idCompeticion);
		}	
		return null;
		
	}
}
