package persistencia.clubes;

import java.io.File;
import java.util.List;
import java.util.Optional;

import bussiness.atleta.AtletaDto;
import persistencia.Gateway;

public interface ClubGateway extends Gateway<ClubRecord> {
	List<String> findAllByNombre();
	void updateDeuda(Double deuda, String nombre);
	Optional<ClubRecord> findByNombre(String nombre);

}
