package persistencia.inscripcion;

import java.util.List;
import java.util.Optional;

import persistencia.Gateway;

public interface InscripcionGateway extends Gateway<InscripcionRecord> {
	
	Optional<InscripcionRecord> findByDni(String dni);
	
	Optional<InscripcionRecord> findByDni(String dni, int idCompetcion);
	
	List<InscripcionRecord> findAllByDni(String dni);
	
	void tramitarInscripcion(int idCompeticion, String dni);
	void anularInscripcion(int idCompeticion, String dni);
	void devolverDinero(int idCompeticion, String dni, double cantidad);
	Optional<InscripcionRecord> findByDniYCompeticion(int idCompeticion, String dni);
	List<InscripcionRecord> findByCompeticion(int id_competicion);
	void asignarDorsal(String dni_atleta,int id_competicion,int dorsal);
	
	
	void pagarInscripcionTarjeta(String dni, int idCompeticion);
	void pagarInscripcionTransferencia(String dni, int idCompeticion);
	Optional<JustificantePagoRecord> generarJustificante(String dni, int idCompeticion);
	Optional<DatosPagoRecord> generarDatosPago(String dni, int idCompeticion);

	List<InscripcionRecord> findInscripcionesByDni(String dni);
	
}
