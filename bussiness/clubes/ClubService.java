package bussiness.clubes;

import java.io.File;
import java.util.List;
import java.util.Optional;

import bussiness.atleta.AtletaDto;
import util.BusinessException;

public interface ClubService {
	List<String> findClubesByNombre() throws BusinessException;
	List<AtletaDto> leerFichero(File archivo) throws BusinessException;
	void updateDeuda(Double deuda, String nombre) throws BusinessException;
	Optional<ClubDto> findByNombre(String nombre);

}
