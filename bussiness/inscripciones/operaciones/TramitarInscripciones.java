package bussiness.inscripciones.operaciones;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Optional;

import persistencia.PersistenceFactory;
import persistencia.competicion.CompeticionGateway;
import persistencia.competicion.CompeticionRecord;
import persistencia.inscripcion.InscripcionGateway;
import persistencia.inscripcion.InscripcionRecord;
import util.BusinessException;
import util.command.Command;

public class TramitarInscripciones implements Command<Void> {

	private File archivo;

	private CompeticionGateway cg = PersistenceFactory.forCompeticion();
	private InscripcionGateway ig = PersistenceFactory.forInscripcion();

	public TramitarInscripciones(File archivo) {
		this.archivo = archivo;
	}

	@Override
	public Void execute() throws BusinessException {
		BufferedReader fichero = null;
		String linea;
		String[] pago = null;

		try {
			fichero = new BufferedReader(new FileReader(archivo));
			linea = fichero.readLine();

			while (linea != null) {
				pago = linea.split(";");
				String dniAtleta = pago[0];
				String nombreCompeticion = pago[1];
				LocalDate fechaPago = LocalDate.parse(pago[2]);
				Double cuota = Double.parseDouble(pago[3]);

				if(comprobarInscripcionCompeticion(dniAtleta, nombreCompeticion)) {  //Es una inscripcion correcta ? Si es asi intento tramitar
					int idCompeticion = cg.findByNombre(nombreCompeticion).get().id_competicion;
					if (estaPendienteDePago(idCompeticion, dniAtleta)) { // Está pendiente de pago? Si es asi intento
							LocalDate fechaInscripcion = ig.findByDniYCompeticion(idCompeticion, dniAtleta).get().fecha_Inscripcion.toLocalDate();
						if (!hanPasado48h(fechaPago, fechaInscripcion)) {
							pagarCuota(cuota, idCompeticion, dniAtleta);
						} else if (hanPasado48h(fechaPago, fechaInscripcion)) {
							ig.anularInscripcion(idCompeticion, dniAtleta);
							ig.devolverDinero(idCompeticion, dniAtleta, cuota);
						}
					} else { // Si no, o esta ya inscrito o su inscripción esta anulada. Entonces le devuelvo
								// el dinero
						ig.devolverDinero(idCompeticion, dniAtleta, cuota);
					}
				}
				
				linea = fichero.readLine();

			}
			
			fichero.close();
		} catch (FileNotFoundException e) {
			throw new BusinessException("Fichero no encontrado");
		} catch (IOException e) {
			throw new BusinessException("Error en el procesamiento del fichero");
		}

		return null;
	}

	private boolean comprobarInscripcionCompeticion(String dniAtleta, String nombreCompeticion) {
		Optional<CompeticionRecord> competicion = cg.findByNombre(nombreCompeticion);
		
		if (competicion.isPresent()) {
			int idCompeticion = competicion.get().id_competicion;
			Optional<InscripcionRecord> inscripcion = ig.findByDniYCompeticion(idCompeticion, dniAtleta);

			if (inscripcion.isPresent()) {
				return true;
			}
		}
	
		return false;
	}

	private boolean hanPasado48h(LocalDate fechaPago, LocalDate fechaInscripcion) {
		long horas = Duration.between(fechaInscripcion.atStartOfDay(), fechaPago.atStartOfDay()).toHours();
		if (horas <= 48) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Metodo que realiza rodo el pago de la cuotas. Si la diferencia es 0 se le
	 * inscribe, si es mayor se le devuelve lo que sobre y se le inscribe y si es
	 * menor se anula la inscripcion y se le devuelve lo pagado
	 * 
	 * @param pagado
	 * @param idCompeticion
	 */
	private void pagarCuota(Double pagado, int idCompeticion, String dniAtleta) {
		CompeticionRecord cr = cg.findById(idCompeticion).get();
		InscripcionRecord ir = ig.findByDniYCompeticion(idCompeticion, dniAtleta).get();
		if (ir.cantidadDevolver == null) { //Si no tiene el campo creado, lo creo
			ir.cantidadDevolver = 0.0;
			ig.devolverDinero(idCompeticion, dniAtleta, ir.cantidadDevolver);
		}
		
		double diferencia = pagado - cr.cuota;
		if (diferencia == 0) {
			ig.tramitarInscripcion(idCompeticion, dniAtleta);
		} else if (diferencia > 0) {
			ig.tramitarInscripcion(idCompeticion, dniAtleta);
			ig.devolverDinero(idCompeticion, dniAtleta, diferencia + ir.cantidadDevolver);
		} else if (diferencia < 0) {
			ig.anularInscripcion(idCompeticion, dniAtleta);
			ig.devolverDinero(idCompeticion, dniAtleta, pagado + ir.cantidadDevolver);
		}
	}

	private boolean estaPendienteDePago(int idCompeticion, String dniAtleta) {
		InscripcionRecord inscripcion = ig.findByDniYCompeticion(idCompeticion, dniAtleta).get();

		if (inscripcion.estado.equals("PENDIENTE DE PAGO")) {
			return true;
		} else {
			return false;
		}
	}

}
