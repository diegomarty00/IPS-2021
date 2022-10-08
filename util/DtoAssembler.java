package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import bussiness.atleta.AtletaDto;
import bussiness.atleta.CompeticionDTO;
import bussiness.clubes.ClubDto;
import bussiness.inscripciones.DatosPagoDTO;
import bussiness.inscripciones.InscripcionDto;
import bussiness.inscripciones.JustificantePagoDTO;
import persistencia.atleta.AtletaRecord;
import persistencia.clubes.ClubRecord;
import persistencia.competicion.CompeticionRecord;
import persistencia.inscripcion.DatosPagoRecord;
import persistencia.inscripcion.InscripcionRecord;
import persistencia.inscripcion.JustificantePagoRecord;

public class DtoAssembler {

	public static Optional<InscripcionDto> toDto(Optional<InscripcionRecord> arg) {
		Optional<InscripcionDto> result = arg.isEmpty()?Optional.ofNullable(null)
				:Optional.ofNullable(toInscripcionDTO(arg.get()));
		return result;
	}
	
	public static Optional<DatosPagoDTO> toDtoPago(Optional<DatosPagoRecord> arg) {
		Optional<DatosPagoDTO> result = arg.isEmpty()?Optional.ofNullable(null)
				:Optional.ofNullable(toDatosPagoDTO(arg.get()));
		return result;
	}
	
	public static Optional<CompeticionDTO> toDtoCompeticion(Optional<CompeticionRecord> arg) {
		Optional<CompeticionDTO> result = arg.isEmpty()?Optional.ofNullable(null)
				:Optional.ofNullable(toDtoCompeticion(arg.get()));
		return result;
	}
	
	public static Optional<JustificantePagoDTO> toDtoJustificante (Optional<JustificantePagoRecord> arg) {
		Optional<JustificantePagoDTO> result = arg.isEmpty()?Optional.ofNullable(null)
				:Optional.ofNullable(toJustificantePagoDTO(arg.get()));
		return result;
	}
	
	private static InscripcionDto toInscripcionDTO(InscripcionRecord arg) {

		InscripcionDto result = new InscripcionDto();
		result.dni = arg.dni_Atleta;
		result.categoria = arg.categoria;
		result.estado = arg.estado;
		result.id_Competicion = arg.id_competicion;
		return result;
	}
	
	private static ClubDto toClubDto(ClubRecord arg) {
		ClubDto result = new ClubDto();
		result.nombreClub = arg.nombreClub;
		result.totalPagar = arg.totalPagar;
		return result;
	}

	private static DatosPagoDTO toDatosPagoDTO(DatosPagoRecord arg) {

		DatosPagoDTO result = new DatosPagoDTO();
		result.cuota = arg.cuota;
		return result;
	}
	
	private static JustificantePagoDTO toJustificantePagoDTO(JustificantePagoRecord arg) {

		JustificantePagoDTO result = new JustificantePagoDTO();
		result.cuota = arg.cuota;
		result.dni_atleta = arg.dni_atleta;
		result.fecha_Inscripcion = arg.fecha_Inscripcion;
		result.nombre_Competicion = arg.nombre_Competicion;
		return result;
	}
	
	
	public static Optional<AtletaDto> toAtleta(Optional<AtletaRecord> arg) {
		Optional<AtletaDto> result = arg.isEmpty()?Optional.ofNullable(null)
				:Optional.ofNullable(toAtletaDto(arg.get()));
		return result;
	}
	public static AtletaDto toAtletaDto(AtletaRecord record) {
		AtletaDto dto = new AtletaDto();
		
		dto.DNI  =   record.dni;
		dto.nombre  =   record.nombre;
		dto.apellido  =   record.apellidos;
		dto.genero  =   record.sexo;
		dto.fechaNacimiento  =   record.fechaNacimieto;
		
		
		return dto;
		
	}
	
	public static List<InscripcionDto> toDtoListInscripcion(List<InscripcionRecord> arg) {
		List<InscripcionDto> result = new ArrayList<InscripcionDto> ();
		for (InscripcionRecord ir : arg) 
			result.add(toInscripcionDTO(ir));
		return result;
	}
	
	public static List<ClubDto> toDtoListClubes(List<ClubRecord> arg) {
		List<ClubDto> result = new ArrayList<ClubDto> ();
		for (ClubRecord cr : arg) 
			result.add(toClubDto(cr));
		return result;
	}
	
	public static CompeticionDTO toDtoCompeticion(CompeticionRecord record) {
		CompeticionDTO dto = new CompeticionDTO();
		dto.id_competicion = record.id_competicion;
		dto.nombre = record.nombre;
		dto.fecha = record.fecha;
		dto.tipo = record.tipo;
		dto.distancia = record.distancia;
		dto.cuota = record.cuota;
		dto.numero_tramos = record.numero_tramos;
		dto.dorsalesReservados = record.dorsales_resrvados;
		dto.plazas = record.plazas;
		return dto;
	}
	

	
	
	
	
	
	
	
}