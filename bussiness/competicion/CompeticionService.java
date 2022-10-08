package bussiness.competicion;

import java.util.Optional;

import bussiness.atleta.CompeticionDTO;
import util.BusinessException;

public interface CompeticionService {

	
	void addCompeticion(CompeticionDTO dto) throws BusinessException;
	void addTramo(TramoDto dto) throws BusinessException;
	Optional<CompeticionDTO> findById(int idCompeticion) throws BusinessException;
	
}
