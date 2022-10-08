package bussiness.atleta;

import java.util.List;
import java.util.Optional;

import util.BusinessException;

public interface AtletaService {
	void pagarConTarjeta(String dni, String nombreCompeticion) throws BusinessException;
	void pagarConTransferencia(String dni, String nombreCompeticion) throws BusinessException;
	
	Optional<AtletaDto> comprobarAtleta(String text, boolean is_dni ) throws BusinessException;
	List<InscripcionDTO> getInscripcionesDeAtleta(String dni)throws BusinessException;
	
}
