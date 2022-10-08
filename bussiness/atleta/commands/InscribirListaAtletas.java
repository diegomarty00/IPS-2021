package bussiness.atleta.commands;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bussiness.atleta.AtletaDto;
import persistencia.PersistenceFactory;
import persistencia.RecordAsembler;
import persistencia.atleta.AtletaGateway;
import persistencia.competicion.CompeticionGateway;
import persistencia.inscripcion.InscripcionGateway;
import persistencia.inscripcion.InscripcionRecord;
import util.BusinessException;
import util.DtoAssembler;
import util.command.Command;

public class InscribirListaAtletas  implements Command<List<AtletaDto>> {

	private List<AtletaDto> atletas;
	private int id_competicion;
	private int id_club;
	
	
	private AtletaGateway atleGat = PersistenceFactory.forAtleta();
	private InscripcionGateway gateway = PersistenceFactory.forInscripcion();
	private CompeticionGateway clas = PersistenceFactory.forCompeticion();
	
	public InscribirListaAtletas(List<AtletaDto> atletas,int id_competicion2, int id_club) {
		super();
		if(atletas != null && !atletas.isEmpty())
		
		
		this.atletas = atletas;
		this.id_competicion = id_competicion2;
		this.id_club = id_club;
	}
	
	public InscribirListaAtletas(List<AtletaDto> atletas,int id_competicion2) {
		super();
		if(atletas != null && !atletas.isEmpty())
		
		
		this.atletas = atletas;
		this.id_competicion = id_competicion2;
	}


	@Override
	public List<AtletaDto> execute() throws BusinessException {
		//Por cada dto crear Atleta e Inscripcion
		//comprobar que no exista atleta
		List<AtletaDto> result = new ArrayList<AtletaDto>();
		int counter= 0;
		for (AtletaDto atletaDto : atletas) {
			if(!atleGat.getByDni(atletaDto.DNI).isPresent()) {
				atletaDto.id_club = id_club;
				atleGat.add(RecordAsembler.fromDto(atletaDto));
				result.add(atletaDto);
				InscripcionRecord record = new InscripcionRecord();
				record.dni_Atleta=atletaDto.DNI;
				record.id_club=atletaDto.id_club;
				record.id_competicion= id_competicion;
				record.fecha_Inscripcion= Date.valueOf( LocalDate.now());
				record.estado = "INSCRITO";
				record.categoria = CalculoCategoria.calculoCategoria(Date.valueOf(atletaDto.fechaNacimiento), this.id_competicion);
				//calcular categoria
				gateway.add(record);
				
				counter++;
			}
		}
		
		return result;
	}

}
