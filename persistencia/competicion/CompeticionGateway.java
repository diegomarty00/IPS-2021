package persistencia.competicion;

import java.util.Optional;

import persistencia.Gateway;

public interface CompeticionGateway extends Gateway<CompeticionRecord> {
	Optional<CompeticionRecord> findByNombre(String nombreCompeticion);
	void addTramo(TramoRecord record);

}
