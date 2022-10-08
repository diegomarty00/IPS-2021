package bussiness.inscripciones;

import java.io.File;
import java.util.List;

import util.BusinessException;

public interface InscripcionService {
	JustificantePagoDTO generarJustificante(String dni, String nombreCompeticion) throws BusinessException;
	DatosPagoDTO generarDatosPago(String dni,  String nombreCompeticion) throws BusinessException;
	List<InscripcionDto> findByDni(String dni) throws BusinessException;
	void tramitarInscripciones(File archivo) throws BusinessException;
	
}
