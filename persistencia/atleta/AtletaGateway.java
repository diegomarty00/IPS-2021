package persistencia.atleta;

import java.util.List;
import java.util.Optional;

import persistencia.Gateway;

public interface AtletaGateway extends Gateway<AtletaRecord>{

	
	
	public Optional<AtletaRecord> getByDni(String dni);
	public Optional<AtletaRecord> getByEmail(String email);

	public List<AtletaRecord> findByCompeticionId(int competicion_id);
	public void setDorsal(int dorsal, String id);

	public List<AtletaRecord> getByCompeticion_id(int id_competicion);

}
